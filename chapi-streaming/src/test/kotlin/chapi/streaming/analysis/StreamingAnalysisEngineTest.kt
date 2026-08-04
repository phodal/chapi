package chapi.streaming.analysis

import chapi.streaming.model.CodeChangeEvent
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class StreamingAnalysisEngineTest {
    private val state = InMemoryStreamingState()
    private val engine = StreamingAnalysisEngine(state, currentTimeMillis = { 1234L })

    @Test
    fun `resolves same-package inheritance in the second pass`() {
        engine.process(
            codeChange(
                eventId = "event-foo",
                filePath = "src/main/java/com/example/Foo.java",
                content = """
                    package com.example;
                    public class Foo {}
                """.trimIndent(),
            ),
        )

        val result = engine.process(
            codeChange(
                eventId = "event-bar",
                filePath = "src/main/java/com/example/Bar.java",
                content = """
                    package com.example;
                    public class Bar extends Foo {}
                """.trimIndent(),
            ),
        )

        val rawBar = result.raw.single().ast!!.DataStructures.single()
        val resolvedBar = result.resolved
            .single { it.filePath.endsWith("Bar.java") }
            .ast!!
            .DataStructures
            .single()

        assertEquals("Foo", rawBar.Extend)
        assertEquals("com.example.Foo", resolvedBar.Extend)
        assertEquals(2, result.resolved.size)
        assertTrue(result.diagnostics.isEmpty())
    }

    @Test
    fun `re-resolves existing project files when a sibling arrives`() {
        val barFirst = engine.process(
            codeChange(
                eventId = "event-bar-first",
                filePath = "Bar.java",
                content = "package com.example; public class Bar extends Foo {}",
            ),
        )
        assertEquals("Foo", barFirst.resolved.single().ast!!.DataStructures.single().Extend)

        val fooArrives = engine.process(
            codeChange(
                eventId = "event-foo-second",
                filePath = "Foo.java",
                content = "package com.example; public class Foo {}",
            ),
        )
        val reResolvedBar = fooArrives.resolved
            .single { it.filePath == "Bar.java" }
            .ast!!
            .DataStructures
            .single()

        assertEquals("com.example.Foo", reResolvedBar.Extend)
    }

    @Test
    fun `does not resolve an unimported class from another package`() {
        engine.process(
            codeChange(
                eventId = "event-other-foo",
                filePath = "other/Foo.java",
                content = "package other; public class Foo {}",
            ),
        )

        val result = engine.process(
            codeChange(
                eventId = "event-example-bar",
                filePath = "example/Bar.java",
                content = "package com.example; public class Bar extends Foo {}",
            ),
        )
        val resolvedBar = result.resolved
            .single { it.filePath == "example/Bar.java" }
            .ast!!
            .DataStructures
            .single()

        assertEquals("Foo", resolvedBar.Extend)
    }

    @Test
    fun `does not emit a second batch for the same event id`() {
        val event = codeChange(
            eventId = "event-once",
            filePath = "Foo.java",
            content = "package com.example; public class Foo {}",
        )

        val first = engine.process(event)
        val duplicate = engine.process(event)

        assertFalse(first.duplicate)
        assertTrue(duplicate.duplicate)
        assertTrue(duplicate.raw.isEmpty())
        assertTrue(duplicate.resolved.isEmpty())
        assertTrue(duplicate.diagnostics.isEmpty())
    }

    private fun codeChange(eventId: String, filePath: String, content: String) = CodeChangeEvent(
        eventId = eventId,
        projectId = "example-project",
        filePath = filePath,
        language = "java",
        content = content,
    )
}
