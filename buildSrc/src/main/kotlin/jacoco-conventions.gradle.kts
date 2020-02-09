plugins {
    jacoco
}

val jacocoTestReport by tasks.getting(JacocoReport::class) {
    dependsOn("test")

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
