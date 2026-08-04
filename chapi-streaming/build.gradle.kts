plugins {
    application
    java
    kotlin("jvm")
    kotlin("plugin.serialization") version "1.9.24"

    `jacoco-conventions`
}

dependencies {
    implementation(project(":chapi-domain"))
    implementation(project(":chapi-ast-java"))

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    implementation("org.apache.kafka:kafka-streams:4.3.1")
    runtimeOnly("org.slf4j:slf4j-simple:2.0.17")

    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
    testImplementation("org.apache.kafka:kafka-streams-test-utils:4.3.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.2")
    testRuntimeOnly("org.junit.platform:junit-platform-console:1.10.2")
}

application {
    mainClass.set("chapi.streaming.ChapiStreamingApplicationKt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}
