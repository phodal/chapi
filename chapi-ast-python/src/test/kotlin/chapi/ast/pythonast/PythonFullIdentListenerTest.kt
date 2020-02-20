package chapi.ast.pythonast

import chapi.domain.core.DataStructType
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class PythonFullIdentListenerTest {
    @Test
    internal fun shouldAnalysisPython2() {
        val python2HelloWorld = """print("Hello, World!")"""

        val codeContainer = PythonAnalyser().analysis(python2HelloWorld, "py2.py")
        assertEquals(codeContainer.FullName, "py2.py")
    }

    @Test
    internal fun shouldAnalysisPython3() {
        val py3HelloWorld = """print "Hello, World!""""
        val codeContainer = PythonAnalyser().analysis(py3HelloWorld, "py3.py")
        assertEquals(codeContainer.FullName, "py3.py")
    }

    @Test
    internal fun shouldIdentClass() {
        val code = """class foo: pass"""
        val codeFile = PythonAnalyser().analysis(code, "")

        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].NodeName, "foo")
        assertEquals(codeFile.DataStructures[0].Type, DataStructType.CLASS)
    }

    @Test
    internal fun shouldIdentClassInheritance() {
        val code = """
class Student(Person):
    pass

"""
        val codeFile = PythonAnalyser().analysis(code, "")

        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].NodeName, "Student")
        assertEquals(codeFile.DataStructures[0].MultipleExtend.size, 1)
        assertEquals(codeFile.DataStructures[0].Extend, "")
        assertEquals(codeFile.DataStructures[0].MultipleExtend[0], "Person")
    }

    @Test
    internal fun shouldIdentClassMethodName() {
        val code = """
class MyServer(IInterface):
    def show(self):
        print('Hello, World 2!')

"""

        val codeFile = PythonAnalyser().analysis(code, "")
        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions[0].Name, "show")
    }

    @Test
    internal fun shouldPutFuncToDefaultWhenNoNode() {
        val code = """
async def show(str):
    print(str)

"""

        val codeFile = PythonAnalyser().analysis(code, "")
        assertEquals(codeFile.DataStructures[0].NodeName, "default")
        assertEquals(codeFile.DataStructures[0].Functions[0].Name, "show")
    }

    @Test
    internal fun shouldIdentFuncParameters() {
        val code = """
async def show(str):
    print(str)

"""

        val codeFile = PythonAnalyser().analysis(code, "")
        assertEquals(codeFile.DataStructures[0].NodeName, "default")
        assertEquals(codeFile.DataStructures[0].Functions[0].Modifiers[0], "async")
        assertEquals(codeFile.DataStructures[0].Functions[0].Parameters.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions[0].Parameters[0].TypeValue, "str")
    }

    @Test
    internal fun shouldIdentFuncParametersInitValue() {
        val code = """
def printinfo( name, age = 35):
   "This prints a passed info into this function"
   print "Name: ", name
   print "Age ", age
   return;

"""

        val codeFile = PythonAnalyser().analysis(code, "")
        assertEquals(codeFile.DataStructures[0].Functions[0].Name, "printinfo")
        assertEquals(codeFile.DataStructures[0].Functions[0].Parameters.size, 2)
        assertEquals(codeFile.DataStructures[0].Functions[0].Parameters[0].TypeValue, "name")
        assertEquals(codeFile.DataStructures[0].Functions[0].Parameters[1].TypeValue, "age")
        assertEquals(codeFile.DataStructures[0].Functions[0].Parameters[1].DefaultValue, "35")
    }

    @Test
    internal fun shouldIdentifyClassAnnotation() {
        val code = """
@decorator
class foo:
    pass

"""

        val codeFile = PythonAnalyser().analysis(code, "fileName.py")
        assertEquals(codeFile.DataStructures[0].NodeName, "foo")
        assertEquals(codeFile.DataStructures[0].FilePath, "fileName.py")
        assertEquals(codeFile.DataStructures[0].Annotations.size, 1)
        assertEquals(codeFile.DataStructures[0].Annotations[0].Name, "decorator")
    }

    @Test
    internal fun shouldIdentifyMultipleClassAnnotation() {
        val code = """
@cache
@decorator
class foo:
    pass

"""

        val codeFile = PythonAnalyser().analysis(code, "")
        assertEquals(codeFile.DataStructures[0].NodeName, "foo")
        assertEquals(codeFile.DataStructures[0].Annotations.size, 2)
        assertEquals(codeFile.DataStructures[0].Annotations[0].Name, "cache")
        assertEquals(codeFile.DataStructures[0].Annotations[1].Name, "decorator")
    }

    @Test
    internal fun shouldIdentifyMultipleClassAnnotationKeyValue() {
        val code = """
@cache(key="value")
class multiple_annotation():
    pass

"""

        val codeFile = PythonAnalyser().analysis(code, "")
        assertEquals(codeFile.DataStructures[0].Annotations[0].Name, "cache")
        assertEquals(codeFile.DataStructures[0].Annotations[0].KeyValues[0].Key, "key")
        assertEquals(codeFile.DataStructures[0].Annotations[0].KeyValues[0].Value, "\"value\"")
    }

    @Test
    internal fun shouldIdentifyMultipleFunctionAnnotationKeyValue() {
        val code = """
@accepts(int, int)
@returns(float)
def bar(low,high):
    pass

"""

        val codeFile = PythonAnalyser().analysis(code, "")
        val firstFunc = codeFile.DataStructures[0].Functions[0]

        assertEquals(firstFunc.Annotations[0].Name, "accepts")
        assertEquals(firstFunc.Annotations[0].KeyValues.size, 2)
        assertEquals(firstFunc.Annotations[0].KeyValues[0].Key, "int")
        assertEquals(firstFunc.Annotations[0].KeyValues[1].Key, "int")

        assertEquals(firstFunc.Annotations[1].Name, "returns")
        assertEquals(firstFunc.Annotations[1].KeyValues[0].Key, "float")
    }

    @Test
    internal fun shouldIdentImportDottedName() {
        val code = """
import collections.abc

"""

        val codeFile = PythonAnalyser().analysis(code, "")

        assertEquals(codeFile.Imports.size, 1)
        assertEquals(codeFile.Imports[0].Source, "collections.abc")
        assertEquals(codeFile.Imports[0].UsageName.size, 0)
    }

    @Test
    internal fun shouldIdentImportAs() {
        val code = """
import collections.abc as ss, itertools

"""

        val codeFile = PythonAnalyser().analysis(code, "")

        assertEquals(codeFile.Imports.size, 1)
        assertEquals(codeFile.Imports[0].Source, "collections.abc")
        assertEquals(codeFile.Imports[0].UsageName.size, 2)
        assertEquals(codeFile.Imports[0].UsageName[0], "ss")
        assertEquals(codeFile.Imports[0].UsageName[1], "itertools")
    }

    @Test
    internal fun shouldIdentFromImport() {
        val code = """
from . import bar

"""

        val codeFile = PythonAnalyser().analysis(code, "")

        assertEquals(codeFile.Imports.size, 1)
        assertEquals(codeFile.Imports[0].Source, ".")
        assertEquals(codeFile.Imports[0].UsageName[0], "bar")
    }

    @Test
    internal fun shouldIdentFromImportAs() {
        val code = """
from classdef import bar as b

"""

        val codeFile = PythonAnalyser().analysis(code, "")

        assertEquals(codeFile.Imports.size, 1)
        assertEquals(codeFile.Imports[0].Source, "classdef")
        assertEquals(codeFile.Imports[0].UsageName[0], "bar")
        assertEquals(codeFile.Imports[0].AsName, "b")
    }

    @Test
    internal fun shouldIdentFuncCall() {
        val code = """
class Employee:
    def __init__(self, name):
        self.name = name
 
emp = Employee("Zara")
"""

        val codeFile = PythonAnalyser().analysis(code, "")
    }
}
