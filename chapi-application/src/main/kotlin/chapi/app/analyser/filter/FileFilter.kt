package chapi.app.analyser.filter

import java.io.File

open class FileFilter {
    companion object {
        fun filterByLanguage(path: String, language: String): Boolean {
            return when (language) {
                "java" -> {
                    path.endsWith(".java")
                }
                "go" -> {
                    path.endsWith(".go")
                }
                else -> {
                    path.endsWith(".java")
                }
            }
        }
    }
}
