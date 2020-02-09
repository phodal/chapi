package chapi.app.analyser

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.file.Paths

internal class ChapiAnalyserTest {
    @Throws(IOException::class)
    fun getResourceFiles(path: String): List<String> = getResourceAsStream(path).use {
        return if (it == null) emptyList() else BufferedReader(InputStreamReader(it)).readLines()
    }

    private fun getResourceAsStream(resource: String): InputStream? =
        Thread.currentThread().contextClassLoader.getResourceAsStream(resource)
            ?: resource::class.java.getResourceAsStream(resource)

    @Test
    fun analysisByPath() {
//        val files = getResourceFiles("e2e/step2-java")
        val resource = this.javaClass.classLoader.getResource("e2e/step2-java/Main.java")
        val file = Paths.get(resource!!.toURI()).toFile()

        var files = arrayOf<AbstractFile>()
        val toAbstractFile = AbstractFile.toAbstractFile(file)
        files += toAbstractFile

        val nodes = ChapiAnalyser().analysisByFiles(files)
        assertEquals(nodes.size, 1)
    }
}
