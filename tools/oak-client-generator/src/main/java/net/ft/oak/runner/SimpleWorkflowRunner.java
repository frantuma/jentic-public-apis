package net.ft.oak.runner;

import net.ft.oak.OakClientRunner;
import net.ft.oak.runner.model.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * A simplified implementation of the WorkflowRunner interface that executes Arazzo workflows.
 * This is a proof of concept that demonstrates workflow execution using the OakClientRunner.
 */
public class SimpleWorkflowRunner implements WorkflowRunner {
    private static final Logger logger = Logger.getLogger(SimpleWorkflowRunner.class.getName());
    
    private final JSONObject arazzoDoc;
    private final Map<String, JSONObject> sourceDescriptions;
    private final Map<String, ExecutionState> executionStates;
    private final Map<String, List<Consumer<Map<String, Object>>>> eventCallbacks;
    private final OakClientRunner oakClientRunner;
    
    /**
     * Creates a new SimpleWorkflowRunner with the specified Arazzo document and source descriptions.
     *
     * @param arazzoDoc The Arazzo document containing workflow definitions
     * @param sourceDescriptions Map of source names to OpenAPI specifications
     * @param oakClientRunner The OakClientRunner to use for executing operations
     */
    public SimpleWorkflowRunner(JSONObject arazzoDoc, Map<String, JSONObject> sourceDescriptions, OakClientRunner oakClientRunner) {
        this.arazzoDoc = arazzoDoc;
        this.sourceDescriptions = sourceDescriptions;
        this.oakClientRunner = oakClientRunner;
        this.executionStates = new ConcurrentHashMap<>();
        this.eventCallbacks = new HashMap<>();
        
        // Initialize event callbacks
        eventCallbacks.put("step_start", new ArrayList<>());
        eventCallbacks.put("step_complete", new ArrayList<>());
        eventCallbacks.put("workflow_start", new ArrayList<>());
        eventCallbacks.put("workflow_complete", new ArrayList<>());
    }
    
    /**
     * Creates a new SimpleWorkflowRunner from an Arazzo document file.
     *
     * @param arazzoPath Path to the Arazzo document file
     * @param basePath Optional base path for source descriptions
     * @return A new SimpleWorkflowRunner
     * @throws IOException If there is an error reading the files
     */
    public static SimpleWorkflowRunner fromArazzoPath(String arazzoPath, String basePath) throws IOException {
        // Load Arazzo document
        JSONObject arazzoDoc = loadJsonFile(arazzoPath);
        
        // Load source descriptions
        Map<String, JSONObject> sourceDescriptions = loadSourceDescriptions(arazzoDoc, arazzoPath, basePath);
        
        // Create OakClientRunner (simplified for prototype)
        OakClientRunner oakClientRunner = new OakClientRunner("default", new File(basePath != null ? basePath : "."));
        
        return new SimpleWorkflowRunner(arazzoDoc, sourceDescriptions, oakClientRunner);
    }
    
    /**
     * Loads a JSON file.
     *
     * @param path Path to the JSON file
     * @return The parsed JSON object
     * @throws IOException If there is an error reading the file
     */
    private static JSONObject loadJsonFile(String path) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(path)));
        return new JSONObject(content);
    }
    
    /**
     * Loads OpenAPI specifications referenced by an Arazzo document.
     *
     * @param arazzoDoc The Arazzo document
     * @param arazzoPath Path to the Arazzo document file
     * @param basePath Optional base path for source descriptions
     * @return Map of source names to OpenAPI specifications
     * @throws IOException If there is an error reading the files
     */
    private static Map<String, JSONObject> loadSourceDescriptions(JSONObject arazzoDoc, String arazzoPath, String basePath) throws IOException {
        Map<String, JSONObject> sourceDescriptions = new HashMap<>();
        
        // For prototype, we'll just load OpenAPI specs from the same directory as the Arazzo document
        // In a real implementation, we would follow the source references in the Arazzo document
        
        File arazzoFile = new File(arazzoPath);
        File arazzoDir = arazzoFile.getParentFile();
        File sourcesDir = basePath != null ? new File(basePath) : arazzoDir;
        
        // Find all OpenAPI files in the sources directory
        File[] files = sourcesDir.listFiles((dir, name) -> name.endsWith(".json") || name.endsWith(".yaml") || name.endsWith(".yml"));
        if (files != null) {
            for (File file : files) {
                if (file.equals(arazzoFile)) continue;
                
                try {
                    JSONObject openAPI = loadJsonFile(file.getAbsolutePath());
                    String sourceName = file.getName().replaceFirst("[.][^.]+$", "");
                    sourceDescriptions.put(sourceName, openAPI);
                    logger.info("Loaded OpenAPI spec: " + sourceName);
                } catch (Exception e) {
                    logger.warning("Failed to parse OpenAPI spec: " + file.getAbsolutePath() + " - " + e.getMessage());
                }
            }
        }
        
        return sourceDescriptions;
    }
    
    @Override
    public String startWorkflow(String workflowId, Map<String, Object> inputs) {
        // Generate a unique execution ID
        String executionId = workflowId + "_" + (executionStates.size() + 1);
        
        // Find the workflow definition
        JSONObject workflow = null;
        JSONArray workflows = arazzoDoc.getJSONArray("workflows");
        for (int i = 0; i < workflows.length(); i++) {
            JSONObject wf = workflows.getJSONObject(i);
            if (wf.getString("workflowId").equals(workflowId)) {
                workflow = wf;
                break;
            }
        }
        
        if (workflow == null) {
            throw new IllegalArgumentException("Workflow " + workflowId + " not found in Arazzo document");
        }
        
        // Initialize execution state
        ExecutionState state = new ExecutionState(
            workflowId,
            inputs != null ? inputs : new HashMap<>(),
            new HashMap<>(),
            new HashMap<>()
        );
        
        // Initialize step statuses
        if (workflow.has("steps")) {
            JSONArray steps = workflow.getJSONArray("steps");
            for (int i = 0; i < steps.length(); i++) {
                JSONObject step = steps.getJSONObject(i);
                state.getStatus().put(step.getString("stepId"), StepStatus.PENDING);
            }
        }
        
        // Store the execution state
        executionStates.put(executionId, state);
        
        // Trigger workflow_start event
        Map<String, Object> eventData = new HashMap<>();
        eventData.put("execution_id", executionId);
        eventData.put("workflow_id", workflowId);
        eventData.put("inputs", inputs);
        triggerEvent("workflow_start", eventData);
        
        return executionId;
    }
    
    @Override
    public Map<String, Object> executeNextStep(String executionId) {
        if (!executionStates.containsKey(executionId)) {
            throw new IllegalArgumentException("Execution " + executionId + " not found");
        }
        
        ExecutionState state = executionStates.get(executionId);
        String workflowId = state.getWorkflowId();
        
        // Find the workflow definition
        JSONObject workflow = null;
        JSONArray workflows = arazzoDoc.getJSONArray("workflows");
        for (int i = 0; i < workflows.length(); i++) {
            JSONObject wf = workflows.getJSONObject(i);
            if (wf.getString("workflowId").equals(workflowId)) {
                workflow = wf;
                break;
            }
        }
        
        if (workflow == null) {
            throw new IllegalArgumentException("Workflow " + workflowId + " not found in Arazzo document");
        }
        
        // Determine the next step to execute
        JSONArray steps = workflow.getJSONArray("steps");
        JSONObject nextStep = null;
        int nextStepIdx = 0;
        
        if (state.getCurrentStepId() == null) {
            // First step in the workflow
            if (steps.length() > 0) {
                nextStep = steps.getJSONObject(0);
            }
        } else {
            // Find the current step index
            Integer currentIdx = null;
            for (int i = 0; i < steps.length(); i++) {
                if (steps.getJSONObject(i).getString("stepId").equals(state.getCurrentStepId())) {
                    currentIdx = i;
                    break;
                }
            }
            
            if (currentIdx != null && currentIdx + 1 < steps.length()) {
                nextStep = steps.getJSONObject(currentIdx + 1);
                nextStepIdx = currentIdx + 1;
            }
        }
        
        if (nextStep == null) {
            // No more steps to execute, workflow is complete
            Map<String, Object> eventData = new HashMap<>();
            eventData.put("execution_id", executionId);
            eventData.put("workflow_id", workflowId);
            eventData.put("outputs", state.getWorkflowOutputs());
            triggerEvent("workflow_complete", eventData);
            
            Map<String, Object> result = new HashMap<>();
            result.put("status", WorkflowExecutionStatus.WORKFLOW_COMPLETE);
            result.put("workflow_id", workflowId);
            result.put("outputs", state.getWorkflowOutputs());
            return result;
        }
        
        // Execute the next step
        String stepId = nextStep.getString("stepId");
        state.setCurrentStepId(stepId);
        state.getStatus().put(stepId, StepStatus.RUNNING);
        
        // Trigger step_start event
        Map<String, Object> startEventData = new HashMap<>();
        startEventData.put("execution_id", executionId);
        startEventData.put("workflow_id", workflowId);
        startEventData.put("step_id", stepId);
        triggerEvent("step_start", startEventData);
        
        // Execute the step
        try {
            Map<String, Object> stepResult;
            
            if (nextStep.has("workflowId")) {
                // Handle nested workflow execution
                stepResult = executeNestedWorkflow(nextStep, state);
            } else {
                // Execute operation step
                stepResult = executeStep(nextStep, state);
            }
            
            boolean success = Boolean.TRUE.equals(stepResult.get("success"));
            
            // Update step status
            state.getStatus().put(stepId, success ? StepStatus.SUCCESS : StepStatus.FAILURE);
            
            // Store step outputs
            state.getStepOutputs().put(stepId, (Map<String, Object>) stepResult.getOrDefault("outputs", new HashMap<>()));
            
            // Trigger step_complete event
            Map<String, Object> completeEventData = new HashMap<>();
            completeEventData.put("execution_id", executionId);
            completeEventData.put("workflow_id", workflowId);
            completeEventData.put("step_id", stepId);
            completeEventData.put("success", success);
            completeEventData.put("outputs", stepResult.getOrDefault("outputs", new HashMap<>()));
            triggerEvent("step_complete", completeEventData);
            
            // For prototype, we'll just continue to the next step
            Map<String, Object> result = new HashMap<>();
            result.put("status", WorkflowExecutionStatus.STEP_COMPLETE);
            result.put("step_id", stepId);
            result.put("success", success);
            result.put("outputs", stepResult.getOrDefault("outputs", new HashMap<>()));
            return result;
            
        } catch (Exception e) {
            logger.severe("Error executing step " + stepId + ": " + e.getMessage());
            state.getStatus().put(stepId, StepStatus.FAILURE);
            
            // Trigger step_complete event with failure
            Map<String, Object> errorEventData = new HashMap<>();
            errorEventData.put("execution_id", executionId);
            errorEventData.put("workflow_id", workflowId);
            errorEventData.put("step_id", stepId);
            errorEventData.put("success", false);
            errorEventData.put("error", e.getMessage());
            triggerEvent("step_complete", errorEventData);
            
            Map<String, Object> result = new HashMap<>();
            result.put("status", WorkflowExecutionStatus.STEP_ERROR);
            result.put("step_id", stepId);
            result.put("error", e.getMessage());
            return result;
        }
    }
    
    @Override
    public WorkflowExecutionResult executeWorkflow(String workflowId, Map<String, Object> inputs) {
        // Register default callbacks for logging
        registerCallback("workflow_start", eventData -> {
            logger.info("=== Starting workflow: " + eventData.get("workflow_id") + " ===");
            logger.info("Inputs: " + eventData.get("inputs"));
        });
        
        registerCallback("step_start", eventData -> {
            logger.info("--- Starting step: " + eventData.get("step_id") + " ---");
        });
        
        registerCallback("step_complete", eventData -> {
            logger.info("--- Completed step: " + eventData.get("step_id") + " (Success: " + eventData.get("success") + ") ---");
            if (eventData.containsKey("outputs")) {
                logger.info("Outputs: " + eventData.get("outputs"));
            }
            if (eventData.containsKey("error")) {
                logger.info("Error: " + eventData.get("error"));
            }
        });
        
        registerCallback("workflow_complete", eventData -> {
            logger.info("=== Completed workflow: " + eventData.get("workflow_id") + " ===");
            logger.info("Outputs: " + eventData.get("outputs"));
        });
        
        String executionId = startWorkflow(workflowId, inputs);
        
        while (true) {
            Map<String, Object> result = executeNextStep(executionId);
            WorkflowExecutionStatus status = (WorkflowExecutionStatus) result.get("status");
            
            if (status == WorkflowExecutionStatus.WORKFLOW_COMPLETE || status == WorkflowExecutionStatus.ERROR) {
                // Get the execution state to access step outputs
                ExecutionState state = executionStates.get(executionId);
                
                // Convert the dictionary result to a WorkflowExecutionResult object
                return new WorkflowExecutionResult(
                    status,
                    (String) result.getOrDefault("workflow_id", workflowId),
                    (Map<String, Object>) result.getOrDefault("outputs", new HashMap<>()),
                    state.getStepOutputs(),
                    inputs,
                    (String) result.get("error")
                );
            }
        }
    }
    
    @Override
    public Map<String, Object> executeOperation(Map<String, Object> inputs, String operationId) {
        // For prototype, we'll just use the OakClientRunner to execute the operation
        // In a real implementation, we would need to handle authentication, server variables, etc.
        
        if (operationId == null) {
            throw new IllegalArgumentException("Either operationId or operationPath must be provided");
        }

        try {
            // For prototype, we'll just execute the operation with the given inputs
            // In a real implementation, we would need to map the inputs to the operation parameters
            
            // Find the operation in the OpenAPI specs
            if (operationId != null) {
                // For prototype, we'll just use a hardcoded tag and arguments
                // In a real implementation, we would extract this from the OpenAPI spec
                String tag = "Default";
                
                // Convert inputs to array of arguments
                // This is a simplified approach for the prototype
                Object[] args = inputs.values().toArray();
                
                // Execute the operation
                Object result = oakClientRunner.executeOperation(operationId, tag, args);
                
                // Return the result
                Map<String, Object> response = new HashMap<>();
                response.put("success", true);
                response.put("outputs", result);
                return response;
            } else {
                // Find operation by path
                // For prototype, we'll just throw an exception
                throw new UnsupportedOperationException("Finding operations by path is not supported in this prototype");
            }
        } catch (Exception e) {
            logger.severe("Error executing operation: " + e.getMessage());
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("error", e.getMessage());
            return response;
        }
    }
    
    @Override
    public void registerCallback(String eventType, Consumer<Map<String, Object>> callback) {
        if (eventCallbacks.containsKey(eventType)) {
            eventCallbacks.get(eventType).add(callback);
        } else {
            logger.warning("Unknown event type: " + eventType);
        }
    }
    
    /**
     * Triggers registered callbacks for an event.
     *
     * @param eventType The type of event
     * @param eventData The event data
     */
    private void triggerEvent(String eventType, Map<String, Object> eventData) {
        for (Consumer<Map<String, Object>> callback : eventCallbacks.getOrDefault(eventType, Collections.emptyList())) {
            try {
                callback.accept(eventData);
            } catch (Exception e) {
                logger.severe("Error in " + eventType + " callback: " + e.getMessage());
            }
        }
    }
    
    /**
     * Executes a step in a workflow.
     *
     * @param step The step to execute
     * @param state The current execution state
     * @return The result of the step execution
     */
    private Map<String, Object> executeStep(JSONObject step, ExecutionState state) {
        // For prototype, we'll just execute the operation with the given inputs
        // In a real implementation, we would need to handle parameter mapping, success criteria, etc.
        
        try {
            // Prepare inputs for the operation
            Map<String, Object> operationInputs = new HashMap<>();
            
            if (step.has("parameters")) {
                JSONArray parameters = step.getJSONArray("parameters");
                for (int i = 0; i < parameters.length(); i++) {
                    JSONObject param = parameters.getJSONObject(i);
                    // For prototype, we'll just use the parameter value directly
                    // In a real implementation, we would need to handle expressions, templates, etc.
                    operationInputs.put(param.getString("name"), param.get("value"));
                }
            }
            
            // Execute the operation
            return executeOperation(operationInputs, step.optString("operationId", null));
        } catch (Exception e) {
            logger.severe("Error executing step: " + e.getMessage());
            Map<String, Object> result = new HashMap<>();
            result.put("success", false);
            result.put("error", e.getMessage());
            return result;
        }
    }
    
    /**
     * Executes a nested workflow.
     *
     * @param step The step containing the nested workflow
     * @param state The current execution state
     * @return The result of the nested workflow execution
     */
    private Map<String, Object> executeNestedWorkflow(JSONObject step, ExecutionState state) {
        // For prototype, we'll just execute the workflow with the given inputs
        // In a real implementation, we would need to handle parameter mapping, etc.
        
        try {
            // Prepare inputs for the nested workflow
            Map<String, Object> workflowInputs = new HashMap<>();
            
            if (step.has("parameters")) {
                JSONArray parameters = step.getJSONArray("parameters");
                for (int i = 0; i < parameters.length(); i++) {
                    JSONObject param = parameters.getJSONObject(i);
                    // For prototype, we'll just use the parameter value directly
                    // In a real implementation, we would need to handle expressions, templates, etc.
                    workflowInputs.put(param.getString("name"), param.get("value"));
                }
            }
            
            // Execute the nested workflow
            WorkflowExecutionResult result = executeWorkflow(step.getString("workflowId"), workflowInputs);
            
            // Check if the workflow succeeded
            boolean success = result.getStatus() == WorkflowExecutionStatus.WORKFLOW_COMPLETE;
            
            // Return the result
            Map<String, Object> stepResult = new HashMap<>();
            stepResult.put("success", success);
            stepResult.put("outputs", result.getOutputs());
            if (result.getError() != null) {
                stepResult.put("error", result.getError());
            }
            return stepResult;
        } catch (Exception e) {
            logger.severe("Error executing nested workflow: " + e.getMessage());
            Map<String, Object> result = new HashMap<>();
            result.put("success", false);
            result.put("error", e.getMessage());
            return result;
        }
    }
}