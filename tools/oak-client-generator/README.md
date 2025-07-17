# Oak Client Generator

A high-performance tool for generating client SDKs from OpenAPI specifications. 
The Oak Client Generator automatically creates Java and Python client libraries with consistent patterns and packaging, making API integration simpler across your projects.

The clients are generated under `/clients` directory, organized by API vendor and version. Each client is built as a standalone JAR for Java and (eventually) a package for Python, ready for immediate use in your applications.
A copy of the client jar is also placed in the `clients/jars` directory for easy access, with the JAR file named in the format `oak-{id}.jar`.

## Features

- **Multi-language Support**: Automatically generates both Java and Python client libraries
- **Concurrent Processing**: Parallel generation with progress monitoring
- **Artifact Management**: Builds and packages Java clients as ready-to-use JARs
- **API Version Support**: Maintains versioned clients for each API

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- Environment variable `M2_HOME` pointing to your Maven installation

## Building the Project

To build the Oak Client Generator from source:

```bash
cd tools/oak-client-generator
mvn clean package
```

This creates a standalone JAR at `target/oak-client-generator-1.0-SNAPSHOT.jar` with all dependencies included.

## Running the Generator

To run the generator with default settings:

```bash
java -jar target/oak-client-generator-1.0-SNAPSHOT.jar
```

By default, the generator looks for OpenAPI specifications in the `../../apis` directory relative to the current working directory.

