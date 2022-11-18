package chapi.ast.rustast

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.io.File

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

    @Test
    @Disabled
    fun allGrammarUnderResources() {
        val content = this::class.java.getResource("/grammar")!!
        File(content.toURI()).walkTopDown().forEach {
            if (it.isFile && it.extension == "rs") {
                RustAnalyser().analysis(it.readText(), it.name)
            }
        }
    }
}
