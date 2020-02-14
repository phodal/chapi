package chapi.domain.core

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class CodeDataStructTest {
    @Test
    internal fun shouldIdentifyUtilClass() {
        assertEquals(CodeDataStruct(NodeName = "BlueUtil").isUtilClass(), true)
    }

    @Test
    internal fun shouldSuccessSetFunctionsFromMap() {
        val funcName = "testFunc"
        val codeFunction = CodeFunction(Name = funcName)
        var methodMap: MutableMap<String, CodeFunction> = mutableMapOf<String, CodeFunction>()
        methodMap[funcName] = codeFunction

        val codeDataStruct = CodeDataStruct(NodeName = "FunctionMap")
        codeDataStruct.setMethodsFromMap(methodMap)

        assertEquals(codeDataStruct.Functions.size, 1)
        assertEquals(codeDataStruct.Functions[0].Name, funcName)
    }

    @Test
    internal fun shouldSuccessGetFullName() {
        val codeDataStruct = CodeDataStruct(NodeName = "FunctionMap")

        assertEquals(codeDataStruct.getClassFullName(), ".FunctionMap")
    }
}
