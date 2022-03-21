package chapi.ast.typescriptast

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class TSIdentifyTest {

    @Test
    internal fun `return origin file name when is one File `() {
        val tsIdentify = TSIdentify("AbstractClass.ts",)
        val pkgName = tsIdentify.resolvePackage()

        assertEquals("AbstractClass.ts", pkgName)
    }

    @Test
    internal fun `return to start with @ with slash start file `() {
        val tsIdentify = TSIdentify("/AbstractClass.ts",)
        val pkgName = tsIdentify.resolvePackage()

        assertEquals("@", pkgName)
    }

    @Test
    internal fun `return to start with src with slash start file `() {
        val tsIdentify = TSIdentify("src/AbstractClass.ts",)
        val pkgName = tsIdentify.resolvePackage()

        assertEquals("@", pkgName)
    }

    @Test
    internal fun `return to start with src with name`() {
        val tsIdentify = TSIdentify("src/grammar/AbstractClass.ts",)
        val pkgName = tsIdentify.resolvePackage()

        assertEquals("@.grammar", pkgName)
    }

    @Test
    internal fun `add @ for start with slash`() {
        val tsIdentify = TSIdentify("/grammar/AbstractClass.ts",)
        val pkgName = tsIdentify.resolvePackage()

        assertEquals("@.grammar", pkgName)
    }
}
