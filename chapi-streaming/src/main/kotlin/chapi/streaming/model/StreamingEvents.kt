package chapi.streaming.model

import chapi.domain.core.CodeContainer
import kotlinx.serialization.Serializable

@Serializable
enum class CodeChangeOperation {
    UPSERT,
    DELETE,
}

@Serializable
data class CodeChangeEvent(
    val eventId: String,
    val projectId: String,
    val filePath: String,
    val language: String,
    val content: String? = null,
    val operation: CodeChangeOperation = CodeChangeOperation.UPSERT,
    val occurredAtEpochMs: Long? = null,
)

@Serializable
enum class AstPhase {
    RAW,
    RESOLVED,
}

@Serializable
data class AstEvent(
    /** The input event that triggered this projection. */
    val eventId: String,
    /** The latest input event that supplied this file's source. */
    val sourceEventId: String,
    val projectId: String,
    val filePath: String,
    val language: String,
    val operation: CodeChangeOperation,
    val phase: AstPhase,
    val ast: CodeContainer? = null,
    val generatedAtEpochMs: Long,
)

@Serializable
enum class DiagnosticSeverity {
    INFO,
    WARNING,
    ERROR,
}

@Serializable
data class DiagnosticEvent(
    val eventId: String,
    val projectId: String,
    val filePath: String,
    val language: String,
    val severity: DiagnosticSeverity,
    val code: String,
    val message: String,
    val generatedAtEpochMs: Long,
)

@Serializable
internal data class SourceSnapshot(
    val eventId: String,
    val filePath: String,
    val language: String,
    val content: String,
)

@Serializable
internal data class ProjectSnapshot(
    val sources: Map<String, SourceSnapshot> = emptyMap(),
)
