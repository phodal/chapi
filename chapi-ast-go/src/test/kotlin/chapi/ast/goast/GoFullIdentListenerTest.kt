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
}
