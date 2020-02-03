package chapi.ast.typescriptast

import org.junit.jupiter.api.Test

internal class TypeScriptIdentAppTest {
    @Test
    internal fun shouldCompile() {
        var code = """
interface IPerson {
    name: string;
}            
"""
        TypeScriptIdentApp().analysis(code)
    }
}
