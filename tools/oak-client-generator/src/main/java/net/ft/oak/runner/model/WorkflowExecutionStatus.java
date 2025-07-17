package net.ft.oak.runner.model;

/**
 * Enum representing the status of a workflow execution.
 * This is the Java equivalent of the WorkflowExecutionStatus enum in Python.
 */
public enum WorkflowExecutionStatus {
    STEP_COMPLETE,
    STEP_ERROR,
    WORKFLOW_COMPLETE,
    ERROR,
    GOTO_STEP,
    GOTO_WORKFLOW,
    RETRY
}