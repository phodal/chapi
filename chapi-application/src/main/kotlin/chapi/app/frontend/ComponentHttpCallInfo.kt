package chapi.app.frontend

import kotlinx.serialization.Serializable

@Serializable
data class ComponentHttpCallInfo(
    // sourceFile::ImportItem , also in mapping
    var inbounds: List<String> = listOf(),
    // sourceFile::ExportFunction
    var outbounds: List<String> = listOf(),
    // component name, only if is a component
    var name: String = "",
    var apiRef: List<HttpApiCallInfo> = listOf()
)

@Serializable
data class HttpApiCallInfo(
    var caller: String = "",
    var base: String = "",
    var url: String = "",
    var method: String = "",
    var data: String = ""
)
