package chapi.app.analyser.testrig

import org.antlr.v4.gui.TestRig

/**
 * test the lexer and parser, and print out the result with test rig
 */
object TestRigUtil {
    enum class Language {
        Kotlin,
        Java,
    }

    enum class Mode(val value: String) {
        Tree("-tree"),
        GUI("-gui"),
        Tokens("-tokens"),
    }

    fun runTestRig(language: Language, mode: Mode, path: String) {
        TestRig.main(
            arrayOf(
                "chapi.ast.antlr.${language}",
                if (path.endsWith(".kts")) "script" else "kotlinFile",
                mode.value,
                path
            )
        )
    }
}

fun main() {
    TestRigUtil.runTestRig(
        TestRigUtil.Language.Kotlin,
        TestRigUtil.Mode.GUI,
        "chapi-ast-kotlin/src/main/kotlin/chapi/ast/kotlinast/KotlinBasicIdentListener.kt"
    )
}
