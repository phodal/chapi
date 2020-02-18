package chapi.app.analyser.filter

import chapi.app.analyser.config.ChapiConfig

open class FileFilter {
    companion object {
        fun filterByLanguage(path: String, config: ChapiConfig): Boolean {
            return when (config.language) {
                "java" -> {
                    path.endsWith(".java")
                }
                "go" -> {
                    path.endsWith(".go")
                }
                "python" -> {
                    path.endsWith(".py")
                }
                "typescript" -> {
                    path.endsWith(".ts")
                }
                "scala" -> {
                    path.endsWith(".scala")
                }
                else -> {
                    path.endsWith(".java")
                }
            }
        }
    }
}
