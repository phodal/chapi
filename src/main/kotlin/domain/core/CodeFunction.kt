package domain.core

open class CodeFunction<Any>(
    var Name: String = "",
    var ReturnType: String = "",
    var MultipleReturns: Array<CodeProperty> = arrayOf<CodeProperty>(),
    var Parameters: Array<CodeProperty> = arrayOf<CodeProperty>(),
    var FunctionCalls: Array<CodeCall> = arrayOf<CodeCall>(),
    var Annotations: Array<CodeAnnotation> = arrayOf<CodeAnnotation>(),
    var Override: Boolean = false,
    var Modifiers: Array<String> = arrayOf<String>(),
    var InnerStructures: Array<CodeDataStruct<Any>> = arrayOf<CodeDataStruct<Any>>(),
    var InnerFunctions: Array<CodeFunction<Any>> = arrayOf<CodeFunction<Any>>(),
    var Extension: Any? = null,
    var Position: CodePosition = CodePosition()
) {
    fun isJavaLangReturnType(): Boolean {
        return this.ReturnType == "String" || this.ReturnType == "int" || this.ReturnType == "float" || this.ReturnType == "void" || this.ReturnType == "char" || this.ReturnType == "double"
    }
}
