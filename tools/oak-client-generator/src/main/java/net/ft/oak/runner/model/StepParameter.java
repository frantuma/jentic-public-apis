package net.ft.oak.runner.model;

/**
 * Represents a parameter for a workflow step.
 * This is the Java equivalent of the parameter model in Python.
 */
public class StepParameter {
    private String name;
    private Object value;
    private String in;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getIn() {
        return in;
    }

    public void setIn(String in) {
        this.in = in;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}