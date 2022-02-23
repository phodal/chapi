plugins {
    base

    val kotlinVersion = "1.6.10"
    kotlin("jvm") version kotlinVersion apply false

    id("java-library")
    id("maven-publish")
    publishing
    signing
//    id("io.github.gradle-nexus.publish-plugin") version "1.1.0"

    java
    jacoco
    id("com.github.kt3k.coveralls") version "2.9.0"

    // todo: erich domain testing & logic
    id("nl.fabianm.kotlin.plugin.generated") version "1.5.0" // hack for jacoco generate code coverage
}

jacoco {
    toolVersion = "0.8.5"
}

allprojects {
    group = "com.phodal.chapi"
    version = "0.0.6"
    description =
        "Chapi is A common language meta information convertor, convert different languages to same meta-data model"

    repositories {
        mavenCentral()
        mavenLocal()
    }
}

subprojects {
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")
    apply(plugin = "signing")
    apply(plugin = "publishing")

    publishing {
        publications {
            create<MavenPublication>("mavenJava") {
                from(components["java"])
                versionMapping {
                    usage("java-api") {
                        fromResolutionOf("runtimeClasspath")
                    }
                    usage("java-runtime") {
                        fromResolutionResult()
                    }
                }
                pom {
                    name.set("Chapi")
                    description.set("Chapi is A common language meta information convertor, convert different languages to same meta-data model")
                    url.set("https://github.com/modernizing/chapi")
                    licenses {
                        license {
                            name.set("MIT")
                            url.set("https://github.com/modernizing/chapi/blob/master/LICENSE")
                        }
                    }
                    developers {
                        developer {
                            id.set("Modernizing")
                            name.set("Modernizing Team")
                            email.set("h@phodal.com")
                        }
                    }
                    scm {
                        connection.set("scm:git:git://github.com/modernizing/chapi.git")
                        developerConnection.set("scm:git:ssh://github.com/modernizing/chapi.git")
                        url.set("https://github.com/modernizing/chapi/")
                    }
                }
            }
        }

        repositories {
            maven {
                val releasesRepoUrl = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                val snapshotsRepoUrl = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
                url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl

                credentials {
                    username = (if (project.findProperty("sonatypeUsername") != null) project.findProperty("sonatypeUsername") else System.getenv("MAVEN_USERNAME")).toString()
                    password = (if (project.findProperty("sonatypePassword") != null) project.findProperty("sonatypePassword") else System.getenv("MAVEN_PASSWORD")).toString()
                }
            }
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/modernizing/chapi")
                credentials {
                    username = System.getenv("GITHUB_ACTOR")
                    password = System.getenv("GITHUB_TOKEN")
                }
            }
        }
    }

//    signing {
//        sign(publishing.publications["mavenJava"])
//    }
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

    jacocoReports(project(":chapi-ast-java"))
    jacocoReports(project(":chapi-ast-typescript"))
    jacocoReports(project(":chapi-ast-python"))
    jacocoReports(project(":chapi-ast-go"))
    jacocoReports(project(":chapi-ast-kotlin"))
    jacocoReports(project(":chapi-ast-c"))
    jacocoReports(project(":chapi-ast-csharp"))
    jacocoReports(project(":chapi-ast-scala"))
    jacocoReports(project(":chapi-ast-cpp"))

    subprojects.forEach {
        archives(it)
    }
}

configure(subprojects) {
    apply("${rootDir}/gradle/chapi-module.gradle")
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
                    exclude("chapi/ast/antlr", "chapi/domain")
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
                    exclude("chapi/ast/antlr", "chapi/domain")
                }
            }
    }))

    reports {
        xml.required.set(false)
        csv.required.set(false)
        html.required.set(true)
        html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
    }

    dependsOn(jacocoMerge)
}
