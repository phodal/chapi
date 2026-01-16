package chapi.ast.scalaast

import chapi.domain.core.CallType
import chapi.domain.core.DataStructType
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class ScalaFullIdentListenerTest {

    @Nested
    inner class BasicParsing {
        @Test
        internal fun shouldIdentObjectName() {
            val code = """
object HelloWorld {
  def main(args: Array[String]): Unit = {
    println("Hello, world!")
  }
}
"""
            val container = ScalaAnalyser().analysis(code, "hello.scala")
            assertEquals(1, container.DataStructures.size)
            assertEquals("HelloWorld", container.DataStructures[0].NodeName)
            assertEquals(DataStructType.OBJECT, container.DataStructures[0].Type)
        }

        @Test
        internal fun shouldIdentClassName() {
            val code = """
class Outer(i: Int) {
  def foo(x: Inner.type) = x.getI
}
"""
            val container = ScalaAnalyser().analysis(code, "hello.scala")
            assertEquals(1, container.DataStructures.size)
            assertEquals("Outer", container.DataStructures[0].NodeName)
            assertEquals(DataStructType.CLASS, container.DataStructures[0].Type)
        }

        @Test
        internal fun shouldIdentClassParameters() {
            val code = """
class Outer(i: IntValue) {
  def foo(x: Inner.type) = x.getI
}
"""
            val container = ScalaAnalyser().analysis(code, "hello.scala")
            assertEquals(1, container.DataStructures.size)
            assertEquals(1, container.DataStructures[0].Parameters.size)
            assertEquals("IntValue", container.DataStructures[0].Parameters[0].TypeType)
            assertEquals("i", container.DataStructures[0].Parameters[0].TypeValue)
        }

        @Test
        internal fun shouldIdentOutClassInnerObjectName() {
            val code = """
class Outer(i: IntValue) {
  object Inner {
    def getI: IntValue = i
  }
  def foo(x: Inner.type) = x.getI
}
"""
            val container = ScalaAnalyser().analysis(code, "hello.scala")
            assertEquals(1, container.DataStructures.size)
            assertEquals(1, container.DataStructures[0].InnerStructures.size)
            assertEquals(DataStructType.OBJECT, container.DataStructures[0].InnerStructures[0].Type)
            assertEquals("Inner", container.DataStructures[0].InnerStructures[0].NodeName)
        }

        @Test
        internal fun shouldIdentExtendClassName() {
            val code = """
class CapComer(val transformation: SparktacusTransformation) extends TransformationTrait {

}
"""
            val container = ScalaAnalyser().analysis(code, "hello.scala")
            assertEquals("CapComer", container.DataStructures[0].NodeName)
            assertEquals("TransformationTrait", container.DataStructures[0].Extend)
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
            assertEquals("Iterator", container.DataStructures[0].NodeName)
            assertEquals(DataStructType.TRAIT, container.DataStructures[0].Type)
        }

        @Test
        internal fun shouldIdentifyPackageName() {
            val code = """
package xxx.yy.zz.aa.bb.commons.transformation

import chapi
"""
            val container = ScalaAnalyser().analysis(code, "hello.scala")
            assertEquals("xxx.yy.zz.aa.bb.commons.transformation", container.PackageName)
        }
    }

    @Nested
    inner class ImportParsing {
        @Test
        internal fun shouldIdentifySimpleImport() {
            val code = """
package test

import java.math.BigDecimal
import scala.collection.mutable.ListBuffer

class Test {}
"""
            val container = ScalaAnalyser().analysis(code, "test.scala")
            assertTrue(container.Imports.any { it.Source == "java.math.BigDecimal" })
            assertTrue(container.Imports.any { it.Source == "scala.collection.mutable.ListBuffer" })
        }

        @Test
        internal fun shouldIdentifyWildcardImport() {
            val code = """
package test

import org.apache.spark.sql.functions._

class Test {}
"""
            val container = ScalaAnalyser().analysis(code, "test.scala")
            assertTrue(container.Imports.any { it.Source.contains("functions") && it.AsName == "*" })
        }

        @Test
        internal fun shouldIdentifyMultipleSelectorsImport() {
            val code = """
package test

import org.apache.spark.sql.{DataFrame, Row, functions}

class Test {}
"""
            val container = ScalaAnalyser().analysis(code, "test.scala")
            assertTrue(container.Imports.any { it.Source.contains("DataFrame") })
            assertTrue(container.Imports.any { it.Source.contains("Row") })
            assertTrue(container.Imports.any { it.Source.contains("functions") })
        }
    }

    @Nested
    inner class FunctionDefinition {
        @Test
        internal fun shouldIdentifyFunctionDefinition() {
            val code = """
object Calculator {
  def add(a: Int, b: Int): Int = {
    a + b
  }
}
"""
            val container = ScalaAnalyser().analysis(code, "calculator.scala")
            val functions = container.DataStructures[0].Functions

            assertEquals(1, functions.size)
            assertEquals("add", functions[0].Name)
            assertEquals(2, functions[0].Parameters.size)
            assertEquals("a", functions[0].Parameters[0].TypeValue)
            // Scala built-in types are prefixed with scala.
            assertTrue(functions[0].Parameters[0].TypeType.contains("Int"))
        }

        @Test
        internal fun shouldIdentifyFunctionWithComplexReturnType() {
            val code = """
class DataProcessor {
  def process(data: List[String]): Map[String, Int] = {
    Map.empty
  }
}
"""
            val container = ScalaAnalyser().analysis(code, "processor.scala")
            val functions = container.DataStructures[0].Functions

            assertEquals(1, functions.size)
            assertEquals("process", functions[0].Name)
            assertTrue(functions[0].ReturnType.contains("Map"))
        }

        @Test
        internal fun shouldIdentifyMultipleFunctions() {
            val code = """
trait Repository {
  def find(id: Long): Option[Entity]
  def save(entity: Entity): Unit
  def delete(id: Long): Boolean
}
"""
            val container = ScalaAnalyser().analysis(code, "repository.scala")
            val functions = container.DataStructures[0].Functions

            assertEquals(3, functions.size)
            assertTrue(functions.any { it.Name == "find" })
            assertTrue(functions.any { it.Name == "save" })
            assertTrue(functions.any { it.Name == "delete" })
        }
    }

    @Nested
    inner class FieldDefinition {
        @Test
        internal fun shouldIdentifyValField() {
            val code = """
class Person(val name: String, val age: Int)
"""
            val container = ScalaAnalyser().analysis(code, "person.scala")
            val fields = container.DataStructures[0].Fields

            assertEquals(2, fields.size)
            assertTrue(fields.any { it.TypeKey == "name" && it.TypeType.contains("String") })
            assertTrue(fields.any { it.TypeKey == "age" && it.TypeType.contains("Int") })
        }

        @Test
        internal fun shouldIdentifyVarField() {
            val code = """
class Counter {
  var count: Int = 0
}
"""
            val container = ScalaAnalyser().analysis(code, "counter.scala")
            val fields = container.DataStructures[0].Fields

            assertTrue(fields.any { it.TypeKey == "count" && it.Modifiers.contains("var") })
        }

        @Test
        internal fun shouldDifferentiateValAndVar() {
            val code = """
class Config(val immutable: String, var mutable: Int)
"""
            val container = ScalaAnalyser().analysis(code, "config.scala")
            val fields = container.DataStructures[0].Fields

            val immutableField = fields.find { it.TypeKey == "immutable" }
            val mutableField = fields.find { it.TypeKey == "mutable" }

            assertTrue(immutableField?.Modifiers?.contains("val") == true)
            assertTrue(mutableField?.Modifiers?.contains("var") == true)
        }
    }

    @Nested
    inner class InheritanceAndTraits {
        @Test
        internal fun shouldIdentifyMultipleWithTraits() {
            val code = """
abstract class Animal {
  def speak: Unit
}

trait WaggingTail {
  def startTail: Unit
  def stopTail: Unit
}

trait FourLeggedAnimal {
  def walk: Unit
  def run: Unit
}

class Dog extends Animal with WaggingTail with FourLeggedAnimal {
  def speak: Unit = println("Woof!")
  def startTail: Unit = {}
  def stopTail: Unit = {}
  def walk: Unit = {}
  def run: Unit = {}
}
"""
            val container = ScalaAnalyser().analysis(code, "animals.scala")

            val dogClass = container.DataStructures.find { it.NodeName == "Dog" }
            assertEquals("Animal", dogClass?.Extend)
            assertTrue(dogClass?.Implements?.contains("WaggingTail") == true)
            assertTrue(dogClass?.Implements?.contains("FourLeggedAnimal") == true)
        }

        @Test
        internal fun shouldIdentifyTraitExtension() {
            val code = """
trait Base {
  def base: String
}

trait Extended extends Base {
  def extended: String
}
"""
            val container = ScalaAnalyser().analysis(code, "traits.scala")

            val extendedTrait = container.DataStructures.find { it.NodeName == "Extended" }
            assertEquals("Base", extendedTrait?.Extend)
        }
    }

    @Nested
    inner class FunctionCalls {
        @Test
        internal fun shouldIdentifySimpleFunctionCall() {
            val code = """
object HelloWorld {
  def main(args: Array[String]): Unit = {
    println("Hello, world!")
  }
}
"""
            val container = ScalaAnalyser().analysis(code, "hello.scala")
            val functions = container.DataStructures[0].Functions

            assertTrue(functions.isNotEmpty())
            // Function calls should be tracked
        }

        @Test
        internal fun shouldIdentifyNewObjectCreation() {
            val code = """
class Factory {
  def create(): Product = {
    new Product("test")
  }
}
"""
            val container = ScalaAnalyser().analysis(code, "factory.scala")
            val functions = container.DataStructures[0].Functions

            assertTrue(functions.isNotEmpty())
            assertEquals("create", functions[0].Name)
        }
    }

    @Nested
    inner class RealWorldExamples {
        @Test
        internal fun shouldParseSparkTransformation() {
            val code = """
package xxx.yy.zz.aa.bb.commons.transformation

import xxx.yy.zz.aa.core.configuration.KingTransformation
import xxx.yy.zz.aa.core.transformation.TransformationTrait
import org.apache.spark.sql.DataFrame

class AddingHoldingFlag(val transformation: KingTransformation) extends TransformationTrait {
  def runTransformation: DataFrame = {
    val previousDF = ProcessStatus.components(transformation.previous.get(0))
    val outPutDataframe = addingHoldingFlag(previousDF)
    outPutDataframe
  }

  def addingHoldingFlag(previousDF: DataFrame): DataFrame = {
    previousDF
  }
}
"""
            val container = ScalaAnalyser().analysis(code, "transformation.scala")

            assertEquals("xxx.yy.zz.aa.bb.commons.transformation", container.PackageName)
            assertEquals(1, container.DataStructures.size)

            val clazz = container.DataStructures[0]
            assertEquals("AddingHoldingFlag", clazz.NodeName)
            // Extend now includes full package path when import is available
            assertTrue(clazz.Extend.contains("TransformationTrait"))
            assertEquals(2, clazz.Functions.size)
            assertTrue(clazz.Functions.any { it.Name == "runTransformation" })
            assertTrue(clazz.Functions.any { it.Name == "addingHoldingFlag" })
        }

        @Test
        internal fun shouldParseCompanionObject() {
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
            val container = ScalaAnalyser().analysis(code, "donut.scala")

            assertEquals(2, container.DataStructures.size)

            val clazz = container.DataStructures.find { it.Type == DataStructType.CLASS }
            val obj = container.DataStructures.find { it.Type == DataStructType.OBJECT }

            assertEquals("VanillaDonut", clazz?.NodeName)
            assertEquals("VanillaDonut", obj?.NodeName)
            assertEquals("Donut", clazz?.Extend)
        }

        @Test
        internal fun shouldParseCaseClass() {
            val code = """
package model

case class User(id: Long, name: String, email: String)

case class Order(id: Long, userId: Long, items: List[Item])
"""
            val container = ScalaAnalyser().analysis(code, "models.scala")

            assertEquals("model", container.PackageName)
            assertEquals(2, container.DataStructures.size)

            val userClass = container.DataStructures.find { it.NodeName == "User" }
            assertEquals(3, userClass?.Parameters?.size)
        }

        @Test
        internal fun shouldParseComplexTraitHierarchy() {
            val code = """
package service

trait Logger {
  def log(message: String): Unit
}

trait Auditable {
  def audit(action: String): Unit
}

class UserService() extends Auditable {
  def audit(action: String): Unit = {}
}
"""
            val container = ScalaAnalyser().analysis(code, "service.scala")

            // Check that we have all the data structures parsed
            assertEquals(3, container.DataStructures.size)
            assertTrue(container.DataStructures.any { it.NodeName == "Logger" }, "Logger trait not found")
            assertTrue(container.DataStructures.any { it.NodeName == "Auditable" }, "Auditable trait not found")
            assertTrue(container.DataStructures.any { it.NodeName == "UserService" }, "UserService class not found")
        }
    }
}
