package chapi.ast.goast

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class GoFullIdentListenerTest {
    @Test
    internal fun shouldIdentifyPackageName() {
        var code = """
package main
"""

        val codeFile = GoAnalyser().analysis(code, "")
        assertEquals(codeFile.PackageName, "main")
    }

    @Test
    internal fun shouldIdentifySingleImport() {
        var code = """
package main

import "fmt"
"""

        val codeFile = GoAnalyser().analysis(code, "")
        assertEquals(codeFile.Imports.size, 1)
        assertEquals(codeFile.Imports[0].Source, "fmt")
    }

    @Test
    internal fun shouldIdentifyMultipleLineImport() {
        var code = """
package main

import "fmt"
import . "time"
"""

        val codeFile = GoAnalyser().analysis(code, "")
        assertEquals(codeFile.Imports.size, 2)
        assertEquals(codeFile.Imports[0].Source, "fmt")
        assertEquals(codeFile.Imports[1].Source, "time")
        assertEquals(codeFile.Imports[1].AsName, ".")
    }
}
