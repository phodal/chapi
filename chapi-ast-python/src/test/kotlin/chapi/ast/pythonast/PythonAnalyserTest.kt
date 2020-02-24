package chapi.ast.pythonast

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class PythonAnalyserTest {
    @Test
    internal fun shouldIdentBuilderPatternSize() {
        val code = """
def build(): 
    p = Person()
    p.setName("Hunter").setAge(24).setSSN("111-22-3333")
"""
        val codeFile = PythonAnalyser().analysis(code, "")
        val firstFunc = codeFile.DataStructures[0].Functions[0]
        assertEquals(firstFunc.FunctionCalls.size, 3)
//        assertEquals(firstFunc.FunctionCalls[0].FunctionName, "setName")
//        assertEquals(firstFunc.FunctionCalls[1].FunctionName, "setAge")
//        assertEquals(firstFunc.FunctionCalls[2].FunctionName, "setSSN")
    }
}
