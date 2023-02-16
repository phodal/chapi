package chapi.domain.core

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

enum class DataStructType (val structType: String) {
    EMPTY(""),
    DEFAULT("default"),
    CLASS("Class"),
    Variable("Variable"),
    INTERFACE("Interface"),
    STRUCT("Struct"),
    OBJECT("Object"),
    INNER_STRUCTURES("InnerStructures"),
    CREATOR_CLASS("CreatorClass"),
    ABSTRACT_CLASS("AbstractClass"),
    // for scala, Rust
    TRAIT("Trait"),
    ENUM("enum")
}

@Serializable
data class CodeDataStruct(
    // class and DataStruct Name
    // for TypeScript/JavaScript, if is a variable, function, it will be naming to `default`
    var NodeName: String = "",
    var Module: String = "",
    var Type: DataStructType = DataStructType.EMPTY,
    var Package: String = "",
    var FilePath: String = "",
    // todo: thinking in change to property
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

    var Imports: Array<CodeImport> = arrayOf(),

    // in TypeScript, a files can export Function, Variable, Class, Interface
    // `export const baseURL = '/api'`
    var Exports: Array<CodeExport> = arrayOf(),

    // todo: select node useonly imports
    var Extension: JsonElement = JsonObject(HashMap()),

    var Position: CodePosition = CodePosition()
) {
    fun isUtilClass(): Boolean {
        return this.NodeName.lowercase().contains("util") ||
                this.NodeName.lowercase().contains("utils")
    }

    fun setMethodsFromMap(methodMap: MutableMap<String, CodeFunction>) {
        this.Functions = methodMap.values.toTypedArray()
    }

    fun filterAnnotations(vararg keys: String): List<CodeAnnotation> {
        return this.Annotations.filter { prop ->
            keys.map { it.lowercase() }.contains(prop.Name.lowercase())
        }
    }

    fun getClassFullName(): String {
        return "${this.Package}.${this.NodeName}"
    }

    fun fileExt(): String {
        return FilePath.substringAfterLast('.', "")
    }

    // src/main.ts -> src/main
    fun fileWithoutSuffix(): String {
        return FilePath.substringBeforeLast('.', "")
    }
}

