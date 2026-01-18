package chapi.domain.core

import kotlinx.serialization.Serializable

/**
 * Enumeration of type kinds to classify different type structures across languages.
 */
enum class TypeRefKind {
    /** Simple type name, e.g., `int`, `String`, `User` */
    SIMPLE,
    /** Generic/parameterized type, e.g., `List<T>`, `Map<K, V>`, `Result<T, E>` */
    GENERIC,
    /** Array type, e.g., `int[]`, `String[][]` */
    ARRAY,
    /** Pointer type (C/C++/Go), e.g., `*int`, `**char` */
    POINTER,
    /** Reference type (C++), e.g., `int&`, `const string&` */
    REFERENCE,
    /** Slice type (Go), e.g., `[]int`, `[]string` */
    SLICE,
    /** Map type (Go), e.g., `map[string]int` */
    MAP,
    /** Channel type (Go), e.g., `chan int`, `<-chan string` */
    CHANNEL,
    /** Union type (TypeScript/Rust), e.g., `string | number` */
    UNION,
    /** Intersection type (TypeScript), e.g., `A & B` */
    INTERSECTION,
    /** Tuple type (TypeScript/Rust/Python), e.g., `[string, number]`, `(i32, i64)` */
    TUPLE,
    /** Function type, e.g., `(x: number) => string`, `fn(i32) -> i64` */
    FUNCTION,
    /** Nullable/Optional type, e.g., `?string`, `Option<T>`, `String?` */
    NULLABLE,
    /** Interface/any type (Go), e.g., `interface{}`, `any` */
    INTERFACE,
    /** Mapped type (TypeScript), e.g., `{ [K in keyof T]: T[K] }` */
    MAPPED,
    /** Conditional type (TypeScript), e.g., `T extends U ? X : Y` */
    CONDITIONAL,
    /** Literal type (TypeScript), e.g., `"hello"`, `42`, `true` */
    LITERAL,
    /** Unknown/unresolved type */
    UNKNOWN
}

/**
 * Structured representation of a type reference across multiple programming languages.
 *
 * This class provides a unified way to represent type information that goes beyond
 * simple string representation, enabling cross-language type comparison, analysis,
 * and transformation.
 *
 * ## Design Principles
 * - **Backward Compatible**: Existing string-based `TypeType`/`ReturnType` fields are preserved.
 * - **Language Agnostic Core**: Common fields work across all languages.
 * - **Language Specific Extensions**: Optional fields for language-specific features.
 * - **Recursive Structure**: Supports nested types via `args`, `union`, `intersection`, etc.
 *
 * ## Language-Specific Usage Examples
 *
 * ### TypeScript
 * ```typescript
 * // Union: string | number | null
 * CodeTypeRef(kind = UNION, union = [CodeTypeRef("string"), CodeTypeRef("number"), CodeTypeRef("null")])
 *
 * // Generic: Promise<User>
 * CodeTypeRef(kind = GENERIC, name = "Promise", args = [CodeTypeRef("User")])
 *
 * // Function: (x: number) => string
 * CodeTypeRef(kind = FUNCTION, parameterTypes = [...], returnType = CodeTypeRef("string"))
 * ```
 *
 * ### Rust
 * ```rust
 * // Result<User, Error>
 * CodeTypeRef(kind = GENERIC, name = "Result", args = [CodeTypeRef("User"), CodeTypeRef("Error")])
 *
 * // impl Trait: T: Clone + Debug
 * CodeTypeRef(kind = SIMPLE, name = "T", traitBounds = ["Clone", "Debug"])
 *
 * // Lifetime: &'a str
 * CodeTypeRef(kind = REFERENCE, name = "str", lifetime = "'a")
 * ```
 *
 * ### Go
 * ```go
 * // Slice: []int
 * CodeTypeRef(kind = SLICE, elementType = CodeTypeRef("int"))
 *
 * // Map: map[string]User
 * CodeTypeRef(kind = MAP, keyType = CodeTypeRef("string"), valueType = CodeTypeRef("User"))
 *
 * // Pointer: *User
 * CodeTypeRef(kind = POINTER, pointerDepth = 1, elementType = CodeTypeRef("User"))
 * ```
 *
 * ### C/C++
 * ```cpp
 * // Pointer: char**
 * CodeTypeRef(kind = POINTER, pointerDepth = 2, name = "char")
 *
 * // Reference: const string&
 * CodeTypeRef(kind = REFERENCE, name = "string", isConst = true)
 *
 * // Template: vector<int>
 * CodeTypeRef(kind = GENERIC, name = "vector", args = [CodeTypeRef("int")])
 * ```
 *
 * @since 2.3.0
 */
@Serializable
@Since("2.3.0")
data class CodeTypeRef(
    // ==================== Core Fields (All Languages) ====================

    /**
     * The raw/original type text as it appears in source code.
     * This preserves the exact syntax for debugging and display purposes.
     *
     * Examples:
     * - Java: `"List<String>"`
     * - TypeScript: `"string | number | null"`
     * - Rust: `"Result<User, Box<dyn Error>>"`
     * - Go: `"map[string]*User"`
     * - C++: `"std::vector<int>"`
     */
    var raw: String = "",

    /**
     * The short/simple name of the type without qualification or parameters.
     *
     * Examples:
     * - `"List"` from `java.util.List<String>`
     * - `"Result"` from `Result<T, E>`
     * - `"User"` from `*User`
     */
    var name: String = "",

    /**
     * Fully qualified name of the type when it can be resolved.
     * Empty if the type cannot be fully resolved or is a primitive.
     *
     * Examples:
     * - `"java.util.List"` for Java List
     * - `"std::vector"` for C++ vector
     * - `"core::result::Result"` for Rust Result
     */
    var qualified: String = "",

    /**
     * The kind/category of this type reference.
     */
    var kind: TypeRefKind = TypeRefKind.SIMPLE,

    /**
     * Generic/template type arguments.
     *
     * Examples:
     * - `Map<String, User>` -> args = [CodeTypeRef("String"), CodeTypeRef("User")]
     * - `Result<T, E>` -> args = [CodeTypeRef("T"), CodeTypeRef("E")]
     * - `vector<int>` -> args = [CodeTypeRef("int")]
     */
    var args: List<CodeTypeRef> = listOf(),

    // ==================== Nullability ====================

    /**
     * Whether the type is nullable/optional.
     *
     * Examples:
     * - TypeScript: `string?`, `string | null`, `string | undefined`
     * - Kotlin: `String?`
     * - C#: `string?`
     * - Rust: `Option<T>` (as wrapper)
     */
    var nullable: Boolean = false,

    // ==================== Union/Intersection Types (TypeScript/Rust) ====================

    /**
     * Component types for union types.
     *
     * Examples:
     * - TypeScript: `string | number` -> union = [CodeTypeRef("string"), CodeTypeRef("number")]
     * - Rust: `Result<T, E>` success/error variants (conceptually)
     */
    var union: List<CodeTypeRef> = listOf(),

    /**
     * Component types for intersection types (TypeScript).
     *
     * Example: `A & B & C` -> intersection = [CodeTypeRef("A"), CodeTypeRef("B"), CodeTypeRef("C")]
     */
    var intersection: List<CodeTypeRef> = listOf(),

    // ==================== Tuple Types ====================

    /**
     * Element types for tuple types.
     *
     * Examples:
     * - TypeScript: `[string, number]` -> tupleElements = [CodeTypeRef("string"), CodeTypeRef("number")]
     * - Rust: `(i32, String)` -> tupleElements = [CodeTypeRef("i32"), CodeTypeRef("String")]
     * - Python: `Tuple[int, str]` -> tupleElements = [CodeTypeRef("int"), CodeTypeRef("str")]
     */
    var tupleElements: List<CodeTypeRef> = listOf(),

    // ==================== Pointer/Reference Types (C/C++/Go/Rust) ====================

    /**
     * Depth of pointer indirection.
     *
     * Examples:
     * - `*int` -> pointerDepth = 1
     * - `**char` -> pointerDepth = 2
     * - `***void` -> pointerDepth = 3
     */
    var pointerDepth: Int = 0,

    /**
     * Whether this is a reference type (C++).
     *
     * Examples:
     * - `int&` -> isReference = true
     * - `const string&` -> isReference = true, isConst = true
     */
    var isReference: Boolean = false,

    /**
     * Whether the type has const qualifier (C/C++).
     */
    var isConst: Boolean = false,

    // ==================== Array/Slice/Collection Types ====================

    /**
     * Array dimensions. 0 means not an array.
     *
     * Examples:
     * - `int[]` -> arrayDimensions = 1
     * - `String[][]` -> arrayDimensions = 2
     * - `int[10]` -> arrayDimensions = 1, arraySize = 10
     */
    var arrayDimensions: Int = 0,

    /**
     * Fixed array size if known (C/C++).
     * Null means dynamic/unknown size.
     */
    var arraySize: Int? = null,

    /**
     * Whether this is a slice type (Go).
     * True for `[]int`, false for `[10]int`.
     */
    var isSlice: Boolean = false,

    // ==================== Map/Dictionary Types (Go) ====================

    /**
     * Key type for map/dictionary types.
     *
     * Example: `map[string]int` -> keyType = CodeTypeRef("string")
     */
    var keyType: CodeTypeRef? = null,

    /**
     * Value/element type for maps, slices, channels, pointers, arrays.
     *
     * Examples:
     * - `map[string]int` -> valueType = CodeTypeRef("int")
     * - `[]User` -> valueType = CodeTypeRef("User")
     * - `chan string` -> valueType = CodeTypeRef("string")
     * - `*User` -> valueType = CodeTypeRef("User")
     */
    var valueType: CodeTypeRef? = null,

    // ==================== Channel Types (Go) ====================

    /**
     * Channel direction for Go channel types.
     * - `null` or empty: bidirectional (`chan T`)
     * - `"send"`: send-only (`chan<- T`)
     * - `"receive"`: receive-only (`<-chan T`)
     */
    var channelDirection: String? = null,

    // ==================== Function Types ====================

    /**
     * Parameter types for function types.
     *
     * Example: `(x: number, y: string) => boolean`
     *   -> parameterTypes = [CodeTypeRef("number"), CodeTypeRef("string")]
     */
    var parameterTypes: List<CodeTypeRef> = listOf(),

    /**
     * Return type for function types.
     *
     * Example: `(x: number) => string` -> returnType = CodeTypeRef("string")
     */
    var returnType: CodeTypeRef? = null,

    // ==================== Rust-Specific Fields ====================

    /**
     * Trait bounds for generic type parameters (Rust).
     *
     * Example: `T: Clone + Debug + 'static`
     *   -> traitBounds = ["Clone", "Debug", "'static"]
     */
    var traitBounds: List<String> = listOf(),

    /**
     * Lifetime parameter (Rust).
     *
     * Examples:
     * - `&'a str` -> lifetime = "'a"
     * - `&'static str` -> lifetime = "'static"
     */
    var lifetime: String? = null,

    // ==================== TypeScript-Specific Fields ====================

    /**
     * Whether this is a `readonly` type (TypeScript).
     */
    var isReadonly: Boolean = false,

    /**
     * Index signature key type for mapped types (TypeScript).
     *
     * Example: `{ [key: string]: number }` -> indexKeyType = CodeTypeRef("string")
     */
    var indexKeyType: CodeTypeRef? = null,

    /**
     * Whether this is a type-only import/reference (TypeScript).
     */
    var isTypeOnly: Boolean = false,

    // ==================== Position Information ====================

    /**
     * Source code position of this type reference.
     */
    var position: CodePosition = CodePosition(),
) {

    companion object {
        /**
         * Creates a simple type reference with just a name.
         */
        fun simple(name: String): CodeTypeRef = CodeTypeRef(
            raw = name,
            name = name,
            kind = TypeRefKind.SIMPLE
        )

        /**
         * Creates a generic type reference with type arguments.
         */
        fun generic(name: String, vararg typeArgs: CodeTypeRef): CodeTypeRef = CodeTypeRef(
            raw = "$name<${typeArgs.joinToString(", ") { it.raw }}>",
            name = name,
            kind = TypeRefKind.GENERIC,
            args = typeArgs.toList()
        )

        /**
         * Creates a union type reference.
         */
        fun union(vararg types: CodeTypeRef): CodeTypeRef = CodeTypeRef(
            raw = types.joinToString(" | ") { it.raw },
            kind = TypeRefKind.UNION,
            union = types.toList()
        )

        /**
         * Creates an intersection type reference.
         */
        fun intersection(vararg types: CodeTypeRef): CodeTypeRef = CodeTypeRef(
            raw = types.joinToString(" & ") { it.raw },
            kind = TypeRefKind.INTERSECTION,
            intersection = types.toList()
        )

        /**
         * Creates an array type reference.
         */
        fun array(elementType: CodeTypeRef, dimensions: Int = 1): CodeTypeRef = CodeTypeRef(
            raw = "${elementType.raw}${"[]".repeat(dimensions)}",
            name = elementType.name,
            kind = TypeRefKind.ARRAY,
            valueType = elementType,
            arrayDimensions = dimensions
        )

        /**
         * Creates a pointer type reference (C/C++/Go).
         */
        fun pointer(pointeeType: CodeTypeRef, depth: Int = 1): CodeTypeRef = CodeTypeRef(
            raw = "${"*".repeat(depth)}${pointeeType.raw}",
            name = pointeeType.name,
            kind = TypeRefKind.POINTER,
            valueType = pointeeType,
            pointerDepth = depth
        )

        /**
         * Creates a slice type reference (Go).
         */
        fun slice(elementType: CodeTypeRef): CodeTypeRef = CodeTypeRef(
            raw = "[]${elementType.raw}",
            name = "slice",
            kind = TypeRefKind.SLICE,
            valueType = elementType,
            isSlice = true
        )

        /**
         * Creates a map type reference (Go).
         */
        fun map(keyType: CodeTypeRef, valueType: CodeTypeRef): CodeTypeRef = CodeTypeRef(
            raw = "map[${keyType.raw}]${valueType.raw}",
            name = "map",
            kind = TypeRefKind.MAP,
            keyType = keyType,
            valueType = valueType
        )

        /**
         * Creates a channel type reference (Go).
         */
        fun channel(elementType: CodeTypeRef, direction: String? = null): CodeTypeRef {
            val rawPrefix = when (direction) {
                "send" -> "chan<- "
                "receive" -> "<-chan "
                else -> "chan "
            }
            return CodeTypeRef(
                raw = "$rawPrefix${elementType.raw}",
                name = "chan",
                kind = TypeRefKind.CHANNEL,
                valueType = elementType,
                channelDirection = direction
            )
        }

        /**
         * Creates a function type reference.
         */
        fun function(paramTypes: List<CodeTypeRef>, returnType: CodeTypeRef): CodeTypeRef = CodeTypeRef(
            raw = "(${paramTypes.joinToString(", ") { it.raw }}) => ${returnType.raw}",
            kind = TypeRefKind.FUNCTION,
            parameterTypes = paramTypes,
            returnType = returnType
        )

        /**
         * Creates a nullable type reference.
         */
        fun nullable(baseType: CodeTypeRef): CodeTypeRef = baseType.copy(
            raw = "${baseType.raw}?",
            nullable = true,
            kind = if (baseType.kind == TypeRefKind.SIMPLE) TypeRefKind.NULLABLE else baseType.kind
        )

        /**
         * Creates a tuple type reference.
         */
        fun tuple(vararg elementTypes: CodeTypeRef): CodeTypeRef = CodeTypeRef(
            raw = "[${elementTypes.joinToString(", ") { it.raw }}]",
            kind = TypeRefKind.TUPLE,
            tupleElements = elementTypes.toList()
        )

        /**
         * Creates a reference type (C++).
         */
        fun reference(baseType: CodeTypeRef, isConst: Boolean = false): CodeTypeRef = CodeTypeRef(
            raw = if (isConst) "const ${baseType.raw}&" else "${baseType.raw}&",
            name = baseType.name,
            kind = TypeRefKind.REFERENCE,
            valueType = baseType,
            isReference = true,
            isConst = isConst
        )
    }

    /**
     * Returns true if this type is a primitive type in the given language context.
     */
    fun isPrimitive(): Boolean {
        val primitives = setOf(
            // Java/Kotlin
            "int", "long", "short", "byte", "float", "double", "boolean", "char", "void",
            "Int", "Long", "Short", "Byte", "Float", "Double", "Boolean", "Char", "Unit",
            // TypeScript/JavaScript
            "number", "string", "boolean", "undefined", "null", "void", "never", "any", "unknown",
            // Rust
            "i8", "i16", "i32", "i64", "i128", "isize",
            "u8", "u16", "u32", "u64", "u128", "usize",
            "f32", "f64", "bool", "str", "String",
            // Go
            "int8", "int16", "int32", "int64",
            "uint8", "uint16", "uint32", "uint64", "uintptr",
            "float32", "float64", "complex64", "complex128",
            "rune", "error",
            // C/C++
            "size_t", "ssize_t", "ptrdiff_t"
        )
        return name.lowercase() in primitives.map { it.lowercase() }
    }

    /**
     * Returns true if this type has generic/template parameters.
     */
    fun isGeneric(): Boolean = args.isNotEmpty()

    /**
     * Returns true if this type is a collection type (list, set, map, etc.).
     */
    fun isCollection(): Boolean {
        val collectionNames = setOf(
            "list", "set", "map", "hashmap", "hashset", "treemap", "treeset",
            "arraylist", "linkedlist", "vector", "deque", "queue", "stack",
            "collection", "iterable", "iterator", "sequence",
            "vec", "hashmap", "btreemap", "btreeset", // Rust
            "slice" // Go
        )
        return name.lowercase() in collectionNames || isSlice || kind == TypeRefKind.MAP
    }

    /**
     * Flattens nested union types into a single list.
     */
    fun flattenUnion(): List<CodeTypeRef> {
        return if (kind == TypeRefKind.UNION) {
            union.flatMap { it.flattenUnion() }
        } else {
            listOf(this)
        }
    }

    /**
     * Returns the base type for pointer/reference/array/slice types.
     */
    fun baseType(): CodeTypeRef? {
        return when (kind) {
            TypeRefKind.POINTER, TypeRefKind.REFERENCE, TypeRefKind.ARRAY, TypeRefKind.SLICE, TypeRefKind.CHANNEL -> valueType
            TypeRefKind.NULLABLE -> valueType ?: this.copy(nullable = false, kind = TypeRefKind.SIMPLE)
            else -> null
        }
    }

    /**
     * Converts this type reference to a human-readable string.
     */
    override fun toString(): String = raw.ifEmpty { name }
}
