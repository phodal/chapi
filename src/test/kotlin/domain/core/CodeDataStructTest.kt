package domain.core

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class CodeDataStructTest {
    @Test
    internal fun shouldIdentifyUtilClass() {
        assertEquals(CodeDataStruct<String>(NodeName = "BlueUtil").isUtilClass(), true)
    }
}
