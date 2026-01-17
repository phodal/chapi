package chapi.domain.core

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

internal class CodeImportTest {
    @Test
    fun testEquals() {
        assertEquals(CodeImport(Source = "source"), CodeImport(Source = "source"))
    }

    @Test
    fun `should support TypeScript named imports with specifiers`() {
        // import { foo, bar as baz } from "module"
        val tsImport = CodeImport(
            Source = "module",
            Kind = ImportKind.NAMED,
            Specifiers = listOf(
                ImportSpecifier(OriginalName = "foo", LocalName = "foo"),
                ImportSpecifier(OriginalName = "bar", LocalName = "baz")
            ),
            // Legacy fields for backward compatibility
            UsageName = listOf("foo", "bar"),
            AsName = "baz"
        )

        assertEquals(ImportKind.NAMED, tsImport.Kind)
        assertEquals(2, tsImport.Specifiers.size)
        assertEquals("foo", tsImport.Specifiers[0].OriginalName)
        assertEquals("foo", tsImport.Specifiers[0].LocalName)
        assertEquals("bar", tsImport.Specifiers[1].OriginalName)
        assertEquals("baz", tsImport.Specifiers[1].LocalName)
    }

    @Test
    fun `should support TypeScript default import`() {
        // import React from "react"
        val tsImport = CodeImport(
            Source = "react",
            Kind = ImportKind.DEFAULT,
            DefaultName = "React"
        )

        assertEquals(ImportKind.DEFAULT, tsImport.Kind)
        assertEquals("React", tsImport.DefaultName)
    }

    @Test
    fun `should support TypeScript namespace import`() {
        // import * as utils from "./utils"
        val tsImport = CodeImport(
            Source = "./utils",
            Kind = ImportKind.NAMESPACE,
            NamespaceName = "utils"
        )

        assertEquals(ImportKind.NAMESPACE, tsImport.Kind)
        assertEquals("utils", tsImport.NamespaceName)
    }

    @Test
    fun `should support TypeScript side-effect import`() {
        // import "polyfill"
        val tsImport = CodeImport(
            Source = "polyfill",
            Kind = ImportKind.SIDE_EFFECT
        )

        assertEquals(ImportKind.SIDE_EFFECT, tsImport.Kind)
    }

    @Test
    fun `should support TypeScript type-only import`() {
        // import type { Type } from "module"
        val tsImport = CodeImport(
            Source = "module",
            Kind = ImportKind.TYPE_ONLY,
            IsTypeOnly = true,
            Specifiers = listOf(
                ImportSpecifier(OriginalName = "Type", LocalName = "Type", IsTypeOnly = true)
            )
        )

        assertEquals(ImportKind.TYPE_ONLY, tsImport.Kind)
        assertTrue(tsImport.IsTypeOnly)
        assertTrue(tsImport.Specifiers[0].IsTypeOnly)
    }

    @Test
    fun `should support Rust use with path segments`() {
        // use crate::infrastructure::parser
        val rustImport = CodeImport(
            Source = "crate::infrastructure::parser",
            Kind = ImportKind.RELATIVE,
            Scope = "crate",
            PathSegments = listOf("crate", "infrastructure", "parser"),
            Specifiers = listOf(
                ImportSpecifier(OriginalName = "parser", LocalName = "parser")
            )
        )

        assertEquals(ImportKind.RELATIVE, rustImport.Kind)
        assertEquals("crate", rustImport.Scope)
        assertEquals(listOf("crate", "infrastructure", "parser"), rustImport.PathSegments)
    }

    @Test
    fun `should support Rust glob import`() {
        // use std::collections::*
        val rustImport = CodeImport(
            Source = "std::collections::*",
            Kind = ImportKind.WILDCARD,
            PathSegments = listOf("std", "collections")
        )

        assertEquals(ImportKind.WILDCARD, rustImport.Kind)
    }

    @Test
    fun `should support Python from-import with alias`() {
        // from module import foo, bar as baz
        val pyImport = CodeImport(
            Source = "module",
            Kind = ImportKind.NAMED,
            Specifiers = listOf(
                ImportSpecifier(OriginalName = "foo", LocalName = "foo"),
                ImportSpecifier(OriginalName = "bar", LocalName = "baz")
            ),
            PathSegments = listOf("module")
        )

        assertEquals(ImportKind.NAMED, pyImport.Kind)
        assertEquals(2, pyImport.Specifiers.size)
    }

    @Test
    fun `should support Python relative import`() {
        // from ..sibling import func
        val pyImport = CodeImport(
            Source = "..sibling",
            Kind = ImportKind.RELATIVE,
            Specifiers = listOf(
                ImportSpecifier(OriginalName = "func", LocalName = "func")
            )
        )

        assertEquals(ImportKind.RELATIVE, pyImport.Kind)
    }

    @Test
    fun `should support Java static import`() {
        // import static org.junit.Assert.assertEquals
        val javaImport = CodeImport(
            Source = "org.junit.Assert",
            Kind = ImportKind.STATIC,
            Scope = "static",
            Specifiers = listOf(
                ImportSpecifier(OriginalName = "assertEquals", LocalName = "assertEquals")
            ),
            PathSegments = listOf("org", "junit", "Assert", "assertEquals")
        )

        assertEquals(ImportKind.STATIC, javaImport.Kind)
        assertEquals("static", javaImport.Scope)
    }

    @Test
    fun `should support Java wildcard import`() {
        // import java.util.*
        val javaImport = CodeImport(
            Source = "java.util",
            Kind = ImportKind.WILDCARD,
            UsageName = listOf("*"),
            PathSegments = listOf("java", "util")
        )

        assertEquals(ImportKind.WILDCARD, javaImport.Kind)
    }

    @Test
    fun `should support Go dot import`() {
        // import . "testing"
        val goImport = CodeImport(
            Source = "testing",
            Kind = ImportKind.DOT,
            PathSegments = listOf("testing")
        )

        assertEquals(ImportKind.DOT, goImport.Kind)
    }

    @Test
    fun `should support Go blank import for side effects`() {
        // import _ "database/sql"
        val goImport = CodeImport(
            Source = "database/sql",
            Kind = ImportKind.SIDE_EFFECT,
            PathSegments = listOf("database", "sql")
        )

        assertEquals(ImportKind.SIDE_EFFECT, goImport.Kind)
    }

    @Test
    fun `should maintain backward compatibility with legacy fields`() {
        // Old code that only uses legacy fields should still work
        val legacyImport = CodeImport(
            Source = "some/module",
            AsName = "alias",
            UsageName = listOf("name1", "name2")
        )

        assertEquals("some/module", legacyImport.Source)
        assertEquals("alias", legacyImport.AsName)
        assertEquals(listOf("name1", "name2"), legacyImport.UsageName)
        // New fields should have sensible defaults
        assertEquals(ImportKind.UNKNOWN, legacyImport.Kind)
        assertEquals(emptyList<ImportSpecifier>(), legacyImport.Specifiers)
        assertEquals("", legacyImport.DefaultName)
        assertEquals("", legacyImport.NamespaceName)
        assertFalse(legacyImport.IsTypeOnly)
    }
}
