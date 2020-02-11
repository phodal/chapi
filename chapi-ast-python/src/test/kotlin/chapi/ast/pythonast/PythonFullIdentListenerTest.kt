package chapi.ast.pythonast

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class PythonFullIdentListenerTest {
    @Test
    internal fun shouldAnalysisPython2() {
        val python2HelloWorld = """print("Hello, World!")"""

        PythonAnalyser().analysis(python2HelloWorld, "")
    }

    @Test
    internal fun shouldAnalysisPython3() {
        val py3HelloWorld = """print "Hello, World!""""
        PythonAnalyser().analysis(py3HelloWorld, "")
    }

    @Test
    internal fun shouldIdentClass() {
        val code = """class foo: pass"""
        val codeFile = PythonAnalyser().analysis(code, "")

        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].NodeName, "foo")
    }
}
