package chapi.domain.core

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

enum class DataStructType(val structType: String) {
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
    ENUM("Enum")
}

@Serializable
data class CodeDataStruct(
    /**
     * for TypeScript/JavaScript, if is a variable, function, it will be naming to `default`
     * for Rust, if is a variable, function, it will be naming to `default`
     */
    var NodeName: String = "",
    var Module: String = "",
    var Type: DataStructType = DataStructType.EMPTY,
    var Package: String = "",
    var FilePath: String = "",
    // todo: thinking in change to property
    var Fields: List<CodeField> = listOf(),
    var MultipleExtend: List<String> = listOf(),
    var Implements: List<String> = listOf(),
    var Extend: String = "",
    var Functions: List<CodeFunction> = listOf(),
    var InnerStructures: List<CodeDataStruct> = listOf(),
    var Annotations: List<CodeAnnotation> = listOf(),
    var FunctionCalls: List<CodeCall> = listOf(),
    @Deprecated(message = "looking for constructor method for SCALA")
    var Parameters: List<CodeProperty> = listOf(), // for Scala

    var Imports: List<CodeImport> = listOf(),

    /**
     * in TypeScript, a files can export Function, Variable, Class, Interface
     * `export const baseURL = '/api'`
     */
    var Exports: List<CodeExport> = listOf(),
    // todo: select node useonly imports
    var Extension: JsonElement = JsonObject(HashMap()),
    var Position: CodePosition = CodePosition(),
    var Content: String = "",
) {
    fun isUtilClass(): Boolean {
        return this.NodeName.lowercase().contains("util") ||
            this.NodeName.lowercase().contains("utils")
    }

    fun setMethodsFromMap(methodMap: MutableMap<String, CodeFunction>) {
        this.Functions = methodMap.values.toList()
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

    /**
     * src/main.ts -> src/main
     */
    fun fileWithoutSuffix(): String {
        return FilePath.substringBeforeLast('.', "")
    }
}

