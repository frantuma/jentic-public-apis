package net.ft.oak.runner.model;

import java.util.List;
import java.util.Map;

/**
 * Represents a step in an Arazzo workflow.
 * This is the Java equivalent of the step model in Python.
 */
public class WorkflowStep {
    private String stepId;
    private String description;
    private String operationId;
    private List<StepParameter> parameters;
    private List<Object> successCriteria;

    public String getStepId() {
        return stepId;
    }

    public void setStepId(String stepId) {
        this.stepId = stepId;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }



    public List<StepParameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<StepParameter> parameters) {
        this.parameters = parameters;
    }


    public List<Object> getSuccessCriteria() {
        return successCriteria;
    }

    public void setSuccessCriteria(List<Object> successCriteria) {
        this.successCriteria = successCriteria;
    }
}