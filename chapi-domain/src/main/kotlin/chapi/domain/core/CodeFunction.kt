package chapi.domain.core

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonObject

@Serializable
open class CodeFunction(
    var Name: String = "",
    var FilePath: String = "",
    var Package: String = "",
    var ReturnType: String = "",
    var MultipleReturns: Array<CodeProperty> = arrayOf(),
    var Parameters: Array<CodeProperty> = arrayOf(),
    var FunctionCalls: Array<CodeCall> = arrayOf(),
    var Annotations: Array<CodeAnnotation> = arrayOf(),
    var Override: Boolean = false,
    var Modifiers: Array<String> = arrayOf(),
    // for example, Java can have Inner Class
    var InnerStructures: Array<CodeDataStruct> = arrayOf(),
    // for lambda or anonymous function inside function.
    var InnerFunctions: Array<CodeFunction> = arrayOf(),
    var Position: CodePosition = CodePosition(),
    var Extension: JsonElement = JsonObject(HashMap()),
    var LocalVariables: Array<CodeProperty> = arrayOf(),
    var IsConstructor: Boolean = false, // todo: move to extension
    var IsReturnHtml: Boolean = false
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

    fun addVarsFromMap(localVars: MutableMap<String, String>) {
        var vars: Array<CodeProperty> = arrayOf()
        for (entry in localVars) {
            val param = CodeProperty(TypeValue = entry.key, TypeType = entry.value)
            vars += param
        }

        this.LocalVariables = vars
    }

    fun fileExt(): String {
        return this.FilePath.substringAfterLast('.', "")
    }

    fun fileName(): String {
        return this.FilePath.substringBeforeLast('.', "")
    }

    // position was removed, if one function change, others position will also change
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CodeFunction) return false

        if (Name != other.Name) return false
        if (FilePath != other.FilePath) return false
        if (Package != other.Package) return false
        if (ReturnType != other.ReturnType) return false
        if (!MultipleReturns.contentEquals(other.MultipleReturns)) return false
        if (!Parameters.contentEquals(other.Parameters)) return false
        if (!FunctionCalls.contentEquals(other.FunctionCalls)) return false
        if (!Annotations.contentEquals(other.Annotations)) return false
        if (Override != other.Override) return false
        if (!Modifiers.contentEquals(other.Modifiers)) return false
        if (!InnerStructures.contentEquals(other.InnerStructures)) return false
        if (!InnerFunctions.contentEquals(other.InnerFunctions)) return false
        if (Extension != other.Extension) return false
        if (!LocalVariables.contentEquals(other.LocalVariables)) return false
        if (IsConstructor != other.IsConstructor) return false
        if (IsReturnHtml != other.IsReturnHtml) return false
        if (extensionMap != other.extensionMap) return false

        return true
    }

    override fun hashCode(): Int {
        var result = Name.hashCode()
        result = 31 * result + FilePath.hashCode()
        result = 31 * result + Package.hashCode()
        result = 31 * result + ReturnType.hashCode()
        result = 31 * result + MultipleReturns.contentHashCode()
        result = 31 * result + Parameters.contentHashCode()
        result = 31 * result + FunctionCalls.contentHashCode()
        result = 31 * result + Annotations.contentHashCode()
        result = 31 * result + Override.hashCode()
        result = 31 * result + Modifiers.contentHashCode()
        result = 31 * result + InnerStructures.contentHashCode()
        result = 31 * result + InnerFunctions.contentHashCode()
        result = 31 * result + Extension.hashCode()
        result = 31 * result + LocalVariables.contentHashCode()
        result = 31 * result + IsConstructor.hashCode()
        result = 31 * result + IsReturnHtml.hashCode()
        result = 31 * result + extensionMap.hashCode()
        return result
    }
}
