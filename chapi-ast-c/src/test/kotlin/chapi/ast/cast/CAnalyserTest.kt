package chapi.ast.cast

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class CAnalyserTest {
    @Test
    fun shouldAnalysis() {
        val helloWorld = """
main()
{
    printf("Hello World");
}
"""
        CAnalyser().analysis(helloWorld, "")
    }

    @Test
    internal fun shouldGetCodeFileName() {
        val code = this::class.java.getResource("/_fixtures/examples/1-helloworld.c").readText()
        val fileName = "helloworld.c"

        val codeFile = CAnalyser().analysis(code, fileName)

        assertEquals(codeFile.FullName, fileName)
    }
}
