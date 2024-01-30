package chapi.ast.cppast

import chapi.domain.core.DataStructType
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

    @Test
    internal fun shouldIdentifyClassFunction() {
        val code = """
            class EntityB: public Entity
            {
            public:
            	EntityB(){};
            	~EntityB(){};
            	ValueObjectD* vo_d; 
            	void init(){
            		vo_d = new ValueObjectD();
            		std::cout << "entity b init" << "\n";
            	};
            };
            """.trimIndent()

        val container = CPPAnalyser().analysis(code, "helloworld.cpp")
        assertEquals(container.DataStructures.size, 1)
        assertEquals(container.DataStructures[0].NodeName, "EntityB")
        assertEquals(container.DataStructures[0].Functions[0].Name, "init")
    }

    @Test
    internal fun shouldIdentifyNameSpace() {
        val code = """
            namespace NS
            {
                class M
                {
                    friend class F;  // Introduces F but doesn't define it
                };
            }
            """.trimIndent()

        val container = CPPAnalyser().analysis(code, "helloworld.cpp")
        assertEquals(container.PackageName, "NS")
        assertEquals(container.DataStructures.size, 1)
        assertEquals(container.DataStructures[0].NodeName, "M")
    }

    @Test
    internal fun shouldIdentifyClassField() {
        val code = """
        class Entity
        {
        public:
            int Id, No;
        };
        """.trimIndent()

        val container = CPPAnalyser().analysis(code, "helloworld.cpp")
        assertEquals(container.DataStructures.size, 1)
        assertEquals(container.DataStructures[0].Fields.size, 2)
        // fields
        assertEquals(container.DataStructures[0].Fields[0].TypeType, "int")
        assertEquals(container.DataStructures[0].Fields[0].TypeKey, "Id")
        assertEquals(container.DataStructures[0].Fields[1].TypeType, "int")
        assertEquals(container.DataStructures[0].Fields[1].TypeKey, "No")
    }

    @Test
    internal fun shouldIdentifyCppStructure() {
        val code = """
        struct Person
        {
            char name[50];
            int age;
            float salary;
        };
        """.trimIndent()

        val container = CPPAnalyser().analysis(code, "helloworld.cpp")
        assertEquals(container.DataStructures.size, 1)
        assertEquals(container.DataStructures[0].Type, DataStructType.STRUCT)
    }
}
