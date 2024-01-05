package chapi.domain.core

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

@Serializable
data class CodePackage(
    var Name: String = "",
    var ID: String = "",
    var codeContainers: List<CodeContainer> = listOf(),
    var Packages : List<CodePackage> = listOf(),
    var Extension: JsonElement = JsonObject(HashMap())
)
