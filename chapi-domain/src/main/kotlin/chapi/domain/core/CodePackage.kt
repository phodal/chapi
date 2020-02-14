package chapi.domain.core

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

@Serializable
class CodePackage(
    var Name: String = "",
    var ID: String = "",
    var codeContainers: Array<CodeContainer> = arrayOf(),
    var Extension: JsonElement = JsonObject(HashMap())
) {

}
