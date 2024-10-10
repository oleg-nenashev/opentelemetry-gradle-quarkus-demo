# Demo: Build Tool Observability with OpenTelemetry, Gradle and Quarkus

Demonstrates using Gradle Quarkus Plugin for building a Quarkus a web application with Gradle Build Tool,
with the WireMock dev service being used for development purposes.
Everything is connected to OpenTelemetry and exposes data there.

Components:

- [Gradle Plugin for Quarkus](https://quarkus.io/guides/gradle-tooling)
- [OpenTelemetry Plugin for Gradle](https://github.com/craigatk/opentelemetry-gradle-plugin)
- [WireMock](https://wiremock.org/)
- [WireMock extension and dev service for Quarkus](https://github.com/quarkiverse/quarkus-wiremock)

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## GitHub Actions with Honeycomb

I took Honeycomb for the GitHub Actions demo.
You can fork the repository and do minimum reconfiguration to reproduce it on your instance.

### Manual test

```shell
SPAN_ID=abc5 TRACE_ID=cda5 OTEL_ENDPOINT=https://api.honeycomb.io OTEL_ENDPOINT_GRPC=api.honeycomb.io:443 OTEL_API_TOKEN_HEADER=x-honeycomb-team OTEL_API_TOKEN=${YOUR_TOKEN} OTEL_SERVICE_NAME=test ./gradlew build
```

## Local Test

### Starting OTel Collector

We will use `jaegertracing/all-in-one` for the demo

```shell
./run-otel.sh
```

### Tracing the build process

```
 SPAN_ID=abc5 TRACE_ID=cda5 ./gradlew build
```

Use `clean`, `test`, or any other Gradle Build Tool's feature to alter the behavior.

### Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
quarkus dev
```

Or:

```shell
 SPAN_ID=abc5 TRACE_ID=cda5  ./gradlew --console=plain quarkusDev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at `http://localhost:8080/q/dev/`.

### Packaging and running the application

The application can be packaged using:

```shell script
./gradlew quarkusBuild
```

It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/quarkus-app/lib/` directory.

The application is now runnable using `java -jar build/quarkus-app/quarkus-run.jar`.
