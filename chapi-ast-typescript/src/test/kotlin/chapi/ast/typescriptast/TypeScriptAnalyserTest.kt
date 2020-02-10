package chapi.ast.typescriptast

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class TypeScriptAnalyserTest {
    @Test
    internal fun shouldAnalysisTypeScriptMultipleClass() {
        val content = this::class.java.getResource("/grammar/Class.ts").readText()
        val codeFile = TypeScriptAnalyser().analysis(content, "")

        assertEquals(codeFile.DataStructures.size, 3)
        assertEquals(codeFile.DataStructures[1].Implements.size, 1)
        assertEquals(codeFile.DataStructures[1].Implements[0], "IPerson")
        assertEquals(codeFile.DataStructures[1].Fields.size, 5)
        assertEquals(codeFile.DataStructures[1].Fields[0].Modifiers[0], "public")
    }

    @Test
    internal fun shouldAnalysisTypeScriptAbstractClass() {
        val content = this::class.java.getResource("/grammar/AbstractClass.ts").readText()
        val codeFile = TypeScriptAnalyser().analysis(content, "")

        assertEquals(codeFile.DataStructures.size, 2)
        assertEquals(codeFile.DataStructures[0].Type, "Class")
        assertEquals(codeFile.DataStructures[1].NodeName, "Employee")
        assertEquals(codeFile.DataStructures[1].Extend, "Person")
    }

    @Test
    internal fun shouldAnalysisTypeScriptModule() {
        val content = this::class.java.getResource("/grammar/Module.ts").readText()
        val codeFile = TypeScriptAnalyser().analysis(content, "")

        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].NodeName, "Employee")
    }

    @Test
    internal fun shouldAnalysisTypeScriptFunctions() {
        val content = this::class.java.getResource("/grammar/Function.ts").readText()
        val codeFile = TypeScriptAnalyser().analysis(content, "")

        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].NodeName, "default")
        val functions = codeFile.DataStructures[0].Functions
        assertEquals(functions.size, 9)
    }
}
