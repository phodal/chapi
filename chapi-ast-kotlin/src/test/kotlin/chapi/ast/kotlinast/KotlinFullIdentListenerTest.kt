package chapi.ast.kotlinast

import chapi.domain.core.CallType
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
            assertEquals(calls[0].Parameters[0].TypeValue, "Hello world!")
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
            assertEquals(calls[0].Parameters[0].TypeValue, "John")

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
            assertEquals(calls[0].Parameters[0].TypeValue, "John")

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
            assertEquals(calls[0].Parameters[0].TypeValue, "Hello world!")
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
            assertEquals(calls[0].Parameters[0].TypeValue, "Hello world!")
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
            assertEquals(calls[0].Parameters[0].TypeValue, "Hello world!")
        }

        @Test
        internal fun `should identify function call for RestTemplate`() {
            val code = """
package chapi.ast.kotlinast
     
import org.springframework.web.client.RestTemplate


@Component
class QualityGateClientImpl(@CustomValueType val baseUrl: String) : QualityGateClient {
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
        internal fun `should identify function call with parameters for RestTemplate`() {
            val code = """
package chapi.ast.kotlinast
     
import org.springframework.web.client.RestTemplate

@Component
class QualityGateClientImpl(@CustomValueType("\${'$'}{client.host}") val baseUrl: String) : QualityGateClient {
    override fun getQualityGate(qualityGateName: String): CouplingQualityGate {
        val couplingQualityGate = RestTemplate().getForObject(baseUrl + "/api/quality-gate-profile/${'$'}qualityGateName", CouplingQualityGate::class.java)
        return couplingQualityGate ?: CouplingQualityGate(null, qualityGateName, emptyList(), null, null)
    }
}
"""

            val container = analyse(code)
            val calls = container.DataStructures[0].Functions[1].FunctionCalls

            assertEquals(calls[0].Package, "org.springframework.web.client.RestTemplate")
            assertEquals(calls[0].NodeName, "RestTemplate")
        }

        @Test
        internal fun `multiple call for testing`() {
            val code = """
package com.thoughtworks.archguard.packages.domain
     
import org.springframework.web.client.RestTemplate

class PackageStore {
    fun addEdge(a: String, b: String, num: IntValue) {
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

        @Test
        internal fun should_inline_variable() {
            val code = """
package com.thoughtworks.archguard.packages.domain
     
import org.springframework.web.client.RestTemplate

class SizingRepositoryImpl(val jdbi: Jdbi) : SizingRepository {
    override fun getClassSizingListAboveMethodCountThresholdCount(systemId: Long, threshold: IntValue): Long {
        return jdbi.withHandle<Long, Exception> {
            val table = "select count(name) as count from code_class where system_id = :systemId and is_test=false and loc is not NULL " +
                    "group by class_name " +
                    "having count>:threshold "
            val sql = "select count(1) from (${'$'}table) as c"
            it.createQuery(sql)
                    .bind("systemId", systemId)
                    .bind("threshold", threshold)
                    .mapTo(Long::class.java)
                    .findOne()
                    .orElse(0)
        }
    }
}
"""

            val container = analyse(code)
            val calls = container.DataStructures[0].Functions[1].FunctionCalls


            assertEquals(10, calls.size)
            assertEquals(true, calls[2].Parameters[0].TypeValue.contains("select count(1)") && calls[2].Parameters[0].TypeValue.contains("select count(name)"))
        }

        @Test
        internal fun `should inline variable to parameter`() {
            val code = """
package com.thoughtworks.archguard.packages.domain
     
import org.springframework.web.client.RestTemplate

@Repository
class SystemOverviewRepositoryImpl(val jdbi: Jdbi) : SystemOverviewRepository {
    fun getSystemInfoRepoBySystemId(systemId: Long): String {
        return jdbi.withHandle<String, Exception> {
            val sql = ""${'"'}
                select repo from system_info where id = :id
            ""${'"'}.trimIndent()
            it.createQuery(sql)
                    .bind("id", systemId)
                    .mapTo(String::class.java)
                    .one()
        }
    }
}
"""

            val container = analyse(code)
            val calls = container.DataStructures[0].Functions[1].FunctionCalls

            var hasCatchVariable = false;
            calls.forEach {
                if (it.FunctionName == "createQuery") {
                    if (it.Parameters[0].TypeValue.contains("select repo from system_info where id = :id")) {
                        hasCatchVariable = true
                    }
                }
            }

            assert(hasCatchVariable)
            assertEquals(9, calls.size)
        }

        @Test
        internal fun `should support for annotation in property`() {
            val code = """
class BadSmellScanner(@Autowired val badSmellRepo: BadSmellRepo) {}
"""

            val container = analyse(code)
            val fields = container.DataStructures[0].Fields
            assertEquals(1, fields.size)
            assertEquals("Autowired", fields[0].Annotations[0].Name)
        }
    }
}
