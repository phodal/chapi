plugins {
    java
    kotlin("jvm")
    kotlin("plugin.serialization") version "1.6.10"

    `jacoco-conventions`
}

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation(project(":chapi-domain"))

    // languages
    implementation(project(":chapi-ast-java"))
    implementation(project(":chapi-ast-go"))
    implementation(project(":chapi-ast-typescript"))
    implementation(project(":chapi-ast-python"))
    implementation(project(":chapi-ast-scala"))
    implementation(project(":chapi-ast-c"))
    implementation(project(":chapi-ast-csharp"))
    implementation(project(":chapi-ast-kotlin"))

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")

    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    // Kotlin reflection.
    implementation(kotlin("test"))
    implementation(kotlin("test-junit"))

    // JUnit 5
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.0")
    testRuntimeOnly("org.junit.platform:junit-platform-console:1.6.0")

    implementation("org.antlr:antlr4:4.9.3")
    implementation("org.antlr:antlr4-runtime:4.9.3")
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}
