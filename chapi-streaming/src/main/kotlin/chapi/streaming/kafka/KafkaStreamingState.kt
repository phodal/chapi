package chapi.streaming.kafka

import chapi.streaming.analysis.StreamingState
import chapi.streaming.model.ProjectSnapshot
import chapi.streaming.model.SourceSnapshot
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.apache.kafka.common.serialization.StringSerializer
import org.apache.kafka.streams.state.KeyValueStore
import java.nio.charset.StandardCharsets
import java.util.Base64

internal class KafkaStreamingState(
    private val processedEvents: KeyValueStore<String, String>,
    private val sources: KeyValueStore<String, String>,
    private val json: Json,
) : StreamingState {
    override fun isProcessed(eventId: String): Boolean = processedEvents[eventId] != null

    override fun markProcessed(eventId: String, processedAtEpochMs: Long) {
        processedEvents.put(eventId, processedAtEpochMs.toString())
    }

    override fun loadProject(projectId: String): ProjectSnapshot {
        val projectSources = mutableMapOf<String, SourceSnapshot>()
        sources.prefixScan(projectPrefix(projectId), StringSerializer()).use { iterator ->
            while (iterator.hasNext()) {
                val source = json.decodeFromString<SourceSnapshot>(iterator.next().value)
                projectSources[source.filePath] = source
            }
        }
        return ProjectSnapshot(sources = projectSources)
    }

    override fun putSource(projectId: String, source: SourceSnapshot) {
        sources.put(sourceKey(projectId, source.filePath), json.encodeToString(source))
    }

    override fun deleteSource(projectId: String, filePath: String) {
        sources.delete(sourceKey(projectId, filePath))
    }

    private fun projectPrefix(projectId: String): String =
        Base64.getUrlEncoder().withoutPadding()
            .encodeToString(projectId.toByteArray(StandardCharsets.UTF_8)) + KEY_SEPARATOR

    private fun sourceKey(projectId: String, filePath: String): String = projectPrefix(projectId) + filePath

    private companion object {
        const val KEY_SEPARATOR = "\u0000"
    }
}
