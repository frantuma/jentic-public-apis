package net.ft.oak;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashSet;
import java.util.Set;

// import static net.ft.oak.OakClientGenerator.API_ROOT;

public class OakClientRunner {
    final static String API_ROOT = "/dati/dev/progetti/jentic/projects/oak/apis";
    final ClassLoader cl;

    public static void main(String[] args) throws IOException {
        System.out.println("OakClientRunner is running...");

        File root = new File(args.length > 0 && args[0] != null ? args[0] : API_ROOT);
        if (!root.exists() || !root.isDirectory()) {
            System.err.println("API root directory not found: " + API_ROOT);
            return;
        }
        OakClientRunner runner = new OakClientRunner("88c652dbb50efeecf3476dce223a239a", root);
        runner.executeOperation("getCost", "Public", new Object[]{43.12, 13.45, "lyft_access", 43.12, 13.45});

    }
    public OakClientRunner(String id, File rootDir) {

        File jarDir = new File(rootDir.getParentFile(), "clients/jars/" + id);
        cl = buildClassLoader(jarDir);
        if (cl == null) {
            System.err.println("Failed to build class loader for: " + jarDir.getAbsolutePath());
        }
    }

    private ClassLoader buildClassLoader(File jarDir) {
        try {
            Set<URL> urls = new HashSet<>();
            for (File jarFile : jarDir.listFiles((dir, name) -> name.endsWith(".jar"))) {
                if (jarFile.isFile()) {
                    System.out.println("Found JAR file: " + jarFile.getAbsolutePath());
                    urls.add(jarFile.toURI().toURL());
                }
            }
            URLClassLoader cl = new URLClassLoader(urls.toArray(new URL[0]),
                    OakClientRunner.class.getClassLoader());
            return cl;
        } catch (Exception e) {
            System.err.println("Failed to build class loader: " + e.getMessage());
            return null;
        }
    }

    public Object executeOperation(String operationId, String tag,  Object[] args) {
        try {
            if (cl != null) {
                Class<?> clientClass = cl.loadClass("org.openapitools.client.api." + tag + "Api");
                Object clientInstance = clientClass.getDeclaredConstructor().newInstance();

                // TODO get the method parameter types and arguments dynamically
                Class<?>[] methodParamTypes = { Double.class, Double.class, String.class, Double.class, Double.class };
                // Object[] methodArgs = { 43.12, 13.45 };

                Object returnValue = clientClass
                        .getMethod(operationId, methodParamTypes)
                        .invoke(clientInstance, args);
                if (returnValue != null) {
                    System.out.println("Operation " + operationId + " executed successfully. Return value: " + returnValue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error executing operation: " + e.getMessage());
        }
        return null;
    }
}