package chapi.ast.typescriptast

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class TypeScriptAnalyserTest {
    fun String.asResource(work: (String) -> Unit) {
        val content = this.javaClass::class.java.getResource(this).readText()
        work(content)
    }

    @Test
    internal fun shouldAnalysisTypeScriptMultipleClass() {
        "/grammar/Class.ts".asResource {
            val codeFile = TypeScriptAnalyser().analysis(it, "")

            assertEquals(codeFile.DataStructures.size, 3)
            assertEquals(codeFile.DataStructures[1].Implements.size, 1)
            assertEquals(codeFile.DataStructures[1].Implements[0], "IPerson")
            assertEquals(codeFile.DataStructures[1].Fields.size, 5)
            assertEquals(codeFile.DataStructures[1].Fields[0].Modifiers[0], "public")
        }
    }

    @Test
    internal fun shouldAnalysisTypeScriptAbstractClass() {
        "/grammar/AbstractClass.ts".asResource {
            val codeFile = TypeScriptAnalyser().analysis(it, "")

            assertEquals(codeFile.DataStructures.size, 2)
            assertEquals(codeFile.DataStructures[0].Type, "Class")
            assertEquals(codeFile.DataStructures[1].NodeName, "Employee")
            assertEquals(codeFile.DataStructures[1].Extend, "Person")
        }
    }

    @Test
    internal fun shouldAnalysisTypeScriptModule() {
        "/grammar/Module.ts".asResource {
            val codeFile = TypeScriptAnalyser().analysis(it, "")

            assertEquals(codeFile.DataStructures.size, 1)
            assertEquals(codeFile.DataStructures[0].NodeName, "Employee")
        }
    }
}
