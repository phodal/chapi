package chapi.domain.core

import kotlinx.serialization.*
import kotlinx.serialization.json.*

@Serializable
open class CodeFunction(
    var Name: String = "",
    var Package : String = "",
    var ReturnType: String = "",
    var MultipleReturns: Array<CodeProperty> = arrayOf(),
    var Parameters: Array<CodeProperty> = arrayOf(),
    var FunctionCalls: Array<CodeCall> = arrayOf(),
    var Annotations: Array<CodeAnnotation> = arrayOf(),
    var Override: Boolean = false,
    var Modifiers: Array<String> = arrayOf(),
    var InnerStructures: Array<CodeDataStruct> = arrayOf(),
    var InnerFunctions: Array<CodeFunction> = arrayOf(),
    var Position: CodePosition = CodePosition(),
    var Extension: JsonElement = JsonObject(HashMap()),
    var LocalVariables : Array<CodeProperty> = arrayOf(),
    var IsConstructor: Boolean = false // todo: move to extension
) {
    private var extensionMap = HashMap<String, JsonElement>()

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

    fun buildFullMethodName(node: CodeDataStruct): String {
        return node.Package + "." + node.NodeName + "." + this.Name
    }


    fun getAllCallString(): Array<String> {
        var calls = arrayOf<String>()
        for (codeCall in this.FunctionCalls) {
            if (codeCall.NodeName != "") {
                calls += codeCall.buildClassFullName()
            }
        }

        return calls
    }

    fun isJUnitTest(): Boolean {
        var isTest = false
        for (annotation in this.Annotations) {
            if (annotation.isIgnoreOrTest()) {
                isTest = true
            }
        }
        return isTest
    }

    fun isOverride(): Boolean {
        for (annotation in this.Annotations) {
            if (annotation.isOverride()) {
                return true
            }
        }

        return false
    }

    fun addExtension(key: String, value: String) {
        this.extensionMap[key] = JsonPrimitive(value)
        this.Extension = JsonObject(this.extensionMap)
    }

    fun isReturnNull(): Boolean {
        return this.Extension.jsonObject["IsReturnNull"] == JsonPrimitive("true")
    }

    override fun toString(): String {
        return Json(JsonConfiguration.Stable).stringify(serializer(), this)
    }
}
