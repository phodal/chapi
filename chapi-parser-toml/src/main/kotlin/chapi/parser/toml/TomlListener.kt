package chapi.parser.toml

import chapi.domain.core.CodeContainer
import chapi.domain.core.CodeField
import kotlin.io.path.Path

enum class TomlType {
    String,
    Int,
    Float,
    Bool,
    Date,
    Array,
    InlineTable,
    None
}

class TomlListener(val filePath: String) : TomlParserBaseListener() {
    private val fileName = Path(filePath).fileName.toString()
    private val rootContainer = CodeContainer(FullName = fileName)
    private var containerMap = mutableMapOf<String, CodeContainer>()

    private var currentTableName = ""

    override fun enterKey_value(ctx: TomlParser.Key_valueContext?) {
        val field = buildField(ctx)
        if (currentTableName == "") {
            rootContainer.Fields += field
        } else {
            containerMap.getOrDefault(currentTableName, CodeContainer(FullName = fileName, PackageName = currentTableName)).also {
                it.Fields += field
                containerMap[currentTableName] = it
            }
        }
    }

    private fun buildField(ctx: TomlParser.Key_valueContext?): CodeField {
        val key = ctx?.key()?.text ?: ""
        val value = ctx?.value()?.text ?: ""
        val firstOrNull = ctx?.value()?.children?.first()
        val type = when (firstOrNull) {
            is TomlParser.StringContext -> TomlType.String
            is TomlParser.IntegerContext -> TomlType.Int
            is TomlParser.Floating_pointContext -> TomlType.Float
            is TomlParser.Bool_Context -> TomlType.Bool
            is TomlParser.Date_timeContext -> TomlType.Date
            is TomlParser.Array_Context -> TomlType.Array
            is TomlParser.Inline_tableContext -> TomlType.InlineTable
            else -> TomlType.None
        }

        val field = CodeField(
            TypeKey = key,
            TypeValue = parseValue(value, type),
            TypeType = type.toString()
        )

        return field
    }


    override fun enterTable(ctx: TomlParser.TableContext?) {
        val tableName = ctx?.standard_table()?.key()?.text ?: ""
        currentTableName = tableName
    }

    private fun parseValue(value: String, type: TomlType): String {
        return when (type) {
            TomlType.String -> value.removeSurrounding("\"")
            TomlType.Int -> value
            TomlType.Float -> value
            TomlType.Bool -> value
            TomlType.Date -> value
            TomlType.Array -> value
            TomlType.InlineTable -> value
            TomlType.None -> value
        }
    }

    fun getNodeInfo(): CodeContainer {
        return rootContainer.copy(Containers = containerMap.values.toList())
    }
}
