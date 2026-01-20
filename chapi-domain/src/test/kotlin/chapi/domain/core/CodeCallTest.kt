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
            IsOptional = true
        )
        
        assertTrue(call.IsOptional)
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
        assertFalse(oldStyleCall.IsOptional)
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

    // ==================== Tests for New Structured Fields (Since 2.4.0) ====================

    @Test
    fun `should handle TypeScript promise chain`() {
        // axios.get("/api").then(res => res.data).catch(handleError)
        val call = CodeCall(
            ReceiverExpr = "axios",
            FunctionName = "get",
            Chain = listOf("then", "catch"),
            Parameters = listOf(CodeProperty(TypeValue = "\"/api\"", TypeType = "string"))
        )

        assertEquals("axios", call.ReceiverExpr)
        assertEquals("get", call.FunctionName)
        assertEquals(listOf("then", "catch"), call.Chain)
        assertTrue(call.isChainedCall())
        assertEquals(3, call.chainLength())
        assertEquals("catch", call.lastMethodName())
        assertEquals(listOf("get", "then", "catch"), call.allMethodNames())
        assertEquals("axios.get().then().catch()", call.buildChainString())
    }

    @Test
    fun `should handle optional chaining call`() {
        // user?.profile?.getName()
        val call = CodeCall(
            ReceiverExpr = "user?.profile",
            FunctionName = "getName",
            IsOptional = true
        )

        assertEquals("user?.profile", call.ReceiverExpr)
        assertEquals("getName", call.FunctionName)
        assertTrue(call.IsOptional)
        assertFalse(call.isChainedCall())
    }

    @Test
    fun `should handle Go fluent API chain`() {
        // v1.Group("/api").GET("/users", handler)
        val call = CodeCall(
            ReceiverExpr = "v1",
            OriginNodeName = "v1",  // backward compatibility
            FunctionName = "Group",
            Chain = listOf("GET"),
            ReceiverType = CodeTypeRef.simple("RouterGroup")
        )

        assertEquals("v1", call.ReceiverExpr)
        assertEquals("v1", call.OriginNodeName)
        assertEquals("Group", call.FunctionName)
        assertEquals(listOf("GET"), call.Chain)
        assertEquals("RouterGroup", call.ReceiverType?.name)
        assertEquals("v1.Group().GET()", call.buildChainString())
    }

    @Test
    fun `should handle Rust async chain`() {
        // client.get(url).send().await?.json::<T>().await?
        val call = CodeCall(
            ReceiverExpr = "client",
            FunctionName = "get",
            Chain = listOf("send", "json"),
            ReceiverType = CodeTypeRef.simple("reqwest::Client"),
            ReturnType = CodeTypeRef.generic("Result", CodeTypeRef.simple("T"), CodeTypeRef.simple("Error"))
        )

        assertEquals("client", call.ReceiverExpr)
        assertEquals("get", call.FunctionName)
        assertEquals(listOf("send", "json"), call.Chain)
        assertEquals("reqwest::Client", call.ReceiverType?.name)
        assertEquals(TypeRefKind.GENERIC, call.ReturnType?.kind)
    }

    @Test
    fun `should handle simple function call without receiver`() {
        // println("hello")
        val call = CodeCall(
            FunctionName = "println",
            Parameters = listOf(CodeProperty(TypeValue = "\"hello\"", TypeType = "string"))
        )

        assertEquals("", call.ReceiverExpr)
        assertEquals("println", call.FunctionName)
        assertFalse(call.isChainedCall())
        assertEquals(1, call.chainLength())
        assertEquals("println", call.lastMethodName())
    }

    @Test
    fun `should handle chain with arguments`() {
        // builder.setName("Alice").setAge(30).build()
        val call = CodeCall(
            ReceiverExpr = "builder",
            FunctionName = "setName",
            Parameters = listOf(CodeProperty(TypeValue = "\"Alice\"", TypeType = "String")),
            Chain = listOf("setAge", "build"),
            ChainArguments = listOf(
                listOf(CodeProperty(TypeValue = "30", TypeType = "int")),
                listOf()  // build() has no arguments
            )
        )

        assertEquals("builder", call.ReceiverExpr)
        assertEquals("setName", call.FunctionName)
        assertEquals(listOf("setAge", "build"), call.Chain)
        assertEquals(2, call.ChainArguments.size)
        assertEquals("30", call.ChainArguments[0][0].TypeValue)
        assertTrue(call.ChainArguments[1].isEmpty())
    }

    @Test
    fun `should fall back to NodeName when ReceiverExpr is empty`() {
        // Legacy format: using NodeName instead of ReceiverExpr
        val call = CodeCall(
            NodeName = "System.out",
            FunctionName = "println"
        )

        assertEquals("", call.ReceiverExpr)
        assertEquals("System.out", call.NodeName)
        // buildChainString should use NodeName as fallback
        assertEquals("System.out.println()", call.buildChainString())
    }

    @Test
    fun `should handle all method names for non-chained call`() {
        val call = CodeCall(FunctionName = "doSomething")

        assertEquals(listOf("doSomething"), call.allMethodNames())
        assertEquals("doSomething", call.lastMethodName())
        assertFalse(call.isChainedCall())
    }
}
