package chapi.ast.typescriptast;

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class EcmaImportHelperTest {

    @Test
    fun should_convertTypeScriptImport_when_importSourceStartsWithAt() {
        // given
        val importSource = "@/src/component"
        val filePath = "src/main.tsx"

        // when
        val result = EcmaImportHelper.convertTypeScriptImport(importSource, filePath)

        // then
        assertEquals("@.src.component", result)
    }

    @Test
    fun should_convertTypeScriptImport_when_importSourceDoesNotStartWithAt() {
        // given
        val importSource = "../component"
        val filePath = "src/main.tsx"

        // when
        val result = EcmaImportHelper.convertTypeScriptImport(importSource, filePath)

        // then
        assertEquals("component", result)
    }

    @Test
    fun should_convertTypeScriptImport_when_importSourceStartsWithSrc() {
        // given
        val importSource = "src/component"
        val filePath = "src/main.tsx"

        // when
        val result = EcmaImportHelper.convertTypeScriptImport(importSource, filePath)

        // then
        assertEquals("@.component", result)
    }

    @Test
    fun should_convertTypeScriptImport_when_importSourceContainsSlash() {
        // given
        val importSource = "src/component/subcomponent"
        val filePath = "src/main.tsx"

        // when
        val result = EcmaImportHelper.convertTypeScriptImport(importSource, filePath)

        // then
        assertEquals("@.component.subcomponent", result)
    }
}
