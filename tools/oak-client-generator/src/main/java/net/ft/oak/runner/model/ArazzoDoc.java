package net.ft.oak.runner.model;

import java.util.List;
import java.util.Map;

/**
 * Represents an Arazzo workflow document.
 * This is the Java equivalent of the Python ArazzoDoc model.
 */
public class ArazzoDoc {
    private List<Workflow> workflows;

    public List<Workflow> getWorkflows() {
        return workflows;
    }

    public void setWorkflows(List<Workflow> workflows) {
        this.workflows = workflows;
    }
}
