package chapi.app.analyser

import chapi.domain.core.CodeCall
import chapi.domain.core.CodeFunction
import kotlinx.serialization.Serializable
import org.junit.jupiter.api.Test
import java.nio.file.Paths
import kotlin.test.assertEquals

@Serializable
data class ApiAdapter(
    // sourceFile::ImportItem
    // also in mapping
    var Import: List<String> = listOf(),
    // sourceFile::ExportFunction
    var Export: List<String> = listOf(),
    // component name, only if is a component
    var ComponentName: String = "",
    var FilePath: String = ""
)

internal class TypeScriptAnalyserAppTest {
    // componentMapList: Import, Export
    var componentCallMap: HashMap<String, MutableList<String>> = hashMapOf()
    var callMap: HashMap<String, CodeCall> = hashMapOf()
    var httpAdapterMap: HashMap<String, CodeCall> = hashMapOf()

    @Test
    fun shouldIdentifySamePackage() {
        val resource = this.javaClass.classLoader.getResource("languages/ts/apicall")!!
        val path = Paths.get(resource.toURI()).toFile().absolutePath

//        val path = "/Volumes/source/archguard/archguard-frontend/archguard/src/.umi/core/devScripts.ts"
        val nodes = TypeScriptAnalyserApp().analysisNodeByPath(path)
        assertEquals(4, nodes.size)

        // fileName::componentName
        val componentList: MutableList<String> = mutableListOf()

        val inbounds: MutableList<String> = mutableListOf()
        val outbounds: MutableList<String> = mutableListOf()

        // 1. first create Component with FunctionCall maps based on Import
        // 2. build axios/umi-request to an API call method
        // 3. mapping for results
        nodes.forEach { node ->
            val isComponent = node.fileExt() == "tsx"
            val moduleName = node.fileWithoutSuffix()

            node.Imports.forEach { imp ->
                imp.UsageName.forEach {
                    println(node.FilePath)
                    inbounds += "${imp.Source}::${it}"
                }
            }

            val componentName = naming(moduleName, node.NodeName)
            if (isComponent) {
                componentList += componentName
                componentCallMap[componentName] = mutableListOf()
            }

            node.Functions.forEach { func ->
                outbounds += moduleName + "::" + func.Name

                var calleeName = "$componentName::${func.Name}"
                if (isComponent) {
                    calleeName = componentName
                }

                // todo: recursion to collection all calls
                run {
                    func.FunctionCalls.forEach { call ->
                        run {
                            callMap[calleeName] = call
                            identAxios(call, httpAdapterMap, calleeName)
                            if (isComponent) {
                                componentCallMap[componentName]?.plusAssign((call.FunctionName))
                            }
                        }
                    }
                    recursiveCall(func, calleeName, isComponent, componentName)
                }
            }
        }

        println(inbounds)
        println(outbounds)

        println(httpAdapterMap)
//        println(callMap)
//        println(componentCallMap)
    }

    private fun recursiveCall(func: CodeFunction, calleeName: String, isComponent: Boolean, componentName: String) {
        func.InnerFunctions.forEach { inner ->
            run {
                inner.FunctionCalls.forEach { innerCall ->
                    run {
                        callMap[calleeName] = innerCall
                        identAxios(innerCall, httpAdapterMap, calleeName)
                        if (isComponent) {
                            componentCallMap[componentName]?.plusAssign((innerCall.FunctionName))
                        }
                    }
                }
            }

            func.InnerFunctions.forEach {
                recursiveCall(it, calleeName, isComponent, componentName)
            }
        }
    }

    private fun naming(moduleName: String, nodeName: String): String {
        if (nodeName == "default") {
            return moduleName
        }

        return "${moduleName}::${nodeName}"
    }

    private fun identAxios(call: CodeCall, httpAdapterMap: HashMap<String, CodeCall>, calleeName: String) {
        val callName = call.FunctionName
        if (callName == "axios" || callName.startsWith("axios.")) {
            if (call.Parameters.isNotEmpty()) {
                httpAdapterMap[calleeName] = call
            }
        }
    }
}
