package chapi.ast.scalaast

import chapi.domain.core.DataStructType
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class ScalaFullIdentListenerTest {
    private val helloworld: String = """
object HelloWorld {
  def main(args: Array[String]): Unit = {
    println("Hello, world!")
  }
}        
"""

    @Test
    internal fun shouldIdentObjectName() {
        val container = ScalaAnalyser().analysis(helloworld, "hello.scala")
        assertEquals(container.DataStructures.size, 1)
        assertEquals(container.DataStructures[0].NodeName, "HelloWorld")
        assertEquals(container.DataStructures[0].Type, DataStructType.OBJECT)
    }

    @Test
    internal fun shouldIdentClassName() {
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

    @Test
    internal fun shouldIdentClassParameters() {
        val code = """
class Outer(i : Int) {
  def foo(x : Inner.type) = x.getI
}
"""

        val container = ScalaAnalyser().analysis(code, "hello.scala")
        assertEquals(container.DataStructures.size, 1)
        assertEquals(container.DataStructures[0].Parameters.size, 1)
        assertEquals(container.DataStructures[0].Parameters[0].TypeType, "Int")
        assertEquals(container.DataStructures[0].Parameters[0].TypeValue, "i")
    }

    @Test
    internal fun shouldIdentOutClassInnerObjectName() {
        val code = """
class Outer(i : Int) {
  object Inner {
    def getI : Int = i
  }
  def foo(x : Inner.type) = x.getI
}
"""

        val container = ScalaAnalyser().analysis(code, "hello.scala")
        assertEquals(container.DataStructures.size, 1)
        assertEquals(container.DataStructures[0].InnerStructures.size, 1)
        assertEquals(container.DataStructures[0].InnerStructures[0].Type, DataStructType.OBJECT)
        assertEquals(container.DataStructures[0].InnerStructures[0].NodeName, "Inner")
    }

    @Test
    internal fun shouldIdentExtendClassName() {

    }

    @Test
    internal fun shouldIdentTraitName() {

    }

    @Test
    internal fun shouldIdentMultipleTraitName() {

    }
}
