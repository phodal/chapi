package chapi.ast.pythonast

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class PythonFullIdentListenerTest {
    @Test
    internal fun shouldAnalysisPython2() {
        val python2HelloWorld = """print("Hello, World!")"""

        PythonAnalyser().analysis(python2HelloWorld, "")
    }

    @Test
    internal fun shouldAnalysisPython3() {
        val py3HelloWorld = """print "Hello, World!""""
        PythonAnalyser().analysis(py3HelloWorld, "")
    }

    @Test
    internal fun shouldIdentClass() {
        val code = """class foo: pass"""
        val codeFile = PythonAnalyser().analysis(code, "")

        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].NodeName, "foo")
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

        val codeFile = PythonAnalyser().analysis(code, "")
        assertEquals(codeFile.DataStructures[0].NodeName, "foo")
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
}
