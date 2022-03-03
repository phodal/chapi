package chapi.app.analyser

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import java.nio.file.Paths
import kotlin.test.assertEquals

internal class CSharpAnalyserAppTest {
    @Test
    fun shouldIdentifySamePackage() {
        val resource = this.javaClass.classLoader.getResource("e2e/csharp/")
        val path = Paths.get(resource!!.toURI()).toFile()

        val nodes = CSharpAnalyserApp().analysisNodeByPath(path.absolutePath)
        println(Json.encodeToString(nodes))
        assertEquals(5, nodes.size);

//        FileWriter("output.json").use { it.write(Json.encodeToString(nodes)) }
//        val sb = StringBuilder()
//        nodes.forEach {
//            it.Functions.forEach { func ->
//                run {
//                    func.FunctionCalls.forEach { call ->
//                        run {
//                            val nodeName = call.NodeName
//                            if (!(nodeName == "axios" || nodeName.startsWith("axios."))) {
//                                if (call.Parameters.isNotEmpty()) {
//                                    sb.append("$nodeName -> ${call.Parameters[0].TypeValue}\n")
//                                } else {
//                                    sb.append("${func.Name}.$nodeName\n")
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        println(sb)
    }
}
