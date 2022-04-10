package chapi.ast.csharpast

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CSharpFullIdentListenerTest {
    @Test
    fun shouldNotCrashForGrammarFile() {
        val code = this::class.java.getResource("/call/hello.cs").readText()
        val node = CSharpAnalyser().analysis(code, "hello.cs")

        assertEquals(1, node.Containers.size)
    }

}
