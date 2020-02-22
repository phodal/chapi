package chapi.ast.pythonast

import org.junit.jupiter.api.Test

internal class PythonAnalyserTest {
    @Test
    internal fun shouldIdentBuilderPattern() {
        val code = """
def build(): 
    p = Person()
    p.setName("Hunter").setAge(24).setSSN("111-22-3333")
"""
        val codeFile = PythonAnalyser().analysis(code, "")
        println(codeFile)
    }
}
