package chapi.ast.cast

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class CFullIdentListenerTest {
    @Test
    internal fun shouldGetCodeFileName() {
        val code = this::class.java.getResource("/_fixtures/examples/1-helloworld.c").readText()
        val fileName = "helloworld.c"

        val codeFile = CAnalyser().analysis(code, fileName)

        assertEquals(codeFile.FullName, fileName)
    }

    @Test
    internal fun shouldIdentifyImport() {
        val code = """
#include <stdio.h>

"""
        val codeFile = CAnalyser().analysis(code, "helloworld.c")

        assertEquals(codeFile.Imports.size, 1)
        assertEquals(codeFile.Imports[0].Source, "stdio.h")
    }

    @Test
    internal fun shouldIdentifyStructName() {
        val code = """
struct list_el {
   int val;
};
"""
        val codeFile = CAnalyser().analysis(code, "helloworld.c")

        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].NodeName, "list_el")
    }

    @Test
    internal fun shouldIdentifyStructPropertyName() {
        val code = """
struct list_el {
   int val;
};
"""
        val codeFile = CAnalyser().analysis(code, "helloworld.c")

        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].Fields.size, 1)
        assertEquals(codeFile.DataStructures[0].Fields[0].TypeType, "int")
        assertEquals(codeFile.DataStructures[0].Fields[0].TypeValue, "val")
    }

    @Test
    internal fun shouldIdentifyStructFunction() {
        val code = """
struct Person {
    int age;
};

int is_old(Person* p, int age) {
    return p->age > 60;
}
"""
        val codeFile = CAnalyser().analysis(code, "helloworld.c")

        assertEquals(codeFile.DataStructures.size, 1)
    }

    @Test
    internal fun shouldIdentifyStructFunctionPoint() {
        val code = """
struct list_el {
   int val;
   void (*func1)(void);
};
"""
        val codeFile = CAnalyser().analysis(code, "helloworld.c")

        assertEquals(codeFile.DataStructures.size, 1)
    }

    @Test
    internal fun shouldIdentifyAnonymousStruct() {
        val code = """
struct {
   int i;
   int j;
}  myVariableName;
"""
        val codeFile = CAnalyser().analysis(code, "helloworld.c")
    }

    @Test
    internal fun shouldIdentifyNestedStruct() {
        val code = """
struct Info{
    char name[30];
    int age;
    struct {
        char area_name[39];
        int house_no;
        char district[39];
    } address; // <<< here it is now
};
"""
        val codeFile = CAnalyser().analysis(code, "helloworld.c")
    }

    @Test
    internal fun shouldIdentifyFunctionName() {
        val code = """
int is_old(Person* p) {
    return p->age > 60;
}
"""
        val codeFile = CAnalyser().analysis(code, "helloworld.c")
        assertEquals(codeFile.DataStructures[0].Functions[0].Name, "is_old")
    }

    @Test
    internal fun shouldIdentifyFunctionSingleParameter() {
        val code = """
int is_old(Person* p) {
    return p->age > 60;
}
        """
        val codeFile = CAnalyser().analysis(code, "helloworld.c")
        val parameters = codeFile.DataStructures[0].Functions[0].Parameters
        assertEquals(parameters.size, 1)
        assertEquals(parameters[0].TypeType, "Person*")
        assertEquals(parameters[0].TypeValue, "p")
    }

    @Test
    internal fun shouldIdentifyFunctionMultiParameters() {
        val code = """
int is_old(Person* p, int a, double b) {
    return p->age > 60;
}
        """
        val codeFile = CAnalyser().analysis(code, "helloworld.c")
        val parameters = codeFile.DataStructures[0].Functions[0].Parameters
        assertEquals(parameters.size, 3)
        assertEquals(parameters[0].TypeType, "Person*")
        assertEquals(parameters[0].TypeValue, "p")

        assertEquals(parameters[1].TypeType, "int")
        assertEquals(parameters[1].TypeValue, "a")

        assertEquals(parameters[2].TypeType, "double")
        assertEquals(parameters[2].TypeValue, "b")
    }

    @Test
    internal fun shouldIdentifyForwardDeclaration() {
        val code = """
struct context;

struct funcptrs{
  void (*func0)(struct context *ctx);
  void (*func1)(void);
};

struct context{
    struct funcptrs fps;
}; 
"""
        val codeFile = CAnalyser().analysis(code, "helloworld.c")
//        assertEquals(codeFile.DataStructures.size, 2)
    }

    @Test
    internal fun shouldIdentifyForwardDeclarationSelf() {
        val code = """
struct element;
typedef struct {
    int value;
    // Use of the forward declaration
    struct element *next;
} element; // Complete definition
"""
        val codeFile = CAnalyser().analysis(code, "helloworld.c")
        assertEquals(codeFile.DataStructures.size, 3)
    }
}
