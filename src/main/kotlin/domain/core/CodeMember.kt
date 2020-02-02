package domain.core

class CodeMember(
    var ID: String = "",
    var FileID: String = "",
    var DataStructID: String = "",
    var AliasPackage: String = "",
    var Name: String = "",
    var Type: String = "",
    var StructureNodes: Array<CodeDataStruct<Any>> = arrayOf(),
    var FunctionNodes: Array<CodeFunction<Any>> = arrayOf(),
    var Namespace: Array<String> = arrayOf(),
    var Position: CodePosition = CodePosition()
) {

}
