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
        assertEquals(firstFunc.FunctionCalls[0].FunctionName, "setName")
        assertEquals(firstFunc.FunctionCalls[1].FunctionName, "setAge")
        assertEquals(firstFunc.FunctionCalls[2].FunctionName, "setSSN")
    }

    @Test
    internal fun shouldPrintFunCall() {
        val code = """
async def show(str):
    print(str)
"""

        val codeFile = PythonAnalyser().analysis(code, "")
        val firstFunc = codeFile.DataStructures[0].Functions[0]
        assertEquals(firstFunc.FunctionCalls.size, 1)
        assertEquals(firstFunc.FunctionCalls[0].FunctionName, "print")
        assertEquals(firstFunc.FunctionCalls[0].NodeName, "")
    }

    @Test
    internal fun shouldIdentLocalVarsForFuncCall() {
        val code = """
def build():
    p = Person()
    p.setName("Hunter").setAge(24).setSSN("111-22-3333")
"""

        val codeFile = PythonAnalyser().analysis(code, "")
        val firstFunc = codeFile.DataStructures[0].Functions[0]
        assertEquals(firstFunc.LocalVariables.size, 1)
        assertEquals(firstFunc.LocalVariables[0].TypeValue, "p")
        assertEquals(firstFunc.LocalVariables[0].TypeType, "Person")
    }

    @Test
    internal fun shouldCaptureUnpackingOperatorInFunctionCall() {
        val code = """
def process_codebase_summary(self, unoplat_codebase, codebase_summary):
    codebase_node_dict = [
        {
            "qualified_name": codebase_summary.codebase_name,
            "objective": codebase_summary.codebase_objective,
        }
    ]

    ConfluenceCodebase.create_or_update(*codebase_node_dict)
"""

        val codeFile = PythonAnalyser().analysis(code, "")
        val firstFunc = codeFile.DataStructures[0].Functions[0]
        assertEquals(firstFunc.FunctionCalls.size, 1)
        assertEquals(firstFunc.FunctionCalls[0].FunctionName, "create_or_update")
        assertEquals(firstFunc.FunctionCalls[0].NodeName, "ConfluenceCodebase")
        assertEquals(firstFunc.FunctionCalls[0].Parameters.size, 1)
        assertEquals(firstFunc.FunctionCalls[0].Parameters[0].TypeValue, "*codebase_node_dict")
    }

    @Test
    internal fun shouldCaptureRegularFunctionCallParameters() {
        val code = """
def test_func():
    result = some_function(arg1, arg2, key=value)
"""

        val codeFile = PythonAnalyser().analysis(code, "")
        val firstFunc = codeFile.DataStructures[0].Functions[0]
        assertEquals(firstFunc.FunctionCalls.size, 1)
        assertEquals(firstFunc.FunctionCalls[0].FunctionName, "some_function")
        assertEquals(firstFunc.FunctionCalls[0].Parameters.size, 3)
        assertEquals(firstFunc.FunctionCalls[0].Parameters[0].TypeValue, "arg1")
        assertEquals(firstFunc.FunctionCalls[0].Parameters[1].TypeValue, "arg2")
        assertEquals(firstFunc.FunctionCalls[0].Parameters[2].TypeValue, "key=value")
    }

    @Test
    internal fun shouldCaptureDoubleUnpackingOperatorInFunctionCall() {
        val code = """
def test_func():
    result = some_function(**kwargs_dict)
"""

        val codeFile = PythonAnalyser().analysis(code, "")
        val firstFunc = codeFile.DataStructures[0].Functions[0]
        assertEquals(firstFunc.FunctionCalls.size, 1)
        assertEquals(firstFunc.FunctionCalls[0].FunctionName, "some_function")
        assertEquals(firstFunc.FunctionCalls[0].Parameters.size, 1)
        assertEquals(firstFunc.FunctionCalls[0].Parameters[0].TypeValue, "**kwargs_dict")
    }
}
