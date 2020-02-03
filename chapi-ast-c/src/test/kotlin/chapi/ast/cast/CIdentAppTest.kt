package chapi.ast.cast

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class CIdentAppTest {
    @Test
    fun shouldAnalysis() {
val helloWorld = """
main()
{
    printf("Hello World");
}
"""
        CIdentApp().analysis(helloWorld, "")
    }

    @Test
    internal fun shouldGetCodeFileName() {
        val code = this::class.java.getResource("/_fixtures/examples/1-helloworld.c").readText()
        val fileName = "helloworld.c"

        val codeFile = CIdentApp().analysis(code, fileName)

        assertEquals(codeFile.FullName, fileName)
    }
}
