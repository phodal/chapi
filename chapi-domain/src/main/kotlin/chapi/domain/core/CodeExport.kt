package chapi.domain.core

import kotlinx.serialization.Serializable

@Serializable
data class CodeExport(
    var Name: String = "",
    var SourceFile: String = "",
    var Type: DataStructType = DataStructType.CLASS
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CodeExport) return false

        if (Name != other.Name) return false
        if (SourceFile != other.SourceFile) return false
        if (Type != other.Type) return false

        return true
    }

    override fun hashCode(): Int {
        var result = Name.hashCode()
        result = 31 * result + SourceFile.hashCode()
        result = 31 * result + Type.hashCode()
        return result
    }
}
