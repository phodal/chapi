group = "com.phodal"
version = "1.0-SNAPSHOT"

plugins {
    val kotlinVersion = "1.3.61"

//    application
    kotlin("jvm") version kotlinVersion
    java // Required by at least JUnit.

//    id("org.jetbrains.kotlin.multiplatform") version "1.3.61"

    // Test coverage
    jacoco
    // Upload jacoco coverage reports to coveralls
     id("com.github.kt3k.coveralls") version "2.9.0"
}

repositories {
    mavenCentral()
    mavenLocal()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib"))
    // To "prevent strange errors".
    implementation(kotlin("reflect"))
    // Kotlin reflection.
    implementation(kotlin("test"))
    implementation(kotlin("test-junit"))

    // JUnit 5
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.0")
    testRuntimeOnly("org.junit.platform:junit-platform-console:1.6.0")

    // Kotlintest
    testImplementation("io.kotlintest:kotlintest-core:3.4.2")
    testImplementation("io.kotlintest:kotlintest-assertions:3.4.2")
    testImplementation("io.kotlintest:kotlintest-runner-junit5:3.4.2")

    // JavaFX tests using TestFX
    testImplementation("org.testfx:testfx-core:4.0.16-alpha")
    testImplementation("org.testfx:testfx-junit:4.0.16-alpha")
    // Only needed for headless testing.
    // testImplementation("org.testfx:openjfx-monocle:8u76-b04") // jdk-9+181 for Java 9

    // Spek
    testImplementation("org.spekframework.spek2:spek-dsl-jvm:2.0.9")
    testImplementation("org.spekframework.spek2:spek-runner-junit5:2.0.9")
}


// Test coverage reporting for coveralls.
tasks {
    // Enable xml for coveralls.
    "jacocoTestReport"(JacocoReport::class) {
        reports {
            // To be read by humans
            html.isEnabled = true
            // To be read by Coveralls etc.
            xml.isEnabled = true
            xml.destination = file("$buildDir/reports/jacoco/test/jacocoTestReport.xml")
        }
    }

    // Trying to run tests every time.
    val test by tasks
    val cleanTest by tasks
    test.dependsOn(cleanTest)

    // Use the built-in JUnit support of Gradle.
    "test"(Test::class) {
        useJUnitPlatform()
    }

    // Sorry, I have no idea.
    Unit
}

