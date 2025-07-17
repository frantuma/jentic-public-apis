package net.ft.oak.runner.model;

import java.util.Map;

/**
 * Represents the result of a workflow execution.
 * This is the Java equivalent of the WorkflowExecutionResult class in Python.
 */
public class WorkflowExecutionResult {
    private WorkflowExecutionStatus status;
    private String workflowId;
    private Map<String, Object> outputs;
    private Map<String, Map<String, Object>> stepOutputs;
    private Map<String, Object> inputs;
    private String error;

    /**
     * Creates a new workflow execution result.
     *
     * @param status The status of the workflow execution
     * @param workflowId The ID of the workflow that was executed
     * @param outputs The outputs of the workflow
     * @param stepOutputs The outputs of each step in the workflow
     * @param inputs The inputs that were provided to the workflow
     * @param error Any error that occurred during execution
     */
    public WorkflowExecutionResult(WorkflowExecutionStatus status, String workflowId,
                                  Map<String, Object> outputs, Map<String, Map<String, Object>> stepOutputs,
                                  Map<String, Object> inputs, String error) {
        this.status = status;
        this.workflowId = workflowId;
        this.outputs = outputs;
        this.stepOutputs = stepOutputs;
        this.inputs = inputs;
        this.error = error;
    }

    public WorkflowExecutionStatus getStatus() {
        return status;
    }

    public void setStatus(WorkflowExecutionStatus status) {
        this.status = status;
    }

    public String getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    public Map<String, Object> getOutputs() {
        return outputs;
    }

    public void setOutputs(Map<String, Object> outputs) {
        this.outputs = outputs;
    }

    public Map<String, Map<String, Object>> getStepOutputs() {
        return stepOutputs;
    }

    public void setStepOutputs(Map<String, Map<String, Object>> stepOutputs) {
        this.stepOutputs = stepOutputs;
    }

    public Map<String, Object> getInputs() {
        return inputs;
    }

    public void setInputs(Map<String, Object> inputs) {
        this.inputs = inputs;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}