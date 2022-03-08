package chapi.app.analyser

import chapi.domain.core.CodeCall
import kotlinx.serialization.Serializable
import org.junit.jupiter.api.Test
import java.nio.file.Paths
import kotlin.test.assertEquals

@Serializable
data class ApiAdapter(
    var SourceFile: String = "",
    var SourceFunc: String = "",
    var TargetApi: String = "",
    var TypeValue: String = ""
)

internal class TypeScriptAnalyserAppTest {
    var callMap: HashMap<String, CodeCall> = hashMapOf()
    var httpAdapterMap: HashMap<String, CodeCall> = hashMapOf()

    @Test
    fun shouldIdentifySamePackage() {
        val resource = this.javaClass.classLoader.getResource("languages/ts/apicall")!!
        val path = Paths.get(resource.toURI()).toFile().absolutePath

        val nodes = TypeScriptAnalyserApp().analysisNodeByPath(path)
        assertEquals(4, nodes.size)

        // fileName::componentName
        val componentList: MutableList<String> = mutableListOf()

        // 1. first create Component with FunctionCall maps based on Import
        // 2. build axios/umi-request to a API call method
        // 3. mapping for results
        nodes.forEach {
            val isComponent = it.fileExt() == "tsx"
            if (isComponent) {
                componentList += "${it.FilePath}::${it.NodeName}"
            }

            it.Functions.forEach { func ->
                var calleeName = "${it.FilePath}::${it.NodeName}::${func.Name}"
                if (isComponent) {
                    calleeName = "${it.FilePath}::${it.NodeName}"
                }

                run {
                    func.FunctionCalls.forEach { call ->
                        run {
                            callMap[calleeName] = call
                        }
                    }
                    func.InnerFunctions.forEach { inner ->
                        run {
                            inner.FunctionCalls.forEach { innerCall ->
                                run {
                                    callMap[calleeName] = innerCall
                                    identAxios(innerCall, httpAdapterMap, calleeName)
                                }
                            }
                        }
                    }
                }
            }
        }

        println(callMap)
        println(httpAdapterMap)
    }

    private fun identAxios(
        call: CodeCall,
        httpAdapterMap: HashMap<String, CodeCall>,
        calleeName: String,
    ) {
        val callName = call.FunctionName
        if (callName == "axios" || callName.startsWith("axios.")) {
            if (call.Parameters.isNotEmpty()) {
                httpAdapterMap[calleeName] = call
            }
        }
    }
}
