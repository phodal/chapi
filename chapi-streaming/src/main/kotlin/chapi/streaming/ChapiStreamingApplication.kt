package chapi.streaming

import chapi.streaming.kafka.ChapiStreamingTopology
import chapi.streaming.kafka.ChapiTopics
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.KafkaStreams
import org.apache.kafka.streams.StreamsConfig
import java.util.Properties
import java.util.concurrent.CountDownLatch
import kotlin.system.exitProcess

fun main() {
    val topics = ChapiTopics(
        codeChange = setting("CHAPI_CODE_CHANGE_TOPIC", "chapi.code-change.v1"),
        astRaw = setting("CHAPI_AST_RAW_TOPIC", "chapi.ast.raw.v1"),
        astResolved = setting("CHAPI_AST_RESOLVED_TOPIC", "chapi.ast.resolved.v1"),
        diagnostic = setting("CHAPI_DIAGNOSTIC_TOPIC", "chapi.diagnostic.v1"),
    )
    val properties = Properties().apply {
        put(StreamsConfig.APPLICATION_ID_CONFIG, setting("CHAPI_APPLICATION_ID", "chapi-streaming-v1"))
        put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, setting("CHAPI_BOOTSTRAP_SERVERS", "localhost:9092"))
        put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.StringSerde::class.java.name)
        put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.StringSerde::class.java.name)
        put(StreamsConfig.PROCESSING_GUARANTEE_CONFIG, StreamsConfig.EXACTLY_ONCE_V2)
        put(StreamsConfig.STATE_DIR_CONFIG, setting("CHAPI_STATE_DIR", "/tmp/chapi-streaming-state"))
        put(StreamsConfig.NUM_STREAM_THREADS_CONFIG, setting("CHAPI_NUM_STREAM_THREADS", "1"))
    }

    val streams = KafkaStreams(ChapiStreamingTopology.build(topics), properties)
    val latch = CountDownLatch(1)
    Runtime.getRuntime().addShutdownHook(Thread {
        streams.close()
        latch.countDown()
    })

    try {
        streams.start()
        latch.await()
    } catch (error: Throwable) {
        streams.close()
        System.err.println("Chapi streaming service failed: ${error.message}")
        exitProcess(1)
    }
}

private fun setting(name: String, defaultValue: String): String =
    System.getProperty(name.lowercase().replace('_', '.'))
        ?: System.getenv(name)
        ?: defaultValue
