package chapi.ast.dartast

import chapi.domain.core.DataStructType
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class DartAnalyserTest {

    @Test
    fun shouldParseHelloWorld() {
        val code = """
            void main() {
              print('Hello, World!');
            }
        """.trimIndent()

        val codeContainer = DartAnalyser().analysis(code, "hello_world.dart")

        assertEquals("dart", codeContainer.Language)
        assertEquals("hello_world.dart", codeContainer.FullName)
        assertNotNull(codeContainer.TopLevel)
        assertEquals(1, codeContainer.TopLevel?.Functions?.size)
        assertEquals("main", codeContainer.TopLevel?.Functions?.get(0)?.Name)
    }

    @Test
    fun shouldParseLibraryName() {
        val code = """
            library my_library;
            
            void foo() {}
        """.trimIndent()

        val codeContainer = DartAnalyser().analysis(code, "my_lib.dart")

        assertEquals("my_library", codeContainer.PackageName)
        assertEquals("my_library", codeContainer.DeclaredPackage)
    }

    @Test
    fun shouldParseImports() {
        val code = """
            import 'dart:async';
            import 'dart:collection' show LinkedHashMap;
            import 'package:flutter/material.dart' as material;
            
            void main() {}
        """.trimIndent()

        val codeContainer = DartAnalyser().analysis(code, "imports.dart")

        assertEquals(3, codeContainer.Imports.size)
        assertEquals("dart:async", codeContainer.Imports[0].Source)
        assertEquals("dart:collection", codeContainer.Imports[1].Source)
        assertEquals("material", codeContainer.Imports[2].AsName)
    }

    @Test
    fun shouldParseSimpleClass() {
        val code = """
            class Person {
              String name;
              int age;
              
              String greet() {
                return 'Hello, my name is name';
              }
            }
        """.trimIndent()

        val codeContainer = DartAnalyser().analysis(code, "person.dart")

        assertEquals(1, codeContainer.DataStructures.size)
        val personClass = codeContainer.DataStructures[0]
        assertEquals("Person", personClass.NodeName)
        assertEquals(DataStructType.CLASS, personClass.Type)
        assertTrue(personClass.Functions.isNotEmpty())
        assertEquals("greet", personClass.Functions[0].Name)
    }

    @Test
    fun shouldParseClassWithInheritance() {
        val code = """
            class Animal {
              void makeSound() {}
            }
            
            class Dog extends Animal {
              @override
              void makeSound() {
                print('Woof!');
              }
            }
        """.trimIndent()

        val codeContainer = DartAnalyser().analysis(code, "animal.dart")

        assertEquals(2, codeContainer.DataStructures.size)
        
        val dogClass = codeContainer.DataStructures[1]
        assertEquals("Dog", dogClass.NodeName)
        assertEquals("Animal", dogClass.Extend)
    }

    @Test
    fun shouldParseClassWithImplements() {
        val code = """
            abstract class Comparable<T> {
              int compareTo(T other);
            }
            
            class Person implements Comparable<Person> {
              String name;
              
              @override
              int compareTo(Person other) {
                return name.compareTo(other.name);
              }
            }
        """.trimIndent()

        val codeContainer = DartAnalyser().analysis(code, "comparable.dart")

        val personClass = codeContainer.DataStructures.find { it.NodeName == "Person" }
        assertNotNull(personClass)
        assertEquals(1, personClass!!.Implements.size)
        assertEquals("Comparable<Person>", personClass.Implements[0])
    }

    @Test
    fun shouldParseAbstractClass() {
        val code = """
            abstract class Animal {
              String get species;
              void makeSound();
            }
        """.trimIndent()

        val codeContainer = DartAnalyser().analysis(code, "abstract.dart")

        assertEquals(1, codeContainer.DataStructures.size)
        val animalClass = codeContainer.DataStructures[0]
        assertEquals("Animal", animalClass.NodeName)
        assertEquals(DataStructType.ABSTRACT_CLASS, animalClass.Type)
    }

    @Test
    fun shouldParseMixin() {
        val code = """
            mixin Printable {
              void printInfo() {
                print(toString());
              }
            }
        """.trimIndent()

        val codeContainer = DartAnalyser().analysis(code, "mixin.dart")

        assertEquals(1, codeContainer.DataStructures.size)
        val mixin = codeContainer.DataStructures[0]
        assertEquals("Printable", mixin.NodeName)
        assertEquals(DataStructType.TRAIT, mixin.Type)
    }

    @Test
    fun shouldParseEnum() {
        val code = """
            enum Status {
              pending,
              active,
              completed,
            }
        """.trimIndent()

        val codeContainer = DartAnalyser().analysis(code, "enum.dart")

        assertEquals(1, codeContainer.DataStructures.size)
        val enumType = codeContainer.DataStructures[0]
        assertEquals("Status", enumType.NodeName)
        assertEquals(DataStructType.ENUM, enumType.Type)
        assertEquals(3, enumType.Fields.size)
    }

    @Test
    fun shouldParseExtension() {
        val code = """
            extension StringExtension on String {
              String capitalize() {
                if (isEmpty) return this;
                return this[0].toUpperCase() + substring(1);
              }
            }
        """.trimIndent()

        val codeContainer = DartAnalyser().analysis(code, "extension.dart")

        assertEquals(1, codeContainer.DataStructures.size)
        val extension = codeContainer.DataStructures[0]
        assertEquals("StringExtension", extension.NodeName)
        assertEquals("String", extension.Extend)
    }

    @Test
    fun shouldParseConstructors() {
        val code = """
            class Person {
              String name;
              
              Person(this.name);
              
              Person.anonymous() : name = 'Unknown';
              
              factory Person.fromJson(Map<String, dynamic> json) {
                return Person(json['name']);
              }
            }
        """.trimIndent()

        val codeContainer = DartAnalyser().analysis(code, "constructors.dart")

        val personClass = codeContainer.DataStructures[0]
        val constructors = personClass.Functions.filter { it.IsConstructor }
        
        assertTrue(constructors.isNotEmpty())
    }

    @Test
    fun shouldParseGetterAndSetter() {
        val code = """
            class Rectangle {
              double _width;
              double _height;
              
              double get area => _width * _height;
              
              set width(double value) {
                _width = value;
              }
            }
        """.trimIndent()

        val codeContainer = DartAnalyser().analysis(code, "getset.dart")

        val rectClass = codeContainer.DataStructures[0]
        val getter = rectClass.Functions.find { it.Name.startsWith("get ") }
        val setter = rectClass.Functions.find { it.Name.startsWith("set ") }

        assertNotNull(getter)
        assertNotNull(setter)
    }

    @Test
    fun shouldParseTopLevelFunction() {
        val code = """
            void greet(String name) {
              print('Hello, name!');
            }
            
            int add(int a, int b) => a + b;
        """.trimIndent()

        val codeContainer = DartAnalyser().analysis(code, "functions.dart")

        assertNotNull(codeContainer.TopLevel)
        assertEquals(2, codeContainer.TopLevel?.Functions?.size)
    }

    @Test
    fun shouldParseTopLevelVariables() {
        val code = """
            const String appName = 'MyApp';
            final int maxRetries = 3;
            var counter = 0;
        """.trimIndent()

        val codeContainer = DartAnalyser().analysis(code, "variables.dart")

        assertNotNull(codeContainer.TopLevel)
        assertTrue(codeContainer.TopLevel?.Fields?.isNotEmpty() == true)
    }

    @Test
    fun shouldParseComplexFile() {
        val code = this::class.java.getResource("/grammar/class_example.dart")?.readText()
        if (code == null) {
            // Skip if resource not found
            return
        }

        val codeContainer = DartAnalyser().analysis(code, "class_example.dart")

        assertEquals("example", codeContainer.PackageName)
        assertTrue(codeContainer.Imports.isNotEmpty())
        assertTrue(codeContainer.DataStructures.isNotEmpty())
    }

    @Test
    fun shouldParseBasicInfo() {
        val code = """
            class Simple {
              void method() {}
            }
        """.trimIndent()

        val codeContainer = DartAnalyser().identBasicInfo(code, "simple.dart")

        assertEquals(1, codeContainer.DataStructures.size)
        assertEquals("Simple", codeContainer.DataStructures[0].NodeName)
    }

    // Dart 3 tests
    @Test
    fun shouldParseSealedClass() {
        val code = """
            sealed class Shape {}
            
            class Circle extends Shape {
              double radius;
            }
            
            class Square extends Shape {
              double side;
            }
        """.trimIndent()

        val codeContainer = DartAnalyser().analysis(code, "sealed.dart")

        assertEquals(3, codeContainer.DataStructures.size)
        val shapeClass = codeContainer.DataStructures[0]
        assertEquals("Shape", shapeClass.NodeName)
        assertTrue(shapeClass.Annotations.any { it.Name == "sealed" })
    }

    @Test
    fun shouldParseBaseClass() {
        val code = """
            base class Vehicle {
              void move() {}
            }
        """.trimIndent()

        val codeContainer = DartAnalyser().analysis(code, "base.dart")

        assertEquals(1, codeContainer.DataStructures.size)
        val vehicleClass = codeContainer.DataStructures[0]
        assertEquals("Vehicle", vehicleClass.NodeName)
        assertTrue(vehicleClass.Annotations.any { it.Name == "base" })
    }

    @Test
    fun shouldParseInterfaceClass() {
        val code = """
            interface class Flyable {
              void fly();
            }
        """.trimIndent()

        val codeContainer = DartAnalyser().analysis(code, "interface.dart")

        assertEquals(1, codeContainer.DataStructures.size)
        val flyableClass = codeContainer.DataStructures[0]
        assertEquals("Flyable", flyableClass.NodeName)
        assertEquals(DataStructType.INTERFACE, flyableClass.Type)
    }

    @Test
    fun shouldParseFinalClass() {
        val code = """
            final class Config {
              final String name;
            }
        """.trimIndent()

        val codeContainer = DartAnalyser().analysis(code, "final.dart")

        assertEquals(1, codeContainer.DataStructures.size)
        val configClass = codeContainer.DataStructures[0]
        assertEquals("Config", configClass.NodeName)
        assertTrue(configClass.Annotations.any { it.Name == "final" })
    }

    @Test
    fun shouldParseMixinClass() {
        val code = """
            mixin class Both {
              void doSomething() {}
            }
        """.trimIndent()

        val codeContainer = DartAnalyser().analysis(code, "mixinclass.dart")

        assertEquals(1, codeContainer.DataStructures.size)
        val bothClass = codeContainer.DataStructures[0]
        assertEquals("Both", bothClass.NodeName)
        assertEquals(DataStructType.TRAIT, bothClass.Type)
    }

    @Test
    fun shouldParseBaseMixin() {
        val code = """
            base mixin Logger {
              void log(String message) {
                print(message);
              }
            }
        """.trimIndent()

        val codeContainer = DartAnalyser().analysis(code, "basemixin.dart")

        assertEquals(1, codeContainer.DataStructures.size)
        val loggerMixin = codeContainer.DataStructures[0]
        assertEquals("Logger", loggerMixin.NodeName)
        assertEquals(DataStructType.TRAIT, loggerMixin.Type)
        assertTrue(loggerMixin.Annotations.any { it.Name == "base" })
    }
}
