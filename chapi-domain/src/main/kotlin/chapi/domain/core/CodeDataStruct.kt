package chapi.domain.core

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

enum class DataStructType (val structType: String) {
    EMPTY(""),
    CLASS("Class"),
    INTERFACE("Interface"),
    STRUCT("Struct"),
    OBJECT("Object"),
    INNERSTRUCTURES("InnerStructures"),
    CREATORCLASS("CreatorClass"),
    ABSTRACTCLASS("AbstractClass"),
    TRAIT("Trait") // for scala
}

@Serializable
open class CodeDataStruct(
    var NodeName: String = "",
    var Type: DataStructType = DataStructType.EMPTY,
    var Package: String = "",
    var FilePath: String = "",
    var Fields: Array<CodeField> = arrayOf(),
    var MultipleExtend: Array<String> = arrayOf(),
    var Implements: Array<String> = arrayOf(),
    var Extend: String = "",
    var Functions: Array<CodeFunction> = arrayOf(),
    var InnerStructures: Array<CodeDataStruct> = arrayOf(),
    var Annotations: Array<CodeAnnotation> = arrayOf(),
    var FunctionCalls: Array<CodeCall> = arrayOf(),

    @Deprecated(message = "looking for constructor method for SCALA")
    var Parameters: Array<CodeProperty> = arrayOf(), // for Scala

    var Imports: Array<CodeImport> = arrayOf<CodeImport>(),

    // todo: select node useonly imports
    var Extension: JsonElement = JsonObject(HashMap())
) {
    open fun isUtilClass(): Boolean {
        return this.NodeName.lowercase().contains("util") ||
                this.NodeName.lowercase().contains("utils")
    }

    fun setMethodsFromMap(methodMap: MutableMap<String, CodeFunction>) {
        var methodsArray: Array<CodeFunction> = arrayOf()
        for (entry in methodMap) {
            methodsArray += entry.value
        }

        this.Functions = methodsArray
    }

    fun getClassFullName(): String {
        return this.Package + "." + this.NodeName
    }

}
