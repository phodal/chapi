package chapi.ast.rustast

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

internal class RustAnalyserTest {
    private val rustAnalyser = RustAnalyser()


    @Test
    internal fun should_identify_package_by_path() {
        val moduleCc = rustAnalyser.analysis("pub mod document;", "enfer_core/src/lib.rs")
        assertEquals(moduleCc.PackageName, "enfer_core")

        val rootCc = rustAnalyser.analysis("pub mod document;", "src/lib.rs")
        assertEquals(rootCc.PackageName, "")

        val subModuleCc = rustAnalyser.analysis("pub mod document;", "enfer_core/src/document.rs")
        assertEquals(subModuleCc.PackageName, "enfer_core::document")
    }


    @Test
    fun analysis() {
        val str = """
            fn main() {
                println!("Hello, world!");
            }
        """.trimIndent()

        val codeContainer = rustAnalyser.analysis(str, "test.rs")
        println(codeContainer)
    }

    @Test
    @Disabled
    fun allGrammarUnderResources() {
        val content = this::class.java.getResource("/grammar")!!
        File(content.toURI()).walkTopDown().forEach {
            if (it.isFile && it.extension == "rs") {
                rustAnalyser.analysis(it.readText(), it.name)
            }
        }
    }
}
