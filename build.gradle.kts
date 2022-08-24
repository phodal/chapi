plugins {
    base

    val kotlinVersion = "1.6.10"
    kotlin("jvm") version kotlinVersion apply false

    id("java-library")
    id("maven-publish")
    publishing
    signing

    java
    id("jacoco-report-aggregation")
    id("com.github.kt3k.coveralls") version "2.9.0"

    id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
}


jacoco {
    toolVersion = "0.8.7"
}

allprojects {
    apply(plugin = "java")

    group = "com.phodal.chapi"
    version = "2.0.0-alpha.5"
    description =
        "Chapi is A common language meta information convertor, convert different languages to same meta-data model"

    repositories {
        mavenCentral()
        mavenLocal()
    }

    java.sourceCompatibility = JavaVersion.VERSION_1_8
    java.targetCompatibility = JavaVersion.VERSION_1_8
}

subprojects {
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")
    apply(plugin = "signing")
    apply(plugin = "publishing")
    apply(plugin = "jacoco")

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
                            name.set("MPL 2.0")
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
                    username = (if (project.findProperty("sonatypeUsername") != null) project.findProperty("sonatypeUsername") else System.getenv("OSSRH_USERNAME")).toString()
                    password = (if (project.findProperty("sonatypePassword") != null) project.findProperty("sonatypePassword") else System.getenv("OSSRH_PASSWORD")).toString()
                }
            }
        }
    }

    signing {
        sign(publishing.publications["mavenJava"])
    }

    java {
        withJavadocJar()
        withSourcesJar()
    }

    tasks.test {
        finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
    }

    tasks.jacocoTestReport {
        dependsOn(tasks.test) // tests are required to run before generating the report
    }

    tasks.jacocoTestReport {
        reports {
            xml.required.set(false)
            csv.required.set(false)
            html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
        }
    }

    tasks.withType<JacocoReport> {
        afterEvaluate {
            classDirectories.setFrom(files(classDirectories.files.map {
                fileTree(it).apply {
                    exclude("chapi/ast/antlr")
                }
            }))
        }
    }
}

dependencies {
    jacocoAggregation(project(":chapi-domain"))
    jacocoAggregation(project(":chapi-ast-java"))
    jacocoAggregation(project(":chapi-ast-typescript"))
    jacocoAggregation(project(":chapi-ast-go"))
    jacocoAggregation(project(":chapi-ast-python"))
    jacocoAggregation(project(":chapi-ast-c"))
    jacocoAggregation(project(":chapi-ast-csharp"))
    jacocoAggregation(project(":chapi-ast-kotlin"))
    jacocoAggregation(project(":chapi-ast-scala"))
    jacocoAggregation(project(":chapi-ast-cpp"))
}

reporting {
    reports {
        val jacocoRootReport by creating(JacocoCoverageReport::class) {
            testType.set(TestSuiteType.UNIT_TEST)
        }
    }
}

tasks.check {
    dependsOn(tasks.named<JacocoReport>("jacocoRootReport"))
}
