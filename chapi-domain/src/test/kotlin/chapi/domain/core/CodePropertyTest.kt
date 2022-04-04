package chapi.domain.core

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class CodePropertyTest {
    @Test
    fun testEquals() {
        assertEquals(
            CodeProperty(TypeType = "type", TypeValue = "value"),
            CodeProperty(TypeType = "type", TypeValue = "value")
        )
    }
}
