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

}
