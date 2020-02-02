package domain.core

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class CodeFunctionTest {
    @Test
    internal fun shouldHandleJavaReturnType() {
        assertEquals(CodeFunction<String>(ReturnType = "String").isJavaLangReturnType(), true)
    }
}
