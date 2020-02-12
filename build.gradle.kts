import org.gradle.kotlin.dsl.withType as withType1

plugins {
    base

    val kotlinVersion = "1.3.61"
    kotlin("jvm") version kotlinVersion apply false

    java // Required by at least JUnit.

    jacoco
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
    jacocoReports(project(":chapi-application"))
    jacocoReports(project(":chapi-testing"))

    jacocoReports(project(":chapi-ast-java"))
    jacocoReports(project(":chapi-ast-typescript"))
    jacocoReports(project(":chapi-ast-python"))
    jacocoReports(project(":chapi-ast-go"))
    jacocoReports(project(":chapi-ast-kotlin"))
    jacocoReports(project(":chapi-ast-c"))

    subprojects.forEach {
        archives(it)
    }
}

// refs: https://github.com/ben-manes/caffeine/blob/v2.6.2/build.gradle#L133
tasks.register("jacocoMerge", JacocoMerge::class) {
    subprojects.forEach { subproject ->
        executionData(subproject.tasks.withType(Test::class.java))
    }
    doFirst {
        executionData = files(executionData.filter {
            it.exists()
        })
    }
}

tasks.register<Copy>("aggregateJacocoReports") {
    from(jacocoReports)
    into(file("$buildDir/reports/jacoco"))
}

tasks.register("jacocoRootReports", JacocoReport::class) {
    dependsOn("jacocoMerge")

    reports {
        xml.setEnabled(true)
        html.setEnabled(true)
        xml.setDestination(file("${project.buildDir}/${project.name}.xml"))
    }
}
