package chapi.ast.cast

import org.junit.jupiter.api.Test

internal class CIdentAppTest {
    @Test
    fun shouldAnalysis() {
val helloWorld = """
main()
{
    printf("Hello World");
}
"""
        CIdentApp().analysis(helloWorld)
    }
}
