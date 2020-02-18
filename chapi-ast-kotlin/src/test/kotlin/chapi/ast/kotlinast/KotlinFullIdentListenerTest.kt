package chapi.ast.kotlinast

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class KotlinFullIdentListenerTest {
    @Test
    fun shouldNotCrashForGrammarFile() {
        val fileName = "AllInOneKotlin.kt"
        val code = this::class.java.getResource("/grammar/$fileName").readText()
        val codeContainer = KotlinAnalyser().analysis(code, fileName)

        assertEquals(codeContainer.FullName, fileName)
    }
}
