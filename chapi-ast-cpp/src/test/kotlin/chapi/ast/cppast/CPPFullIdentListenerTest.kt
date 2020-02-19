package chapi.ast.cppast

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class CPPFullIdentListenerTest {
    @Test
    internal fun shouldAnalysisHelloWorld() {
        val code = this::class.java.getResource("/grammar/helloworld.cpp").readText()
        val container = CPPAnalyser().analysis(code, "helloworld.cpp")
        assertEquals(container.FullName, "helloworld.cpp")
    }

    @Test
    internal fun shouldIdentFunctionName() {
        val code = this::class.java.getResource("/grammar/helloworld.cpp").readText()
        val container = CPPAnalyser().analysis(code, "helloworld.cpp")
        assertEquals(container.DataStructures.size, 1)
        assertEquals(container.DataStructures[0].Functions.size, 1)
        assertEquals(container.DataStructures[0].Functions[0].Name, "main")
    }
}
