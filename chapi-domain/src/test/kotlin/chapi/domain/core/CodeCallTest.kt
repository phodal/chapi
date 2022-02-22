package chapi.domain.core

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class CodeCallTest {
    @Test
    internal fun shouldBuildFullName() {
        val fullName = CodeCall(Package = "package", NodeName = "nodeName").buildClassFullName()
        assertEquals(fullName, "package.nodeName")
    }

    @Test
    internal fun shouldBuildFullMethodName_WhenIsConstructor() {
        val fullMethodName =
            CodeCall(Package = "package", NodeName = "nodeName", FunctionName = "")
                .buildFullMethodName()

        assertEquals(fullMethodName, "package.nodeName")
    }

    @Test
    internal fun shouldBuildFullMethodName_WhenIsNormalMethod() {
        val fullMethodName =
            CodeCall(
                Package = "package",
                NodeName = "nodeName",
                FunctionName = "method"
            ).buildFullMethodName()

        assertEquals(fullMethodName, "package.nodeName.method")
    }

    @Test
    internal fun shouldHandleSystemOutput() {
        val isSystemOutput = CodeCall(
            NodeName = "System.out",
            FunctionName = "println"
        ).isSystemOutput()

        assertEquals(isSystemOutput, true)
    }

    @Test
    internal fun shouldHandleSleep() {
        val isSleep = CodeCall(NodeName = "Thread", FunctionName = "sleep").isThreadSleep()

        assertEquals(isSleep, true)
    }

    @Test
    internal fun shouldHandleHasAssertion() {
        assertEquals(CodeCall(FunctionName = "assertEquals").hasAssertion(), true)
        assertEquals(CodeCall(FunctionName = "should").hasAssertion(), true)
    }

    @Test
    internal fun shouldReturnFalseWhenNotAssertion() {
        assertEquals(CodeCall(FunctionName = "get").hasAssertion(), false)
    }
}
