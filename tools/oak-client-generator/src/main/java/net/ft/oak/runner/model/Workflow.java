package net.ft.oak.runner.model;

import java.util.List;
import java.util.Map;

/**
 * Represents a workflow in an Arazzo document.
 * This is the Java equivalent of the workflow model in Python.
 */
public class Workflow {
    private String workflowId;
    private String description;
    private List<WorkflowStep> steps;
    private Map<String, String> outputs;

    public String getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public List<WorkflowStep> getSteps() {
        return steps;
    }

    public void setSteps(List<WorkflowStep> steps) {
        this.steps = steps;
    }

    public Map<String, String> getOutputs() {
        return outputs;
    }

    public void setOutputs(Map<String, String> outputs) {
        this.outputs = outputs;
    }
}