plugins {
    base

    val kotlinVersion = "1.3.61"
    kotlin("jvm") version kotlinVersion apply false

    java // Required by at least JUnit.

//    id("org.jetbrains.kotlin.multiplatform") version "1.3.61"
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

val jacocoReports by configurations.creating {
    isCanBeResolved = true
    isCanBeConsumed = false
    attributes {
        attribute(Usage.USAGE_ATTRIBUTE, project.objects.named(Usage::class, "jacocoReports"))
    }
}

dependencies {
    jacocoReports(project(":chapi-domain"))
    jacocoReports(project(":chapi-ast-java"))

    subprojects.forEach {
        archives(it)
    }
}

tasks.register<Copy>("aggregateJacocoReports") {
    from(jacocoReports)
    into(file("$buildDir/jacoco"))
}
