package chapi.domain.core

import kotlinx.serialization.Serializable

@Serializable
class CodeMember(
    var ID: String = "",
    var FileID: String = "",
    var DataStructID: String = "",
    var AliasPackage: String = "",
    var Name: String = "",
    var Type: String = "",
    var StructureNodes: Array<CodeDataStruct> = arrayOf(),
    var FunctionNodes: Array<CodeFunction> = arrayOf(),
    var Namespace: Array<String> = arrayOf(),
    var Position: CodePosition = CodePosition()
) {

    fun buildMemberId() {
        val isDefaultFunction = this.DataStructID == "default"
        when {
            isDefaultFunction -> {
                for (node in this.FunctionNodes) {
                    this.ID = this.AliasPackage + ":" + node.Name
                }
            }
            else -> {
                var packageName = this.FileID
                if (this.FileID != this.AliasPackage) {
                    packageName = this.FileID + "|" + this.AliasPackage
                }
                if (this.FileID == "" && this.AliasPackage != "") {
                    packageName = this.AliasPackage
                }

                this.ID = packageName + "::" + this.DataStructID
            }
        }
    }

    // position was removed, if one function change, others position will also change
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CodeMember) return false

        if (ID != other.ID) return false
        if (FileID != other.FileID) return false
        if (DataStructID != other.DataStructID) return false
        if (AliasPackage != other.AliasPackage) return false
        if (Name != other.Name) return false
        if (Type != other.Type) return false
        if (!StructureNodes.contentEquals(other.StructureNodes)) return false
        if (!FunctionNodes.contentEquals(other.FunctionNodes)) return false
        if (!Namespace.contentEquals(other.Namespace)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = ID.hashCode()
        result = 31 * result + FileID.hashCode()
        result = 31 * result + DataStructID.hashCode()
        result = 31 * result + AliasPackage.hashCode()
        result = 31 * result + Name.hashCode()
        result = 31 * result + Type.hashCode()
        result = 31 * result + StructureNodes.contentHashCode()
        result = 31 * result + FunctionNodes.contentHashCode()
        result = 31 * result + Namespace.contentHashCode()
        result = 31 * result + Position.hashCode()
        return result
    }
}
