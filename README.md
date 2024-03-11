# Demo: Quarkus web service with Gradle

> **WARNING:**: This branch delibirately includes old Quarkus version with
[CVE-2023-4853](https://quarkus.io/blog/cve-2023-4853/) in `io.quarkus:quarkus-vertx-http`

Demonstrates using Gradle Quarkus Plugin for building a Quarkus a web application with Gradle,
with the WireMock dev service being used for development purposes.
It uses Hacker News as a data source and mocks its REST API for local development.

Components:

- [Gradle Plugin for Quarkus](https://quarkus.io/guides/gradle-tooling)
- [WireMock](https://wiremock.org/)
- [WireMock extension and dev service for Quarkus](https://github.com/quarkiverse/quarkus-wiremock)

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
quarkus dev
```

Or:

```shell
./gradlew --console=plain quarkusDev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at `http://localhost:8080/q/dev/`.

## Packaging and running the application

The application can be packaged using:

```shell script
./gradlew quarkusBuild
```

It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/quarkus-app/lib/` directory.

The application is now runnable using `java -jar build/quarkus-app/quarkus-run.jar`.

### Uber JAR

If you want to build an _über-jar_, execute the following command:

```shell script
./gradlew quarkusBuild -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar build/*-runner.jar`.
