package chapi.ast.kotlinast

import org.junit.jupiter.api.Test

internal class KotlinAnalyserTest {
    @Test
    fun `should not crash for grammar file in basic identifier mode`() {
        val fileName = "AllInOneKotlin.kt"
        val code = readResource(fileName).readText()
        val codeContainer = KotlinAnalyser().analysis(code, fileName, AnalysisMode.Basic)

        kotlin.test.assertEquals(codeContainer.FullName, fileName)
    }

    @Test
    fun `should not crash for grammar file in full identifier mode`() {
        val fileName = "AllInOneKotlin.kt"
        val code = readResource(fileName).readText()
        val codeContainer = KotlinAnalyser().analysis(code, fileName, AnalysisMode.Full)

        kotlin.test.assertEquals(codeContainer.FullName, fileName)
    }

    @Suppress("SameParameterValue")
    private fun readResource(fileName: String) = this::class.java.getResource("/grammar/$fileName")!!
}
