package chapi.domain.core

import kotlinx.serialization.Serializable

/**
 * Represents file-level/module-level declarations that don't belong to any class or struct.
 *
 * Many languages support top-level declarations outside of class/struct definitions:
 * - TypeScript/JavaScript: top-level functions, variables, exports
 * - Python: module-level functions, variables
 * - Go: package-level functions, variables, constants
 * - Rust: free functions, constants, static items
 * - C/C++: global functions, variables
 * - Kotlin: top-level functions, properties
 *
 * ## Purpose
 *
 * This structure replaces the previous convention of using a synthetic `CodeDataStruct`
 * with `NodeName="default"` to hold top-level elements. The `default` node approach had
 * several problems:
 * - Inconsistent across languages (some used "default", some used filename, some left empty)
 * - Mixed semantics with actual class/struct definitions
 * - No clear indication that these are file-scope vs class-scope declarations
 *
 * ## Migration from `default` node
 *
 * **Before (legacy):**
 * ```kotlin
 * CodeContainer(
 *     DataStructures = listOf(
 *         CodeDataStruct(NodeName = "default", Functions = [...], Fields = [...])
 *     )
 * )
 * ```
 *
 * **After (with TopLevelScope):**
 * ```kotlin
 * CodeContainer(
 *     TopLevel = TopLevelScope(Functions = [...], Fields = [...]),
 *     DataStructures = listOf(/* only actual classes/structs */)
 * )
 * ```
 *
 * ## Examples by Language
 *
 * ### TypeScript
 * ```typescript
 * // Top-level variable
 * const API_URL = "https://api.example.com";
 *
 * // Top-level function
 * export function fetchData() { ... }
 *
 * // Top-level arrow function
 * export const handler = async () => { ... };
 * ```
 * → `TopLevelScope(Fields=[API_URL], Functions=[fetchData, handler], Exports=[...])`
 *
 * ### Python
 * ```python
 * # Module-level constant
 * MAX_RETRIES = 3
 *
 * # Module-level function
 * def process_data(data):
 *     ...
 * ```
 * → `TopLevelScope(Fields=[MAX_RETRIES], Functions=[process_data])`
 *
 * ### Go
 * ```go
 * package main
 *
 * var globalConfig Config
 *
 * func main() { ... }
 *
 * func helper() { ... }
 * ```
 * → `TopLevelScope(Fields=[globalConfig], Functions=[main, helper])`
 *
 * ### Rust
 * ```rust
 * static CONFIG: Config = Config::default();
 *
 * fn main() { ... }
 *
 * fn helper() -> Result<(), Error> { ... }
 * ```
 * → `TopLevelScope(Fields=[CONFIG], Functions=[main, helper])`
 *
 * @since 2.4.0
 */
@Serializable
@Since("2.4.0")
data class TopLevelScope(
    /**
     * Top-level/free functions not belonging to any class or struct.
     *
     * Examples:
     * - TypeScript: `export function fetchData() { }`
     * - Python: `def process_data(): ...`
     * - Go: `func main() { }`
     * - Rust: `fn main() { }`
     * - C/C++: `int main() { }`
     */
    var Functions: List<CodeFunction> = listOf(),

    /**
     * Top-level variables, constants, and static items.
     *
     * Examples:
     * - TypeScript: `const API_URL = "..."`
     * - Python: `MAX_RETRIES = 3`
     * - Go: `var globalConfig Config`
     * - Rust: `static CONFIG: Config = ...`
     * - C/C++: `int globalCounter = 0;`
     */
    var Fields: List<CodeField> = listOf(),

    /**
     * Export declarations at file level.
     *
     * Primarily for TypeScript/JavaScript ES modules:
     * - `export { foo, bar }`
     * - `export default expression`
     * - `export * from "module"`
     *
     * Also used for Rust `pub use` re-exports.
     */
    var Exports: List<CodeExport> = listOf(),

    /**
     * Type aliases defined at top level.
     *
     * Examples:
     * - TypeScript: `type UserId = string`
     * - Rust: `type Result<T> = std::result::Result<T, MyError>`
     * - Go: `type Handler func(w http.ResponseWriter, r *http.Request)`
     * - C/C++: `typedef int ErrorCode;` or `using ErrorCode = int;`
     */
    var TypeAliases: List<CodeTypeAlias> = listOf(),

    /**
     * Extension map for language-specific top-level constructs.
     *
     * Examples:
     * - Rust: `mod` declarations, `extern crate`
     * - Go: `init()` functions (can be tracked separately)
     * - Python: `__all__` list
     */
    var Extension: Map<String, String> = mapOf(),
) {
    /**
     * Returns true if this scope has any declarations.
     */
    fun isEmpty(): Boolean = Functions.isEmpty() && Fields.isEmpty() && 
                             Exports.isEmpty() && TypeAliases.isEmpty()

    /**
     * Returns true if this scope has any declarations.
     */
    fun isNotEmpty(): Boolean = !isEmpty()

    /**
     * Returns total count of all declarations.
     */
    fun declarationCount(): Int = Functions.size + Fields.size + Exports.size + TypeAliases.size

    /**
     * Finds a function by name.
     */
    fun findFunction(name: String): CodeFunction? = Functions.find { it.Name == name }

    /**
     * Finds a field by name (TypeKey).
     */
    fun findField(name: String): CodeField? = Fields.find { it.TypeKey == name }

    /**
     * Returns all exported names.
     */
    fun exportedNames(): List<String> = Exports.flatMap { export ->
        if (export.Specifiers.isNotEmpty()) {
            export.Specifiers.map { it.ExportedName }
        } else if (export.Name.isNotEmpty()) {
            listOf(export.Name)
        } else {
            emptyList()
        }
    }
}

/**
 * Represents a type alias declaration.
 *
 * Type aliases create alternative names for existing types, common in many languages:
 * - TypeScript: `type UserId = string;`
 * - Rust: `type Result<T> = std::result::Result<T, MyError>;`
 * - Go: `type Handler func(w http.ResponseWriter, r *http.Request)`
 * - C++: `using ErrorCode = int;` or `typedef int ErrorCode;`
 * - Kotlin: `typealias StringMap = Map<String, String>`
 *
 * @since 2.4.0
 */
@Serializable
@Since("2.4.0")
data class CodeTypeAlias(
    /**
     * The name of the type alias.
     */
    var Name: String = "",

    /**
     * The original type text as written in source.
     */
    var OriginalType: String = "",

    /**
     * Structured type reference for the aliased type.
     */
    var TypeRef: CodeTypeRef? = null,

    /**
     * Generic type parameters for parameterized type aliases.
     *
     * Example: `type Result<T> = ...` → TypeParameters = ["T"]
     */
    var TypeParameters: List<String> = listOf(),

    /**
     * Modifiers like visibility (pub, public, private, etc.).
     */
    var Modifiers: List<String> = listOf(),

    /**
     * Annotations on the type alias (if supported by language).
     */
    var Annotations: List<CodeAnnotation> = listOf(),

    /**
     * Source position.
     */
    var Position: CodePosition = CodePosition(),
)
