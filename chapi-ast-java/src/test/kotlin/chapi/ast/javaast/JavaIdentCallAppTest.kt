package chapi.ast.javaast

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class JavaIdentCallAppTest {
    @Test
    internal fun shouldIdentifyFunctionCallName() {
        val code = """
public class HelloWorld {
    public static void main(String []args) {
       System.out.println("Hello World");
    }
}
"""
        val codeFile = JavaIdentApp().analysis(code, "")
        val codeCalls = codeFile.DataStructures[0].Functions[0].FunctionCalls

        assertEquals(codeCalls.size, 1)
        assertEquals(codeCalls[0].NodeName, "System.out")
        assertEquals(codeCalls[0].FunctionName, "println")
    }

    @Test
    internal fun shouldIdentifyFunctionCallPosition() {
        val code = """
public class HelloWorld {
    public static void main(String []args) {
       System.out.println("Hello World");
    }
}
"""
        val codeFile = JavaIdentApp().analysis(code, "")
        val firstCall = codeFile.DataStructures[0].Functions[0].FunctionCalls[0]

        assertEquals(firstCall.Position.StartLine, 4)
        assertEquals(firstCall.Position.StartLinePosition, 18)
        assertEquals(firstCall.Position.StopLine, 4)
        assertEquals(firstCall.Position.StopLinePosition, 39)
    }
}
