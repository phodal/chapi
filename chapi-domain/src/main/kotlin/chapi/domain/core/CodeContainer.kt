package chapi.domain.core

import chapi.domain.core.CodeDataStruct.Companion.contentByPosition
import kotlinx.serialization.Serializable

/**
 * Represents the kind of container, providing semantic clarity across different languages.
 *
 * Different languages use different concepts for organizing code:
 * - Java/Kotlin: packages
 * - C#/C++: namespaces
 * - Rust: modules (mod) and crates
 * - TypeScript/JavaScript: ES modules (files)
 * - Python: modules and packages
 * - Go: packages
 * - Protobuf/Thrift: packages for IDL
 * - Toml/CMake: configuration files
 */
@Serializable
enum class ContainerKind(val kind: String) {
    /** Default/unknown container type */
    UNKNOWN("Unknown"),
    /** A source file (e.g., .java, .kt, .ts, .py, .go, .rs) */
    SOURCE_FILE("SourceFile"),
    /** A language-declared package (Java, Go, Kotlin) */
    PACKAGE("Package"),
    /** A namespace (C#, C++, TypeScript) */
    NAMESPACE("Namespace"),
    /** A module (Rust mod, Python module, ES module) */
    MODULE("Module"),
    /** A crate (Rust) */
    CRATE("Crate"),
    /** A configuration file (Toml, yaml, json config) */
    CONFIG("Config"),
    /** A build script (CMake, Makefile, build.gradle) */
    BUILD_SCRIPT("BuildScript"),
    /** An IDL definition file (Protobuf, Thrift, OpenAPI) */
    IDL("IDL"),
}

/**
 * Represents a code container that holds the structure of a source file or module.
 *
 * The container provides both legacy fields for backward compatibility and new structured
 * fields for precise cross-language semantic comparison.
 */
@Serializable
data class CodeContainer(
    var FullName: String = "",
    /**
     * **Legacy field - kept for backward compatibility.**
     *
     * In Rust, the package name is the module name.
     * In C++, C#, the namespace is the package name.
     * In toml, the package name is the table name.
     *
     * For new code, prefer using the structured fields:
     * - [DeclaredPackage] for explicitly declared package/namespace
     * - [ResolvedModulePath] for path-derived module names
     * - [NamespacePath] for namespace hierarchies
     */
    var PackageName: String = "",
    var Imports: List<CodeImport> = listOf(),
    var Members: List<CodeMember> = listOf(),
    var DataStructures: List<CodeDataStruct> = listOf(),
    /**
     * In toml, the fields are the key-value pairs.
     */
    var Fields: List<CodeField> = listOf(),
    var Containers: List<CodeContainer> = listOf(),
    @Since("2.2.5")
    var Content: String = "",

    // ==================== New Structured Fields (Since 2.4.0) ====================

    /**
     * The programming language of this container.
     *
     * Examples: "java", "kotlin", "typescript", "rust", "go", "python", "csharp", "cpp", "c",
     * "scala", "protobuf", "thrift", "toml", "cmake"
     */
    @Since("2.4.0")
    var Language: String = "",

    /**
     * The semantic kind of this container, providing cross-language clarity.
     *
     * This helps distinguish between:
     * - Source files vs modules vs namespaces
     * - Code containers vs configuration files vs build scripts
     */
    @Since("2.4.0")
    var Kind: ContainerKind = ContainerKind.UNKNOWN,

    /**
     * The explicitly declared package or namespace name in the source code.
     *
     * Examples:
     * - Java: `package com.example.service;` → "com.example.service"
     * - C#: `namespace MyApp.Services { }` → "MyApp.Services"
     * - Go: `package main` → "main"
     * - Rust: (empty, Rust uses module paths instead of declared packages)
     *
     * This is distinct from [ResolvedModulePath] which is derived from file paths.
     */
    @Since("2.4.0")
    var DeclaredPackage: String = "",

    /**
     * The module path resolved from file system structure or language conventions.
     *
     * Examples:
     * - Rust: "crate::infrastructure::git::parser" (derived from file path)
     * - Python: "mypackage.submodule" (derived from directory structure)
     * - TypeScript: "./services/user" (relative import path)
     *
     * This is distinct from [DeclaredPackage] which is explicitly declared in code.
     */
    @Since("2.4.0")
    var ResolvedModulePath: String = "",

    /**
     * The namespace hierarchy as a list, supporting nested namespaces.
     *
     * Examples:
     * - C#: `namespace A.B.C { }` → ["A", "B", "C"]
     * - C++: `namespace A { namespace B { } }` → ["A", "B"]
     * - TypeScript: `namespace Validation { }` → ["Validation"]
     *
     * Empty for languages without namespace concepts (Java, Go, Rust, Python).
     */
    @Since("2.4.0")
    var NamespacePath: List<String> = listOf(),
) {
    fun buildSourceCode(codeLines: List<String>) {
        this.DataStructures.map { ds ->
            ds.Imports = this.Imports

            ds.Content = contentByPosition(codeLines, ds.Position)
            ds.Functions.map {
                // in Chapi, the position of function is not correct, so we need to fix it
                val position = it.Position
                if (it.Annotations.isNotEmpty()) {
                    val annotationPos = it.Annotations.first().Position
                    position.StartLine = annotationPos.StartLine
                    position.StartLinePosition = annotationPos.StartLinePosition
                }

                it.Content = contentByPosition(codeLines, it.Position)
            }
        }
    }
}
