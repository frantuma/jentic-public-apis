package net.ft.oak.runner;

import net.ft.oak.OakClientRunner;

import java.util.HashMap;

/**
 * Main entry point for the Java port of the Arazzo workflow runner.
 * This is a proof of concept that demonstrates workflow execution using the OakClientRunner.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Arazzo Workflow Runner - Java Port");
        System.out.println("----------------------------------");
        System.out.println("This is a proof of concept that demonstrates workflow execution using the OakClientRunner.");
        System.out.println("It uses Swagger Core and Swagger Parser to read OpenAPI specs and the OakClientRunner to invoke operations.");
        System.out.println();
        
        // Run the demo
        // ArazzoRunnerDemo.main(args);

        String rootDir = OakClientRunner.getApiRoot().getParent().resolve("tools/oak-runner-java").toString();
        ArazzoWorkflowRunner runner = ArazzoWorkflowRunner.fromArazzoPath(
            rootDir + "/src/main/resources/workflows.arazzo.json",
                rootDir + "/src/main/resources"
        );
        runner.executeWorkflow("pre-ride-planning-and-estimation", new HashMap<>());
    }
}