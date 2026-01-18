package chapi.domain.core

import kotlinx.serialization.Serializable

/**
 * Type kind classification for cross-language type representation.
 */
enum class TypeRefKind {
    SIMPLE,
    GENERIC,
    ARRAY,
    POINTER,
    REFERENCE,
    SLICE,
    MAP,
    CHANNEL,
    UNION,
    INTERSECTION,
    TUPLE,
    FUNCTION,
    NULLABLE,
    INTERFACE,
    MAPPED,
    LITERAL,
    UNKNOWN
}

/**
 * Structured type reference for cross-language type analysis.
 *
 * This class provides unified type representation across different programming languages,
 * enabling type comparison, analysis and transformation.
 *
 * @since 2.3.0
 */
@Serializable
@Since("2.3.0")
data class CodeTypeRef(
    /** Original type text as it appears in source code */
    var raw: String = "",

    /** Short/simple name of the type */
    var name: String = "",

    /** Fully qualified name when resolved */
    var qualified: String = "",

    /** Type category */
    var kind: TypeRefKind = TypeRefKind.SIMPLE,

    /** Generic/template type arguments */
    var args: List<CodeTypeRef> = listOf(),

    /** Whether the type is nullable/optional */
    var nullable: Boolean = false,

    /** Union type components (TypeScript/Rust) */
    var union: List<CodeTypeRef> = listOf(),

    /** Intersection type components (TypeScript) */
    var intersection: List<CodeTypeRef> = listOf(),

    /** Tuple element types */
    var tupleElements: List<CodeTypeRef> = listOf(),

    /** Pointer indirection depth (C/C++/Go) */
    var pointerDepth: Int = 0,

    /** Whether this is a reference type (C++) */
    var isReference: Boolean = false,

    /** Whether the type has const qualifier */
    var isConst: Boolean = false,

    /** Array dimensions */
    var arrayDimensions: Int = 0,

    /** Fixed array size if known */
    var arraySize: Int? = null,

    /** Whether this is a slice type (Go) */
    var isSlice: Boolean = false,

    /** Key type for map types (Go) */
    var keyType: CodeTypeRef? = null,

    /** Value/element type for maps, slices, channels, pointers */
    var valueType: CodeTypeRef? = null,

    /** Channel direction: null=bidirectional, "send", "receive" (Go) */
    var channelDirection: String? = null,

    /** Parameter types for function types */
    var parameterTypes: List<CodeTypeRef> = listOf(),

    /** Return type for function types */
    var returnType: CodeTypeRef? = null,

    /** Trait bounds (Rust) */
    var traitBounds: List<String> = listOf(),

    /** Lifetime parameter (Rust) */
    var lifetime: String? = null,

    /** Whether this is readonly (TypeScript) */
    var isReadonly: Boolean = false,

    /** Index signature key type for mapped types (TypeScript) */
    var indexKeyType: CodeTypeRef? = null,

    /** Whether this is a type-only reference (TypeScript) */
    var isTypeOnly: Boolean = false,

    /** Source position */
    var position: CodePosition = CodePosition(),
) {
    companion object {
        fun simple(name: String) = CodeTypeRef(raw = name, name = name, kind = TypeRefKind.SIMPLE)

        fun generic(name: String, vararg typeArgs: CodeTypeRef) = CodeTypeRef(
            raw = "$name<${typeArgs.joinToString(", ") { it.raw }}>",
            name = name,
            kind = TypeRefKind.GENERIC,
            args = typeArgs.toList()
        )

        fun union(vararg types: CodeTypeRef) = CodeTypeRef(
            raw = types.joinToString(" | ") { it.raw },
            kind = TypeRefKind.UNION,
            union = types.toList()
        )

        fun intersection(vararg types: CodeTypeRef) = CodeTypeRef(
            raw = types.joinToString(" & ") { it.raw },
            kind = TypeRefKind.INTERSECTION,
            intersection = types.toList()
        )

        fun array(elementType: CodeTypeRef, dimensions: Int = 1) = CodeTypeRef(
            raw = "${elementType.raw}${"[]".repeat(dimensions)}",
            name = elementType.name,
            kind = TypeRefKind.ARRAY,
            valueType = elementType,
            arrayDimensions = dimensions
        )

        fun pointer(pointeeType: CodeTypeRef, depth: Int = 1) = CodeTypeRef(
            raw = "${"*".repeat(depth)}${pointeeType.raw}",
            name = pointeeType.name,
            kind = TypeRefKind.POINTER,
            valueType = pointeeType,
            pointerDepth = depth
        )

        fun slice(elementType: CodeTypeRef) = CodeTypeRef(
            raw = "[]${elementType.raw}",
            name = "slice",
            kind = TypeRefKind.SLICE,
            valueType = elementType,
            isSlice = true
        )

        fun map(keyType: CodeTypeRef, valueType: CodeTypeRef) = CodeTypeRef(
            raw = "map[${keyType.raw}]${valueType.raw}",
            name = "map",
            kind = TypeRefKind.MAP,
            keyType = keyType,
            valueType = valueType
        )

        fun channel(elementType: CodeTypeRef, direction: String? = null): CodeTypeRef {
            val prefix = when (direction) {
                "send" -> "chan<- "
                "receive" -> "<-chan "
                else -> "chan "
            }
            return CodeTypeRef(
                raw = "$prefix${elementType.raw}",
                name = "chan",
                kind = TypeRefKind.CHANNEL,
                valueType = elementType,
                channelDirection = direction
            )
        }

        fun function(paramTypes: List<CodeTypeRef>, returnType: CodeTypeRef) = CodeTypeRef(
            raw = "(${paramTypes.joinToString(", ") { it.raw }}) => ${returnType.raw}",
            kind = TypeRefKind.FUNCTION,
            parameterTypes = paramTypes,
            returnType = returnType
        )

        fun nullable(baseType: CodeTypeRef) = baseType.copy(
            raw = "${baseType.raw}?",
            nullable = true,
            kind = if (baseType.kind == TypeRefKind.SIMPLE) TypeRefKind.NULLABLE else baseType.kind
        )

        fun tuple(vararg elementTypes: CodeTypeRef) = CodeTypeRef(
            raw = "[${elementTypes.joinToString(", ") { it.raw }}]",
            kind = TypeRefKind.TUPLE,
            tupleElements = elementTypes.toList()
        )

        fun reference(baseType: CodeTypeRef, isConst: Boolean = false) = CodeTypeRef(
            raw = if (isConst) "const ${baseType.raw}&" else "${baseType.raw}&",
            name = baseType.name,
            kind = TypeRefKind.REFERENCE,
            valueType = baseType,
            isReference = true,
            isConst = isConst
        )
    }

    fun isPrimitive(): Boolean {
        val primitives = setOf(
            "int", "long", "short", "byte", "float", "double", "boolean", "char", "void",
            "Int", "Long", "Short", "Byte", "Float", "Double", "Boolean", "Char", "Unit",
            "number", "string", "boolean", "undefined", "null", "void", "never", "any", "unknown",
            "i8", "i16", "i32", "i64", "i128", "isize", "u8", "u16", "u32", "u64", "u128", "usize",
            "f32", "f64", "bool", "str", "String",
            "int8", "int16", "int32", "int64", "uint8", "uint16", "uint32", "uint64", "uintptr",
            "float32", "float64", "complex64", "complex128", "rune", "error", "size_t"
        )
        return name.lowercase() in primitives.map { it.lowercase() }
    }

    fun isGeneric() = args.isNotEmpty()

    fun isCollection(): Boolean {
        val collectionNames = setOf(
            "list", "set", "map", "hashmap", "hashset", "treemap", "treeset",
            "arraylist", "linkedlist", "vector", "deque", "queue", "stack",
            "collection", "iterable", "vec", "slice"
        )
        return name.lowercase() in collectionNames || isSlice || kind == TypeRefKind.MAP
    }

    fun flattenUnion(): List<CodeTypeRef> =
        if (kind == TypeRefKind.UNION) union.flatMap { it.flattenUnion() } else listOf(this)

    fun baseType(): CodeTypeRef? = when (kind) {
        TypeRefKind.POINTER, TypeRefKind.REFERENCE, TypeRefKind.ARRAY,
        TypeRefKind.SLICE, TypeRefKind.CHANNEL -> valueType
        TypeRefKind.NULLABLE -> valueType ?: this.copy(nullable = false, kind = TypeRefKind.SIMPLE)
        else -> null
    }

    override fun toString() = raw.ifEmpty { name }
}
