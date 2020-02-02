package domain.core

open class CodeDataStruct<Any>(
    var NodeName: String = "",
    var Type: String = "",
    var Package: String = "",
    var FilePath: String = "",
    var Fields: Array<CodeField> = arrayOf<CodeField>(),
    var MultipleExtend: Array<String> = arrayOf<String>(),
    var Implements: Array<String> = arrayOf<String>(),
    var Extend: String = "",
    var Functions: Array<CodeFunction<Any>> = arrayOf<CodeFunction<Any>>(),
    var InnerStructures: Array<CodeDataStruct<Any>> = arrayOf<CodeDataStruct<Any>>(),
    var Annotations: Array<CodeAnnotation> = arrayOf<CodeAnnotation>(),
    var FunctionCalls: Array<CodeCall> = arrayOf<CodeCall>(),
    var InOutProperties: Array<CodeProperty> = arrayOf<CodeProperty>(),
    var Imports: Array<CodeImport> = arrayOf<CodeImport>(),
    var Extension: Any? = null
) {
    open fun isUtilClass(): Boolean {
        return this.NodeName.toLowerCase().contains("util") ||
                this.NodeName.toLowerCase().contains("utils")
    }
}
