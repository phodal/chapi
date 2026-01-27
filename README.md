# Chapi

<img src="docs/logo.svg" width="100" height="100" alt="Chapi Logo">

[![Chapi CI](https://github.com/modernizing/chapi/actions/workflows/ci.yml/badge.svg)](https://github.com/modernizing/chapi/actions/workflows/ci.yml)
[![codecov](https://codecov.io/gh/phodal/chapi/graph/badge.svg?token=sdB5L3xbRv)](https://codecov.io/gh/phodal/chapi)
[![Maven Central](https://img.shields.io/maven-central/v/com.phodal.chapi/chapi-domain)](https://central.sonatype.com/artifact/com.phodal.chapi/chapi-domain)

> **CHAPI (Common Hierarchical Abstract Parser and Information Converter)** streamlines code analysis by converting
> source code from different languages into a unified abstract model, making cross-language analysis and tooling
> easier.
>
> **Chapi** 是一个通用层次抽象解析器与信息转换器，它可以将不同编程语言的源代码转换为统一的层次抽象模型，
> 从而简化跨语言的代码分析与工具构建。

Chapi => Cha Pi => Tea Pi => Tea π => 茶 π.
Reference: [Tea if by sea, cha if by land](https://qz.com/1176962/map-how-the-word-tea-spread-over-land-and-sea-to-conquer-the-world/).

Chapi (pronounced /tʃɑpi/) can also be read as “XP” in Chinese if you pronounce “X” as “叉”.

## Status & language coverage

### Language stages

| Feature        | Java | Python | Go | Kotlin | TS/JS | C  | C# | Scala | C++ | Rust | Swift |
|----------------|------|--------|----|--------|-------|----|----|-------|-----|------|-------|
| HTTP API decl  | ✅    | 🆕     | ✅  | ✅      | ✅     | 🆕 | ✅  | ✅     | 🆕  | ✅    |       |
| Syntax parsing | ✅    | ✅      | ✅  | ✅      | ✅     | ✅  | ✅  | ✅     | ✅   | ✅    | ✅     |
| Function calls | ✅    | ✅      | ✅  | ✅      | ✅     | ✅  | ✅  |       |     | ✅    | ✅     |
| Arch/package   | ✅    |        | ✅  | ✅      | ✅     | 🆕 | ✅  | ✅     | ✅   | ✅    | ✅     |
| Real-world     | ✅    |        |    | ✅      | ✅     |    |    |       |     |      |       |

### IDL stages

| Feature        | Protobuf | Thrift |
|----------------|----------|--------|
| Syntax parsing | ✅        | ✅      |
| HTTP API decl  | ✅        | ✅      |
| Arch/package   | ✅        |        |
| Real-world     | ✅        |        |

### Projects using Chapi

- [ArchGuard](https://github.com/archguard/archguard) — An architecture workbench for architecture governance. It can
  analyze architecture at container/component/code levels, create architecture fitness functions, and inspect system
  dependencies.
- [UnitGen](https://github.com/unit-mesh/unit-gen) — A fine-tuning data framework that generates datasets from your
  existing codebase.
- [ChocoBuilder](https://github.com/unit-mesh/choco-builder) — An LLM toolkit for building custom AI assistants.

PS: PRs are welcome — feel free to add your project here.

## Language information

Tested language versions:

- Java: 8, 11, 17
- TypeScript/JavaScript
- Kotlin
- Rust: v1.60.0
- Python: 2, 3
- Swift: 5, 6 (with typed throws, async/await, actors, ownership modifiers)

Gradle modules (by tier):

```text
// tier 1 languages
":chapi-ast-java",
":chapi-ast-typescript",

// tier 1 model language
":chapi-ast-protobuf",
":chapi-ast-thrift",

// tier 2 languages
":chapi-ast-kotlin",
":chapi-ast-go",
":chapi-ast-python",
":chapi-ast-scala",

// tier 3 languages
":chapi-ast-rust",
":chapi-ast-csharp",
":chapi-ast-c",
":chapi-ast-cpp",
":chapi-ast-swift",

// others
":chapi-parser-toml",
":chapi-parser-cmake",
```

Language families (refs):

- First-class function: https://en.wikipedia.org/wiki/First-class_function
- Algol family: https://wiki.c2.com/?AlgolFamily

| Category   | Languages                                     | Planned support         |
|------------|-----------------------------------------------|-------------------------|
| C family   | C#, Java, Go, C, C++, Objective-C, Rust, ...  | C++, C, Java, C#, Rust? |
| Functional | Scheme, Lisp, Clojure, Scala, ...             | Scala                   |
| Scripting  | Lua, PHP, JavaScript, Python, Perl, Ruby, ... | Python, JavaScript      |
| Other      | Fortran, Swift, Matlab, ...                   | Swift?, Fortran?        |

## Parsing / analysis rules

Chapi scans **twice** to improve cross-file resolution.

- It helps find data structures in the same package/module.

### TypeScript

1. `PackageName` uses the resolved path. For example, `src/grammar/blbla.ts` becomes `@.grammar`.
2. Top-level functions in a file use `default` as `DataStructure.Name`.
3. `export default Object` uses `default` as `FunctionName` and belongs to the `default` data structure.

### C# notes

- Interpolated string parsing
    - Official grammar:
      https://github.com/dotnet/roslyn/blob/main/src/Compilers/CSharp/Portable/Generated/CSharp.Generated.g4
    - Related Antlr issue:
      https://github.com/antlr/grammars-v4/issues/1146
- Import analysis
    - In C#, importing a `namespace` allows resolving calls inside that namespace.

### C

We use https://github.com/shevek/jcpp to preprocess C code.

### Kotlin

- `warpTargetFullType` is required to resolve classes in the same package.

## Usage

Add dependencies:

```groovy
dependencies {
    implementation "com.phodal.chapi:chapi-ast-java:2.5.2"
    implementation "com.phodal.chapi:chapi-domain:2.5.2"
}
```

Example (Kotlin):

```text
import chapi.domain.core.CodeDataStruct
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import org.archguard.scanner.core.sourcecode.SourceCodeContext
import java.io.File

class CSharpAnalyser(override val context: SourceCodeContext)

private val client = context.client
private val impl = chapi.ast.csharpast.CSharpAnalyser()

fun analyse(): List<CodeDataStruct> = runBlocking {
    getFilesByPath(context.path) {
        it.absolutePath.endsWith(".cs")
    }
        .map { async { analysisByFile(it) } }.awaitAll()
        .flatten()
        .also { client.saveDataStructure(it) }
}

fun analysisByFile(file: File): List<CodeDataStruct> {
    val codeContainer = impl.analysis(file.readContent(), file.name)
    return codeContainer.Containers.flatMap { container ->
        container.DataStructures.map {
            it.apply {
                it.Imports = codeContainer.Imports
                it.FilePath = file.absolutePath
            }
        }
    }
}
```

### Examples Input & Output

Java source:

```java
package adapters.outbound.persistence.blog;

public class BlogPO implements PersistenceObject<Blog> {
    @Override
    public Blog toDomainModel() {

    }
}
```

Output:

```json
{
    "Imports": [],
    "Implements": [
        "PersistenceObject<Blog>"
    ],
    "NodeName": "BlogPO",
    "Extend": "",
    "Type": "CLASS",
    "FilePath": "",
    "InOutProperties": [],
    "Functions": [
        {
            "IsConstructor": false,
            "InnerFunctions": [],
            "Position": {
                "StartLine": 6,
                "StartLinePosition": 133,
                "StopLine": 8,
                "StopLinePosition": 145
            },
            "Package": "",
            "Name": "toDomainModel",
            "MultipleReturns": [],
            "Annotations": [
                {
                    "Name": "Override",
                    "KeyValues": []
                }
            ],
            "Extension": {},
            "Override": false,
            "extensionMap": {},
            "Parameters": [],
            "InnerStructures": [],
            "ReturnType": "Blog",
            "Modifiers": [],
            "FunctionCalls": []
        }
    ],
    "Annotations": [],
    "Extension": {},
    "Parameters": [],
    "Fields": [],
    "MultipleExtend": [],
    "InnerStructures": [],
    "Package": "adapters.outbound.persistence.blog",
    "FunctionCalls": []
}
```

## Development

Syntax parsing identification rules:

1. Package name
2. Import name
3. Class / data structure
    1. Structure name
    2. Structure parameters
    3. Function names
    4. Return types
    5. Function parameters
4. Function
    1. Function name
    2. Return types
    3. Function parameters
5. Method call
    1. New instance call
    2. Parameter call
    3. Field call

### Build Antlr grammar

1. Install Antlr: `brew install antlr`
2. Compile grammars: `./scripts/compile-antlr.sh`

### Data structures

```
// for multiple project analysis
code_project
code_module

// for package dependency analysis
code_package_info
code_dependency

// package or file as dependency analysis
code_package
code_container

// class-first or function-first
code_data_struct
code_function

// function or class detail
code_annotation
code_field
code_import
code_member
code_position
code_property

// method call information
code_call
```

## License

[![Phodal's Idea](http://brand.phodal.com/shields/idea-small.svg)](http://ideas.phodal.com/)

@2020 A [Phodal Huang](https://www.phodal.com)'s [Idea](http://github.com/phodal/ideas). This code is distributed under
the MPL license. See `LICENSE` in this directory.
