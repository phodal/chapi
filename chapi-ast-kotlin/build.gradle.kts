plugins {
    id("antlr")
    java
    kotlin("jvm")
    kotlin("plugin.serialization") version "1.6.10"

    `jacoco-conventions`
}

repositories {
    mavenCentral()
    mavenLocal()
    jcenter()
}

dependencies {
    antlr("org.antlr:antlr4:4.9.3")

    // project deps
    implementation(project(":chapi-domain"))

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")

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

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

sourceSets.main {
    java.srcDirs("${project.buildDir}/generated-src")
}

tasks.generateGrammarSource {
    maxHeapSize = "64m"
    arguments = arguments + listOf("-package", "chapi.ast.antlr") + listOf("-visitor", "-long-messages")
    outputDirectory = file("${project.buildDir}/generated-src/chapi/ast/antlr")
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
