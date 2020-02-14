package chapi.domain.core

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CodePropertyTest {

    @Test
    fun testToString() {
        val codeProperty = CodeProperty(
            TypeType = "Type",
            TypeValue = "typ"
        )

        assertEquals(codeProperty.toString(), """{"Modifiers":[],"DefaultValue":"","TypeValue":"typ","TypeType":"Type","ReturnTypes":[],"Parameters":[]}""")
    }
}
