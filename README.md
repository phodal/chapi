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

| Feature        | Java | Python | Go | Kotlin | TS/JS | C  | C# | Scala | C++ | Rust |
|----------------|------|--------|----|--------|-------|----|----|-------|-----|------|
| HTTP API decl  | ✅    | 🆕     | 🆕 | ✅      | ✅     | 🆕 | 🆕 |       | ✅   | 🆕   |
| Syntax parsing | ✅    | ✅      | ✅  | ✅      | ✅     | ✅  | 🆕 | ✅     | 🆕  | ✅    |
| Function calls | ✅    | ✅      | 🆕 | ✅      | ✅     | 🆕 |    |       |     | ✅    |
| Arch/package   | ✅    |        |    | ✅      | ✅     | 🆕 |    | ✅     | ✅   | 🆕   |
| Real-world     | ✅    |        |    | ✅      | ✅     |    |    |       |     |      |

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

Gradle modules (by tier):

```text
// tier 1 languages
":chapi-ast-java",
":chapi-ast-typescript",

// tier 1 model language
":chapi-ast-protobuf",

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
    implementation "com.phodal.chapi:chapi-ast-java:2.3.6"
    implementation "com.phodal.chapi:chapi-domain:2.3.6"
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

## Examples

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

## Development（中文）

环境：IntelliJ IDEA、JDK 11+

1. 克隆代码：`git clone https://github.com/phodal/chapi`
2. 构建：`./gradlew build`

### 参与开发

为了尽可能降低 bug 的出现概率，项目采用 TDD 的方式进行：先编写对应的语法测试，再实现代码。通过尽可能高的测试覆盖率，来降低回归风险。

项目主要由 domain + 各语言 AST + application 构建：

- domain：统一代码模型
- 各语言 AST：把源码转换为统一的层次抽象模型
- application：对外暴露的简易 API

每个 AST 项目的入口是 `xxAnalyser`，返回的是一个 `CodeContainer`（代码容器）。在非 C# 语言中，它等同于 `CodeFile`（代码文件）。

`CodeContainer` 内对应的领域模型如下所示：

```
// class-first or function-first
code_data_struct // 类、struct、interface 等
code_function    // 函数。如果是头等函数语言（first-class function），会用 NodeName = "default" 包在 code_data_struct 模型里

// function or class detail
code_annotation  // 注解
code_field       // 全局变量
code_import      // 包依赖
code_member      // 保留字段
code_position    // 位置信息
code_property    // 参数相关

// method call information
code_call        // 函数调用，如 fmt.Println
```

### 加入开发

1. 寻找感兴趣的语言 / 添加新的语言 AST。

通过 TDD 的方式逐步实现下面这些能力（推荐按顺序），示例可参考
[JavaFullIdentListenerTest.kt](https://github.com/phodal/chapi/blob/master/chapi-ast-java/src/test/kotlin/chapi/ast/javaast/JavaFullIdentListenerTest.kt)：

1. package name
2. import name
3. class / data struct
    1. struct name
    2. struct parameters
    3. function name
    4. return types
    5. function parameters
4. function
    1. function name
    2. return types
    3. function parameters
5. method call
    1. new instance call
    2. parameter call
    3. field call
    4. other calls...

### 提交信息格式

发布时使用标准的 [CHANGELOG.md](CHANGELOG.md)。

提交信息格式：`<type>[Language]: <message>`

示例：`feat(java): <grammars> init python & go grammars Phodal Huang 2020/2/2, 5:01 PM`

type 列表：

- build: 影响构建系统或外部依赖关系的更改（示例范围：gulp，broccoli，npm）
- ci: 更改持续集成文件和脚本（示例范围：Travis，Circle，BrowserStack，SauceLabs）
- docs: 仅文档更改
- feat: 新功能
- fix: 修复错误
- perf: 性能优化
- refactor: 代码重构（不修复 bug 也不添加功能）
- style: 不影响代码含义的变化（空白、格式化、缺少分号等）
- test: 添加缺失测试或更正现有测试

## License

[![Phodal's Idea](http://brand.phodal.com/shields/idea-small.svg)](http://ideas.phodal.com/)

@2020 A [Phodal Huang](https://www.phodal.com)'s [Idea](http://github.com/phodal/ideas). This code is distributed under
the MPL license. See `LICENSE` in this directory.
