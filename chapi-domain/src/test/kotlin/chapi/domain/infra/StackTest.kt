package chapi.domain.infra

import chapi.domain.core.CodeDataStruct
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class StackTest {
    @Test
    internal fun shouldEnablePushElement() {
        var classNodeStack =
            Stack<CodeDataStruct>()
        val nodeName = "hello"
        val codeDataStruct = CodeDataStruct(NodeName = nodeName)

        classNodeStack.push(codeDataStruct)

        assertEquals(classNodeStack.count(), 1)
        assertEquals(classNodeStack.elements[0].NodeName, nodeName)
        assertEquals(classNodeStack.isEmpty(), false)
    }

    @Test
    internal fun shouldEnablePushPopElement() {
        var classNodeStack =
            Stack<CodeDataStruct>()
        val nodeName = "hello"
        val codeDataStruct = CodeDataStruct(NodeName = nodeName)

        classNodeStack.push(codeDataStruct)
        classNodeStack.pop()

        assertEquals(classNodeStack.count(), 0)
        assertEquals(classNodeStack.isEmpty(), true)
        assertEquals(classNodeStack.peek(), null)
    }

    @Test
    internal fun shouldEnablePeekElement() {
        var classNodeStack =
            Stack<CodeDataStruct>()
        val nodeName = "hello"
        val codeDataStruct = CodeDataStruct(NodeName = nodeName)

        classNodeStack.push(codeDataStruct)
        classNodeStack.push(codeDataStruct)
        classNodeStack.pop()

        assertEquals(classNodeStack.peek()!!.NodeName, nodeName)
    }

    @Test
    internal fun shouldEnableToString() {
        var classNodeStack = Stack<String>()

        classNodeStack.push("hello")
        classNodeStack.push("world")

        assertEquals(classNodeStack.toString(), "[hello, world]")
    }
}
