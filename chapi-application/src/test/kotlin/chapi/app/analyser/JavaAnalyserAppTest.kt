package chapi.app.analyser

import chapi.app.analyser.support.AbstractFile
import chapi.domain.core.CodeCall
import chapi.domain.core.CodeDataStruct
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.file.Paths

internal class JavaAnalyserAppTest {
    @Throws(IOException::class)
    fun getResourceFiles(path: String): List<String> = getResourceAsStream(path).use {
        return if (it == null) emptyList() else BufferedReader(InputStreamReader(it)).readLines()
    }

    private fun getResourceAsStream(resource: String): InputStream? =
        Thread.currentThread().contextClassLoader.getResourceAsStream(resource)
            ?: resource::class.java.getResourceAsStream(resource)

    @Test
    fun analysisByFile() {
        val resource = this.javaClass.classLoader.getResource("e2e/step2-java/Main.java")
        val file = Paths.get(resource!!.toURI()).toFile()

        var files = arrayOf<AbstractFile>()
        val toAbstractFile = AbstractFile.toAbstractFile(file)
        files += toAbstractFile

        val nodes = JavaAnalyserApp().analysisByFiles(files)
        assertEquals(nodes.size, 1)
    }

    @Test
    fun analysisByDir() {
        val resource = this.javaClass.classLoader.getResource("e2e/step2-java/")
        val path = Paths.get(resource!!.toURI()).toFile()

        val nodes = JavaAnalyserApp().analysisNodeByPath(path.absolutePath)
        assertEquals(nodes.size, 14)
    }

    @Test
    fun shouldIdentifyImport() {
        val resource = this.javaClass.classLoader.getResource("e2e/step2-java/")
        val path = Paths.get(resource!!.toURI()).toFile()

        val nodes = JavaAnalyserApp().analysisNodeByPath(path.absolutePath)
        var nodeMap: HashMap<String, CodeDataStruct> = HashMap()
        for (node in nodes) {
            nodeMap[node.getClassFullName()] = node
        }

        assertEquals(nodeMap["repositories.Repository"]!!.Imports.size, 1)
        assertEquals(nodeMap["repositories.Repository"]!!.Imports[0].Source, "domain")
        assertEquals(nodeMap["repositories.Repository"]!!.FilePath, "Repository.java")
    }

    @Test
    fun shouldIdentifySamePackage() {
        val resource = this.javaClass.classLoader.getResource("e2e/step2-java/")
        val path = Paths.get(resource!!.toURI()).toFile()

        val nodes = JavaAnalyserApp().analysisNodeByPath(path.absolutePath)
        var nodeMap: HashMap<String, CodeDataStruct> = HashMap()
        for (node in nodes) {
            nodeMap[node.getClassFullName()] = node
        }

        var mainFunCallMap: HashMap<String, CodeCall> = HashMap()
        val mainFunc = nodeMap[".Main"]!!.Functions[0]
        for (function in mainFunc.FunctionCalls) {
            mainFunCallMap[function.NodeName] = function
        }

        assertEquals(mainFunCallMap["AggregateRootARepo"]!!.Type, "same package")
        assertEquals(mainFunCallMap["AggregateRootARepo"]!!.Package, "repositories")
    }
}
