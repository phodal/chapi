package domain.core

open class CodeFunction(
    var Name: String = "",
    var ReturnType: String = "",
    var MultipleReturns: Array<CodeProperty> = arrayOf(),
    var Parameters: Array<CodeProperty> = arrayOf(),
    var FunctionCalls: Array<CodeCall> = arrayOf(),
    var Annotations: Array<CodeAnnotation> = arrayOf(),
    var Override: Boolean = false,
    var Modifiers: Array<String> = arrayOf(),
    var InnerStructures: Array<CodeDataStruct> = arrayOf(),
    var InnerFunctions: Array<CodeFunction> = arrayOf(),
    var Extension: Any? = null,
    var Position: CodePosition = CodePosition()
) {
    fun isJavaLangReturnType(): Boolean {
        return this.ReturnType == "String" || this.ReturnType == "int" || this.ReturnType == "float" || this.ReturnType == "void" || this.ReturnType == "char" || this.ReturnType == "double"
    }

    fun isStatic(): Boolean {
        for (modifier in this.Modifiers) {
            if (modifier == "static") {
                return true
            }
        }

        return false
    }

    fun isGetterSetter(): Boolean {
        return this.Name.startsWith("set") || this.Name.startsWith("get")
    }
}
