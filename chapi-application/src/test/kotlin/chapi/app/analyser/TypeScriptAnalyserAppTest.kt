package chapi.app.analyser

import chapi.domain.core.CodeCall
import chapi.domain.core.CodePosition
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import java.nio.file.Paths
import kotlin.test.assertEquals

data class ConsumerModel(
    // like component
    var ConsumerName: String = "",
    // API name
    var TargetName: String = "",
    // consumer file path
    var ConsumerFile: String = "",
    // a simple log for consumer
    var ConsumerCallLog: String = "",
    var ConsumerPosition: CodePosition = CodePosition()
)

@Serializable
data class ApiAdapter(
    var SourceFile: String = "",
    var SourceFunc: String = "",
    var TargetApi: String = "",
    var TypeValue: String = ""
)

internal class TypeScriptAnalyserAppTest {
    @Test
    fun shouldIdentifySamePackage() {
        val resource = this.javaClass.classLoader.getResource("languages/ts/apicall")!!
        val path = Paths.get(resource.toURI()).toFile().absolutePath

        val nodes = TypeScriptAnalyserApp().analysisNodeByPath(path)
        assertEquals(4, nodes.size)

        var calls: List<CodeCall> = listOf()
        nodes.map { ds ->
            ds.Functions.map {
                val isComponent = it.fileExt() == ".tsx"
                if (isComponent) {
                    println(it.FilePath)
                }
            }
        }

        val sb = StringBuilder()
        val adapters: List<ApiAdapter> = listOf();
        nodes.forEach {
            it.Functions.forEach { func ->
                run {
                    func.FunctionCalls.forEach { call ->
                        run {
                            val nodeName = call.NodeName
                            if (!(nodeName == "axios" || nodeName.startsWith("axios."))) {
                                val adapter = ApiAdapter()
                                if (call.Parameters.isNotEmpty()) {
                                    sb.append("$nodeName -> ${call.Parameters[0].TypeValue}\n")
                                    adapter.SourceFunc = nodeName
                                    adapter.TypeValue = call.Parameters[0].TypeValue
                                } else {
                                    adapter.SourceFunc = nodeName
                                    sb.append("${func.Name}.$nodeName\n")
                                }
                            }
                        }
                    }
                }
            }
        }

        println(Json.encodeToString(adapters))
        println(sb)
    }
}
