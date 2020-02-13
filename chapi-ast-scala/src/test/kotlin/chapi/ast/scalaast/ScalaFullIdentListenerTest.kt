package chapi.ast.scalaast

import org.junit.jupiter.api.Test

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
}
