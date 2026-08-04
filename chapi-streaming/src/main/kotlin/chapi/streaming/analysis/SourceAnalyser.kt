package chapi.streaming.analysis

import chapi.ast.javaast.JavaAnalyser
import chapi.domain.core.CodeContainer
import chapi.streaming.model.SourceSnapshot

internal interface StreamingSourceAnalyser {
    fun raw(source: SourceSnapshot): CodeContainer

    fun resolved(source: SourceSnapshot, projectClassNames: List<String>): CodeContainer
}

internal class JavaStreamingSourceAnalyser : StreamingSourceAnalyser {
    override fun raw(source: SourceSnapshot): CodeContainer =
        JavaAnalyser().identBasicInfo(source.content, source.filePath)

    override fun resolved(source: SourceSnapshot, projectClassNames: List<String>): CodeContainer =
        JavaAnalyser().identFullInfo(
            str = source.content,
            fileName = source.filePath,
            classes = projectClassNames,
        )
}

internal class StreamingAnalyserRegistry(
    private val analysers: Map<String, StreamingSourceAnalyser> = mapOf(
        "java" to JavaStreamingSourceAnalyser(),
    ),
) {
    fun find(language: String): StreamingSourceAnalyser? = analysers[normalize(language)]

    fun supports(language: String): Boolean = find(language) != null

    private fun normalize(language: String): String = language.trim().lowercase()
}
