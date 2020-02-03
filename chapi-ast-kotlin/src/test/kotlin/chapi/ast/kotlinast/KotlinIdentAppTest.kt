package chapi.ast.kotlinast

import org.junit.jupiter.api.Test

internal class KotlinIdentAppTest {
    @Test
    fun analysis() {
val helloworld = """
fun main(args : Array<String>) {
    println("Hello, World!")
}
"""
        KotlinIdentApp().analysis(helloworld)
    }
}
