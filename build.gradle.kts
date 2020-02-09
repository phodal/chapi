plugins {
    base

    val kotlinVersion = "1.3.61"
    kotlin("jvm") version kotlinVersion apply false

    java // Required by at least JUnit.

//    id("org.jetbrains.kotlin.multiplatform") version "1.3.61"

    // Test coverage
    jacoco
    // Upload jacoco coverage reports to coveralls
    id("com.github.kt3k.coveralls") version "2.9.0"
}

allprojects {
    group = "com.phodal"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
        mavenLocal()
        jcenter()
    }
}


dependencies {
    subprojects.forEach {
        archives(it)
    }
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

