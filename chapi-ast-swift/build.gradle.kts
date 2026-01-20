plugins {
    id("antlr")
    java
    kotlin("jvm")
    kotlin("plugin.serialization") version "1.9.24"

    `jacoco-conventions`
}

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    antlr("org.antlr:antlr4:4.13.2")

    // project deps
    implementation(project(":chapi-domain"))

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")

    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    testImplementation(kotlin("test"))

    // JUnit 5
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.2")
    testRuntimeOnly("org.junit.platform:junit-platform-console:1.10.2")

    implementation("org.antlr:antlr4:4.13.2")
    implementation("org.antlr:antlr4-runtime:4.13.2")
}

sourceSets.main {
    java.srcDirs("${project.layout.buildDirectory.get().asFile}/generated-src")
}

tasks.generateGrammarSource {
    maxHeapSize = "256m"
    arguments = arguments + listOf("-package", "chapi.ast.antlr") + listOf("-visitor", "-long-messages")
    outputDirectory = file("${project.layout.buildDirectory.get().asFile}/generated-src/chapi/ast/antlr")
}

tasks.named("compileKotlin") {
    dependsOn(tasks.withType<AntlrTask>())
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

