package chapi.ast.typescriptast

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class TypeScriptIdentAppTest {
    @Test
    internal fun shouldCompile() {
        var code = """
interface IPerson {
    name: string;
}            
"""
        TypeScriptIdentApp().analysis(code, "")
    }

    @Test
    internal fun shouldIdentifyInterfaceName() {
        var code = """
export interface IPerson {
    name: string;
    gender: string;
}
"""
        val codeFile = TypeScriptIdentApp().analysis(code, "")
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
        val codeFile = TypeScriptIdentApp().analysis(code, "")
        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].NodeName, "Foo")
    }
}
