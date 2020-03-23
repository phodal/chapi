package chapi.ast.typescriptast;

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class TypeScriptCallTest {

    @Test
    internal fun shouldIdentifyNewObjectCall() {
        val code = """
function testNew() {
  let emp = new Employee(100,"Steve");
  emp.display();
}
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "")
        assertEquals(codeFile.DataStructures.size, 1)
        val functionCalls = codeFile.DataStructures[0].Functions[0].FunctionCalls
        assertEquals(functionCalls.size, 1)
        assertEquals(functionCalls[0].FunctionName, "display")
        assertEquals(functionCalls[0].NodeName, "Employee")
        assertEquals(functionCalls[0].Parameters.size, 0)
    }
}
