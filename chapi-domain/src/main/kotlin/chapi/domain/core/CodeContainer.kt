package chapi.domain.core

import kotlinx.serialization.Serializable

@Serializable
open class CodeContainer(
    var FullName: String = "",
    var PackageName: String = "",
    var Imports: Array<CodeImport> = arrayOf(),
    var Members: Array<CodeMember> = arrayOf(),
    var DataStructures: Array<CodeDataStruct> = arrayOf(),
    var Fields: Array<CodeField> = arrayOf(),
    var Containers: Array<CodeContainer> = arrayOf()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CodeContainer) return false

        if (FullName != other.FullName) return false
        if (PackageName != other.PackageName) return false
        if (!Imports.contentEquals(other.Imports)) return false
        if (!Members.contentEquals(other.Members)) return false
        if (!DataStructures.contentEquals(other.DataStructures)) return false
        if (!Fields.contentEquals(other.Fields)) return false
        if (!Containers.contentEquals(other.Containers)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = FullName.hashCode()
        result = 31 * result + PackageName.hashCode()
        result = 31 * result + Imports.contentHashCode()
        result = 31 * result + Members.contentHashCode()
        result = 31 * result + DataStructures.contentHashCode()
        result = 31 * result + Fields.contentHashCode()
        result = 31 * result + Containers.contentHashCode()
        return result
    }
}

