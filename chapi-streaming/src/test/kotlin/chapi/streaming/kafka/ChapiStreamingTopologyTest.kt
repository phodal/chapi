package chapi.streaming.kafka

import chapi.streaming.model.AstEvent
import chapi.streaming.model.CodeChangeEvent
import chapi.streaming.model.DiagnosticEvent
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.apache.kafka.streams.StreamsConfig
import org.apache.kafka.streams.TopologyTestDriver
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.Properties
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ChapiStreamingTopologyTest {
    private val topics = ChapiTopics(
        codeChange = "input",
        astRaw = "raw",
        astResolved = "resolved",
        diagnostic = "diagnostic",
    )
    private lateinit var driver: TopologyTestDriver

    @BeforeEach
    fun setUp() {
        val properties = Properties().apply {
            put(StreamsConfig.APPLICATION_ID_CONFIG, "chapi-streaming-test")
            put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "unused:9092")
        }
        driver = TopologyTestDriver(ChapiStreamingTopology.build(topics), properties)
    }

    @AfterEach
    fun tearDown() {
        driver.close()
    }

    @Test
    fun `routes raw and resolved events and deduplicates by event id`() {
        val input = driver.createInputTopic(topics.codeChange, StringSerializer(), StringSerializer())
        val raw = driver.createOutputTopic(topics.astRaw, StringDeserializer(), StringDeserializer())
        val resolved = driver.createOutputTopic(topics.astResolved, StringDeserializer(), StringDeserializer())
        val event = CodeChangeEvent(
            eventId = "event-1",
            projectId = "project-1",
            filePath = "Foo.java",
            language = "java",
            content = "package com.example; public class Foo {}",
        )
        val payload = ChapiStreamingTopology.json.encodeToString(event)

        input.pipeInput(event.projectId, payload)
        input.pipeInput(event.projectId, payload)

        assertEquals("Foo.java", ChapiStreamingTopology.json.decodeFromString<AstEvent>(raw.readValue()).filePath)
        assertEquals("Foo.java", ChapiStreamingTopology.json.decodeFromString<AstEvent>(resolved.readValue()).filePath)
        assertTrue(raw.isEmpty)
        assertTrue(resolved.isEmpty)
    }

    @Test
    fun `keeps project sources in state for same-package resolution`() {
        val input = driver.createInputTopic(topics.codeChange, StringSerializer(), StringSerializer())
        val raw = driver.createOutputTopic(topics.astRaw, StringDeserializer(), StringDeserializer())
        val resolved = driver.createOutputTopic(topics.astResolved, StringDeserializer(), StringDeserializer())
        val foo = CodeChangeEvent(
            eventId = "foo-1",
            projectId = "project-1",
            filePath = "Foo.java",
            language = "java",
            content = "package com.example; public class Foo {}",
        )
        val bar = CodeChangeEvent(
            eventId = "bar-1",
            projectId = "project-1",
            filePath = "Bar.java",
            language = "java",
            content = "package com.example; public class Bar extends Foo {}",
        )

        input.pipeInput(foo.projectId, ChapiStreamingTopology.json.encodeToString(foo))
        raw.readValue()
        resolved.readValue()
        input.pipeInput(bar.projectId, ChapiStreamingTopology.json.encodeToString(bar))

        raw.readValue()
        val resolvedEvents = resolved.readValuesToList()
            .map { ChapiStreamingTopology.json.decodeFromString<AstEvent>(it) }
        val resolvedBar = resolvedEvents.single { it.filePath == "Bar.java" }

        assertEquals(2, resolvedEvents.size)
        assertEquals("com.example.Foo", resolvedBar.ast!!.DataStructures.single().Extend)
    }

    @Test
    fun `sends records with the wrong partitioning key to diagnostics`() {
        val input = driver.createInputTopic(topics.codeChange, StringSerializer(), StringSerializer())
        val diagnostics = driver.createOutputTopic(topics.diagnostic, StringDeserializer(), StringDeserializer())
        val event = CodeChangeEvent(
            eventId = "event-2",
            projectId = "project-2",
            filePath = "Foo.java",
            language = "java",
            content = "public class Foo {}",
        )

        input.pipeInput("wrong-key", ChapiStreamingTopology.json.encodeToString(event))
        input.pipeInput("wrong-key", ChapiStreamingTopology.json.encodeToString(event))

        val diagnostic = ChapiStreamingTopology.json.decodeFromString<DiagnosticEvent>(diagnostics.readValue())
        assertEquals("INVALID_RECORD_KEY", diagnostic.code)
        assertTrue(diagnostics.isEmpty)
    }
}
