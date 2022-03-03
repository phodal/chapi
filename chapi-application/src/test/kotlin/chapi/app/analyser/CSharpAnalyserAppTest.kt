package chapi.app.analyser

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.io.FileWriter

internal class CSharpAnalyserAppTest {
    @Disabled
    @Test
    fun shouldIdentifySamePackage() {
        val testPath = ""
        val nodes = CSharpAnalyserApp().analysisNodeByPath(testPath)
        println(nodes)

        FileWriter("output.json").use { it.write(Json.encodeToString(nodes)) }
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
