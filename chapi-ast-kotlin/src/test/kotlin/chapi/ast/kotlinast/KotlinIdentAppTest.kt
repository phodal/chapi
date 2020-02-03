package chapi.ast.kotlinast

import chapi.ast.goast.KotlinIdentApp
import org.junit.jupiter.api.Test

internal class KotlinIdentAppTest {
    @Test
    fun analysis() {
val helloworld = """
fun main(args : Array<String>) {
    println("Hello, World!")
}
"""
        KotlinIdentApp().Analysis(helloworld)
    }
}
