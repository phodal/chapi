package chapi.app.frontend

import chapi.app.frontend.path.ecmaImportConvert
import chapi.app.frontend.path.relativeRoot
import chapi.domain.core.CodeCall
import chapi.domain.core.CodeDataStruct
import chapi.domain.core.CodeFunction

class FrontendApiAnalyser {
    private var componentCallMap: HashMap<String, MutableList<String>> = hashMapOf()
    private var componentInbounds: HashMap<String, MutableList<String>> = hashMapOf()
    private var callMap: HashMap<String, CodeCall> = hashMapOf()
    private var httpAdapterMap: HashMap<String, CodeCall> = hashMapOf()

    // 1. first create Component with FunctionCall maps based on Import
    // 2. build axios/umi-request to an API call method
    // 3. mapping for results
    fun analysis(nodes: Array<CodeDataStruct>, path: String): Array<ComponentHttpCallInfo> {
        nodes.forEach { node ->
            val inbounds: MutableList<String> = mutableListOf()
            val outbounds: MutableList<String> = mutableListOf()

            val isComponent = node.fileExt() == "tsx"
            val moduleName = relativeRoot(path, node.FilePath).substringBeforeLast('.', "")

            node.Imports.forEach { imp ->
                imp.UsageName.forEach {
                    val source = ecmaImportConvert(path, node.FilePath, imp.Source)
                    inbounds += naming(source, it)
                }
            }

            val componentName = naming(moduleName, node.NodeName)
            if (isComponent) {
                componentCallMap[componentName] = mutableListOf()
            }

            node.Functions.forEach { func ->
                outbounds += naming(moduleName, func.Name)

                var calleeName = naming(componentName, func.Name)
                if (isComponent) {
                    calleeName = componentName
                }

                // todo: recursion to collection all calls
                run {
                    func.FunctionCalls.forEach { call ->
                        run {
                            callMap[calleeName] = call
                            findAxiosToHttpList(call, httpAdapterMap, calleeName)
                            if (isComponent) {
                                componentCallMap[componentName]?.plusAssign((call.FunctionName))
                            }
                        }
                    }
                    recursiveCall(func, calleeName, isComponent, componentName)
                }
            }

            if (isComponent) {
                componentInbounds[componentName] = inbounds
            }
        }

        var componentCalls: Array<ComponentHttpCallInfo> = arrayOf();
        componentInbounds.forEach { map ->
            val componentRef = ComponentHttpCallInfo(name = map.key)
            map.value.forEach {
                if (httpAdapterMap[it] != null) {
                    val call = httpAdapterMap[it]!!
                    val httpApi = HttpApiCallInfo()

                    httpApi.caller = it
                    call.Parameters.forEach { prop ->
                        for (codeProperty in prop.ObjectValue) {
                            when (codeProperty.TypeValue) {
                                "baseURL" -> {
                                    httpApi.base = codeProperty.ObjectValue[0].TypeValue
                                }
                                "url" -> {
                                    httpApi.url = codeProperty.ObjectValue[0].TypeValue
                                }
                                "method" -> {
                                    httpApi.method = codeProperty.ObjectValue[0].TypeValue
                                }
                                "data" -> {
                                    httpApi.data = codeProperty.ObjectValue[0].TypeValue
                                }
                            }
                        }
                    }

                    httpApi.caller = naming("api/addition/systemInfo", "updateSystemInfo")

                    componentRef.apiRef += httpApi
                }
            }

            if (componentRef.apiRef.isNotEmpty()) {
                componentCalls += componentRef
            }
        }
        return componentCalls
    }

    private fun recursiveCall(func: CodeFunction, calleeName: String, isComponent: Boolean, componentName: String) {
        func.InnerFunctions.forEach { inner ->
            run {
                inner.FunctionCalls.forEach { innerCall ->
                    run {
                        callMap[calleeName] = innerCall
                        findAxiosToHttpList(innerCall, httpAdapterMap, calleeName)
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

    private fun findAxiosToHttpList(call: CodeCall, httpAdapterMap: HashMap<String, CodeCall>, calleeName: String) {
        val callName = call.FunctionName
        if (callName == "axios" || callName.startsWith("axios.")) {
            if (call.Parameters.isNotEmpty()) {
                httpAdapterMap[calleeName] = call
            }
        }
    }

}
