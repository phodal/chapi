package chapi.domain.core

import kotlinx.serialization.Serializable

@Serializable
data class CodeProperty(
    var Modifiers: List<String> = listOf(),
    var DefaultValue: String = "",
    var TypeValue: String,
    var TypeType: String,
    var Annotations: List<CodeAnnotation> = listOf(),
    // for TypeScript and Parameter
    var ObjectValue: List<CodeProperty> = listOf(),
    var ReturnTypes: List<CodeProperty> = listOf(),
    var Parameters: List<CodeProperty> = listOf(),
)
