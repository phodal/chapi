package domain.core

open class CodeCall(
    var Package: String = "",
    var Type: String = "",
    var NodeName: String = "",
    var MethodName: String = "",
    var Parameters: Array<CodeProperty> = arrayOf<CodeProperty>(),
    var Position: CodePosition = CodePosition()
) {
    open fun buildClassFullName(): String {
        return this.Package + "." + this.NodeName
    }
}
