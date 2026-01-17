plugins {
    jacoco
}

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
        xml.required.set(false)
        csv.required.set(false)
        html.required.set(true)
        html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
    }
}
