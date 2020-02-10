package domain.core

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

@Serializable
class CodeProperty(
    var Modifiers: Array<String> = arrayOf(),
    var ParamName: String = "",
    var TypeValue: String,
    var TypeType: String,
    var ReturnTypes: Array<CodeProperty> = arrayOf(),
    var Parameters: Array<CodeProperty> = arrayOf()
) {
    override fun toString(): String {
        return Json(JsonConfiguration.Stable).stringify(serializer(), this)
    }
}
