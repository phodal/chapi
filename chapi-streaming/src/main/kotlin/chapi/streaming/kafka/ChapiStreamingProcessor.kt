package chapi.streaming.kafka

import chapi.streaming.analysis.StreamingAnalysisEngine
import chapi.streaming.analysis.StreamingState
import chapi.streaming.model.CodeChangeEvent
import chapi.streaming.model.DiagnosticEvent
import chapi.streaming.model.DiagnosticSeverity
import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.apache.kafka.streams.processor.api.Processor
import org.apache.kafka.streams.processor.api.ProcessorContext
import org.apache.kafka.streams.processor.api.Record
import org.apache.kafka.streams.state.KeyValueStore

internal class ChapiStreamingProcessor(
    private val json: Json,
) : Processor<String, String, String, String> {
    private lateinit var context: ProcessorContext<String, String>
    private lateinit var engine: StreamingAnalysisEngine
    private lateinit var state: StreamingState

    override fun init(context: ProcessorContext<String, String>) {
        this.context = context
        @Suppress("UNCHECKED_CAST")
        val processedEvents = context.getStateStore(ChapiStreamingTopology.PROCESSED_EVENTS_STORE) as KeyValueStore<String, String>
        @Suppress("UNCHECKED_CAST")
        val projects = context.getStateStore(ChapiStreamingTopology.PROJECTS_STORE) as KeyValueStore<String, String>
        state = KafkaStreamingState(processedEvents, projects, json)
        engine = StreamingAnalysisEngine(state)
    }

    override fun process(record: Record<String, String>) {
        val value = record.value()
        if (value == null) {
            forwardInvalidRecord(record, "NULL_EVENT", "Code-change events must have a JSON value")
            return
        }

        val event = try {
            json.decodeFromString<CodeChangeEvent>(value)
        } catch (error: SerializationException) {
            forwardInvalidRecord(record, "INVALID_JSON", error.message ?: "Invalid code-change event JSON")
            return
        } catch (error: IllegalArgumentException) {
            forwardInvalidRecord(record, "INVALID_JSON", error.message ?: "Invalid code-change event JSON")
            return
        }

        if (state.isProcessed(event.eventId)) {
            return
        }

        if (record.key() != event.projectId) {
            val now = System.currentTimeMillis()
            state.markProcessed(event.eventId, now)
            forwardDiagnostic(
                key = event.projectId,
                timestamp = record.timestamp(),
                diagnostic = DiagnosticEvent(
                    eventId = event.eventId,
                    projectId = event.projectId,
                    filePath = event.filePath,
                    language = event.language,
                    severity = DiagnosticSeverity.ERROR,
                    code = "INVALID_RECORD_KEY",
                    message = "Kafka record key must equal projectId so project state remains co-partitioned",
                    generatedAtEpochMs = now,
                ),
            )
            return
        }

        val batch = engine.process(event)
        batch.raw.forEach { astEvent ->
            context.forward(
                record.withKey(outputKey(astEvent.projectId, astEvent.filePath)).withValue(json.encodeToString(astEvent)),
                ChapiStreamingTopology.RAW_SINK,
            )
        }
        batch.resolved.forEach { astEvent ->
            context.forward(
                record.withKey(outputKey(astEvent.projectId, astEvent.filePath)).withValue(json.encodeToString(astEvent)),
                ChapiStreamingTopology.RESOLVED_SINK,
            )
        }
        batch.diagnostics.forEach { diagnostic ->
            forwardDiagnostic(event.projectId, record.timestamp(), diagnostic)
        }
    }

    private fun forwardInvalidRecord(record: Record<String, String>, code: String, message: String) {
        forwardDiagnostic(
            key = record.key().orEmpty(),
            timestamp = record.timestamp(),
            diagnostic = DiagnosticEvent(
                eventId = "unknown-${context.recordMetadata().map { "${it.partition()}-${it.offset()}" }.orElse("record")}",
                projectId = record.key().orEmpty(),
                filePath = "",
                language = "",
                severity = DiagnosticSeverity.ERROR,
                code = code,
                message = message,
                generatedAtEpochMs = System.currentTimeMillis(),
            ),
        )
    }

    private fun forwardDiagnostic(key: String, timestamp: Long, diagnostic: DiagnosticEvent) {
        context.forward(
            Record(key, json.encodeToString(diagnostic), timestamp),
            ChapiStreamingTopology.DIAGNOSTIC_SINK,
        )
    }

    private fun outputKey(projectId: String, filePath: String): String = "$projectId:$filePath"
}
