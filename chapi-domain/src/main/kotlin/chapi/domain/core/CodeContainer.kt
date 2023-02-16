package chapi.domain.core

import kotlinx.serialization.Serializable

@Serializable
data class CodeContainer(
    var FullName: String = "",
    var PackageName: String = "",
    var Imports: List<CodeImport> = listOf(),
    var Members: List<CodeMember> = listOf(),
    var DataStructures: List<CodeDataStruct> = listOf(),
    var Fields: List<CodeField> = listOf(),
    var Containers: List<CodeContainer> = listOf()
)
