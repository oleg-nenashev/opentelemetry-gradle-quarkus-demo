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

## Starting 

```shell
./run-otel.sh
```

## Tracing the build process

```
 SPAN_ID=abc5 TRACE_ID=cda5 ./gradlew build
```

Use `clean`, `test`, or any other Gradle Build Tool's feature to alter the behavior.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
 SPAN_ID=abc5 TRACE_ID=cda5 quarkus dev
```

Or:

```shell
 SPAN_ID=abc5 TRACE_ID=cda5  ./gradlew --console=plain quarkusDev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at `http://localhost:8080/q/dev/`.
