package chapi.domain.core

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

@Serializable
class CodePackage(
    var Name: String = "",
    var ID: String = "",
    var codeContainers: Array<CodeContainer> = arrayOf(),
    var Packages : Array<CodePackage> = arrayOf(),

    var Extension: JsonElement = JsonObject(HashMap())
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CodePackage) return false

        if (Name != other.Name) return false
        if (ID != other.ID) return false
        if (!codeContainers.contentEquals(other.codeContainers)) return false
        if (!Packages.contentEquals(other.Packages)) return false
        if (Extension != other.Extension) return false

        return true
    }

    override fun hashCode(): Int {
        var result = Name.hashCode()
        result = 31 * result + ID.hashCode()
        result = 31 * result + codeContainers.contentHashCode()
        result = 31 * result + Packages.contentHashCode()
        result = 31 * result + Extension.hashCode()
        return result
    }
}
