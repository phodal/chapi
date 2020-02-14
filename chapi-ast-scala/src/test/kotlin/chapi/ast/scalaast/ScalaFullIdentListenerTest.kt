package chapi.ast.scalaast

import chapi.domain.core.DataStructType
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class ScalaFullIdentListenerTest {
    private val hellowoorld: String = """
object HelloWorld {
  def main(args: Array[String]): Unit = {
    println("Hello, world!")
  }
}        
"""

    @Test
    internal fun shouldAnalysis() {
        ScalaAnalyser().analysis(hellowoorld, "hello.scala")
    }

    @Test
    internal fun shouldAnalysisObjectName() {
        val container = ScalaAnalyser().analysis(hellowoorld, "hello.scala")
        assertEquals(container.DataStructures.size, 1)
        assertEquals(container.DataStructures[0].NodeName, "HelloWorld")
        assertEquals(container.DataStructures[0].Type, DataStructType.OBJECT)
    }

    @Test
    internal fun shouldAnalysisClassName() {
        val code = """
class Outer(i : Int) {
  def foo(x : Inner.type) = x.getI
}
        """

        val container = ScalaAnalyser().analysis(code, "hello.scala")
        assertEquals(container.DataStructures.size, 1)
        assertEquals(container.DataStructures[0].NodeName, "Outer")
        assertEquals(container.DataStructures[0].Type, DataStructType.CLASS)
    }
}
