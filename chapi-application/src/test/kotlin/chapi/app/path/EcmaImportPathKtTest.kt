package chapi.app.path

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class EcmaPathConvertKtTest {

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
}
