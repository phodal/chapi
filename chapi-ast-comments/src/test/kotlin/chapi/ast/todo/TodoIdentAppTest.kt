package chapi.ast.todo

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class TodoIdentAppTest {
    @Test
    internal fun shouldIdentifyTodoMessage() {
        var todoTxt = "# todo: should identify todo message"
        val todos = TodoIdentApp().Analysis(todoTxt)

        assertEquals(todos.size, 1)
    }
}

