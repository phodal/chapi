package chapi.domain.core

import kotlinx.serialization.Serializable

@Serializable
open class CodeField(
    var TypeType: String = "",
    var TypeValue: String = "",
    var TypeKey: String = "",
    var Modifiers: Array<String> = arrayOf(),
    // for TypeScript and JavaScript only, examples: `export default sample = createHello() `
    var Calls: Array<CodeCall> = arrayOf()
)
