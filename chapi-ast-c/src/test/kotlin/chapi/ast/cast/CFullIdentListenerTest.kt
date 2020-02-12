package chapi.ast.cast

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class CFullIdentListenerTest {
    @Test
    internal fun shouldGetCodeFileName() {
        val code = this::class.java.getResource("/_fixtures/examples/1-helloworld.c").readText()
        val fileName = "helloworld.c"

        val codeFile = CAnalyser().analysis(code, fileName)

        assertEquals(codeFile.FullName, fileName)
    }

    @Test
    internal fun shouldIdentifyImport() {
        val code = """
#include <stdio.h>

"""
        val codeFile = CAnalyser().analysis(code, "helloworld.c")

        assertEquals(codeFile.Imports.size, 1)
        assertEquals(codeFile.Imports[0].Source, "stdio.h")
    }

    @Test
    internal fun shouldIdentifyStructName() {
        val code = """
struct list_el {
   int val;
};
"""
        val codeFile = CAnalyser().analysis(code, "helloworld.c")

        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].NodeName, "list_el")
    }
}
