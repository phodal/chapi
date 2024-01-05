package chapi.parser.toml

enum class TomlType {
    String_,
    Int,
    Float,
    Bool,
    Date,
    Array,
    InlineTable,
    None;

    override fun toString(): String {
        return when (this) {
            String_ -> "String"
            Int -> "Int"
            Float -> "Float"
            Bool -> "Bool"
            Date -> "Date"
            Array -> "Array"
            InlineTable -> "InlineTable"
            None -> "None"
        }
    }

    fun fromString(type: String): TomlType {
        return when (type) {
            "String" -> String_
            "Int" -> Int
            "Float" -> Float
            "Bool" -> Bool
            "Date" -> Date
            "Array" -> Array
            "InlineTable" -> InlineTable
            else -> None
        }
    }
}
