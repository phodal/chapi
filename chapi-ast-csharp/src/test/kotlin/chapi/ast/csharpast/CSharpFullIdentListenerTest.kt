package chapi.ast.csharpast

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CSharpFullIdentListenerTest {

    @Test
    fun analysis() {
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

        CSharpAnalyser().analysis(code, "hello.cs")
    }
}
