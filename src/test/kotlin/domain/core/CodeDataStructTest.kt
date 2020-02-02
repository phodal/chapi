package domain.core

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class CodeDataStructTest {
    @Test
    internal fun shouldIdentifyUtilClass() {
        assertEquals(CodeDataStruct(NodeName = "BlueUtil").isUtilClass(), true)
    }
}
