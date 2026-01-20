package chapi.domain.core

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class CodeCallTest {
    @Test
    internal fun shouldBuildFullName() {
        val fullName = CodeCall(Package = "package", NodeName = "nodeName").buildClassFullName()
        assertEquals(fullName, "package.nodeName")
    }

    // ==================== Tests for new structured fields (since 2.4.0) ====================

    @Test
    internal fun shouldSupportStructuredCallFields() {
        val call = CodeCall(
            Package = "com.example",
            NodeName = "HttpClient",
            FunctionName = "get",
            ReceiverExpr = "client",
            ReceiverType = CodeTypeRef.simple("HttpClient"),
            Callee = "get",
            Chain = listOf("get", "then", "catch")
        )
        
        assertEquals("client", call.ReceiverExpr)
        assertEquals("HttpClient", call.ReceiverType?.name)
        assertEquals("get", call.Callee)
        assertEquals(listOf("get", "then", "catch"), call.Chain)
    }

    @Test
    internal fun shouldSupportOptionalChaining() {
        val call = CodeCall(
            FunctionName = "profile",
            ReceiverExpr = "user",
            Callee = "profile",
            IsOptionalChain = true
        )
        
        assertTrue(call.IsOptionalChain)
        assertEquals("profile", call.Callee)
        assertEquals("user", call.ReceiverExpr)
    }

    @Test
    internal fun shouldSupportChainedCallsWithTypeRef() {
        val call = CodeCall(
            FunctionName = "axios->then->catch",
            ReceiverExpr = "axios.get('/api')",
            ReceiverType = CodeTypeRef.generic(
                "Promise",
                CodeTypeRef.simple("Response")
            ),
            Callee = "catch",
            Chain = listOf("get", "then", "catch")
        )
        
        assertEquals("axios.get('/api')", call.ReceiverExpr)
        assertEquals("Promise", call.ReceiverType?.name)
        assertEquals(1, call.ReceiverType?.args?.size)
        assertEquals("Response", call.ReceiverType?.args?.get(0)?.name)
        assertEquals("catch", call.Callee)
        assertEquals(3, call.Chain.size)
    }

    @Test
    internal fun shouldMaintainBackwardCompatibility() {
        // Old-style CodeCall creation should still work
        val oldStyleCall = CodeCall(
            Package = "com.example",
            Type = CallType.FUNCTION,
            NodeName = "Service",
            FunctionName = "doSomething",
            Parameters = listOf(CodeProperty(TypeValue = "arg1", TypeType = "String")),
            Position = CodePosition(StartLine = 10, StopLine = 10)
        )
        
        assertEquals("com.example", oldStyleCall.Package)
        assertEquals("Service", oldStyleCall.NodeName)
        assertEquals("doSomething", oldStyleCall.FunctionName)
        assertEquals("com.example.Service.doSomething", oldStyleCall.buildFullMethodName())
        
        // New fields should have default values
        assertEquals("", oldStyleCall.ReceiverExpr)
        assertNull(oldStyleCall.ReceiverType)
        assertEquals("", oldStyleCall.Callee)
        assertFalse(oldStyleCall.IsOptionalChain)
        assertTrue(oldStyleCall.Chain.isEmpty())
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

    @Test
    internal fun testEquals() {
        assertEquals(CodeCall(FunctionName = "get"), CodeCall(FunctionName = "get"))
    }
}
