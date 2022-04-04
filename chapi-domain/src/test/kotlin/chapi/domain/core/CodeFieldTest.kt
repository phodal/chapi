package chapi.domain.core

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class CodeFieldTest {

    @Test
    fun testEquals() {
        assertEquals(CodeField(TypeType = "type"), CodeField(TypeType = "type"))
    }
}
