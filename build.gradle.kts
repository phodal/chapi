plugins {
    base

    val kotlinVersion = "1.3.61"
    kotlin("jvm") version kotlinVersion apply false

    java

    jacoco
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
val jacocoMerge by tasks.registering(JacocoMerge::class) {
    subprojects.forEach { subproject ->
        executionData(subproject.tasks.withType(Test::class.java))
    }
    doFirst {
        executionData = files(executionData.filter {
            it.exists()
        })
    }
}

// refs: https://github.com/stankevichevg/axon-couchbase/blob/b455cbf420963656adf8f3d964c68f9811c9e8e3/build.gradle.kts
tasks.register("jacocoRootReports", JacocoReport::class) {
    dependsOn("test")
    dependsOn(jacocoMerge)

    executionData(files(jacocoMerge))
    additionalClassDirs(files(subprojects.flatMap { project ->
        listOf("java", "kotlin")
            .map {
                project.buildDir.path + "/classes/$it/main"
            }
            .map {
                fileTree(it).apply {
                    exclude("chapi/ast/antlr")
                }
            }
    }))
    additionalSourceDirs(files(subprojects.flatMap { project ->
        listOf("java", "kotlin")
            .map {
                project.file("src/main/$it").absolutePath
            }
            .map {
                fileTree(it).apply {
                    exclude("chapi/ast/antlr")
                }
            }
    }))

    reports {
        xml.setEnabled(true)
        html.setEnabled(true)
        xml.setDestination(file("${project.buildDir}/reports/jacoco/test/jacocoTestReport.xml"))
    }
    dependsOn(jacocoMerge)
}
