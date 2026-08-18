package chapi.ast.arkts

import chapi.domain.core.DataStructType
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class ArkTSAnalyserTest {
    private val analyser = ArkTSAnalyser()

    @Test
    fun shouldParseArkUIComponentAndExtractModel() {
        val source = resource("/fixtures/CounterPage.ets")
        val result = analyser.analysisWithDiagnostics(source, "src/main/ets/pages/CounterPage.ets")

        assertNoDiagnostics(result)
        assertEquals("arkts", result.container.Language)
        assertEquals("@kit.AbilityKit", result.container.Imports.single().Source)

        val page = result.container.DataStructures.single { it.NodeName == "CounterPage" }
        assertEquals(DataStructType.STRUCT, page.Type)
        assertEquals("src/main/ets/pages/CounterPage.ets", page.FilePath)
        assertEquals(listOf("Entry", "Component"), page.Annotations.map { it.Name })
        assertTrue(page.Position.StartLine > 0)

        val count = page.Fields.single { it.TypeKey == "count" }
        assertEquals("number", count.TypeType)
        assertEquals("0", count.TypeValue)
        assertEquals(listOf("State"), count.Annotations.map { it.Name })

        val build = page.Functions.single { it.Name == "build" }
        val calls = build.FunctionCalls.map { it.FunctionName }
        assertTrue(calls.any { it.contains("Column") }, calls.toString())
        assertTrue(calls.any { it.contains("Text") }, calls.toString())
        assertTrue(calls.any { it.contains("Button") || it.contains("onClick") }, calls.toString())
    }

    @Test
    fun shouldParsePinnedUpstreamCorpusSubset() {
        val treeSitter = analyser.analysisWithDiagnostics(
            resource("/corpus/tree-sitter/decorated-struct.ets"),
            "decorated-struct.ets"
        )
        assertNoDiagnostics(treeSitter)
        assertEquals(DataStructType.STRUCT, treeSitter.container.DataStructures.single { it.NodeName == "Foo" }.Type)

        val official = analyser.analysisWithDiagnostics(
            resource("/corpus/openharmony/StructTest1.ets"),
            "StructTest1.ets"
        )
        assertNoDiagnostics(official)
        assertEquals(DataStructType.STRUCT, official.container.DataStructures.single { it.NodeName == "CE" }.Type)
    }

    @Test
    fun shouldParseAnnotationLazyImportBuilderAndLeadingDotCalls() {
        val source = """
            import lazy { LazyFeature as DeferredFeature } from './lazy-feature';

            @interface RouteOptions {
              value: string = "default";
              count: number = 1;
            }

            @Builder
            function titleBuilder(value: string): void {
              Text(value)
            }

            @Extend(Text)
            function fancy(fontSize: number): void {
              .fontColor(Color.Red)
              .fontSize(fontSize)
            }
        """.trimIndent()

        val result = analyser.analysisWithDiagnostics(source, "Builders.ets")
        assertNoDiagnostics(result)
        assertEquals("./lazy-feature", result.container.Imports.single().Source)

        val annotation = result.container.DataStructures.single { it.NodeName == "RouteOptions" }
        assertEquals(DataStructType.INTERFACE, annotation.Type)
        assertEquals(listOf("value", "count"), annotation.Fields.map { it.TypeKey })

        val functions = assertNotNull(result.container.TopLevel).Functions
        assertEquals(listOf("Builder"), functions.single { it.Name == "titleBuilder" }.Annotations.map { it.Name })
        val leadingCalls = functions.single { it.Name == "fancy" }.FunctionCalls.map { it.FunctionName }
        assertEquals(listOf("fontColor", "fontSize"), leadingCalls)
    }

    @Test
    fun shouldReportInvalidArkTSSyntax() {
        val result = analyser.analysisWithDiagnostics(
            "@Component struct Broken { build( {",
            "Broken.ets"
        )

        assertTrue(result.diagnostics.isNotEmpty())
    }

    private fun resource(path: String): String =
        requireNotNull(this::class.java.getResource(path)) { "Missing test resource: $path" }.readText()

    private fun assertNoDiagnostics(result: ArkTSAnalysisResult) {
        assertTrue(
            result.diagnostics.isEmpty(),
            result.diagnostics.joinToString("\n") { "${it.line}:${it.column} ${it.message}" }
        )
    }
}
