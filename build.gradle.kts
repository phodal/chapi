import java.time.Duration
import org.w3c.dom.Element
import javax.xml.parsers.DocumentBuilderFactory

plugins {
    base

    val kotlinVersion = "1.9.24"
    kotlin("jvm") version kotlinVersion apply false

    kotlin("plugin.serialization") version kotlinVersion apply false

    id("java-library")
    id("maven-publish")
    publishing
    signing

    java
    id("jacoco-report-aggregation")
    id("com.github.kt3k.coveralls") version "2.12.2"

    id("io.github.gradle-nexus.publish-plugin") version "1.3.0"
}


jacoco {
    toolVersion = "0.8.12"
}

allprojects {
    apply(plugin = "java")

    group = "com.phodal.chapi"
    version = "2.5.2"
    description =
        "Chapi is A common hierarchical abstract parser && information convertor, streamlines code analysis by converting diverse language source code into a unified abstract model, simplifying cross-language development."

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

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

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
                    url.set("https://github.com/phodal/chapi")
                    licenses {
                        license {
                            name.set("MPL 2.0")
                            url.set("https://github.com/phodal/chapi/blob/master/LICENSE")
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
                        connection.set("scm:git:git://github.com/phodal/chapi.git")
                        developerConnection.set("scm:git:ssh://github.com/phodal/chapi.git")
                        url.set("https://github.com/phodal/chapi/")
                    }
                }
            }
        }

        repositories {
            maven {
                // Updated for new Sonatype Central Portal (OSSRH sunset on June 30, 2025)
                val releasesRepoUrl = uri("https://ossrh-staging-api.central.sonatype.com/service/local/staging/deploy/maven2/")
                val snapshotsRepoUrl = uri("https://central.sonatype.com/repository/maven-snapshots/")
                url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl

                credentials {
                    username = (if (project.findProperty("sonatypeUsername") != null) project.findProperty("sonatypeUsername") else System.getenv("MAVEN_USERNAME")).toString()
                    password = (if (project.findProperty("sonatypePassword") != null) project.findProperty("sonatypePassword") else System.getenv("MAVEN_PASSWORD")).toString()
                }
            }
        }
    }

    signing {
        // Use GPG agent for signing (more secure, no password in config files)
        val signingKeyName = project.findProperty("signing.gnupg.keyName") as String? ?: System.getenv("GPG_KEY_NAME")

        if (signingKeyName != null) {
            // Use GPG agent
            useGpgCmd()
            isRequired = true
        } else {
            // Fallback to traditional signing if GPG agent is not configured
            val signingKeyId = project.findProperty("signing.keyId") as String? ?: System.getenv("GPG_KEY_ID")
            val signingPassword = project.findProperty("signing.password") as String? ?: System.getenv("GPG_PASSPHRASE")
            val signingKey = project.findProperty("signing.secretKeyRingFile") as String? ?: System.getenv("GPG_SECRET_KEY_RING_FILE")

            isRequired = signingKeyId != null && signingPassword != null && signingKey != null
        }

        sign(publishing.publications["mavenJava"])
    }

    java {
        withJavadocJar()
        withSourcesJar()
    }

    // Ensure sourcesJar and kotlinSourcesJar tasks depend on ANTLR grammar generation
    tasks.named("sourcesJar") {
        dependsOn(tasks.withType<AntlrTask>())
    }

    // Also ensure kotlinSourcesJar depends on ANTLR tasks (if it exists)
    tasks.matching { it.name == "kotlinSourcesJar" }.configureEach {
        dependsOn(tasks.withType<AntlrTask>())
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
    jacocoAggregation(project(":chapi-ast-protobuf"))
    jacocoAggregation(project(":chapi-ast-swift"))
    jacocoAggregation(project(":chapi-ast-dart"))

    jacocoAggregation(project(":chapi-parser-toml"))
    jacocoAggregation(project(":chapi-parser-cmake"))
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

// Helper function to read Maven settings.xml
fun getMavenCredentials(): Pair<String?, String?> {
    val settingsFile = file("${System.getProperty("user.home")}/.m2/settings.xml")
    if (!settingsFile.exists()) {
        return Pair(null, null)
    }

    try {
        val factory = DocumentBuilderFactory.newInstance()
        val builder = factory.newDocumentBuilder()
        val doc = builder.parse(settingsFile)
        val servers = doc.getElementsByTagName("server")
        val targetServerIds = setOf("ossrh", "central", "sonatype-nexus-staging")

        for (i in 0 until servers.length) {
            val server = servers.item(i) as Element
            val serverId = server.getElementsByTagName("id").item(0)?.textContent
            if (serverId == null || serverId !in targetServerIds) {
                continue
            }
            val username = server.getElementsByTagName("username").item(0)?.textContent
            val password = server.getElementsByTagName("password").item(0)?.textContent

            if (username != null && password != null) {
                return Pair(username, password)
            }
        }
    } catch (e: Exception) {
        logger.warn("Failed to read Maven settings.xml: ${e.message}")
    }

    return Pair(null, null)
}

val (mavenUsername, mavenPassword) = getMavenCredentials()

// Configure Nexus Publishing for new Sonatype Central Portal
// See: https://central.sonatype.org/publish/publish-portal-ossrh-staging-api/#configuration
nexusPublishing {
    repositories {
        sonatype {
            // New Central Portal API endpoints (required for accounts created after Feb 2021)
            nexusUrl.set(uri("https://ossrh-staging-api.central.sonatype.com/service/local/"))
            snapshotRepositoryUrl.set(uri("https://central.sonatype.com/repository/maven-snapshots/"))

            // Credentials: try gradle.properties, then environment variables, then Maven settings.xml
            username.set(
                project.findProperty("sonatypeUsername")?.toString()
                    ?: System.getenv("MAVEN_USERNAME")
                    ?: mavenUsername
            )
            password.set(
                project.findProperty("sonatypePassword")?.toString()
                    ?: System.getenv("MAVEN_PASSWORD")
                    ?: mavenPassword
            )
        }
    }

    // Increase timeout for publishing operations
    connectTimeout.set(Duration.ofMinutes(3))
    clientTimeout.set(Duration.ofMinutes(3))
}
