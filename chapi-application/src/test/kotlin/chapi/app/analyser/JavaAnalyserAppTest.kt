package chapi.app.analyser

import chapi.app.analyser.support.AbstractFile
import chapi.domain.core.CallType
import chapi.domain.core.CodeCall
import chapi.domain.core.CodeDataStruct
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.nio.file.Paths

internal class JavaAnalyserAppTest {
    @Test
    fun analysisByFile() {
        val resource = this.javaClass.classLoader.getResource("e2e/step2-java/Main.java")
        val path = Paths.get(resource!!.toURI()).toFile()

        val nodes = JavaAnalyserApp().analysisNodeByPath(path.absolutePath)
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
        val nodeMap: HashMap<String, CodeDataStruct> = HashMap()
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
        val nodeMap: HashMap<String, CodeDataStruct> = HashMap()
        for (node in nodes) {
            nodeMap[node.getClassFullName()] = node
        }

        val mainFunCallMap: HashMap<String, CodeCall> = HashMap()
        val mainFunc = nodeMap[".Main"]!!.Functions[0]
        for (function in mainFunc.FunctionCalls) {
            mainFunCallMap[function.NodeName] = function
        }

        assertEquals(mainFunCallMap["AggregateRootARepo"]!!.Type, CallType.SAME_PACKAGE)
        assertEquals(mainFunCallMap["AggregateRootARepo"]!!.Package, "repositories")
    }
}
