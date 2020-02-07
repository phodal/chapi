package domain.core

import kotlinx.serialization.*
import kotlinx.serialization.json.*

@Serializable
class CodeProperty(
    var Modifiers: Array<String> = arrayOf(),
    var ParamName: String = "",
    var TypeValue: String,
    var TypeType: String,
    var ReturnTypes: Array<CodeProperty> = arrayOf(),
    var Parameters: Array<CodeProperty> = arrayOf()
) {

}
