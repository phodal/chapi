package chapi.ast.arkts

import chapi.domain.core.DataStructType
import chapi.domain.core.ImportKind
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

    @Test
    fun shouldParseAllInOneRegressionFixture() {
        val result = analyser.analysisWithDiagnostics(
            resource("/fixtures/AllInOne.ets"),
            "src/main/ets/pages/AllInOne.ets"
        )

        assertNoDiagnostics(result)

        val imports = result.container.Imports.groupBy { it.Source }
        assertEquals(ImportKind.DEFAULT, imports.getValue("@ohos.app.ability.UIAbility").single().Kind)
        assertTrue(imports.getValue("@kit.AbilityKit").map { it.Kind }.containsAll(listOf(ImportKind.NAMED, ImportKind.NAMESPACE)))
        assertEquals(ImportKind.TYPE_ONLY, imports.getValue("./types").single().Kind)
        assertTrue(imports.getValue("./types").single().Specifiers.all { it.IsTypeOnly })
        assertEquals("lazy", imports.getValue("./lazy-feature").single().Scope)
        assertEquals(ImportKind.SIDE_EFFECT, imports.getValue("./bootstrap").single().Kind)

        val structures = result.container.DataStructures.associateBy { it.NodeName }
        val expectedStructures = setOf(
            "FeatureMeta", "AnnotatedService", "DefaultArkTSService", "BaseEntity", "TodoItem", "Box",
            "InMemoryRepository", "BuilderState", "TodoRow", "CustomPanel", "StorageCard", "ThemeConsumer",
            "ArkTSAllInOnePage"
        )
        assertTrue(structures.keys.containsAll(expectedStructures), structures.keys.toString())
        assertEquals(DataStructType.INTERFACE, structures.getValue("FeatureMeta").Type)
        assertEquals(DataStructType.STRUCT, structures.getValue("TodoRow").Type)

        val serviceAnnotation = structures.getValue("AnnotatedService").Annotations.single { it.Name == "FeatureMeta" }
        assertTrue(serviceAnnotation.KeyValues.single().Value.contains("all-in-one"))

        val todoRow = structures.getValue("TodoRow")
        assertEquals(listOf("Reusable", "Component"), todoRow.Annotations.map { it.Name })
        assertEquals(listOf("Require", "Prop"), todoRow.Fields.single { it.TypeKey == "title" }.Annotations.map { it.Name })
        assertEquals("() => void".replace(" ", ""), todoRow.Fields.single { it.TypeKey == "actions" }.TypeType)

        val page = structures.getValue("ArkTSAllInOnePage")
        assertEquals("src/main/ets/pages/AllInOne.ets", page.FilePath)
        assertTrue(page.Position.StartLine > 0)
        assertEquals(listOf("Entry", "Component"), page.Annotations.map { it.Name })
        assertTrue(
            page.Functions.map { it.Name }.containsAll(
                listOf("aboutToAppear", "aboutToDisappear", "onPageShow", "onPageHide", "header", "emptyState", "pageStyle", "build")
            )
        )

        val buildCalls = page.Functions.single { it.Name == "build" }.FunctionCalls.map { it.FunctionName }
        assertSubsequence(
            buildCalls,
            listOf("Column", "header", "GlobalSectionTitle", "EditableLabel", "CustomPanel", "TextInput", "ForEach", "TodoRow", "Divider", "Button", "Toggle", "ThemeConsumer", "StorageCard", "Text", "pageStyle")
        )

        val allCalls = result.container.DataStructures
            .flatMap { it.Functions }
            .flatMap { it.FunctionCalls }
            .map { it.FunctionName }
        assertTrue(
            allCalls.containsAll(listOf("Row", "Text", "onClick", "fontSize", "headline", "globalCardStyle")),
            allCalls.toString()
        )

        val topLevel = assertNotNull(result.container.TopLevel)
        assertEquals(3, topLevel.Functions.count { it.Name == "formatValue" })
        assertTrue(topLevel.Functions.map { it.Name }.containsAll(listOf("GlobalSectionTitle", "EditableLabel", "loadTodo")))
        assertTrue(topLevel.Exports.any { it.Name == "logger" && it.FromSource == "./logger" })
        assertTrue(topLevel.Exports.any { it.Name == "*" && it.FromSource == "./shared" })
    }

    @Test
    fun shouldParseFocusedLanguageDeclarationsAndControlFlow() {
        val source = """
            import type { DTO } from './types';

            export enum State { Idle, Ready = 1 }
            export type Result<T> = T | null;

            abstract class Base<T> {
              protected value: T;
              constructor(value: T) { this.value = value; }
              get current(): T { return this.value; }
              abstract run(input?: T): Promise<T | null>;
            }

            class Service extends Base<string> {
              override async run(input: string = 'default'): Promise<string | null> {
                for (let index: number = 0; index < 1; index++) {
                  if (input.length > 0) return await Promise.resolve(input);
                }
                return null;
              }
            }

            export function format(value: number): string;
            export function format(value: string): string;
            export function format(value: number | string): string { return `${'$'}{value}`; }
        """.trimIndent()

        val result = analyser.analysisWithDiagnostics(source, "LanguageForms.ets")
        assertNoDiagnostics(result)
        assertEquals(ImportKind.TYPE_ONLY, result.container.Imports.single().Kind)
        val structures = result.container.DataStructures.associateBy { it.NodeName }
        assertTrue(structures.keys.containsAll(listOf("Base", "Service")))
        assertTrue(structures.getValue("Base").Functions.map { it.Name }.containsAll(listOf("constructor", "get", "run")))
        assertEquals(3, assertNotNull(result.container.TopLevel).Functions.count { it.Name == "format" })
    }

    @Test
    fun shouldKeepBuildOwnershipWithNestedArkUIArrowCallbacks() {
        val source = """
            @Component
            struct NestedPage {
              private items: string[] = ['one'];

              build() {
                ForEach(this.items, (item: string) => {
                  Button(item)
                    .onClick(() => {
                      console.info(item)
                    })
                })
              }
            }
        """.trimIndent()

        val result = analyser.analysisWithDiagnostics(source, "NestedPage.ets")
        assertNoDiagnostics(result)
        val page = result.container.DataStructures.single { it.NodeName == "NestedPage" }
        val build = page.Functions.single { it.Name == "build" }
        assertTrue(build.FunctionCalls.map { it.FunctionName }.containsAll(listOf("ForEach", "Button", "onClick", "info")))
        assertTrue(result.container.TopLevel?.Functions.orEmpty().none { it.Name.isBlank() || it.Name == "build" })
    }

    @Test
    fun shouldParseAmbientDeclarations() {
        val result = analyser.analysisWithDiagnostics(resource("/fixtures/Ambient.d.ets"), "types/Ambient.d.ets")
        assertNoDiagnostics(result)
        val service = result.container.DataStructures.single { it.NodeName == "AmbientService" }
        assertEquals(listOf("constructor", "run"), service.Functions.map { it.Name })
        assertEquals("types/Ambient.d.ets", service.FilePath)
        assertTrue(assertNotNull(result.container.TopLevel).Functions.any { it.Name == "createService" })
    }

    @Test
    fun shouldParseVersionSensitiveArkUIV2Decorators() {
        val result = analyser.analysisWithDiagnostics(resource("/fixtures/ArkUIV2.ets"), "ArkUIV2.ets")
        assertNoDiagnostics(result)
        val counter = result.container.DataStructures.single { it.NodeName == "V2Counter" }
        assertEquals(listOf("ComponentV2"), counter.Annotations.map { it.Name })
        assertEquals(listOf("Local"), counter.Fields.single { it.TypeKey == "localCount" }.Annotations.map { it.Name })
        assertEquals("theme", counter.Fields.single { it.TypeKey == "theme" }.Annotations.single().KeyValues.single().Value.trim('\''))
        assertTrue(counter.Functions.map { it.Name }.containsAll(listOf("onLocalCountChanged", "build")))
    }

    @Test
    fun shouldParseConcurrencyDecorators() {
        val result = analyser.analysisWithDiagnostics(resource("/fixtures/Concurrency.ets"), "Concurrency.ets")
        assertNoDiagnostics(result)
        val item = result.container.DataStructures.single { it.NodeName == "WorkItem" }
        assertEquals(listOf("Sendable"), item.Annotations.map { it.Name })
        val functions = assertNotNull(result.container.TopLevel).Functions
        assertEquals(listOf("Concurrent"), functions.single { it.Name == "doubleValue" }.Annotations.map { it.Name })
        assertTrue(functions.any { it.Name == "execute" })
    }

    @Test
    fun shouldReportDiagnosticsForMalformedArkUIAndLeadingDotChains() {
        val result = analyser.analysisWithDiagnostics(resource("/fixtures/Negative.ets"), "Negative.ets")
        assertTrue(result.diagnostics.isNotEmpty())
        assertTrue(result.diagnostics.all { it.line > 0 && it.column >= 0 && it.message.isNotBlank() })
    }

    private fun resource(path: String): String =
        requireNotNull(this::class.java.getResource(path)) { "Missing test resource: $path" }.readText()

    private fun assertNoDiagnostics(result: ArkTSAnalysisResult) {
        assertTrue(
            result.diagnostics.isEmpty(),
            result.diagnostics.joinToString("\n") { "${it.line}:${it.column} ${it.message}" }
        )
    }

    private fun assertSubsequence(actual: List<String>, expected: List<String>) {
        var offset = 0
        expected.forEach { item ->
            val relativeIndex = actual.subList(offset, actual.size).indexOf(item)
            assertTrue(relativeIndex >= 0, "Missing $item after index $offset in $actual")
            offset += relativeIndex + 1
        }
    }
}
