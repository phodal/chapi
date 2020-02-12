plugins {
    jacoco
}
//
//val jacocoCoverageVerification by tasks.getting(JacocoCoverageVerification::class) {
//    afterEvaluate {
//        classDirectories.setFrom(files(classDirectories.files.map {
//            fileTree(it).apply {
//                exclude("chapi/ast/antlr")
//            }
//        }))
//    }
//}

val jacocoTestReport by tasks.getting(JacocoReport::class) {
    dependsOn("test")

    afterEvaluate {
        classDirectories.setFrom(files(classDirectories.files.map {
            fileTree(it).apply {
                exclude("chapi/ast/antlr")
            }
        }))
    }

    reports {
        xml.setEnabled(true)
        xml.setDestination(file("${project.buildDir}/${project.name}.xml"))
        html.setEnabled(true)
    }

}

configurations.create("jacocoReports") {
    isCanBeResolved = false
    isCanBeConsumed = true
    attributes {
        attribute(Usage.USAGE_ATTRIBUTE, project.objects.named(Usage::class, "jacocoReports"))
    }
    outgoing.artifact(jacocoTestReport.reports.xml.destination) {
        builtBy(jacocoTestReport)
    }
}
