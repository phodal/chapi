package chapi.ast.cppast

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class CPPBasicIdentListenerTest {
    @Test
    internal fun shouldAnalysisHelloWorld() {
        val code = this::class.java.getResource("/grammar/helloworld.cpp").readText()
        val container = CPPAnalyser().analysis(code, "helloworld.cpp")
        assertEquals(container.FullName, "helloworld.cpp")
    }

    @Test
    internal fun shouldIdentFunctionName() {
        val code = this::class.java.getResource("/grammar/helloworld.cpp").readText()
        val container = CPPAnalyser().analysis(code, "helloworld.cpp")
        assertEquals(container.DataStructures.size, 1)
        assertEquals(container.DataStructures[0].Functions.size, 1)
        assertEquals(container.DataStructures[0].Functions[0].Name, "main")
    }

    @Test
    internal fun shouldIdentFunctionReturnType() {
        val code = """
#include <iostream>
using namespace std;

int main(){}
"""
        val container = CPPAnalyser().analysis(code, "helloworld.cpp")
        assertEquals(container.DataStructures[0].Functions[0].ReturnType, "int")
    }

    @Test
    internal fun shouldIdentFunctionParameters() {
        val code = """
#include <iostream>

void display(char c, int n) {
    for(int i = 1; i <= n; ++i) {
        cout << c;
    }
    cout << endl;
}
"""
        val container = CPPAnalyser().analysis(code, "helloworld.cpp")
        assertEquals(container.DataStructures[0].Functions[0].ReturnType, "void")
        val params = container.DataStructures[0].Functions[0].Parameters

        assertEquals(params.size, 2)
        assertEquals(params[0].TypeType, "char")
        assertEquals(params[0].TypeValue, "c")
        assertEquals(params[1].TypeType, "int")
        assertEquals(params[1].TypeValue, "n")
    }

    @Test
    internal fun shouldIdentifyClassName() {
        val code = """
        class Entity
        {
        public:
            int Id;
        };
        """.trimIndent()

        val container = CPPAnalyser().analysis(code, "helloworld.cpp")
        assertEquals(container.DataStructures.size, 1)
        assertEquals(container.DataStructures[0].NodeName, "Entity")
    }

    @Test
    internal fun shouldIdentifyInheritance() {
        val code = """
        class Entity
        {
        public:
            int Id;
        };
        
        class AggregateRoot: public Entity
        {
        };
        """.trimIndent()

        val container = CPPAnalyser().analysis(code, "helloworld.cpp")
        assertEquals(container.DataStructures.size, 2)
        assertEquals(container.DataStructures[0].NodeName, "Entity")
        assertEquals(container.DataStructures[1].NodeName, "AggregateRoot")
        assertEquals(container.DataStructures[1].Implements[0], "Entity")
    }
}
