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
        }
    }
}
