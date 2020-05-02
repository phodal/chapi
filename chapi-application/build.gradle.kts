plugins {
    application

    java
    kotlin("jvm")
    kotlin("plugin.serialization") version "1.3.72"

    `jacoco-conventions`
}

repositories {
    mavenCentral()
    mavenLocal()
    jcenter()
}

application {
    mainClassName = "chapi.app.cli.Main"
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = application.mainClassName
    }

    from(configurations.runtime.get().map {if (it.isDirectory) it else zipTree(it)})
}

dependencies {
    // project deps
    implementation(project(":chapi-domain"))

    // languages
    implementation(project(":chapi-ast-java"))
    implementation(project(":chapi-ast-go"))
    implementation(project(":chapi-ast-typescript"))
    implementation(project(":chapi-ast-python"))
    implementation(project(":chapi-ast-scala"))
    implementation(project(":chapi-ast-c"))

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.20.0") // JVM dependency

    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    // Kotlin reflection.
    implementation(kotlin("test"))
    implementation(kotlin("test-junit"))

    // JUnit 5
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.0")
    testRuntimeOnly("org.junit.platform:junit-platform-console:1.6.0")

    implementation("org.antlr:antlr4:4.8-1")
    implementation("org.antlr:antlr4-runtime:4.8-1")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}
