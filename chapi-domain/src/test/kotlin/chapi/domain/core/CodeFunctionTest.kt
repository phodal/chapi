package chapi.domain.core

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class CodeFunctionTest {
    @Test
    internal fun shouldHandleJavaReturnType() {
        assertEquals(CodeFunction(ReturnType = "String", IsConstructor = false).isJavaLangReturnType(), true)
    }

    @Test
    internal fun shouldHandleStaticModifiers() {
        var modifiers: Array<String> = arrayOf()
        modifiers += "static"

        assertEquals(CodeFunction(Modifiers = modifiers, IsConstructor = false).isStatic(), true)
    }

    @Test
    internal fun shouldHandleGetterAndSetter() {
        assertEquals(CodeFunction(Name = "getFunc", IsConstructor = false).isGetterSetter(), true)
        assertEquals(CodeFunction(Name = "setFunc", IsConstructor = false).isGetterSetter(), true)
    }

    @Test
    internal fun shouldEnableBuildFullName() {
        val dataStruct = CodeDataStruct(Package = "package", NodeName = "nodeName")

        val fullMethodName = CodeFunction(Name = "getFunc", IsConstructor = false).buildFullMethodName(dataStruct)

        assertEquals(fullMethodName, "package.nodeName.getFunc")
    }

    @Test
    internal fun shouldGetAllCallStrings() {
        var calls = arrayOf<CodeCall>()
        calls += CodeCall(Package = "package", NodeName = "nodeName")

        val allCallString = CodeFunction(
            Name = "getFunc",
            FunctionCalls = calls,
            IsConstructor = false
        ).getAllCallString()

        assertEquals(allCallString.size, 1)
        assertEquals(allCallString[0], "package.nodeName")
    }

    @Test
    internal fun shouldHandleJunitTest() {
        var annotations = arrayOf<CodeAnnotation>()
        annotations += CodeAnnotation(Name = "Test")

        val isTest = CodeFunction(
            Name = "getFunc",
            Annotations = annotations,
            IsConstructor = false
        ).isJUnitTest()

        assertEquals(isTest, true)
    }

    @Test
    internal fun shouldEnableAddExtension() {
        val codeFunction = CodeFunction(Name = "getFunc")
        codeFunction.addExtension("key", "value")

        val expect = HashMap<String, JsonElement>()
        expect["key"] = JsonPrimitive("value")

        assertEquals(codeFunction.Extension, expect)
    }
}
