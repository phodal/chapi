package chapi.ast.kotlinast

import chapi.domain.core.CallType
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class KotlinFullIdentListenerTest {
    private fun analyse(code: String, fileName: String = "hello.kt") =
        KotlinAnalyser().analysis(code, fileName, AnalysisMode.Full)

    @Nested
    inner class Calls {
        @Test
        internal fun `should identify function call of individual function`() {
            val code = """
fun main() {
    println("Hello world!")
}     
"""

            val container = analyse(code)
            val calls = container.DataStructures[0].Functions[0].FunctionCalls

            assertEquals(calls.size, 1)
            assertEquals(calls[0].NodeName, "")
            assertEquals(calls[0].FunctionName, "println")
            assertEquals(calls[0].Parameters.size, 1)
            assertEquals(calls[0].Parameters[0].TypeType, "kotlin.String")
            assertEquals(calls[0].Parameters[0].TypeValue, "\"Hello world!\"")
        }

        @Test
        internal fun `should identify function call of creator`() {
            val code = """
package chapi.ast.kotlinast
     
class Person(val name: String)

fun main() {
    val person = Person("John")
    println(person.name)
}
"""

            val container = analyse(code)
            val calls = container.DataStructures[0].Functions[0].FunctionCalls

            assertEquals(calls[0].Package, "chapi.ast.kotlinast")
            assertEquals(calls[0].FunctionName, "Person")
            assertEquals(calls[0].Type, CallType.CREATOR)
            assertEquals(calls[0].Parameters[0].TypeType, "kotlin.String")
            assertEquals(calls[0].Parameters[0].TypeValue, "\"John\"")

            assertEquals(calls[1].Package, "")
            assertEquals(calls[1].FunctionName, "println")
            assertEquals(calls[1].Type, CallType.FUNCTION)
            assertEquals(calls[1].Parameters[0].TypeType, "")
            assertEquals(calls[1].Parameters[0].TypeValue, "person.name")
        }

        @Test
        internal fun `should identify function call of creator after main`() {
            val code = """
package chapi.ast.kotlinast
    
fun main() {
    val person = Person("John")
    println(person.name)
}

class Person(val name: String)
"""

            val container = analyse(code)
            val calls = container.DataStructures[0].Functions[0].FunctionCalls

            assertEquals(calls[0].Package, "chapi.ast.kotlinast")
            assertEquals(calls[0].FunctionName, "Person")
            assertEquals(calls[0].Type, CallType.CREATOR)
            assertEquals(calls[0].Parameters[0].TypeType, "kotlin.String")
            assertEquals(calls[0].Parameters[0].TypeValue, "\"John\"")

            assertEquals(calls[1].Package, "")
            assertEquals(calls[1].FunctionName, "println")
            assertEquals(calls[1].Type, CallType.FUNCTION)
            assertEquals(calls[1].Parameters[0].TypeType, "")
            assertEquals(calls[1].Parameters[0].TypeValue, "person.name")
        }

        @Test
        internal fun `should identify function call of depended class`() {
            val code = """
package chapi.ast.kotlinast
     
import hello.Mouth

class Person(private val mouth: Mouth) {
    fun sayHello() {
        mouth.say("Hello world!")
    }
}
"""

            val container = analyse(code)
            // function 0 is constructor
            val calls = container.DataStructures[0].Functions[1].FunctionCalls

            assertEquals(calls[0].Package, "hello")
            assertEquals(calls[0].FunctionName, "say")
            assertEquals(calls[0].Type, CallType.FUNCTION)
            assertEquals(calls[0].NodeName, "Mouth")
            assertEquals(calls[0].Parameters[0].TypeType, "kotlin.String")
            assertEquals(calls[0].Parameters[0].TypeValue, "\"Hello world!\"")
        }

        @Test
        internal fun `should identify function call of imported object`() {
            val code = """
package chapi.ast.kotlinast
     
import hello.Mouth

class Person(private val mouth: Mouth) {
    fun sayHello() {
        Mouth.say("Hello world!")
    }
}
"""

            val container = analyse(code)
            val calls = container.DataStructures[0].Functions[1].FunctionCalls

            assertEquals(calls[0].Package, "hello")
            assertEquals(calls[0].FunctionName, "say")
            assertEquals(calls[0].Type, CallType.FUNCTION)
            assertEquals(calls[0].NodeName, "Mouth")
            assertEquals(calls[0].Parameters[0].TypeType, "kotlin.String")
            assertEquals(calls[0].Parameters[0].TypeValue, "\"Hello world!\"")
        }

        @Test
        internal fun `should identify function call of imported function`() {
            val code = """
package chapi.ast.kotlinast
     
import hello.Mouth.say

class Person(private val mouth: Mouth) {
    fun sayHello() {
        say("Hello world!")
    }
}
"""

            val container = analyse(code)
            val calls = container.DataStructures[0].Functions[1].FunctionCalls

            assertEquals(calls[0].Package, "hello")
            assertEquals(calls[0].FunctionName, "say")
            assertEquals(calls[0].Type, CallType.FUNCTION)
            assertEquals(calls[0].NodeName, "Mouth")
            assertEquals(calls[0].Parameters[0].TypeType, "kotlin.String")
            assertEquals(calls[0].Parameters[0].TypeValue, "\"Hello world!\"")
        }

        @Test
        internal fun `should identify function call for RestTemplate`() {
            val code = """
package chapi.ast.kotlinast
     
import org.springframework.web.client.RestTemplate


@Component
class QualityGateClientImpl(@Value val baseUrl: String) : QualityGateClient {
    override fun getQualityGate(qualityGateName: String): CouplingQualityGate {
        RestTemplate().getForObject("/api/quality-gate-profile/mcc", CouplingQualityGate::class.java)
    }
}
"""

            val container = analyse(code)
            val calls = container.DataStructures[0].Functions[1].FunctionCalls

            assertEquals(calls[0].Package, "org.springframework.web.client.RestTemplate")
            assertEquals(calls[0].NodeName, "RestTemplate")
        }

        @Test
        @Disabled
        internal fun `multiple call for testing`() {
            val code = """
package com.thoughtworks.archguard.packages.domain
     
import org.springframework.web.client.RestTemplate

class PackageStore {
    fun addEdge(a: String, b: String, num: Int) {
        val aId = getNodeId(a)
        val bId = getNodeId(b)
        packageEdges.add(PackageEdge(aId, bId, num))
    }
}
"""

            val container = analyse(code)
            val calls = container.DataStructures[0].Functions[0].FunctionCalls

            assertEquals(4, calls.size)
        }
    }
}
