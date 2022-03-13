package chapi.app.backend

import chapi.domain.core.CodeCall
import chapi.domain.core.CodeDataStruct

/**
 * TODO display the overview of the apis for the legacy system
 * vs open api, swagger, etc
 * |    they are used to unify the api integration and build the api documentation.
 * |    this analyser is just to parse and show the topology of the apis and the components behind them.
 * |    so we shouldn't care the details of the api, like parameter type, the data structure and the body serialization. etc.
 */
class BackendApiAnalyser {
    fun analysis(nodes: Array<CodeDataStruct>, path: String): Array<ComponentHttpEndpointInfo> {
        TODO()
    }
}

// 0. start at a component (code-data-struct with special annotations)
// 1. got a bunch of http endpoints (functions with special annotations)
// 2. each endpoint may depend on some dependencies (function calls)
// 3. each function call refers to a function, may depend on more dependencies (function calls)
// ...
// n. until no more dependencies (e.g. end at interface or jar library
data class ComponentHttpEndpointInfo(
    val name: String,
    val base: String,
    val apiRef: List<HttpEndpoint>,
)

data class HttpEndpoint(
    val base: String,
    val path: String,
    val method: String,
    val name: String,
    val variables: List<HttpEndpointPathVariable>,
    val parameters: List<HttpEndpointParameter>,
    val hasRequestBody: Boolean,
    val hasResponseBody: Boolean,
    // TODO group with the node (e.g. call 2 methods which are in the same interface)
    val dependencies: List<FunctionDependency>,
) {
    val url: String = "$base$path"
}

data class HttpEndpointPathVariable(
    val name: String,
    val type: String,
)

data class HttpEndpointParameter(
    val name: String,
    val type: String,
    val required: Boolean,
    val defaultValue: String,
)

// TODO show more details of the component dependency
data class FunctionDependency(
    val functionCall: CodeCall,
    val dependencies: List<FunctionDependency>,
)
