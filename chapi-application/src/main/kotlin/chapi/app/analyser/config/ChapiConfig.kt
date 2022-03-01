package chapi.app.analyser.config

data class ChapiConfig(
    var language: String = "",
    var withoutTest: Boolean = false,
    var filterRule: String = ""
)

// TODO replace string value
object Language {
    const val KOTLIN = "kotlin"
    const val JAVA = "java"
}
