package chapi.ast.cppast

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class CPPFullIdentListenerTest {
    @Test
    internal fun shouldAnalysisHelloWorld() {
val code = """
#include <iostream>
using namespace std;
int main() {
    cout << "Hello, World!";
    return 0;
}
"""

        val fileName = "helloworld.cpp"
        val container = CPPAnalyser().analysis(code, fileName)

        assertEquals(container.FullName, "helloworld.cpp")
    }
}
