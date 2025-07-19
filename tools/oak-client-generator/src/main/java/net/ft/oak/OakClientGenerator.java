package net.ft.oak;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.Invoker;
import org.openapitools.codegen.DefaultGenerator;
import org.openapitools.codegen.config.CodegenConfigurator;

public class OakClientGenerator {
    // public static final String API_ROOT = "../../apis";
    final static String API_ROOT = "/dati/dev/progetti/jentic/projects/oak/apis";

    public static final String GROUP_ID = "org.openapitools.client";

    public static void main(String[] args) throws IOException {
        System.out.println("Oak Client Generator is running...");

        File root = new File(args.length > 0 && args[0] != null ? args[0] : API_ROOT);
        if (!root.exists() || !root.isDirectory()) {
            System.err.println("API root directory not found: " + API_ROOT);
            return;
        }
        File jarOut   = new File(root.getParentFile(), "clients/jars");
        jarOut.mkdirs();
        List<ApiInfo> openapiFiles = new ArrayList<>();
        findOpenApiFiles(root, openapiFiles);


        int poolSize = Math.max(2, Runtime.getRuntime().availableProcessors());
        ExecutorService workerPool = Executors.newFixedThreadPool(poolSize, r -> {
            Thread t = new Thread(r);
            t.setName("oak-generator-" + t.getId());
            t.setDaemon(false);
            return t;
        });

        ScheduledExecutorService monitorPool = Executors.newSingleThreadScheduledExecutor(
                r -> {
                    Thread t = new Thread(r, "oak-monitor");
                    t.setDaemon(true);
                    return t;
                });

        AtomicInteger submitted = new AtomicInteger();
        AtomicInteger succeeded = new AtomicInteger();
        AtomicInteger failed    = new AtomicInteger();

        monitorPool.scheduleAtFixedRate(() -> {
            System.out.printf("[%s] progress – submitted=%d, succeeded=%d, failed=%d%n",
                    LocalTime.now(), submitted.get(), succeeded.get(), failed.get());
        }, 2, 5, TimeUnit.SECONDS);

        /* -----------------------------------------------------------------
           Submit one task per (file, language) pair
           ----------------------------------------------------------------- */
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (ApiInfo apiInfo : openapiFiles) {
            File openapiFile = apiInfo.file;
            String versionDir = openapiFile.getParentFile()
                    .getAbsolutePath()
                    .replace("/apis/", "/clients/");
            File javaOut   = new File(versionDir, "java");
            File pythonOut = new File(versionDir, "python");
            javaOut.mkdirs();
            pythonOut.mkdirs();

            for (String lang : List.of("java", "python")) {
                File outDir = lang.equals("java") ? javaOut : pythonOut;

                CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
                    OakClientGenerator gen = new OakClientGenerator();
                    try {
                        gen.generateAndBuildClient(openapiFile, outDir, lang, apiInfo.id, apiInfo.version, jarOut);
                        succeeded.incrementAndGet();
                    } catch (Exception ex) {
                        failed.incrementAndGet();
                        System.err.printf("[ERROR] %s while processing %s (%s)%n",
                                ex.getMessage(), openapiFile.getName(), lang);
                    }
                }, workerPool);

                submitted.incrementAndGet();
                futures.add(cf);
            }
        }

        /* -----------------------------------------------------------------
           Wait for completion & clean-up
           ----------------------------------------------------------------- */
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        workerPool.shutdown();
        monitorPool.shutdownNow();

        System.out.printf("Generation finished – submitted=%d, succeeded=%d, failed=%d%n",
                submitted.get(), succeeded.get(), failed.get());

/*
        // Uncomment this block to run the generator in a single-threaded mode
        for (File openapiFile : openapiFiles) {
            String versionDir = openapiFile.getParentFile().getAbsolutePath().replace("/apis/", "/clients/");
            File javaOut = new File(versionDir, "java");
            File pythonOut = new File(versionDir, "python");
            javaOut.mkdirs();
            pythonOut.mkdirs();
            OakClientGenerator generator = new OakClientGenerator();
            generator.generateAndBuildClient(openapiFile, javaOut, "java");
            generator.generateAndBuildClient(openapiFile, pythonOut, "python");
        }*/
    }

    private static void findOpenApiFiles(File dir, List<ApiInfo> result) throws IOException {
        File[] files = dir.listFiles();
        if (files == null) return;
        for (File file : files) {
            if (file.isDirectory()) {
                findOpenApiFiles(file, result);
            } else if (file.getName().equals("openapi.json")) {
                if (file.getAbsolutePath().contains("lyft")) {
                    System.out.println("Lyft API: " + file.getAbsolutePath());
                    ApiInfo apiInfo = new ApiInfo();
                    apiInfo.file = file;
                    apiInfo.version = file.getParentFile().getName();
                    File metaFile = new File(file.getParentFile().getParentFile(), "meta.json");
                    ObjectNode node = (ObjectNode) new ObjectMapper().readTree(Files.readString(metaFile.getCanonicalFile().toPath()));
                    apiInfo.id = node.get("api_info").get("id").asText();
                    result.add(apiInfo);
                }
            }
        }
    }

    private void generateClient(File openapiFile, File outDir, String lang) {
        String generatorName = lang.equals("java") ? "java" : "python";
        ProcessBuilder pb = new ProcessBuilder(
            "openapi-generator-cli",
            "generate",
            "-i", openapiFile.getAbsolutePath(),
            "-g", generatorName,
            "-o", outDir.getAbsolutePath()
        );
        pb.inheritIO();
        try {
            Process proc = pb.start();
            int exitCode = proc.waitFor();
            if (exitCode == 0) {
                System.out.println("Generated " + lang + " client at: " + outDir.getAbsolutePath());
            } else {
                System.err.println("Failed to generate " + lang + " client for: " + openapiFile.getAbsolutePath());
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error running OpenAPI Generator: " + e.getMessage());
        }
    }

    public void generateAndBuildClient(File openapiFile, File outDir, String lang, String id, String version, File jarOutDir) {
        try {
            CodegenConfigurator configurator = buildCodegenConfigurator(openapiFile, outDir, lang, id);
            DefaultGenerator generator = new DefaultGenerator();
            generator.opts(configurator.toClientOptInput()).generate();
            System.out.println("Programmatically generated " + lang + " client at: " + outDir.getAbsolutePath());
            if (lang.equals("java")) {
                // Run Maven to install the generated Java client
                runMaven(outDir);
                // copy the generated JAR to the output directory
                File idDir = new File(jarOutDir, id);
                idDir.mkdirs();
                File jarFile = new File(outDir, "target/oak-" + id + "-" + version +".jar");
                if (jarFile.exists()) {
                    Files.copy(jarFile.toPath(), Paths.get(idDir.getCanonicalPath(), "oak-" + id + ".jar"),
                             java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Copied JAR to: " + jarOutDir.getCanonicalPath());
                    File libDir = new File(outDir, "target/lib");
                    if (libDir.exists() && libDir.isDirectory()) {
                        for (File libFile : libDir.listFiles()) {
                            if (libFile.getName().endsWith(".jar")) {
                                Files.copy(libFile.toPath(), Paths.get(idDir.getCanonicalPath(), libFile.getName()),
                                        StandardCopyOption.REPLACE_EXISTING);
                                System.out.println("Copied library JAR: " + libFile.getName());
                            }
                        }
                    }
                } else {
                    System.err.println("JAR file not found after Maven build: " + jarFile.getAbsolutePath());
                }
            }
        } catch (Exception e) {
            System.err.println("Error generating " + lang + " client programmatically for: " + openapiFile.getAbsolutePath());
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    protected CodegenConfigurator buildCodegenConfigurator(File openapiFile, File outDir, String lang, String id) throws Exception {
        System.out.println("Configuring generator for: " + openapiFile.getAbsolutePath());
        CodegenConfigurator configurator = new CodegenConfigurator();
        configurator.setInputSpec(openapiFile.getAbsolutePath());
        configurator.setGeneratorName(lang);
        // doesn't validate, in real world log and skip if not valid
        configurator.setValidateSpec(false); // Skip validation for simplicity;
        configurator.setOutputDir(outDir.getAbsolutePath());

        // configurator.setPackageName(GROUP_ID);
        // configurator.setGroupId(GROUP_ID);
        configurator.setArtifactId("oak-" + id);
        if (lang.equals("java")) {
            // configurator.addAdditionalProperty("apiPackage", GROUP_ID + ".api");
            // configurator.addAdditionalProperty("dynamicOperations", "true");
            // configurator.setLibrary("okhttp-gson");
            // configurator.addAdditionalProperty("useSingleRequestParameter", "true");
        }
        return configurator;
    }

    protected void runMaven(File rootDir) throws Exception {
        System.out.println("Running Maven install for: " + rootDir.getAbsolutePath());
        InvocationRequest req = new DefaultInvocationRequest();
        File pomFile = new File(rootDir, "pom.xml");
        req.setPomFile(pomFile);
        req.setGoals(List.of("install"));
        Invoker invoker = new DefaultInvoker();
        invoker.setMavenHome(Paths.get(System.getenv("M2_HOME")).toFile());
        invoker.execute(req);

    }

    static class ApiInfo {
        File file;
        String id;
        String version;
    }

    private static String getApiRoot() {
        String userDir = System.getProperty("user.dir");
        File dir = new File(userDir);
        // Check if running from module directory
        if (dir.getName().equals("oak-client-generator")) {
            // repo root is two levels up
            return dir.getParentFile().getParentFile().toPath().resolve("apis").toString();
        } else {
            // Assume running from repo root
            return dir.toPath().resolve("apis").toString();
        }
    }
}