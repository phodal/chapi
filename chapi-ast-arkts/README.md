# Chapi ArkTS parser

`chapi-ast-arkts` is an experimental, independent ANTLR parser for ArkTS application source (`.ets` and `.d.ets`).
It is derived from Chapi's TypeScript grammar and listener, but generates separate `ArkTSLexer` and `ArkTSParser`
classes so ArkTS changes cannot alter TypeScript parsing behavior.

## Supported scope

The current focused test suite covers:

- normal, `@ohos`, `@kit`, and `import lazy` imports;
- ArkTS `struct` and `@interface` declarations;
- decorators on structs, fields, methods, functions, and classes;
- ArkUI component blocks, nested component calls, modifiers, and `@Extend` leading-dot calls;
- fields, methods, top-level functions, types, source positions, and representative `CodeCall` extraction;
- explicit lexer/parser diagnostics through `analysisWithDiagnostics`.

Use `ArkTSAnalyser.analysis(code, filePath)` for the standard Chapi `CodeContainer`, or
`analysisWithDiagnostics(code, filePath)` when syntax diagnostics must be checked:

```kotlin
val result = ArkTSAnalyser().analysisWithDiagnostics(source, "pages/Index.ets")
require(result.diagnostics.isEmpty())
val container = result.container
```

## Compatibility boundary

This is syntax and Chapi-domain extraction support, not parity with the official ArkTS compiler, type checker, or
linter. Version-specific ArkUI V2 decorators, declaration-only forms, concurrency extensions, and the complete
upstream parser corpus are not yet certified. ArkUI's declarative component body is represented through ordinary
function and call models because `chapi-domain` does not currently define a dedicated UI tree.

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
