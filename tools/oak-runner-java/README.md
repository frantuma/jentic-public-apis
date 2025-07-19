# Arazzo Workflow Runner - Java Port

This is a Java port of the Python Arazzo Workflow Runner. It's a proof of concept that demonstrates workflow execution using the OakClientRunner.

## Overview

The Arazzo Workflow Runner executes Arazzo workflows step-by-step, following the paths defined in the workflow specification. It builds an execution tree based on the possible paths and executes OpenAPI operations sequentially, handling success/failure conditions and flow control.

This Java port uses Swagger Core and Swagger Parser to read OpenAPI specs and the OakClientRunner to invoke operations.

## Components

- **Model Classes**: Java equivalents of the Python model classes for Arazzo workflows
  - `ArazzoDoc`: Represents an Arazzo workflow document
  - `Workflow`: Represents a workflow in an Arazzo document
  - `WorkflowStep`: Represents a step in an Arazzo workflow
  - `StepParameter`: Represents a parameter for a workflow step
  - `ExecutionState`: Represents the state of a workflow execution
  - `StepStatus`: Enum representing the status of a workflow step
  - `WorkflowExecutionStatus`: Enum representing the status of a workflow execution
  - `ActionType`: Enum representing the type of action to take after a step execution
  - `WorkflowExecutionResult`: Represents the result of a workflow execution

- **Runner Classes**:
  - `WorkflowRunner`: Interface for a workflow runner that executes Arazzo workflows
  - `ArazzoRunnerDemo`: A simple demonstration of executing Arazzo workflows using the OakClientRunner
  - `Main`: Main entry point for the Java port of the Arazzo workflow runner

## Building and Running

To build the project:

```bash
cd oak/tools/oak-runner-java
mvn clean package
```

To run the project:

```bash
java -jar target/oak-runner-java-1.0-SNAPSHOT.jar
```

## Example

The `ArazzoRunnerDemo` class demonstrates how to execute a simple workflow with a single step that calls the `getCost` operation from the Lyft API.

```java
// Create a sample workflow
Map<String, Object> workflow = new HashMap<>();
workflow.put("workflowId", "sample-workflow");
workflow.put("name", "Sample Workflow");
workflow.put("description", "A sample workflow for demonstration purposes");

// Create a sample step
Map<String, Object> step = new HashMap<>();
step.put("stepId", "step1");
step.put("name", "Sample Step");
step.put("description", "A sample step that executes an operation");
step.put("operationId", "getCost");

// Create sample parameters
List<Map<String, Object>> parameters = new ArrayList<>();
Map<String, Object> param1 = new HashMap<>();
param1.put("name", "startLat");
param1.put("value", 43.12);
parameters.add(param1);

// ... more parameters ...

step.put("parameters", parameters);

// Add the step to the workflow
List<Map<String, Object>> steps = new ArrayList<>();
steps.add(step);
workflow.put("steps", steps);

// Execute the workflow
executeWorkflow(workflow);
```

## Future Improvements

This is a proof of concept and has several limitations:

1. It doesn't handle authentication
2. It doesn't support all features of the Python implementation
3. It uses a simplified approach to parsing Arazzo documents and OpenAPI specs
4. It doesn't handle error conditions and edge cases

A full implementation would address these limitations and provide a more robust solution.