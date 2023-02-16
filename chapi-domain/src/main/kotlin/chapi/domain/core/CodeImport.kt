package chapi.domain.core

import kotlinx.serialization.Serializable

@Serializable
data class CodeImport(
    var Source: String = "",
    // todo: define for new usage
    var AsName: String = "",
    // import UsageName from 'usage'
    // import AsSource as UsageName from 'source'
    var UsageName: Array<String> = arrayOf(),
    var Scope: String = ""
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CodeImport

        if (Source != other.Source) return false
        if (AsName != other.AsName) return false
        if (!UsageName.contentEquals(other.UsageName)) return false
        if (Scope != other.Scope) return false

        return true
    }

    override fun hashCode(): Int {
        var result = Source.hashCode()
        result = 31 * result + AsName.hashCode()
        result = 31 * result + UsageName.contentHashCode()
        result = 31 * result + Scope.hashCode()
        return result
    }
}
