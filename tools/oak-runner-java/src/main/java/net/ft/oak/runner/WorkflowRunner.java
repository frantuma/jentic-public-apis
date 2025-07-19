package net.ft.oak.runner;

import net.ft.oak.runner.model.WorkflowExecutionResult;

import java.util.Map;
import java.util.function.Consumer;

/**
 * Interface for a workflow runner that executes Arazzo workflows.
 */
public interface WorkflowRunner {
    
    /**
     * Start a new workflow execution.
     *
     * @param workflowId ID of the workflow to execute
     * @param inputs Input parameters for the workflow
     * @return execution_id Unique ID for this workflow execution
     */
    String startWorkflow(String workflowId, Map<String, Object> inputs);
    
    /**
     * Execute the next step in the workflow.
     *
     * @param executionId ID of the workflow execution
     * @return Result of the step execution
     */
    Map<String, Object> executeNextStep(String executionId);
    
    /**
     * Execute a workflow until completion.
     *
     * @param workflowId ID of the workflow to execute
     * @param inputs Input parameters for the workflow
     * @return Result of the workflow execution
     */
    WorkflowExecutionResult executeWorkflow(String workflowId, Map<String, Object> inputs);
    
    /**
     * Execute a single API operation directly.
     *
     * @param inputs Input parameters for the operation
     * @param operationId The operationId of the operation to execute
     * @return Result of the operation execution
     */
    Map<String, Object> executeOperation(Map<String, Object> inputs, String operationId);
    
    /**
     * Register a callback for workflow execution events.
     *
     * @param eventType Type of event ('step_start', 'step_complete', 'workflow_start', 'workflow_complete')
     * @param callback Function to call when the event occurs
     */
    void registerCallback(String eventType, Consumer<Map<String, Object>> callback);
}