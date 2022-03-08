package chapi.app.path

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class EcmaImportPathKtTest {

    @Test
    internal fun sameLevelPath() {
        val path = ecmaPathConvert("src/components/", "./config.js")
        assertEquals(path, "src/components/config.js")
    }

    @Test
    internal fun parentPath() {
        val path = ecmaPathConvert("src/components/", "../config.js")
        assertEquals(path, "src/config.js")
    }

    @Test
    internal fun fileSameLevel() {
        val path = ecmaPathConvert("src/components/Hello.js", "../config.js")
        assertEquals(path, "src/components/config.js")
    }

    @Test
    internal fun resolvePath() {
        val path = ecmaPathConvert("src/components/Hello.js", "@/src/components/config.js")
        assertEquals(path, "src/components/config.js")
    }

    @Test
    internal fun resolveRealWorldPath() {
        val full = "chapi/chapi-application/ts/apicall/addition/"
        val path = ecmaPathConvert(full, "../components/config.js")
        assertEquals(path, "chapi/chapi-application/ts/apicall/components/config.js")
    }

    // source: /Volumes/source/modernizing/chapi/chapi-application/build/resources/test/languages/ts/apicall/addition/systemInfo.ts
    @Test
    internal fun relativePath() {
        val path = relativeRoot("root/src/components/Hello.js", "root/src/components/config.js")
        assertEquals(path, "../config.js")
    }
}
