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
}
