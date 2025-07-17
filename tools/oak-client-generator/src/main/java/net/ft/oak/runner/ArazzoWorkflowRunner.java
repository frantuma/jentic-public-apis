package net.ft.oak.runner;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.parser.OpenAPIV3Parser;
import io.swagger.v3.parser.core.models.ParseOptions;
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

/**
 * Implementation of the WorkflowRunner interface that executes Arazzo workflows.
 * This is the Java equivalent of the OAKRunner class in Python.
 */
public class ArazzoWorkflowRunner implements WorkflowRunner {
    private static final Logger logger = Logger.getLogger(ArazzoWorkflowRunner.class.getName());
    
    private final ArazzoDoc arazzoDoc;
    private final Map<String, OpenAPI> sourceDescriptions;
    private final Map<String, ExecutionState> executionStates;
    private final Map<String, List<Consumer<Map<String, Object>>>> eventCallbacks;
    private final ObjectMapper objectMapper;

    /**
     * Creates a new ArazzoWorkflowRunner with the specified Arazzo document and source descriptions.
     *
     * @param arazzoDoc The Arazzo document containing workflow definitions
     * @param sourceDescriptions Map of source names to OpenAPI specifications
     * @param oakClientRunner The OakClientRunner to use for executing operations
     */
    public ArazzoWorkflowRunner(ArazzoDoc arazzoDoc, Map<String, OpenAPI> sourceDescriptions, OakClientRunner oakClientRunner) {
        this.arazzoDoc = arazzoDoc;
        this.sourceDescriptions = sourceDescriptions;
        this.executionStates = new ConcurrentHashMap<>();
        this.eventCallbacks = new HashMap<>();
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        
        // Initialize event callbacks
        eventCallbacks.put("step_start", new ArrayList<>());
        eventCallbacks.put("step_complete", new ArrayList<>());
        eventCallbacks.put("workflow_start", new ArrayList<>());
        eventCallbacks.put("workflow_complete", new ArrayList<>());
    }
    
    /**
     * Creates a new ArazzoWorkflowRunner from an Arazzo document file.
     *
     * @param arazzoPath Path to the Arazzo document file
     * @param basePath Optional base path for source descriptions
     * @return A new ArazzoWorkflowRunner
     * @throws IOException If there is an error reading the files
     */
    public static ArazzoWorkflowRunner fromArazzoPath(String arazzoPath, String basePath) throws IOException {
        // Load Arazzo document
        ArazzoDoc arazzoDoc = loadArazzoDoc(arazzoPath);

        // Load source descriptions
        Map<String, OpenAPI> sourceDescriptions = loadSourceDescriptions(arazzoDoc, arazzoPath, basePath);

        return new ArazzoWorkflowRunner(arazzoDoc, sourceDescriptions, null);
    }
    
    /**
     * Loads an Arazzo document from a file.
     *
     * @param arazzoPath Path to the Arazzo document file
     * @return The parsed Arazzo document
     * @throws IOException If there is an error reading the file
     */
    private static ArazzoDoc loadArazzoDoc(String arazzoPath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String content = new String(Files.readAllBytes(Paths.get(arazzoPath)));
        return mapper.readValue(content, ArazzoDoc.class);
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
    private static Map<String, OpenAPI> loadSourceDescriptions(ArazzoDoc arazzoDoc, String arazzoPath, String basePath) throws IOException {
        Map<String, OpenAPI> sourceDescriptions = new HashMap<>();
        
        // For prototype, we'll just load OpenAPI specs from the same directory as the Arazzo document
        // In a real implementation, we would follow the source references in the Arazzo document
        
        File arazzoFile = new File(arazzoPath);
        File arazzoDir = arazzoFile.getParentFile();
        File sourcesDir = basePath != null ? new File(basePath) : arazzoDir;
        
        // Find all OpenAPI files in the sources directory
        File[] files = sourcesDir.listFiles((dir, name) -> name.endsWith(".json") || name.endsWith(".yaml") || name.endsWith(".yml"));
        if (files != null) {
            ParseOptions parseOptions = new ParseOptions();
            parseOptions.setResolve(true);
            
            for (File file : files) {
                if (file.equals(arazzoFile)) continue;
                
                try {
                    OpenAPI openAPI = new OpenAPIV3Parser().readLocation(file.getAbsolutePath(), null, parseOptions).getOpenAPI();
                    if (openAPI != null) {
                        String sourceName = file.getName().replaceFirst("[.][^.]+$", "");
                        sourceDescriptions.put(sourceName, openAPI);
                        logger.info("Loaded OpenAPI spec: " + sourceName);
                    }
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
        Workflow workflow = null;
        for (Workflow wf : arazzoDoc.getWorkflows()) {
            if (wf.getWorkflowId().equals(workflowId)) {
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
        if (workflow.getSteps() != null) {
            for (WorkflowStep step : workflow.getSteps()) {
                state.getStatus().put(step.getStepId(), StepStatus.PENDING);
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
        Workflow workflow = null;
        for (Workflow wf : arazzoDoc.getWorkflows()) {
            if (wf.getWorkflowId().equals(workflowId)) {
                workflow = wf;
                break;
            }
        }
        
        if (workflow == null) {
            throw new IllegalArgumentException("Workflow " + workflowId + " not found in Arazzo document");
        }
        
        // Determine the next step to execute
        List<WorkflowStep> steps = workflow.getSteps();
        WorkflowStep nextStep = null;
        int nextStepIdx = 0;
        
        if (state.getCurrentStepId() == null) {
            // First step in the workflow
            if (steps != null && !steps.isEmpty()) {
                nextStep = steps.get(0);
            }
        } else {
            // Find the current step index
            Integer currentIdx = null;
            for (int i = 0; i < steps.size(); i++) {
                if (steps.get(i).getStepId().equals(state.getCurrentStepId())) {
                    currentIdx = i;
                    break;
                }
            }
            
            if (currentIdx != null && currentIdx + 1 < steps.size()) {
                nextStep = steps.get(currentIdx + 1);
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
        String stepId = nextStep.getStepId();
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
            
            // Execute operation step
            stepResult = executeStep(nextStep, state);

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
                // Find operation by ID
                for (Map.Entry<String, OpenAPI> entry : sourceDescriptions.entrySet()) {
                    OpenAPI openAPI = entry.getValue();

                    // get the runner from cache or load for this OpenAPI spec
                    // in prototype, we will use a fixed ID and path
                    OakClientRunner runner = new OakClientRunner("88c652dbb50efeecf3476dce223a239a", new File("/dati/dev/progetti/jentic/projects/oak/apis"));
                    // Find the operation in the paths
                    for (Map.Entry<String, PathItem> pathEntry : openAPI.getPaths().entrySet()) {
                        PathItem pathItem = pathEntry.getValue();
                        
                        // Check each HTTP method
                        for (Map.Entry<PathItem.HttpMethod, Operation> opEntry : pathItem.readOperationsMap().entrySet()) {
                            Operation operation = opEntry.getValue();
                            
                            if (operationId.equals(operation.getOperationId())) {
                                // Found the operation
                                // For prototype, we'll just execute it with the given inputs
                                // In a real implementation, we would need to map the inputs to the operation parameters
                                
                                // Extract tag for OakClientRunner
                                String tag = operation.getTags() != null && !operation.getTags().isEmpty() ? 
                                    operation.getTags().get(0) : "Default";
                                
                                // Convert inputs to array of arguments
                                // This is a simplified approach for the prototype
                                Object[] args = inputs.values().toArray();

                                // build the parameter types dynamically
                                // in real life use binding library
                                List<Class> inferredArgs = new ArrayList<>();
                                operation.getParameters().forEach(param -> {
                                    switch ( param.getSchema().getType() ) {
                                        case "integer":
                                        case "number":
                                            inferredArgs.add(Double.class);
                                            break;
                                        case "string":
                                            inferredArgs.add(String.class);
                                            break;
                                        default:
                                            inferredArgs.add(Double.class);
                                    }
                                });

                                // Execute the operation
                                Object result = runner.executeOperation(operationId, tag, inferredArgs.toArray(new Class[0]));
                                
                                // Return the result
                                Map<String, Object> response = new HashMap<>();
                                response.put("success", true);
                                response.put("outputs", result);
                                return response;
                            }
                        }
                    }
                }
                
                throw new IllegalArgumentException("Operation " + operationId + " not found in any OpenAPI spec");
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
    private Map<String, Object> executeStep(WorkflowStep step, ExecutionState state) {
        // For prototype, we'll just execute the operation with the given inputs
        // In a real implementation, we would need to handle parameter mapping, success criteria, etc.
        
        try {
            // Prepare inputs for the operation
            Map<String, Object> operationInputs = new HashMap<>();
            
            if (step.getParameters() != null) {
                for (StepParameter param : step.getParameters()) {
                    // For prototype, we'll just use the parameter value directly
                    // In a real implementation, we would need to handle expressions, templates, etc.
                    operationInputs.put(param.getName(), param.getValue());
                }
            }
            
            // Execute the operation
            return executeOperation(operationInputs, step.getOperationId());
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
    private Map<String, Object> executeNestedWorkflow(WorkflowStep step, ExecutionState state) {
        // For prototype, we'll just execute the workflow with the given inputs
        // In a real implementation, we would need to handle parameter mapping, etc.
        
        try {
            // Prepare inputs for the nested workflow
            Map<String, Object> workflowInputs = new HashMap<>();
            
            if (step.getParameters() != null) {
                for (StepParameter param : step.getParameters()) {
                    // For prototype, we'll just use the parameter value directly
                    // In a real implementation, we would need to handle expressions, templates, etc.
                    workflowInputs.put(param.getName(), param.getValue());
                }
            }
            
            // Execute the nested workflow
            WorkflowExecutionResult result = executeWorkflow(step.getOperationId(), workflowInputs); // executeWorkflow(step.getWorkflowId(), workflowInputs);
            
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