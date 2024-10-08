plugins {
    `kotlin-dsl` version "5.1.1"
    `java`
    `idea`
    id("io.quarkus") version "3.15.1"
    `maven-publish`
    id("com.atkinsondev.opentelemetry-build") version "1.14.0"
}

group = "com.gradle.demos.quarkus.wiremock"
version = "1.0.0-SNAPSHOT"
description = "Demo: WireMock Dev Service for Quarkus"

repositories {
    mavenLocal()
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project

dependencies {
    implementation("io.quarkus:quarkus-opentelemetry")
    implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))
    api(libs.io.quarkus.quarkus.arc)
    api(libs.io.quarkus.quarkus.resteasy.reactive)
    api(libs.io.quarkus.quarkus.rest.client.reactive.jackson)
    api(libs.io.quarkus.quarkus.resteasy.reactive.jackson)
    api("io.quarkiverse.wiremock:quarkus-wiremock:1.3.0")

    testImplementation(libs.io.quarkus.quarkus.junit5)
    testImplementation("io.quarkiverse.wiremock:quarkus-wiremock-test:1.3.0")
    testImplementation(libs.io.rest.assured.rest.assured) {
      exclude(group = "org.apache.groovy", module = "groovy")
      exclude(group = "org.apache.groovy", module = "groovy-xml")
    }
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc>() {
    options.encoding = "UTF-8"
}


tasks.withType<Test> {
    dependsOn(":quarkusBuild")
    useJUnitPlatform()
    systemProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager")
    maxParallelForks = 1;
}

//if (System.getenv("OTEL_ENDPOINT") != null) {
openTelemetryBuild {
    serviceName = System.getenv("OTEL_SERVICE_NAME")
    endpoint = System.getenv("OTEL_ENDPOINT")
    headers = mapOf (System.getenv("OTEL_API_TOKEN_HEADER") to System.getenv("OTEL_API_TOKEN"))
    exporterMode = com.atkinsondev.opentelemetry.build.OpenTelemetryExporterMode.GRPC
    traceViewUrl = "https://ui.honeycomb.io/team-a-utomation/environments/test/datasets/demo-java-cicd-o11y/trace?trace_id={traceId}"
}

// https://opentelemetry.io/docs/zero-code/java/agent/
tasks.withType<Test> {
    environment("TRACE_ID", System.getenv("TRACE_ID"))
    environment("SPAN_ID", System.getenv("SPAN_ID"))
    jvmArgs(
        "-javaagent:opentelemetry-javaagent.jar",
        "-Dotel.service.name=" + System.getenv("OTEL_SERVICE_NAME"),
        "-Dotel.instrumentation.runtime-telemetry-java17.enabled=true" /* JFR Metrics for tests */,
        "-Dotel.exporter.otlp.protocol=http/protobuf",
        "-Dotel.exporter.otlp.endpoint=" + System.getenv("OTEL_ENDPOINT"),
        "-Dotel.exporter.otlp.headers=" + System.getenv("OTEL_API_TOKEN_HEADER") + "=" + System.getenv("OTEL_API_TOKEN"))
}


//} else {
//    openTelemetryBuild {
//        endpoint = "http://localhost:4317"
//        exporterMode = com.atkinsondev.opentelemetry.build.OpenTelemetryExporterMode.GRPC
//    }
//}
