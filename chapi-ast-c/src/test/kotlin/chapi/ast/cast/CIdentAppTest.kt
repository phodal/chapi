package chapi.ast.cast

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CIdentAppTest {
    @Test
    fun shouldAnalysis() {
val helloWorld = """
main()
{
    printf("Hello World");
}
"""
        CIdentApp().Analysis(helloWorld)
    }
}
