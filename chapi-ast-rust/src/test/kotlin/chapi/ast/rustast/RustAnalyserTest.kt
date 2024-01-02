package chapi.ast.rustast

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

internal class RustAnalyserTest {
    private val rustAnalyser = RustAnalyser()


    @Test
    internal fun should_identify_package_by_path() {
        // check os
        if (System.getProperty("os.name").lowercase().contains("windows")) {
            val moduleCc = rustAnalyser.analysis("pub mod document;", "enfer_core\\src\\lib.rs")
            assertEquals(moduleCc.PackageName, "enfer_core")

            val rootCc = rustAnalyser.analysis("pub mod document;", "src\\lib.rs")
            assertEquals(rootCc.PackageName, "")

            val subModuleCc = rustAnalyser.analysis("pub mod document;", "enfer_core\\src\\document.rs")
            assertEquals(subModuleCc.PackageName, "enfer_core::document")
        } else {
            val moduleCc = rustAnalyser.analysis("pub mod document;", "enfer_core/src/lib.rs")
            assertEquals(moduleCc.PackageName, "enfer_core")

            val rootCc = rustAnalyser.analysis("pub mod document;", "src/lib.rs")
            assertEquals(rootCc.PackageName, "")

            val subModuleCc = rustAnalyser.analysis("pub mod document;", "enfer_core/src/document.rs")
            assertEquals(subModuleCc.PackageName, "enfer_core::document")
        }
    }

    @Test
    fun should_success_build_main_position() {
        val str = """
            fn main() {
                println!("Hello, world!");
            }
        """.trimIndent()

        val codeContainer = rustAnalyser.analysis(str, "main.rs")
        val dataStructures = codeContainer.DataStructures
        assertEquals(dataStructures.size, 1)
        assertEquals(dataStructures[0].NodeName, "main")
        val position = dataStructures[0].Functions[0].Position

        assertEquals(position.StartLine, 1)
        assertEquals(position.StopLine, 3)
    }

    @Test
    fun should_build_structure_position() {
        val str = """
            pub struct Point {
                x: i32,
                y: i32,
            }
        """.trimIndent()

        val codeContainer = rustAnalyser.analysis(str, "main.rs")
        val dataStructures = codeContainer.DataStructures
        assertEquals(dataStructures.size, 1)
        assertEquals(dataStructures[0].NodeName, "Point")
        val position = dataStructures[0].Position

        assertEquals(position.StartLine, 1)
        assertEquals(position.StopLine, 4)
    }

    @Test
    fun allGrammarUnderResources() {
        val content = this::class.java.getResource("/grammar")!!
        File(content.toURI()).walkTopDown().forEach {
            if (it.isFile && it.extension == "rs") {
                rustAnalyser.analysis(it.readText(), it.name)
            }
        }
    }
}
