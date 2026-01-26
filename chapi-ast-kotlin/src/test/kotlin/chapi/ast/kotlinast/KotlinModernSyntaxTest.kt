package chapi.ast.kotlinast

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Tests for modern Kotlin syntax features:
 * - Range until operator (..<) - Kotlin 1.7.20+
 * - Definitely non-nullable types (T & Any) - Kotlin 1.7+
 * - Data objects - Kotlin 1.9+
 * - Suspend anonymous functions
 */
internal class KotlinModernSyntaxTest {
    private fun analyse(code: String, fileName: String = "modern.kt") =
        KotlinAnalyser().analysis(code, fileName, AnalysisMode.Basic)

    @Nested
    inner class RangeUntilOperator {
        @Test
        fun `should parse range until operator`() {
            val code = """
package test

fun rangeUntilExample() {
    for (i in 0..<10) {
        println(i)
    }
}
"""
            val codeContainer = analyse(code)
            assertEquals(1, codeContainer.DataStructures.size)
            assertEquals("rangeUntilExample", codeContainer.DataStructures[0].Functions[0].Name)
        }

        @Test
        fun `should parse range until in variable assignment`() {
            val code = """
package test

class RangeTest {
    fun getRange(): IntRange {
        return 0..<100
    }
}
"""
            val codeContainer = analyse(code)
            assertEquals("RangeTest", codeContainer.DataStructures[0].NodeName)
            assertEquals("getRange", codeContainer.DataStructures[0].Functions[0].Name)
        }
    }

    @Nested
    inner class DefinitelyNonNullableTypes {
        @Test
        fun `should parse definitely non-nullable type`() {
            val code = """
package test

fun <T> elvisLike(x: T, y: T & Any): T & Any = x ?: y
"""
            val codeContainer = analyse(code)
            assertEquals(1, codeContainer.DataStructures.size)
            assertEquals("elvisLike", codeContainer.DataStructures[0].Functions[0].Name)
        }

        @Test
        fun `should parse definitely non-nullable type in class`() {
            val code = """
package test

class NonNullableProcessor<T> {
    fun process(value: T & Any): T & Any {
        return value
    }
}
"""
            val codeContainer = analyse(code)
            assertEquals("NonNullableProcessor", codeContainer.DataStructures[0].NodeName)
        }
    }

    @Nested
    inner class DataObjects {
        @Test
        fun `should parse data object declaration`() {
            val code = """
package test

data object MySingleton {
    val name = "Singleton"
}
"""
            val codeContainer = analyse(code)
            assertEquals(1, codeContainer.DataStructures.size)
            assertEquals("MySingleton", codeContainer.DataStructures[0].NodeName)
        }

        @Test
        fun `should parse data object in sealed hierarchy`() {
            val code = """
package test

sealed interface Status

data object Loading : Status
data class Success(val data: String) : Status
data object Error : Status
"""
            val codeContainer = analyse(code)
            assertTrue(codeContainer.DataStructures.size >= 1)
            // Should contain Status, Loading, Success, and Error
            val nodeNames = codeContainer.DataStructures.map { it.NodeName }
            assertTrue(nodeNames.contains("Status"))
        }
    }

    @Nested
    inner class SuspendAnonymousFunctions {
        @Test
        fun `should parse suspend anonymous function`() {
            val code = """
package test

import kotlinx.coroutines.delay

class CoroutineExample {
    val suspendFunc = suspend fun() {
        delay(1000)
    }
}
"""
            val codeContainer = analyse(code)
            assertEquals("CoroutineExample", codeContainer.DataStructures[0].NodeName)
        }

        @Test
        fun `should parse suspend anonymous function with receiver`() {
            val code = """
package test

class SuspendBuilder {
    val block = suspend fun String.(): Int {
        return this.length
    }
}
"""
            val codeContainer = analyse(code)
            assertEquals("SuspendBuilder", codeContainer.DataStructures[0].NodeName)
        }
    }

    @Nested
    inner class ValueClasses {
        @Test
        fun `should parse value class`() {
            val code = """
package test

@JvmInline
value class Password(private val s: String)
"""
            val codeContainer = analyse(code)
            assertEquals(1, codeContainer.DataStructures.size)
            assertEquals("Password", codeContainer.DataStructures[0].NodeName)
        }
    }

    @Nested
    inner class SuspendFunctionTypes {
        @Test
        fun `should parse suspend function type in delegation`() {
            val code = """
package test

interface SuspendAction {
    suspend fun execute()
}

class SuspendActionImpl : SuspendAction {
    override suspend fun execute() {
        println("Executing")
    }
}
"""
            val codeContainer = analyse(code)
            assertTrue(codeContainer.DataStructures.size >= 1)
        }
    }
}
