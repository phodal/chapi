package domain.core

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class CodeCallTest {
    @Test
    internal fun shouldBuildFullName() {
        val fullName = CodeCall(Package = "package", NodeName = "nodeName").buildClassFullName()
        assertEquals(fullName, "package.nodeName")
    }
}
