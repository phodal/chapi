package chapi.app.analyser.config

data class ChapiConfig(
    var language: String = "",
    var withoutTest: Boolean = false,
    // todo: change to grep
    var filterRule: String = "",
    // path resolve
    var ignoreMinFile: Boolean = true
)

// TODO replace string value
object Language {
    const val TypeScript = "typescript"
    const val KOTLIN = "kotlin"
    const val JAVA = "java"
    const val CSharp = "c#"
}
