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

    @Test
    internal fun shouldIdentifyMultipleNode() {
        var code = """
interface IPerson {
    name: string;
}

class Person implements IPerson {
    public publicString: string;
    private privateString: string;
    protected protectedString: string;
    readonly readonlyString: string;
    name: string;

    constructor(name: string) {
        this.name = name;
    }
}
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "")
        assertEquals(codeFile.DataStructures.size, 2)
        assertEquals(codeFile.DataStructures[0].NodeName, "IPerson")
        assertEquals(codeFile.DataStructures[1].NodeName, "Person")
    }

    @Test
    internal fun shouldIdentifyImplements() {
        var code = """
class Person implements IPerson {
    public publicString: string;
    private privateString: string;
    protected protectedString: string;
    readonly readonlyString: string;
    name: string;

    constructor(name: string) {
        this.name = name;
    }
}
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "")
        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].Implements[0], "IPerson")
    }

}
