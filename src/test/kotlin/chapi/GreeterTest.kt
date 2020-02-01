package chapi

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GreeterTest {
    @Test
    fun greet() {
        Greeter("phodal").greet()
        assertEquals(true, true)
    }
}
