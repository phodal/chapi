plugins {
    base

    val kotlinVersion = "1.6.10"
    kotlin("jvm") version kotlinVersion apply false

    id("java-library")
    id("maven-publish")
    publishing
    signing

    java
    jacoco
    id("jacoco-report-aggregation")
    id("com.github.kt3k.coveralls") version "2.9.0"
}


jacoco {
    toolVersion = "0.8.7"
    reportsDirectory.set(layout.buildDirectory.dir("customJacocoReportDir"))
}

allprojects {
    group = "com.phodal.chapi"
    version = "0.0.7"
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
}
