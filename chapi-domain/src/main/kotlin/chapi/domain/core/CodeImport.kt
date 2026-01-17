package chapi.domain.core

import kotlinx.serialization.Serializable

/**
 * Represents the kind/style of import statement.
 *
 * Different languages have different import mechanisms:
 * - TypeScript/JavaScript: default, named, namespace, side-effect, type-only
 * - Rust: use paths with tree structure
 * - Python: from-import, relative imports
 * - Java: static imports, wildcard imports
 * - Go: blank imports, dot imports
 */
@Serializable
enum class ImportKind(val kind: String) {
    /** Default/unknown import kind */
    UNKNOWN("Unknown"),
    /** Default import: `import defaultExport from "module"` (TS/JS) */
    DEFAULT("Default"),
    /** Named imports: `import { a, b } from "module"` (TS/JS), `from x import a, b` (Python) */
    NAMED("Named"),
    /** Namespace import: `import * as ns from "module"` (TS/JS) */
    NAMESPACE("Namespace"),
    /** Side-effect only import: `import "module"` (TS/JS), `import _ "pkg"` (Go) */
    SIDE_EFFECT("SideEffect"),
    /** Static import: `import static pkg.Class.method` (Java) */
    STATIC("Static"),
    /** Wildcard/glob import: `import pkg.*` (Java), `from x import *` (Python), `use x::*` (Rust) */
    WILDCARD("Wildcard"),
    /** Relative import: `from .sibling import x` (Python), `from "../utils"` (TS) */
    RELATIVE("Relative"),
    /** Type-only import: `import type { Type } from "module"` (TS) */
    TYPE_ONLY("TypeOnly"),
    /** Dot import: `import . "pkg"` (Go) - imports into current namespace */
    DOT("Dot"),
}

/**
 * Represents a single import specifier within a named import.
 *
 * Examples:
 * - `import { foo } from "mod"` → ImportSpecifier(originalName="foo", localName="foo")
 * - `import { foo as bar } from "mod"` → ImportSpecifier(originalName="foo", localName="bar")
 * - `import type { Type } from "mod"` → ImportSpecifier(originalName="Type", localName="Type", isTypeOnly=true)
 * - Scala: `import pkg.{A => B}` → ImportSpecifier(originalName="A", localName="B")
 * - Rust: `use pkg::{a as b}` → ImportSpecifier(originalName="a", localName="b")
 */
@Serializable
data class ImportSpecifier(
    /** The exported name from the source module */
    var OriginalName: String = "",
    /** The local binding name (may be same as originalName if no alias) */
    var LocalName: String = "",
    /** Whether this is a type-only import (TypeScript) */
    @Since("2.4.0")
    var IsTypeOnly: Boolean = false,
)

/**
 * Represents an import statement in source code.
 *
 * This structure supports various import styles across different languages while
 * maintaining backward compatibility with the original string-based fields.
 *
 * ## Examples by Language
 *
 * ### TypeScript/JavaScript
 * ```typescript
 * import defaultExport from "module";           // Kind=DEFAULT, DefaultName="defaultExport"
 * import { foo, bar as baz } from "module";     // Kind=NAMED, Specifiers=[...]
 * import * as ns from "module";                 // Kind=NAMESPACE, NamespaceName="ns"
 * import "module";                              // Kind=SIDE_EFFECT
 * import type { Type } from "module";           // Kind=TYPE_ONLY
 * ```
 *
 * ### Rust
 * ```rust
 * use crate::module::item;                      // Source="crate::module::item"
 * use crate::module::{a, b as c};               // Specifiers=[a→a, b→c]
 * use crate::module::*;                         // Kind=WILDCARD
 * ```
 *
 * ### Python
 * ```python
 * import module                                 # Source="module"
 * from module import a, b as c                  # Kind=NAMED, Specifiers=[...]
 * from .relative import x                       # Kind=RELATIVE
 * ```
 *
 * ### Java
 * ```java
 * import pkg.Class;                             # Source="pkg.Class"
 * import pkg.*;                                 # Kind=WILDCARD
 * import static pkg.Class.method;              # Kind=STATIC
 * ```
 */
@Serializable
data class CodeImport(
    /**
     * **Legacy field - kept for backward compatibility.**
     *
     * The source module path or package name.
     */
    var Source: String = "",
    /**
     * **Legacy field - kept for backward compatibility.**
     *
     * For new code, use [Specifiers] with [ImportSpecifier.LocalName] instead.
     */
    var AsName: String = "",
    /**
     * **Legacy field - kept for backward compatibility.**
     *
     * For new code, use [Specifiers] to get detailed import information.
     */
    var UsageName: List<String> = listOf(),
    /**
     * Import scope indicator.
     * - Rust: "crate" or "cargo"
     * - Java: "static" for static imports
     */
    var Scope: String = "",

    // ==================== New Structured Fields (Since 2.4.0) ====================

    /**
     * The semantic kind of this import.
     */
    @Since("2.4.0")
    var Kind: ImportKind = ImportKind.UNKNOWN,

    /**
     * Detailed specifiers for named imports.
     *
     * Each specifier maps an exported name to a local binding name.
     * Empty for non-named imports (default, namespace, side-effect).
     */
    @Since("2.4.0")
    var Specifiers: List<ImportSpecifier> = listOf(),

    /**
     * The local binding name for default imports.
     *
     * Example: `import React from "react"` → DefaultName = "React"
     */
    @Since("2.4.0")
    var DefaultName: String = "",

    /**
     * The local namespace name for namespace imports.
     *
     * Example: `import * as utils from "./utils"` → NamespaceName = "utils"
     */
    @Since("2.4.0")
    var NamespaceName: String = "",

    /**
     * Whether this is a type-only import (TypeScript).
     *
     * Example: `import type { Type } from "module"`
     */
    @Since("2.4.0")
    var IsTypeOnly: Boolean = false,

    /**
     * The import path as a structured list (useful for Rust path trees).
     *
     * Example: `use crate::infrastructure::parser` → ["crate", "infrastructure", "parser"]
     */
    @Since("2.4.0")
    var PathSegments: List<String> = listOf(),
)
