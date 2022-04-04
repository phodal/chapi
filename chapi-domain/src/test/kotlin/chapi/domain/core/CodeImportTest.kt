package chapi.domain.core

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

internal class CodeImportTest {
    @Test
    fun testEquals() {
        assertEquals(CodeImport(Source = "source"), CodeImport(Source = "source"))
    }
}
