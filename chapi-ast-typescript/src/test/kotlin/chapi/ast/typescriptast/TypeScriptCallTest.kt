package chapi.ast.typescriptast;

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

internal class TypeScriptCallTest {

    @Test
    fun shouldIdentifyNewObjectCall() {
        val code = """
function testNew() {
  let emp = new Employee(100,"Steve");
  emp.display();
}
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "")

        assertEquals(codeFile.DataStructures.size, 1)
        val functionCalls = codeFile.DataStructures[0].Functions[0].FunctionCalls
        assertEquals(functionCalls.size, 1)
        assertEquals(functionCalls[0].FunctionName, "display")
        assertEquals(functionCalls[0].NodeName, "Employee")
        assertEquals(functionCalls[0].Parameters.size, 0)
    }

    /**
     * Issue #41 - Test TopLevel structure is populated for TypeScript
     */
    @Test
    fun shouldPopulateTopLevelStructure() {
        val code = """
const API_URL = "https://api.example.com";

export function fetchData() {
    return fetch(API_URL);
}

export const handler = async () => {
    return "result";
};
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "test.ts")

        // New: TopLevel should be populated
        assertNotNull(codeFile.TopLevel, "TopLevel should be populated")
        assertTrue(codeFile.TopLevel!!.Functions.isNotEmpty(), "TopLevel should have functions")
        assertTrue(codeFile.TopLevel!!.Fields.isNotEmpty(), "TopLevel should have fields")
        
        // Should have fetchData and handler functions
        val functionNames = codeFile.TopLevel!!.Functions.map { it.Name }
        assertTrue(functionNames.contains("fetchData"), "TopLevel should contain fetchData function")
        assertTrue(functionNames.contains("handler"), "TopLevel should contain handler function")
        
        // Should have API_URL field
        val fieldNames = codeFile.TopLevel!!.Fields.map { it.TypeKey }
        assertTrue(fieldNames.contains("API_URL"), "TopLevel should contain API_URL field")
    }

    /**
     * Issue #41 - Test structured import/export with new fields
     */
    @Test
    fun shouldPopulateStructuredImportFields() {
        val code = """
import { foo, bar as baz } from "module";
import * as utils from "./utils";
import React from "react";
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "test.ts")

        val namedImport = codeFile.Imports.find { it.Source == "module" }
        assertNotNull(namedImport)
        assertEquals(chapi.domain.core.ImportKind.NAMED, namedImport.Kind)
        assertTrue(namedImport.Specifiers.isNotEmpty(), "Named import should have specifiers")
        
        val namespaceImport = codeFile.Imports.find { it.Source.contains("utils") }
        assertNotNull(namespaceImport)
        assertEquals(chapi.domain.core.ImportKind.NAMESPACE, namespaceImport.Kind)
        assertEquals("utils", namespaceImport.NamespaceName)
        
        val defaultImport = codeFile.Imports.find { it.Source == "react" }
        assertNotNull(defaultImport)
        assertEquals(chapi.domain.core.ImportKind.DEFAULT, defaultImport.Kind)
        assertEquals("React", defaultImport.DefaultName)
    }

    /**
     * Issue #41 - Test CodeContainer new fields (Language, Kind)
     */
    @Test
    fun shouldPopulateContainerMetadata() {
        val code = """
export const x = 1;
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "test.ts")

        assertEquals("typescript", codeFile.Language)
        assertEquals(chapi.domain.core.ContainerKind.MODULE, codeFile.Kind)
    }
}
