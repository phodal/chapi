package chapi.ast.typescriptast

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class TypeScriptFullIdentListenerTest {
    @Test
    internal fun shouldCompile() {
        var code = """
interface IPerson {
    name: string;
}            
"""
        TypeScriptAnalyser().analysis(code, "")
    }

    @Test
    internal fun shouldIdentifyInterfaceName() {
        var code = """
export interface IPerson {
    name: string;
    gender: string;
}
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "")
        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].NodeName, "IPerson")
    }

    @Test
    internal fun shouldIdentifyInnerClass() {
        var code = """
class Foo {
    static Bar = class {

    }
}
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "")
        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].NodeName, "Foo")
    }
}
