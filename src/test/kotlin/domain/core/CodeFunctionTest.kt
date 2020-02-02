package domain.core

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class CodeFunctionTest {
    @Test
    internal fun shouldHandleJavaReturnType() {
        assertEquals(CodeFunction(ReturnType = "String").isJavaLangReturnType(), true)
    }

    @Test
    internal fun shouldHandleStaticModifiers() {
        var modifiers: Array<String> = arrayOf()
        modifiers += "static"

        assertEquals(CodeFunction(Modifiers = modifiers).isStatic(), true)
    }

    @Test
    internal fun shouldHandleGetterAndSetter() {
        assertEquals(CodeFunction(Name = "getFunc").isGetterSetter(), true)
        assertEquals(CodeFunction(Name = "setFunc").isGetterSetter(), true)
    }

    @Test
    internal fun shouldEnableBuildFullName() {
        val dataStruct = CodeDataStruct(Package = "package", NodeName = "nodeName")

        val fullMethodName = CodeFunction(Name = "getFunc").buildFullMethodName(dataStruct)

        assertEquals(fullMethodName, "package.nodeName.getFunc")
    }

    @Test
    internal fun shouldGetAllCallStrings() {
        var calls = arrayOf<CodeCall>()
        calls += CodeCall(Package = "package", NodeName = "nodeName")

        val allCallString = CodeFunction(Name = "getFunc", FunctionCalls = calls).getAllCallString()

        assertEquals(allCallString.size, 1)
        assertEquals(allCallString[0], "package.nodeName")
    }


    @Test
    internal fun shouldHandleJunitTest() {
        var annotations = arrayOf<CodeAnnotation>()
        annotations += CodeAnnotation(Name = "Test")

        val isTest = CodeFunction(Name = "getFunc", Annotations = annotations).isJUnitTest()

        assertEquals(isTest, true)
    }
}
