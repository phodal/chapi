plugins {
    java
    id("antlr")
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
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.0")

    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    // Kotlin reflection.
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
    maxHeapSize = "64m"
    arguments = arguments + listOf("-package", "chapi.parser.toml") + listOf("-visitor", "-long-messages")
    outputDirectory = file("${project.layout.buildDirectory.get().asFile}/generated-src/chapi/parser/toml")
}

tasks.withType<AntlrTask> {

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
