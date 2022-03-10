package chapi.app.path

import chapi.app.frontend.path.ecmaImportConvert
import chapi.app.frontend.path.importConvert
import chapi.app.frontend.path.relativeRoot
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class EcmaImportPathKtTest {

    @Test
    internal fun sameLevelPath() {
        val path = importConvert("src/components/", "./config.js")
        assertEquals(path, "src/components/config.js")
    }

    @Test
    internal fun parentPath() {
        val path = importConvert("src/components/", "../config.js")
        assertEquals(path, "src/config.js")
    }

    @Test
    internal fun fileSameLevel() {
        val path = importConvert("src/components/Hello.js", "../config.js")
        assertEquals(path, "src/config.js")
    }

    @Test
    internal fun resolvePath() {
        val path = importConvert("src/components/Hello.js", "@/src/components/config.js")
        assertEquals(path, "src/components/config.js")
    }

    @Test
    internal fun resolveRealWorldPath() {
        val full = "chapi/chapi-application/ts/apicall/addition/"
        val path = importConvert(full, "../components/config.js")
        assertEquals(path, "chapi/chapi-application/ts/apicall/components/config.js")
    }

    @Test
    internal fun relativePath() {
        val path = relativeRoot("root/src/components/Hello.js", "root/src/components/config.js")
        assertEquals(path, "../config.js")
    }

    @Test
    internal fun withoutChangeNotRelative() {
        val path = importConvert("src/components/", "jquery")
        assertEquals(path, "jquery")
    }

    @Test
    internal fun convertFullPath() {
        val path = ecmaImportConvert("modernizing/chapi/chapi-application", "modernizing/chapi/chapi-application/src/components/hello.js", "./config.js")
        assertEquals(path, "src/components/config.js")
    }
}
