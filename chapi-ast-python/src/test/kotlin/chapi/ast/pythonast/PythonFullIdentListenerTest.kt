package chapi.ast.pythonast

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class PythonFullIdentListenerTest {
    @Test
    internal fun shouldAnalysisPython2() {
        val python2HelloWorld = """
print("Hello, World!")
        """

        PythonAnalyser().analysis(python2HelloWorld, "")
    }

    @Test
    internal fun shouldAnalysisPython3() {
        val py3HelloWorld = """
print "Hello, World!"
        """
        PythonAnalyser().analysis(py3HelloWorld, "")
    }
}
