package chapi.ast.csharpast

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CSharpFullIdentListenerTest {

    @Test
    fun shouldIdentUsingSystem() {
        val code = """
using System; 
  
namespace HelloWorldApp { 
    class Geeks { 
        static void Main(string[] args) { 
            Console.WriteLine("Hello World!"); 
            Console.ReadKey(); 
        } 
    } 
} 
"""
        val codeFile = CSharpAnalyser().analysis(code, "hello.cs")
        assertEquals(codeFile.Imports.size, 1)
        assertEquals(codeFile.Imports[0].Source, "System")
    }

    @Test
    fun shouldIdentUsingSpace() {
        val code = """
using SomeNameSpace.Nested;

"""
        val codeFile = CSharpAnalyser().analysis(code, "hello.cs")
        assertEquals(codeFile.Imports.size, 1)
        assertEquals(codeFile.Imports[0].Source, "SomeNameSpace.Nested")
    }
}
