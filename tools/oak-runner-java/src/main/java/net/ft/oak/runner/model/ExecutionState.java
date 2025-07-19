package net.ft.oak.runner.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the state of a workflow execution.
 * This is the Java equivalent of the ExecutionState class in Python.
 */
public class ExecutionState {
    private String workflowId;
    private String currentStepId;
    private Map<String, Object> inputs;
    private Map<String, Object> workflowOutputs;
    private Map<String, Map<String, Object>> stepOutputs;
    private Map<String, StepStatus> status;
    private Map<String, Map<String, Object>> dependencyOutputs;
    private Map<String, Object> runtimeParams;

    /**
     * Creates a new execution state for a workflow.
     *
     * @param workflowId The ID of the workflow being executed
     * @param inputs The input parameters for the workflow
     * @param dependencyOutputs The outputs from dependency workflows
     * @param runtimeParams Runtime parameters for execution
     */
    public ExecutionState(String workflowId, Map<String, Object> inputs, 
                         Map<String, Map<String, Object>> dependencyOutputs,
                         Map<String, Object> runtimeParams) {
        this.workflowId = workflowId;
        this.inputs = inputs != null ? inputs : new HashMap<>();
        this.workflowOutputs = new HashMap<>();
        this.stepOutputs = new HashMap<>();
        this.status = new HashMap<>();
        this.dependencyOutputs = dependencyOutputs != null ? dependencyOutputs : new HashMap<>();
        this.runtimeParams = runtimeParams != null ? runtimeParams : new HashMap<>();
    }

    public String getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    public String getCurrentStepId() {
        return currentStepId;
    }

    public void setCurrentStepId(String currentStepId) {
        this.currentStepId = currentStepId;
    }

    public Map<String, Object> getInputs() {
        return inputs;
    }

    public void setInputs(Map<String, Object> inputs) {
        this.inputs = inputs;
    }

    public Map<String, Object> getWorkflowOutputs() {
        return workflowOutputs;
    }

    public void setWorkflowOutputs(Map<String, Object> workflowOutputs) {
        this.workflowOutputs = workflowOutputs;
    }

    public Map<String, Map<String, Object>> getStepOutputs() {
        return stepOutputs;
    }

    public void setStepOutputs(Map<String, Map<String, Object>> stepOutputs) {
        this.stepOutputs = stepOutputs;
    }

    public Map<String, StepStatus> getStatus() {
        return status;
    }

    public void setStatus(Map<String, StepStatus> status) {
        this.status = status;
    }

    public Map<String, Map<String, Object>> getDependencyOutputs() {
        return dependencyOutputs;
    }

    public void setDependencyOutputs(Map<String, Map<String, Object>> dependencyOutputs) {
        this.dependencyOutputs = dependencyOutputs;
    }

    public Map<String, Object> getRuntimeParams() {
        return runtimeParams;
    }

    public void setRuntimeParams(Map<String, Object> runtimeParams) {
        this.runtimeParams = runtimeParams;
    }
}