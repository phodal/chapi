package chapi.ast.arkts

import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertTrue

@Tag("compatibility")
class ArkTSCorpusCompatibilityTest {
    @Test
    fun parseClassifiedPositiveCorpus() {
        val root = File(requireNotNull(System.getProperty("arkts.corpus.dir")))
        require(root.isDirectory) { "ArkTS corpus directory does not exist: $root" }

        val files = root.walkTopDown()
            .filter { it.isFile && (it.name.endsWith(".ets") || it.name.endsWith(".d.ets")) }
            .sortedBy { it.relativeTo(root).invariantSeparatorsPath }
            .toList()
        require(files.isNotEmpty()) { "No .ets or .d.ets files found under $root" }

        val analyser = ArkTSAnalyser()
        val failures = files.mapNotNull { file ->
            val result = analyser.analysisWithDiagnostics(file.readText(), file.relativeTo(root).invariantSeparatorsPath)
            if (result.diagnostics.isEmpty()) {
                null
            } else {
                val first = result.diagnostics.first()
                "${file.relativeTo(root).invariantSeparatorsPath}:${first.line}:${first.column} ${first.message}"
            }
        }

        println("ArkTS corpus compatibility: parsed=${files.size}, passed=${files.size - failures.size}, failed=${failures.size}")
        failures.forEach(::println)
        assertTrue(failures.isEmpty(), "ArkTS compatibility failures: ${failures.size}/${files.size}")
    }
}
