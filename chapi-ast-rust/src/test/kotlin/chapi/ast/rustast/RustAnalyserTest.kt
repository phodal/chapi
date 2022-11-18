package chapi.ast.rustast

import org.junit.jupiter.api.Test

internal class RustAnalyserTest {

    @Test
    fun analysis() {
        val str = """
            fn main() {
                println!("Hello, world!");
            }
        """.trimIndent()

        val codeContainer = RustAnalyser().analysis(str, "test.rs")
    }
}
