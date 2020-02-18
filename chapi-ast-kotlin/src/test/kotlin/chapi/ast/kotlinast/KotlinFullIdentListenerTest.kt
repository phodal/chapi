package chapi.ast.kotlinast

import org.junit.jupiter.api.Test

internal class KotlinFullIdentListenerTest {
    @Test
    fun analysis() {
val helloworld = """
fun main(args : Array<String>) {
    println("Hello, World!")
}
"""
        KotlinAnalyser().analysis(helloworld)
    }
}
