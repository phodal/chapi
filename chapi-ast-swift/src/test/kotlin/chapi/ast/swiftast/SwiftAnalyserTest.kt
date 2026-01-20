package chapi.ast.swiftast

import chapi.domain.core.ContainerKind
import chapi.domain.core.DataStructType
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class SwiftAnalyserTest {
    @Test
    fun shouldParseImportsAndTypes() {
        val code = """
            import Foundation
            import SwiftUI

            enum State {
              case idle
              case loading
            }

            struct User {
              let id: Int
              func name() -> String { return "u" }
            }

            class Service {
              func fetch() -> Int { return 1 }
            }

            func topLevel() -> Void { }
        """.trimIndent()

        val container = SwiftAnalyser().analysis(code, "example.swift")

        assertEquals("swift", container.Language)
        assertEquals(ContainerKind.SOURCE_FILE, container.Kind)

        assertEquals(2, container.Imports.size)
        assertEquals("Foundation", container.Imports[0].Source)
        assertEquals("SwiftUI", container.Imports[1].Source)

        val enumDs = container.DataStructures.firstOrNull { it.NodeName == "State" }
        assertNotNull(enumDs)
        assertEquals(DataStructType.ENUM, enumDs.Type)

        val structDs = container.DataStructures.firstOrNull { it.NodeName == "User" }
        assertNotNull(structDs)
        assertEquals(DataStructType.STRUCT, structDs.Type)
        assertTrue(structDs.Functions.any { it.Name == "name" })

        val classDs = container.DataStructures.firstOrNull { it.NodeName == "Service" }
        assertNotNull(classDs)
        assertEquals(DataStructType.CLASS, classDs.Type)
        assertTrue(classDs.Functions.any { it.Name == "fetch" })

        val topLevel = container.TopLevel
        assertNotNull(topLevel)
        assertTrue(topLevel.Functions.any { it.Name == "topLevel" })
    }
}

