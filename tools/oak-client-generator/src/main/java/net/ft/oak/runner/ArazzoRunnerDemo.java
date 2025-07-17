package net.ft.oak.runner;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.parser.OpenAPIV3Parser;
import io.swagger.v3.parser.core.models.AuthorizationValue;
import io.swagger.v3.parser.core.models.ParseOptions;
import net.ft.oak.OakClientRunner;
import net.ft.oak.runner.model.ArazzoDoc;
import net.ft.oak.runner.model.StepParameter;
import net.ft.oak.runner.model.Workflow;
import net.ft.oak.runner.model.WorkflowStep;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * A simple demonstration of executing Arazzo workflows using the OakClientRunner.
 * This is a proof of concept that shows how to execute operations from an Arazzo workflow.
 */
public class ArazzoRunnerDemo {
    private static final Logger logger = Logger.getLogger(ArazzoRunnerDemo.class.getName());

    private static OpenAPI oas;
    public static void main(String[] args) {
        logger.info("Starting Arazzo Runner Demo");
        
        try {
            String workflowString = Files.readString(new File("/dati/dev/progetti/jentic/projects/oak/tools/oak-client-generator/src/main/resources/workflows.arazzo.json").toPath());
            ParseOptions options = new ParseOptions();
            options.setResolve(true);
            options.setFlatten(true);
            AuthorizationValue authorizationValue = new AuthorizationValue();
            authorizationValue.setType("apiKey");
            authorizationValue.setValue("xxxxx");
            authorizationValue.setKeyName("Authorization");

            oas = new OpenAPIV3Parser().read("/dati/dev/progetti/jentic/projects/oak/tools/oak-client-generator/src/main/resources/openapi.json", Collections.singletonList(authorizationValue), null);

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            System.out.println("Workflow JSON: " + workflowString);
            ArazzoDoc arazzoDoc = objectMapper.readValue(workflowString, ArazzoDoc.class);
            // Map arazzo = objectMapper.readValue(workflowString, Map.class);

            // Yaml.prettyPrint(arazzoDoc);
            Workflow workflow = arazzoDoc.getWorkflows().get(0);
            // Execute the workflow
            executeWorkflow(workflow);
            
        } catch (Exception e) {
            logger.severe("Error executing workflow: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Executes a workflow by executing each step in sequence.
     *
     * @param workflow The workflow to execute
     * @throws Exception If there is an error executing the workflow
     */
    private static void executeWorkflow(Workflow workflow) throws Exception {
        logger.info("=== Starting workflow: " + workflow.getWorkflowId() + " ===");

        // Execute each step
        for (WorkflowStep step : workflow.getSteps()) {
            executeStep(step);
        }
        
        logger.info("=== Completed workflow: " + workflow.getWorkflowId() + " ===");
    }
    
    /**
     * Executes a step by executing the operation with the given parameters.
     *
     * @param step The step to execute
     * @throws Exception If there is an error executing the step
     */
    private static void executeStep(WorkflowStep step) throws Exception {
        logger.info("--- Starting step: " + step.getStepId() + " ---");

        // Get the operation ID
        String operationId = (String) step.getOperationId();
        
        // Get the parameters
        List<StepParameter> parameters = step.getParameters();
        
        // Prepare the arguments
        Object[] args = new Object[parameters.size()];
        for (int i = 0; i < parameters.size(); i++) {
            StepParameter param = parameters.get(i);
            args[i] = param.getValue();
        }
        // load the OAS (cache..) and build the parameter types dynamically
        // in real life use binding library
        final Operation[] foundOperation = {null};
        oas.getPaths().forEach((path, pathItem) -> {
            logger.info("Path: " + path);
            pathItem.readOperationsMap().forEach((httpMethod, operation) -> {
                logger.info("Operation: " + httpMethod + " - " + operation.getOperationId());
                if (operation.getOperationId().equals(operationId)) {
                    logger.info("Found operation: " + operation.getOperationId());
                    foundOperation[0] = operation;
                }
            });
        });
        Operation operation = foundOperation[0];

        List<Class> inferredArgs = new ArrayList<>();
        operation.getParameters().forEach(param -> {
            logger.info("Parameter: " + param.getName() + " - " + param.getSchema().getType());
            switch ( param.getSchema().getType() ) {
                case "integer":
                case "number":
                    inferredArgs.add(Double.class);
                    break;
                case "string":
                    inferredArgs.add(String.class);
                    break;
                default:
                    inferredArgs.add(Double.class);
            }
        });


        // Create the OakClientRunner
        // In a real implementation, we would use the appropriate API ID and real param and param types.
        OakClientRunner runner = new OakClientRunner("88c652dbb50efeecf3476dce223a239a", new File("/dati/dev/progetti/jentic/projects/oak/apis"));
        
        // Execute the operation
        logger.info("Executing operation: " + operationId);
        Object result = runner.executeOperation(operationId, "Public", args, inferredArgs.toArray(new Class[0]));
        
        // Log the result
        logger.info("Operation result: " + result);
        
        logger.info("--- Completed step: " + step.getStepId() + " ---");
    }
}