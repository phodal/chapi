package chapi.streaming.analysis

import chapi.domain.core.CodeContainer
import chapi.streaming.model.AstEvent
import chapi.streaming.model.AstPhase
import chapi.streaming.model.CodeChangeEvent
import chapi.streaming.model.CodeChangeOperation
import chapi.streaming.model.DiagnosticEvent
import chapi.streaming.model.DiagnosticSeverity
import chapi.streaming.model.ProjectSnapshot
import chapi.streaming.model.SourceSnapshot

internal data class AnalysisBatch(
    val raw: List<AstEvent> = emptyList(),
    val resolved: List<AstEvent> = emptyList(),
    val diagnostics: List<DiagnosticEvent> = emptyList(),
    val duplicate: Boolean = false,
)

internal class StreamingAnalysisEngine(
    private val state: StreamingState,
    private val registry: StreamingAnalyserRegistry = StreamingAnalyserRegistry(),
    private val currentTimeMillis: () -> Long = System::currentTimeMillis,
) {
    fun process(event: CodeChangeEvent): AnalysisBatch {
        if (state.isProcessed(event.eventId)) {
            return AnalysisBatch(duplicate = true)
        }

        val now = currentTimeMillis()
        val validationError = validate(event)
        if (validationError != null) {
            state.markProcessed(event.eventId, now)
            return AnalysisBatch(diagnostics = listOf(diagnostic(event, "INVALID_EVENT", validationError, now)))
        }

        val previousProject = state.loadProject(event.projectId)
        val sources = previousProject.sources.toMutableMap()
        if (event.operation == CodeChangeOperation.DELETE) {
            sources.remove(event.filePath)
        } else {
            sources[event.filePath] = SourceSnapshot(
                eventId = event.eventId,
                filePath = event.filePath,
                language = event.language.trim().lowercase(),
                content = event.content.orEmpty(),
            )
        }

        val project = ProjectSnapshot(sources = sources.toMap())
        val diagnostics = mutableListOf<DiagnosticEvent>()
        val rawByPath = mutableMapOf<String, CodeContainer>()

        project.sources.values.sortedBy { it.filePath }.forEach { source ->
            val analyser = registry.find(source.language)
            if (analyser == null) {
                diagnostics += diagnostic(
                    event = event,
                    code = "UNSUPPORTED_LANGUAGE",
                    message = "No streaming analyser is registered for '${source.language}'",
                    now = now,
                    source = source,
                )
                return@forEach
            }

            try {
                rawByPath[source.filePath] = analyser.raw(source)
            } catch (error: Exception) {
                diagnostics += diagnostic(
                    event = event,
                    code = "RAW_ANALYSIS_FAILED",
                    message = error.message ?: error::class.simpleName.orEmpty(),
                    now = now,
                    source = source,
                )
            }
        }

        val projectClassNamesByPackage = rawByPath.values
            .flatMap { container ->
                container.DataStructures.mapNotNull { structure ->
                    if (structure.NodeName.isBlank()) {
                        null
                    } else {
                        structure.Package to listOf(structure.Package, structure.NodeName)
                            .filter { it.isNotBlank() }
                            .joinToString(".")
                    }
                }
            }
            .groupBy(keySelector = { it.first }, valueTransform = { it.second })
            .mapValues { (_, classNames) -> classNames.distinct().sorted() }

        val resolved = project.sources.values.sortedBy { it.filePath }.mapNotNull { source ->
            val analyser = registry.find(source.language) ?: return@mapNotNull null
            try {
                // JavaFullIdentListener expects this list to contain types from the
                // current package, not every same-named type in the project.
                val sourcePackage = rawByPath[source.filePath]?.PackageName.orEmpty()
                val samePackageClassNames = projectClassNamesByPackage[sourcePackage].orEmpty()
                AstEvent(
                    eventId = event.eventId,
                    sourceEventId = source.eventId,
                    projectId = event.projectId,
                    filePath = source.filePath,
                    language = source.language,
                    operation = CodeChangeOperation.UPSERT,
                    phase = AstPhase.RESOLVED,
                    ast = analyser.resolved(source, samePackageClassNames),
                    generatedAtEpochMs = now,
                )
            } catch (error: Exception) {
                diagnostics += diagnostic(
                    event = event,
                    code = "RESOLVED_ANALYSIS_FAILED",
                    message = error.message ?: error::class.simpleName.orEmpty(),
                    now = now,
                    source = source,
                )
                null
            }
        }

        val raw = when (event.operation) {
            CodeChangeOperation.UPSERT -> {
                val source = project.sources.getValue(event.filePath)
                rawByPath[event.filePath]?.let { ast ->
                    listOf(
                        AstEvent(
                            eventId = event.eventId,
                            sourceEventId = source.eventId,
                            projectId = event.projectId,
                            filePath = event.filePath,
                            language = source.language,
                            operation = CodeChangeOperation.UPSERT,
                            phase = AstPhase.RAW,
                            ast = ast,
                            generatedAtEpochMs = now,
                        ),
                    )
                }.orEmpty()
            }

            CodeChangeOperation.DELETE -> listOf(
                AstEvent(
                    eventId = event.eventId,
                    sourceEventId = event.eventId,
                    projectId = event.projectId,
                    filePath = event.filePath,
                    language = event.language.trim().lowercase(),
                    operation = CodeChangeOperation.DELETE,
                    phase = AstPhase.RAW,
                    ast = null,
                    generatedAtEpochMs = now,
                ),
            )
        }

        if (event.operation == CodeChangeOperation.DELETE) {
            state.deleteSource(event.projectId, event.filePath)
        } else {
            state.putSource(event.projectId, project.sources.getValue(event.filePath))
        }
        state.markProcessed(event.eventId, now)

        return AnalysisBatch(raw = raw, resolved = resolved, diagnostics = diagnostics)
    }

    private fun validate(event: CodeChangeEvent): String? = when {
        event.eventId.isBlank() -> "eventId must not be blank"
        event.projectId.isBlank() -> "projectId must not be blank"
        event.filePath.isBlank() -> "filePath must not be blank"
        event.language.isBlank() -> "language must not be blank"
        !registry.supports(event.language) -> "Unsupported language '${event.language}'"
        event.operation == CodeChangeOperation.UPSERT && event.content == null ->
            "content is required for UPSERT events"

        else -> null
    }

    private fun diagnostic(
        event: CodeChangeEvent,
        code: String,
        message: String,
        now: Long,
        source: SourceSnapshot? = null,
    ): DiagnosticEvent = DiagnosticEvent(
        eventId = event.eventId,
        projectId = event.projectId,
        filePath = source?.filePath ?: event.filePath,
        language = source?.language ?: event.language,
        severity = DiagnosticSeverity.ERROR,
        code = code,
        message = message,
        generatedAtEpochMs = now,
    )
}
