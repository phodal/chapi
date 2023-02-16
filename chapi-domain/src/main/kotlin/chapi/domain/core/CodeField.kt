package chapi.domain.core

import chapi.domain.core.CodeCall
import kotlinx.serialization.Serializable

@Serializable
data class CodeField(
    var TypeType: String = "",
    var TypeValue: String = "",
    var TypeKey: String = "",
    var Annotations: List<CodeAnnotation> = listOf(),
    var Modifiers: List<String> = listOf(),
    // for TypeScript and JavaScript only, examples: `export default sample = createHello() `
    var Calls: List<CodeCall> = listOf()
)
