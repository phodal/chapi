package domain.core

open class CodeFile(
    var FullName: String = "",
    var PackageName: String = "",
    var Imports: Array<CodeImport> = arrayOf(),
    var Members: Array<CodeMember> = arrayOf(),
    var DataStructures: Array<CodeDataStruct<Any>> = arrayOf(),
    var Fields: Array<CodeField> = arrayOf()
) {

}
