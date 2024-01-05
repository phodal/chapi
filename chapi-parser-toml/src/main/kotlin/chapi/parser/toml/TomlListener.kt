package chapi.parser.toml

import chapi.domain.core.CodeContainer
import chapi.domain.core.CodeField
import kotlin.io.path.Path

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
        val valueContext = ctx?.value()

        val field = codeField(key, valueContext)
        return field
    }

    private fun codeField(key: String, valueContext: TomlParser.ValueContext?): CodeField {
        val firstOrNull = valueContext?.children?.first()
        val type = when (firstOrNull) {
            is TomlParser.StringContext -> TomlType.String_
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
            TypeValue = parseValue(valueContext?.text ?: "", type),
            TypeType = type.toString()
        )

        if (type == TomlType.Array) {
            field.ArrayValue = buildArrayValue(valueContext)
        }

        if (type == TomlType.InlineTable) {
            field.ArrayValue = buildInlineTableValue(valueContext)
        }

        return field
    }

    private fun buildArrayValue(valueContext: TomlParser.ValueContext?): List<CodeField> {
        return valueContext?.array_()?.value()?.map {
            codeField("", it)
        } ?: listOf()
    }


    private fun buildInlineTableValue(valueContext: TomlParser.ValueContext?): List<CodeField> {
        return valueContext?.inline_table()?.inline_table_keyvals()?.map {
            codeField(it.key().text, it.value())
        } ?: listOf()
    }

    override fun enterTable(ctx: TomlParser.TableContext?) {
        val tableName = ctx?.standard_table()?.key()?.text ?: ""
        currentTableName = tableName
    }

    private fun parseValue(value: String, type: TomlType): String {
        return when (type) {
            TomlType.String_ -> value.removeSurrounding("\"")
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
