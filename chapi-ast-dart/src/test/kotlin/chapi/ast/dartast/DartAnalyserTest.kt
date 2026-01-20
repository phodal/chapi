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

    // Additional coverage tests
    @Test
    fun shouldParseExports() {
        val code = """
            library mylib;
            
            export 'src/models.dart';
            export 'src/utils.dart' show Helper, Utils;
            
            void main() {}
        """.trimIndent()

        val codeContainer = DartAnalyser().analysis(code, "exports.dart")

        assertNotNull(codeContainer.TopLevel)
        val exports = codeContainer.TopLevel?.Exports ?: emptyList()
        assertEquals(2, exports.size)
        assertEquals("src/models.dart", exports[0].Name)
        assertEquals("src/utils.dart", exports[1].Name)
    }

    @Test
    fun shouldParseClassFields() {
        val code = """
            class User {
              static const int maxAge = 120;
              static final String defaultName = 'Unknown';
              late String name;
              final int age;
              String? nickname;
              
              User(this.name, this.age);
            }
        """.trimIndent()

        val codeContainer = DartAnalyser().analysis(code, "fields.dart")

        val userClass = codeContainer.DataStructures[0]
        assertEquals("User", userClass.NodeName)
        assertTrue(userClass.Fields.isNotEmpty())
    }

    @Test
    fun shouldParseMethodParameters() {
        val code = """
            class Calculator {
              int add(int a, int b) => a + b;
              
              void greet(String name, {int? age, bool isVip = false}) {
                print('Hello name');
              }
              
              void log([String message = 'default']) {
                print(message);
              }
            }
        """.trimIndent()

        val codeContainer = DartAnalyser().analysis(code, "params.dart")

        val calcClass = codeContainer.DataStructures[0]
        assertEquals("Calculator", calcClass.NodeName)
        assertEquals(3, calcClass.Functions.size)
        
        val addMethod = calcClass.Functions.find { it.Name == "add" }
        assertNotNull(addMethod)
        assertEquals(2, addMethod!!.Parameters.size)
    }

    @Test
    fun shouldParseTypedef() {
        val code = """
            typedef IntCallback = void Function(int value);
            typedef JsonMap = Map<String, dynamic>;
            typedef Compare<T> = int Function(T a, T b);
            
            void main() {}
        """.trimIndent()

        val codeContainer = DartAnalyser().analysis(code, "typedef.dart")

        // Typedefs are parsed as data structures
        assertTrue(codeContainer.DataStructures.size >= 3)
    }

    @Test
    fun shouldParseOperatorOverloading() {
        val code = """
            class Vector {
              final int x, y;
              
              Vector(this.x, this.y);
              
              Vector operator +(Vector other) {
                return Vector(x + other.x, y + other.y);
              }
              
              bool operator ==(Object other) {
                if (other is Vector) {
                  return x == other.x && y == other.y;
                }
                return false;
              }
            }
        """.trimIndent()

        val codeContainer = DartAnalyser().analysis(code, "operator.dart")

        val vectorClass = codeContainer.DataStructures[0]
        assertEquals("Vector", vectorClass.NodeName)
        
        val operators = vectorClass.Functions.filter { it.Name.startsWith("operator") }
        assertTrue(operators.isNotEmpty())
    }

    @Test
    fun shouldParseMixinWithConstraints() {
        val code = """
            mixin Musical on Performer {
              void playInstrument() {}
            }
            
            mixin Theatrical on Performer implements Actor {
              void act() {}
            }
            
            class Performer {}
            abstract class Actor {}
        """.trimIndent()

        val codeContainer = DartAnalyser().analysis(code, "mixin_constraints.dart")

        val musicalMixin = codeContainer.DataStructures.find { it.NodeName == "Musical" }
        assertNotNull(musicalMixin)
        assertEquals(DataStructType.TRAIT, musicalMixin!!.Type)
        assertTrue(musicalMixin.MultipleExtend.isNotEmpty())
    }

    @Test
    fun shouldParseClassWithMixins() {
        val code = """
            mixin A {}
            mixin B {}
            class Base {}
            
            class Child extends Base with A, B {
              void method() {}
            }
        """.trimIndent()

        val codeContainer = DartAnalyser().analysis(code, "with_mixins.dart")

        val childClass = codeContainer.DataStructures.find { it.NodeName == "Child" }
        assertNotNull(childClass)
        assertEquals("Base", childClass!!.Extend)
        assertTrue(childClass.MultipleExtend.isNotEmpty())
    }

    @Test
    fun shouldParseImportHide() {
        val code = """
            import 'dart:math' hide Random;
            
            void main() {}
        """.trimIndent()

        val codeContainer = DartAnalyser().analysis(code, "import_hide.dart")

        assertEquals(1, codeContainer.Imports.size)
        assertEquals("dart:math", codeContainer.Imports[0].Source)
    }

    @Test
    fun shouldParseStaticMethods() {
        val code = """
            class Utils {
              static void log(String message) {
                print(message);
              }
              
              static int parse(String value) => int.parse(value);
            }
        """.trimIndent()

        val codeContainer = DartAnalyser().analysis(code, "static.dart")

        val utilsClass = codeContainer.DataStructures[0]
        assertEquals(2, utilsClass.Functions.size)
        assertTrue(utilsClass.Functions.all { it.Modifiers.contains("static") })
    }

    @Test
    fun shouldParseAsyncMethods() {
        val code = """
            class ApiService {
              Future<String> fetchData() async {
                return 'data';
              }
              
              Stream<int> countStream() async* {
                for (var i = 0; i < 10; i++) {
                  yield i;
                }
              }
            }
        """.trimIndent()

        val codeContainer = DartAnalyser().analysis(code, "async.dart")

        val serviceClass = codeContainer.DataStructures[0]
        assertEquals(2, serviceClass.Functions.size)
    }

    @Test
    fun shouldParseGenericClass() {
        val code = """
            class Box<T> {
              T? value;
              
              Box([this.value]);
              
              void set(T newValue) {
                value = newValue;
              }
              
              T? get() => value;
            }
        """.trimIndent()

        val codeContainer = DartAnalyser().analysis(code, "generic.dart")

        val boxClass = codeContainer.DataStructures[0]
        assertEquals("Box", boxClass.NodeName)
        assertTrue(boxClass.Functions.isNotEmpty())
    }

    @Test
    fun shouldParseAnonymousExtension() {
        val code = """
            extension on int {
              bool get isEven => this % 2 == 0;
            }
        """.trimIndent()

        val codeContainer = DartAnalyser().analysis(code, "anon_ext.dart")

        assertEquals(1, codeContainer.DataStructures.size)
        val extension = codeContainer.DataStructures[0]
        assertEquals("anonymous", extension.NodeName)
        assertEquals("int", extension.Extend)
    }

    @Test
    fun shouldParseTopLevelGetter() {
        val code = """
            String get appVersion => '1.0.0';
            
            set appConfig(String value) {
              print('Setting config');
            }
        """.trimIndent()

        val codeContainer = DartAnalyser().analysis(code, "top_getter.dart")

        assertNotNull(codeContainer.TopLevel)
        val functions = codeContainer.TopLevel?.Functions ?: emptyList()
        assertTrue(functions.any { it.Name.startsWith("get ") })
        assertTrue(functions.any { it.Name.startsWith("set ") })
    }

    @Test
    fun shouldParseComplexFileWithAssert() {
        val code = this::class.java.getResource("/grammar/class_example.dart")?.readText()
        assertNotNull(code, "Resource /grammar/class_example.dart not found")

        val codeContainer = DartAnalyser().analysis(code!!, "class_example.dart")

        assertEquals("example", codeContainer.PackageName)
        assertTrue(codeContainer.Imports.isNotEmpty())
        assertTrue(codeContainer.DataStructures.isNotEmpty())
    }
}
