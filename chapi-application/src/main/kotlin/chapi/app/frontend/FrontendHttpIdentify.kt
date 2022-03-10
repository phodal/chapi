package chapi.app.frontend

import chapi.domain.core.CodeCall
import kotlinx.serialization.Serializable

@Serializable
data class ComponentHttpCall(
    // sourceFile::ImportItem , also in mapping
    var inbounds: List<String> = listOf(),
    // sourceFile::ExportFunction
    var outbounds: List<String> = listOf(),
    // component name, only if is a component
    var name: String = "",
    var apiRef: List<FeHttpApiCall> = listOf()
)

@Serializable
data class FeHttpApiCall(
    var caller: String = "",
    var base: String = "",
    var url: String = "",
    var method: String = "",
    var data: String = ""
)

interface HttpIdentify {
    fun isMatch(call: CodeCall): Boolean
    fun convert(call: CodeCall): FeHttpApiCall
}

class FrontendHttpIdentify {
}
