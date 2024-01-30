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
    /// add 2.2.6 for cpp
    UNION("Union"),

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
    /**
     * for Rust, if `mod tests`, the Module will be `tests`
     */
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
    /**
     * If [NodeName] is default, [CodePosition] may be empty
     */
    var Position: CodePosition = CodePosition(),
    @Since("2.1.4")
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

    fun toUml(): String {
        val output = StringBuilder()

        val superClass = Implements + if (Extend.isNotBlank()) {
            listOf(Extend)
        } else {
            emptyList()
        }
        val superClasses = if (superClass.isNotEmpty()) {
            ": " + superClass.joinToString(", ") + " "
        } else {
            ""
        }

        output.append("class $NodeName $superClasses{\n")
        Fields.forEach {
            output.append("   ${it.TypeKey}: ${it.TypeType}\n")
        }

        var getterSetter: List<String> = listOf()
        val methodsWithoutGetterSetter = Functions.filter { it.Name != NodeName }
            .filter { it.Name !in listOf("toString", "hashCode", "equals") }
            .filter {
                val isGetter = it.Name.startsWith("get") && it.Parameters.isEmpty()
                val isSetter = it.Name.startsWith("set") && it.Parameters.size == 1
                if (isGetter || isSetter) {
                    getterSetter = listOf(it.Name)
                    return@filter false
                }
                return@filter true
            }

        if (getterSetter.isNotEmpty()) {
            output.append("\n   'getter/setter: ${getterSetter.joinToString(", ")}\n")
        }

        val methodCodes = methodsWithoutGetterSetter
            .joinToString("\n") { method ->
                val params =
                    method.Parameters.joinToString(", ") { parameter ->
                        "${parameter.TypeValue}: ${parameter.TypeType}"
                    }
                val returnType = method.ReturnType.replace("kotlin.", "")
                "   + ${method.Name}($params)" + if (returnType.isNotBlank()) ": $returnType" else ""
            }

        if (methodCodes.isNotBlank()) {
            output.append("\n")
            output.append(methodCodes)
        }

        output.append("\n")
        output.append(" }\n")

        // TODO: split output and add comments line
        return output.split("\n").joinToString("\n") {
            "// $it"
        }
    }

    companion object {
        fun contentByPosition(lines: List<String>, position: CodePosition): String {
            val startLine = if (position.StartLine == 0) {
                0
            } else {
                position.StartLine - 1
            }
            val endLine = if (position.StopLine == 0) {
                0
            } else {
                position.StopLine - 1
            }

            val startLineContent = lines[startLine]
            val endLineContent = lines[endLine]

            val startColumn = if (position.StartLinePosition > startLineContent.length) {
                if (startLineContent.isBlank()) {
                    0
                } else {
                    startLineContent.length
                }
            } else {
                position.StartLinePosition
            }

            val endColumn = if (position.StopLinePosition > endLineContent.length) {
                if (endLineContent.isBlank()) {
                    0
                } else {
                    endLineContent.length
                }
            } else {
                position.StopLinePosition
            }

            val start = startLineContent.substring(startColumn)
            val end = endLineContent.substring(endColumn)

            val code = if (startLine == endLine) {
                start
            } else {
                start + "\n" + lines.subList(startLine + 1, endLine).joinToString("\n") + "\n" + end
            }

            return code
        }
    }
}

