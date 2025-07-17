package net.ft.oak.runner.model;

/**
 * Enum representing the type of action to take after a step execution.
 * This is the Java equivalent of the ActionType enum in Python.
 */
public enum ActionType {
    CONTINUE,
    END,
    GOTO,
    RETRY
}