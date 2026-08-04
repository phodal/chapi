package chapi.streaming.analysis

import chapi.streaming.model.ProjectSnapshot
import chapi.streaming.model.SourceSnapshot
import java.util.concurrent.ConcurrentHashMap

internal interface StreamingState {
    fun isProcessed(eventId: String): Boolean

    fun markProcessed(eventId: String, processedAtEpochMs: Long)

    fun loadProject(projectId: String): ProjectSnapshot

    fun putSource(projectId: String, source: SourceSnapshot)

    fun deleteSource(projectId: String, filePath: String)
}

internal class InMemoryStreamingState : StreamingState {
    private val processedEvents = ConcurrentHashMap<String, Long>()
    private val projects = ConcurrentHashMap<String, ProjectSnapshot>()

    override fun isProcessed(eventId: String): Boolean = processedEvents.containsKey(eventId)

    override fun markProcessed(eventId: String, processedAtEpochMs: Long) {
        processedEvents[eventId] = processedAtEpochMs
    }

    override fun loadProject(projectId: String): ProjectSnapshot = projects[projectId] ?: ProjectSnapshot()

    override fun putSource(projectId: String, source: SourceSnapshot) {
        val current = loadProject(projectId)
        projects[projectId] = current.copy(sources = current.sources + (source.filePath to source))
    }

    override fun deleteSource(projectId: String, filePath: String) {
        val current = loadProject(projectId)
        projects[projectId] = current.copy(sources = current.sources - filePath)
    }
}
