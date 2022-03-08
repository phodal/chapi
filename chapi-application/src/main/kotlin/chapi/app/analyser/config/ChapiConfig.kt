package chapi.app.analyser.config

data class ChapiConfig(
    var language: String = "",
    var withoutTest: Boolean = false,
    // todo: use grep
    var filterRule: String = ""
    // path resolve
)

// TODO replace string value
object Language {
    const val TypeScript = "typescript"
    const val KOTLIN = "kotlin"
    const val JAVA = "java"
    const val CSharp = "c#"
}
