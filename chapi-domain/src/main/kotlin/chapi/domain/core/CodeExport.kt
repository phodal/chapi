package chapi.domain.core

import kotlinx.serialization.Serializable

/**
 * Represents the kind/style of export statement.
 *
 * Different languages have different export mechanisms:
 * - TypeScript/JavaScript: default, named, re-export, type-only
 * - Rust: pub use (re-export)
 */
@Serializable
enum class ExportKind(val kind: String) {
    /** Default/unknown export kind */
    UNKNOWN("Unknown"),
    /** Default export: `export default expression` (TS/JS) */
    DEFAULT("Default"),
    /** Named export: `export { a, b }` or `export const x = ...` (TS/JS) */
    NAMED("Named"),
    /** Re-export all: `export * from "module"` (TS/JS), `pub use module::*` (Rust) */
    RE_EXPORT_ALL("ReExportAll"),
    /** Re-export named: `export { a, b } from "module"` (TS/JS) */
    RE_EXPORT_NAMED("ReExportNamed"),
    /** Type-only export: `export type { Type }` (TS) */
    TYPE_ONLY("TypeOnly"),
}

/**
 * Represents a single export specifier within a named export.
 *
 * Examples:
 * - `export { foo }` → ExportSpecifier(localName="foo", exportedName="foo")
 * - `export { foo as bar }` → ExportSpecifier(localName="foo", exportedName="bar")
 * - `export type { Type }` → ExportSpecifier(localName="Type", exportedName="Type", isTypeOnly=true)
 */
@Serializable
data class ExportSpecifier(
    /** The local name being exported */
    var LocalName: String = "",
    /** The exported name (may be same as localName if no alias) */
    var ExportedName: String = "",
    /** Whether this is a type-only export (TypeScript) */
    @Since("2.4.0")
    var IsTypeOnly: Boolean = false,
)

/**
 * Represents an export statement in source code.
 *
 * This structure supports various export styles across different languages while
 * maintaining backward compatibility with the original fields.
 *
 * ## Examples by Language
 *
 * ### TypeScript/JavaScript
 * ```typescript
 * export default expression;                    // Kind=DEFAULT
 * export { foo, bar as baz };                   // Kind=NAMED, Specifiers=[...]
 * export * from "module";                       // Kind=RE_EXPORT_ALL, FromSource="module"
 * export { a, b } from "module";                // Kind=RE_EXPORT_NAMED, Specifiers=[...], FromSource="module"
 * export type { Type };                         // Kind=TYPE_ONLY
 * export const x = 1;                           // Kind=NAMED (inline declaration)
 * ```
 *
 * ### Rust
 * ```rust
 * pub use crate::module::item;                  // Re-export
 * ```
 */
@Serializable
data class CodeExport(
    /**
     * **Legacy field - kept for backward compatibility.**
     *
     * The exported name.
     */
    var Name: String = "",
    /**
     * **Legacy field - kept for backward compatibility.**
     *
     * The source file path.
     */
    var SourceFile: String = "",
    /**
     * The type of the exported entity (class, function, variable, etc.).
     */
    var Type: DataStructType = DataStructType.CLASS,

    // ==================== New Structured Fields (Since 2.4.0) ====================

    /**
     * The semantic kind of this export.
     */
    @Since("2.4.0")
    var Kind: ExportKind = ExportKind.UNKNOWN,

    /**
     * Detailed specifiers for named exports.
     *
     * Each specifier maps a local name to an exported name.
     * Empty for default exports and re-export-all.
     */
    @Since("2.4.0")
    var Specifiers: List<ExportSpecifier> = listOf(),

    /**
     * The source module for re-exports.
     *
     * Example: `export { a } from "module"` → FromSource = "module"
     */
    @Since("2.4.0")
    var FromSource: String = "",

    /**
     * Whether this is a type-only export (TypeScript).
     *
     * Example: `export type { Type }`
     */
    @Since("2.4.0")
    var IsTypeOnly: Boolean = false,
)
