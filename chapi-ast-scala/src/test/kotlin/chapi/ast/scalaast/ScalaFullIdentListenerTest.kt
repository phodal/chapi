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
        val code = """
class CapComer(val transformation: SparktacusTransformation) extends TransformationTrait{

}
        """
        val container = ScalaAnalyser().analysis(code, "hello.scala")
        assertEquals(container.DataStructures[0].NodeName, "CapComer")
        assertEquals(container.DataStructures[0].Extend, "TransformationTrait")
    }

    @Test
    internal fun shouldIdentTraitName() {
        val code = """
trait Iterator[A] {
  def hasNext: Boolean
  def next(): A
}

class IntIterator(to: Int) extends Iterator[Int] {
  private var current = 0
  override def hasNext: Boolean = current < to   
}
"""

        val container = ScalaAnalyser().analysis(code, "hello.scala")
        assertEquals(container.DataStructures[0].NodeName, "Iterator")
        assertEquals(container.DataStructures[0].Type, DataStructType.TRAIT)
    }

    @Test
    internal fun shouldIdentMultipleTraitName() {
        val code = """
abstract class Animal {
  def speak
}

trait WaggingTail {
  def startTail
  def stopTail
}

trait FourLeggedAnimal {
  def walk
  def run
}

class Dog extends Animal with WaggingTail with FourLeggedAnimal {
  // implementation code here ...
}
"""

    }

    @Test
    internal fun shouldIdentInstantiateObject() {
        val code = """
 class VanillaDonut(name: String) extends Donut(name) {
   override def printName: Unit = println(name)
 }
 object VanillaDonut {
   def apply(name: String): Donut = {
     new VanillaDonut(name)
   }
}
"""
    }

    @Test
    internal fun shouldIdentifyPackageName() {
        //given
        val code = """
package  xxx.yy.zz.aa.bb.commons.transformation

import chapi
"""

        //when
        val container = ScalaAnalyser().analysis(code, "hello.scala")

        //then
        assertEquals(container.PackageName, "xxx.yy.zz.aa.bb.commons.transformation")
    }
}
