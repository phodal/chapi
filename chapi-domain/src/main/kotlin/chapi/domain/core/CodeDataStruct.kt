package chapi.domain.core

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
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
    var Fields: Array<CodeField> = arrayOf<CodeField>(),
    var MultipleExtend: Array<String> = arrayOf<String>(),
    var Implements: Array<String> = arrayOf<String>(),
    var Extend: String = "",
    var Functions: Array<CodeFunction> = arrayOf<CodeFunction>(),
    var InnerStructures: Array<CodeDataStruct> = arrayOf<CodeDataStruct>(),
    var Annotations: Array<CodeAnnotation> = arrayOf<CodeAnnotation>(),
    var FunctionCalls: Array<CodeCall> = arrayOf<CodeCall>(),

    @Deprecated(message = "looking for constructor method for SCALA")
    var Parameters: Array<CodeProperty> = arrayOf(), // for Scala

    @Deprecated(message = "need to figure why")
    var InOutProperties: Array<CodeProperty> = arrayOf<CodeProperty>(),

    // todo: select node useonly imports
    var Imports: Array<CodeImport> = arrayOf<CodeImport>(),
    var Extension: JsonElement = JsonObject(HashMap())
) {
    open fun isUtilClass(): Boolean {
        return this.NodeName.toLowerCase().contains("util") ||
                this.NodeName.toLowerCase().contains("utils")
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

    override fun toString(): String {
        return Json(JsonConfiguration.Stable).stringify(serializer(), this)
    }
}
