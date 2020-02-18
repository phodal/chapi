package chapi.ast.kotlinast

import org.junit.jupiter.api.Test

internal class KotlinFullIdentListenerTest {
    @Test
    fun shouldNotCrashForGrammarFile() {
        val code = this::class.java.getResource("/grammar/AllInOneKotlin.kt").readText()
        KotlinAnalyser().analysis(code)
    }
}
