package chapi.ast.kotlinast

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import java.io.BufferedReader
import java.io.File
import java.nio.file.Paths
import kotlin.test.assertTrue

internal class KotlinAnalyserTest {
    // add compatibility tests for Kotlin with psi?? https://github.com/JetBrains/kotlin/tree/master/compiler/testData/psi

    private val analyser = KotlinAnalyser()
    private val modulePath: File by lazy { Paths.get("").toFile().absoluteFile }
    private val kotlinFiles: Sequence<File> by lazy { modulePath.walk().filter { it.isFile && it.extension == "kt" } }

    @Test
    fun `should not crash for grammar file in basic identifier mode`() = runBlocking {
        val codeContainers = kotlinFiles
            .map {
                async { analyser.analysis(it.bufferedReader().use(BufferedReader::readText), it.name) }
            }
            .toList().awaitAll()
            .onEach {
                println("${it.FullName}, contains ${it.DataStructures.size} data structures")
            }

        assertTrue { codeContainers.isNotEmpty() }
    }
}
