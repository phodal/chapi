package chapi.app.analyser.filter

import chapi.app.analyser.config.ChapiConfig
import chapi.app.analyser.config.Language

open class FileFilter {
    companion object {
        fun filterByLanguage(path: String, config: ChapiConfig): Boolean {
            if (config.filterRule.isNotEmpty()) {
                return config.filterRule.toRegex().matches(path)
            }

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
                    path.endsWith(".ts") || path.endsWith(".tsx")
                }
                "scala" -> {
                    path.endsWith(".scala")
                }
                Language.CSharp -> {
                    path.endsWith(".cs")
                }
                Language.KOTLIN -> {
                    path.endsWith(".kt") || path.endsWith(".kts")
                }
                else -> {
                    path.endsWith(".java")
                }
            }
        }
    }
}
