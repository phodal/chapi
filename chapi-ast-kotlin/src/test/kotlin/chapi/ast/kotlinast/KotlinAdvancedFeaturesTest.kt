package chapi.ast.kotlinast

import chapi.domain.core.CallType
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * 扩展的函数调用和完整分析测试，提升代码覆盖率
 */
internal class KotlinAdvancedFeaturesTest {
    private fun analyse(code: String, fileName: String = "test.kt") =
        KotlinAnalyser().analysis(code, fileName, AnalysisMode.Full)

    @Nested
    inner class AdvancedFunctionCallTests {
        @Test
        fun `should identify named parameter calls`() {
            val code = """
package test

fun greet(name: String, age: Int) {
    println("${'$'}name is ${'$'}age")
}

fun main() {
    greet(name = "Alice", age = 30)
}
"""
            val container = analyse(code)
            val calls = container.DataStructures[0].Functions.find { it.Name == "main" }?.FunctionCalls
            assertTrue(calls!!.isNotEmpty())
            assertEquals("greet", calls[0].FunctionName)
        }

        @Test
        fun `should identify chained method calls`() {
            val code = """
package test

class Builder {
    fun setName(name: String): Builder = this
    fun setAge(age: Int): Builder = this
    fun build(): String = "Built"
}

fun main() {
    val result = Builder()
        .setName("Alice")
        .setAge(30)
        .build()
}
"""
            val container = analyse(code)
            val mainFunc = container.DataStructures[0].Functions.find { it.Name == "main" }
            assertTrue(mainFunc!!.FunctionCalls.size >= 3)
        }

        @Test
        fun `should identify lambda parameter calls`() {
            val code = """
package test

fun processNumbers(numbers: List<Int>, action: (Int) -> Unit) {
    numbers.forEach(action)
}

fun main() {
    processNumbers(listOf(1, 2, 3)) { num ->
        println(num)
    }
}
"""
            val container = analyse(code)
            val calls = container.DataStructures[0].Functions.find { it.Name == "main" }?.FunctionCalls
            assertTrue(calls!!.any { it.FunctionName == "processNumbers" })
        }

        @Test
        fun `should identify scope function calls`() {
            val code = """
package test

data class Person(var name: String, var age: Int)

fun main() {
    val person = Person("Alice", 30).apply {
        name = "Bob"
        age = 25
    }
    
    person.let { p ->
        println(p.name)
    }
    
    person.run {
        println(this.name)
    }
}
"""
            val container = analyse(code)
            val calls = container.DataStructures[0].Functions.find { it.Name == "main" }?.FunctionCalls
            assertTrue(calls!!.any { it.FunctionName == "apply" })
        }

        @Test
        fun `should identify extension function calls`() {
            val code = """
package test

fun String.reverse(): String = this.reversed()

fun main() {
    val result = "hello".reverse()
}
"""
            val container = analyse(code)
            val calls = container.DataStructures[0].Functions.find { it.Name == "main" }?.FunctionCalls
            assertTrue(calls!!.any { it.FunctionName == "reverse" })
        }

        @Test
        fun `should identify infix function calls`() {
            val code = """
package test

infix fun Int.times(str: String): String = str.repeat(this)

fun main() {
    val result = 3 times "hello"
}
"""
            val container = analyse(code)
            // Verify that the infix function is parsed
            val infixFunc = container.DataStructures[0].Functions.find { it.Name == "times" }
            assertTrue(infixFunc != null)
        }

        @Test
        fun `should identify invoke operator calls`() {
            val code = """
package test

class Greeter(val greeting: String) {
    operator fun invoke(name: String) = "${'$'}greeting, ${'$'}name!"
}

fun main() {
    val greeter = Greeter("Hello")
    val message = greeter("Alice")
}
"""
            val container = analyse(code)
            val calls = container.DataStructures[0].Functions.find { it.Name == "main" }?.FunctionCalls
            assertTrue(calls!!.size >= 1)
        }

        @Test
        fun `should identify constructor reference calls`() {
            val code = """
package test

data class Person(val name: String)

fun main() {
    val persons = listOf("Alice", "Bob").map(::Person)
}
"""
            val container = analyse(code)
            val calls = container.DataStructures[0].Functions.find { it.Name == "main" }?.FunctionCalls
            assertTrue(calls!!.isNotEmpty())
        }
    }

    @Nested
    inner class CollectionOperationTests {
        @Test
        fun `should identify collection operations`() {
            val code = """
package test

fun main() {
    val numbers = listOf(1, 2, 3, 4, 5)
    
    val doubled = numbers.map { it * 2 }
    val even = numbers.filter { it % 2 == 0 }
    val sum = numbers.reduce { acc, i -> acc + i }
}
"""
            val container = analyse(code)
            val calls = container.DataStructures[0].Functions[0].FunctionCalls
            assertTrue(calls.any { it.FunctionName == "map" })
            assertTrue(calls.any { it.FunctionName == "filter" })
            assertTrue(calls.any { it.FunctionName == "reduce" })
        }

        @Test
        fun `should identify sequence operations`() {
            val code = """
package test

fun main() {
    val sequence = sequenceOf(1, 2, 3)
        .map { it * 2 }
        .filter { it > 3 }
        .toList()
}
"""
            val container = analyse(code)
            val calls = container.DataStructures[0].Functions[0].FunctionCalls
            assertTrue(calls.isNotEmpty())
        }
    }

    @Nested
    inner class CoroutineTests {
        @Test
        fun `should identify suspend function calls`() {
            val code = """
package test

import kotlinx.coroutines.*

suspend fun loadData(): String {
    delay(1000)
    return "Data"
}

suspend fun process() {
    val data = loadData()
    println(data)
}
"""
            val container = analyse(code)
            val processFunc = container.DataStructures[0].Functions.find { it.Name == "process" }
            assertTrue(processFunc!!.FunctionCalls.any { it.FunctionName == "loadData" })
        }

        @Test
        fun `should identify coroutine builders`() {
            val code = """
package test

import kotlinx.coroutines.*

fun main() = runBlocking {
    launch {
        delay(1000)
        println("World")
    }
    println("Hello")
}
"""
            val container = analyse(code)
            val calls = container.DataStructures[0].Functions[0].FunctionCalls
            assertTrue(calls.any { it.FunctionName == "runBlocking" })
        }

        @Test
        fun `should identify async await`() {
            val code = """
package test

import kotlinx.coroutines.*

suspend fun fetchData() = coroutineScope {
    val deferred1 = async { loadData1() }
    val deferred2 = async { loadData2() }
    val result1 = deferred1.await()
    val result2 = deferred2.await()
}

suspend fun loadData1(): String = "Data1"
suspend fun loadData2(): String = "Data2"
"""
            val container = analyse(code)
            val fetchFunc = container.DataStructures[0].Functions.find { it.Name == "fetchData" }
            assertTrue(fetchFunc!!.FunctionCalls.any { it.FunctionName == "async" })
        }
    }

    @Nested
    inner class OperatorOverloadingTests {
        @Test
        fun `should identify plus operator usage`() {
            val code = """
package test

data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point) = Point(x + other.x, y + other.y)
}

fun main() {
    val p1 = Point(1, 2)
    val p2 = Point(3, 4)
    val p3 = p1 + p2
}
"""
            val container = analyse(code)
            val calls = container.DataStructures[0].Functions.find { it.Name == "main" }?.FunctionCalls
            assertTrue(calls!!.any { it.FunctionName == "PrimaryConstructor" })
        }

        @Test
        fun `should identify get set operators`() {
            val code = """
package test

class Grid {
    operator fun get(x: Int, y: Int): Int = x + y
    operator fun set(x: Int, y: Int, value: Int) {}
}

fun main() {
    val grid = Grid()
    val value = grid[1, 2]
    grid[1, 2] = 10
}
"""
            val container = analyse(code)
            assertTrue(container.DataStructures.size >= 1)
        }

        @Test
        fun `should identify contains operator`() {
            val code = """
package test

class Range(val start: Int, val end: Int) {
    operator fun contains(value: Int): Boolean {
        return value in start..end
    }
}

fun main() {
    val range = Range(1, 10)
    val inRange = 5 in range
}
"""
            val container = analyse(code)
            assertTrue(container.DataStructures.size >= 1)
        }
    }

    @Nested
    inner class DelegatedPropertyTests {
        @Test
        fun `should identify lazy delegate`() {
            val code = """
package test

class Example {
    val lazyValue: String by lazy {
        println("Computed!")
        "Hello"
    }
}

fun main() {
    val ex = Example()
    println(ex.lazyValue)
}
"""
            val container = analyse(code)
            val calls = container.DataStructures[0].Functions.find { it.Name == "main" }?.FunctionCalls
            assertTrue(calls!!.any { it.FunctionName == "PrimaryConstructor" })
        }

        @Test
        fun `should identify observable delegate`() {
            val code = """
package test

import kotlin.properties.Delegates

class User {
    var name: String by Delegates.observable("Initial") { prop, old, new ->
        println("${'$'}old -> ${'$'}new")
    }
}
"""
            val container = analyse(code)
            assertEquals("User", container.DataStructures[0].NodeName)
        }

        @Test
        fun `should identify map delegate`() {
            val code = """
package test

class User(map: Map<String, Any?>) {
    val name: String by map
    val age: Int by map
}
"""
            val container = analyse(code)
            assertEquals(2, container.DataStructures[0].Fields.size)
        }
    }

    @Nested
    inner class ReflectionTests {
        @Test
        fun `should identify class reference`() {
            val code = """
package test

fun main() {
    val kClass = String::class
    val javaClass = String::class.java
}
"""
            val container = analyse(code)
            assertTrue(container.DataStructures[0].Functions[0].FunctionCalls.isNotEmpty())
        }

        @Test
        fun `should identify callable reference`() {
            val code = """
package test

fun isOdd(x: Int) = x % 2 != 0

fun main() {
    val numbers = listOf(1, 2, 3)
    val odds = numbers.filter(::isOdd)
}
"""
            val container = analyse(code)
            val calls = container.DataStructures[0].Functions.find { it.Name == "main" }?.FunctionCalls
            assertTrue(calls!!.any { it.FunctionName == "filter" })
        }

        @Test
        fun `should identify property reference`() {
            val code = """
package test

data class Person(val name: String, val age: Int)

fun main() {
    val persons = listOf(Person("Alice", 30), Person("Bob", 25))
    val names = persons.map(Person::name)
}
"""
            val container = analyse(code)
            val calls = container.DataStructures[0].Functions.find { it.Name == "main" }?.FunctionCalls
            assertTrue(calls!!.isNotEmpty())
        }
    }

    @Nested
    inner class SmartCastTests {
        @Test
        fun `should parse smart cast in when`() {
            val code = """
package test

fun describe(obj: Any): String {
    return when (obj) {
        is String -> "String of length ${'$'}{obj.length}"
        is Int -> "Int value ${'$'}obj"
        is Boolean -> if (obj) "true" else "false"
        else -> "Unknown"
    }
}
"""
            val container = analyse(code)
            assertEquals("describe", container.DataStructures[0].Functions[0].Name)
        }

        @Test
        fun `should parse smart cast in if`() {
            val code = """
package test

fun getLength(obj: Any): Int {
    if (obj is String) {
        return obj.length
    }
    return 0
}
"""
            val container = analyse(code)
            assertEquals("getLength", container.DataStructures[0].Functions[0].Name)
        }
    }

    @Nested
    inner class ControlFlowTests {
        @Test
        fun `should parse try catch finally`() {
            val code = """
package test

fun riskyOperation(): String {
    try {
        return performOperation()
    } catch (e: Exception) {
        return "Error: ${'$'}{e.message}"
    } finally {
        cleanup()
    }
}

fun performOperation(): String = "Success"
fun cleanup() {}
"""
            val container = analyse(code)
            val calls = container.DataStructures[0].Functions.find { it.Name == "riskyOperation" }?.FunctionCalls
            assertTrue(calls!!.any { it.FunctionName == "performOperation" })
        }

        @Test
        fun `should parse for loop variants`() {
            val code = """
package test

fun iterateCollections() {
    val items = listOf(1, 2, 3)
    
    for (item in items) {
        println(item)
    }
    
    for (i in 0..5) {
        println(i)
    }
    
    for (i in 0 until 10 step 2) {
        println(i)
    }
}
"""
            val container = analyse(code)
            val calls = container.DataStructures[0].Functions[0].FunctionCalls
            assertTrue(calls.any { it.FunctionName == "println" })
        }

        @Test
        fun `should parse while and do while`() {
            val code = """
package test

fun loops() {
    var x = 0
    while (x < 10) {
        println(x)
        x++
    }
    
    do {
        println(x)
        x--
    } while (x > 0)
}
"""
            val container = analyse(code)
            assertTrue(container.DataStructures[0].Functions[0].FunctionCalls.isNotEmpty())
        }
    }

    @Nested
    inner class StringTemplateTests {
        @Test
        fun `should parse string interpolation`() {
            val code = """
package test

fun greet(name: String, age: Int) {
    val message = "Hello, ${'$'}name! You are ${'$'}age years old."
    println(message)
}
"""
            val container = analyse(code)
            val calls = container.DataStructures[0].Functions[0].FunctionCalls
            assertTrue(calls.any { it.FunctionName == "println" })
        }

        @Test
        fun `should parse complex string templates`() {
            val code = """
package test

data class Person(val name: String, val age: Int)

fun describe(person: Person) {
    println("${'$'}{person.name} is ${'$'}{person.age} years old")
}
"""
            val container = analyse(code)
            assertTrue(container.DataStructures.size >= 1)
        }
    }

    @Nested
    inner class DSLTests {
        @Test
        fun `should parse HTML DSL style`() {
            val code = """
package test

class HTML {
    fun head(init: Head.() -> Unit) = Head().apply(init)
    fun body(init: Body.() -> Unit) = Body().apply(init)
}

class Head {
    fun title(text: String) {}
}

class Body {
    fun p(text: String) {}
}

fun html(init: HTML.() -> Unit): HTML = HTML().apply(init)

fun example() {
    html {
        head {
            title("Page Title")
        }
        body {
            p("Hello, World!")
        }
    }
}
"""
            val container = analyse(code)
            val exampleFunc = container.DataStructures[0].Functions.find { it.Name == "example" }
            assertTrue(exampleFunc!!.FunctionCalls.isNotEmpty())
        }

        @Test
        fun `should parse builder pattern with lambdas`() {
            val code = """
package test

class PersonBuilder {
    var name: String = ""
    var age: Int = 0
    
    fun build() = Person(name, age)
}

data class Person(val name: String, val age: Int)

fun person(init: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(init).build()
}

fun main() {
    val p = person {
        name = "Alice"
        age = 30
    }
}
"""
            val container = analyse(code)
            val calls = container.DataStructures[0].Functions.find { it.Name == "main" }?.FunctionCalls
            assertTrue(calls!!.any { it.FunctionName == "person" })
        }
    }

    @Nested
    inner class AnnotationUsageTests {
        @Test
        fun `should identify annotation with parameters`() {
            val code = """
package test

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class LogExecutionTime

class Service {
    @LogExecutionTime
    fun process() {}
}
"""
            val container = analyse(code)
            val serviceClass = container.DataStructures.find { it.NodeName == "Service" }
            assertTrue(serviceClass!!.Functions[0].Annotations.any { it.Name == "LogExecutionTime" })
        }

        @Test
        fun `should identify multiple annotations`() {
            val code = """
package test

class Controller {
    @GET
    @Path("/users")
    @Produces("application/json")
    fun getUsers(): List<User> = emptyList()
}

data class User(val id: Int)
"""
            val container = analyse(code)
            val func = container.DataStructures[0].Functions[0]
            assertTrue(func.Annotations.size >= 3)
        }
    }
}
