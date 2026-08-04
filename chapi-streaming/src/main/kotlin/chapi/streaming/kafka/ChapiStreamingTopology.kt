package chapi.streaming.kafka

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.Topology
import org.apache.kafka.streams.processor.api.ProcessorSupplier
import org.apache.kafka.streams.state.Stores

data class ChapiTopics(
    val codeChange: String = "chapi.code-change.v1",
    val astRaw: String = "chapi.ast.raw.v1",
    val astResolved: String = "chapi.ast.resolved.v1",
    val diagnostic: String = "chapi.diagnostic.v1",
)

object ChapiStreamingTopology {
    internal const val SOURCE = "code-change-source"
    internal const val PROCESSOR = "two-pass-analysis-processor"
    internal const val RAW_SINK = "raw-ast-sink"
    internal const val RESOLVED_SINK = "resolved-ast-sink"
    internal const val DIAGNOSTIC_SINK = "diagnostic-sink"
    internal const val PROCESSED_EVENTS_STORE = "processed-event-ids"
    internal const val PROJECTS_STORE = "project-sources"

    @OptIn(ExperimentalSerializationApi::class)
    val json: Json = Json {
        encodeDefaults = true
        explicitNulls = false
        ignoreUnknownKeys = true
    }

    fun build(topics: ChapiTopics = ChapiTopics()): Topology {
        val stringSerde = Serdes.String()
        val processedEventsStore = Stores.keyValueStoreBuilder(
            Stores.persistentKeyValueStore(PROCESSED_EVENTS_STORE),
            stringSerde,
            stringSerde,
        )
        val projectsStore = Stores.keyValueStoreBuilder(
            Stores.persistentKeyValueStore(PROJECTS_STORE),
            stringSerde,
            stringSerde,
        )

        return Topology()
            .addSource(
                SOURCE,
                stringSerde.deserializer(),
                stringSerde.deserializer(),
                topics.codeChange,
            )
            .addProcessor(
                PROCESSOR,
                ProcessorSupplier { ChapiStreamingProcessor(json) },
                SOURCE,
            )
            .addStateStore(processedEventsStore, PROCESSOR)
            .addStateStore(projectsStore, PROCESSOR)
            .addSink(
                RAW_SINK,
                topics.astRaw,
                stringSerde.serializer(),
                stringSerde.serializer(),
                PROCESSOR,
            )
            .addSink(
                RESOLVED_SINK,
                topics.astResolved,
                stringSerde.serializer(),
                stringSerde.serializer(),
                PROCESSOR,
            )
            .addSink(
                DIAGNOSTIC_SINK,
                topics.diagnostic,
                stringSerde.serializer(),
                stringSerde.serializer(),
                PROCESSOR,
            )
    }
}
