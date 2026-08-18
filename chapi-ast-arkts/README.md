# Chapi ArkTS parser

`chapi-ast-arkts` is an experimental, independent ANTLR parser for ArkTS application source (`.ets` and `.d.ets`).
It is derived from Chapi's TypeScript grammar and listener, but generates separate `ArkTSLexer` and `ArkTSParser`
classes so ArkTS changes cannot alter TypeScript parsing behavior.

## Supported scope

The current focused test suite covers:

- default, named, namespace, type-only, side-effect, `@ohos`, `@kit`, and `import lazy` imports;
- ArkTS `struct` and `@interface` declarations;
- decorators on structs, fields, methods, functions, and classes;
- ArkUI component blocks, nested component calls, modifiers, and `@Extend` leading-dot calls;
- fields, methods, top-level functions, types, source positions, and representative `CodeCall` extraction;
- explicit lexer/parser diagnostics through `analysisWithDiagnostics`.

The test suite also includes a 689-line Chapi-owned `AllInOne.ets` regression contract covering module forms,
language declarations and control flow, custom annotations, inheritance and generics, top-level functions, nested
ArkUI components, state decorators, builders, lifecycle methods, events, bindings, conditional rendering, and
`ForEach` callbacks. Focused tests protect the individual grammar areas and nested function-ownership behavior.

Use `ArkTSAnalyser.analysis(code, filePath)` for the standard Chapi `CodeContainer`, or
`analysisWithDiagnostics(code, filePath)` when syntax diagnostics must be checked:

```kotlin
val result = ArkTSAnalyser().analysisWithDiagnostics(source, "pages/Index.ets")
require(result.diagnostics.isEmpty())
val container = result.container
```

## Compatibility boundary

This is syntax and Chapi-domain extraction support, not parity with the official ArkTS compiler, type checker, or
linter. Focused fixtures exercise declaration-only `.d.ets` forms, ArkUI V2 decorators, and concurrency decorators,
but these version-sensitive lanes and the complete upstream parser corpus are not yet certified against a specific
HarmonyOS SDK release. ArkUI's declarative component body is represented through ordinary function and call models
because `chapi-domain` does not currently define a dedicated UI tree.

## Grammar and fixture provenance

The lexer/parser started from this repository's `chapi-ast-typescript` grammar, whose MIT license header is retained.
ArkTS deltas were implemented independently with the pinned Tree-sitter corpus and OpenHarmony parser tests used as
compatibility references. The small attributed fixture subset, pinned revisions, licenses, and expected outcomes are
recorded in [`src/test/resources/corpus/corpus-manifest.json`](src/test/resources/corpus/corpus-manifest.json).

Run the focused parser and extraction tests with:

```shell
./gradlew :chapi-ast-arkts:test
```

An optional non-default compatibility task can scan a checked-out directory that has already been classified as
positive ArkTS syntax. It reports the parsed and failed file counts and fails when any `.ets`/`.d.ets` file has a
syntax diagnostic:

```shell
./gradlew :chapi-ast-arkts:arktsCorpusCompatibility \
  -ParktsCorpusDir=/path/to/classified-positive-fixtures
```
