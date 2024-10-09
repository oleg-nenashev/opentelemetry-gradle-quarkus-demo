plugins {
    `kotlin-dsl`
    `java`
    `idea`
    id("io.quarkus") version "3.15.1"
    `maven-publish`
    id("com.atkinsondev.opentelemetry-build") version "1.9.1"
    id("com.gradle.develocity") version("3.18.1")
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

openTelemetryBuild {
    endpoint = "http://localhost:4317"
    exporterMode = com.atkinsondev.opentelemetry.build.OpenTelemetryExporterMode.GRPC
}

develocity {

}
