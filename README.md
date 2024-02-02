# Chapi

<img src="docs/logo.svg" width="100" height="100" alt="Chapi Logo">

[![Chapi CI](https://github.com/modernizing/chapi/actions/workflows/ci.yml/badge.svg)](https://github.com/modernizing/chapi/actions/workflows/ci.yml)
[![codecov](https://codecov.io/gh/phodal/chapi/graph/badge.svg?token=sdB5L3xbRv)](https://codecov.io/gh/phodal/chapi)
[![Maintainability](https://api.codeclimate.com/v1/badges/2af5f5168a9ceb2ebe9b/maintainability)](https://codeclimate.com/github/phodal/chapi/maintainability)
![Maven Central](https://img.shields.io/maven-central/v/com.phodal.chapi/chapi-domain)

> CHAPI (Common Hierarchical Abstract Parser and Information Converter) streamlines code analysis by converting diverse
> language source code into a unified abstract model, simplifying cross-language development. Chapi
> 是一个通用层次抽象解析器与信息转换器，它可以将不同编程语言的源代码转换为统一的层次抽象模型。

Chapi => Cha Pi => Tea Pi => Tea π => 茶 π. See on in
refs: [Tea if by sea, cha if by land](https://qz.com/1176962/map-how-the-word-tea-spread-over-land-and-sea-to-conquer-the-world/).

Chapi (pronounce /tʃɑpi/) also pronounce XP in Chinese if you always call X in 叉.

language stages:

| Features      | Java | Python | Go | Kotlin | TS/JS | C  | C# | Scala | C++ | Rust |
|---------------|------|--------|----|--------|-------|----|----|-------|-----|------|
| http api decl | ✅    | 🆕     | 🆕 | ✅      | ✅     | 🆕 | 🆕 |       | ✅   | 🆕   |
| syntax parse  | ✅    | ✅      | ✅  | ✅      | ✅     | ✅  | 🆕 | ✅     | 🆕  | ✅    |
| function call | ✅    | ✅      | 🆕 | ✅      | ✅     | 🆕 |    |       |     | ✅    |
| arch/package  | ✅    |        |    | ✅      | ✅     | 🆕 |    | ✅     | ✅   | 🆕   |
| real world    | ✅    |        |    | ✅      | ✅     |    |    |       |     |      |

- [ArchGuard](https://github.com/archguard/archguard) - ArchGuard is an architecture workbench, also for architecture
  governance, which can analysis architecture in container, component, code level, create architecture fitness
  functions,
  and analysis system dependencies.
- [UnitGen](https://github.com/unit-mesh/unit-gen) is a code fine-tuning data framework that generates data from your
  existing codebase.
- [ChocolateFactory](https://github.com/unit-mesh/chocolate-factory) is a cutting-edge LLM toolkit designed to empower
  you in creating your very own AI assistant.

PS: welcome to PR to send your projects

## Language Information

language versions（tested）:

- Java: 8, 11, 17
- TypeScript/JavaScript
- Kotlin
- Rust: v1.60.0
- Python: 2, 3

```goovy
// tier 1 languages
":chapi-ast-java",
":chapi-ast-typescript",

// tier 2 languages
":chapi-ast-kotlin",
":chapi-ast-go",
":chapi-ast-python",

// tier 3 languages
":chapi-ast-c",
":chapi-ast-rust",
":chapi-ast-csharp",
":chapi-ast-scala",
":chapi-ast-cpp",
```

Language Family [wiki](https://en.wikipedia.org/wiki/First-class_function)

Algol Family [https://wiki.c2.com/?AlgolFamily](https://wiki.c2.com/?AlgolFamily)

|            | Languages                                     | plan support            |
|------------|-----------------------------------------------|-------------------------|
| C family   | C#, Java, Go, C, C++, Objective-C, Rust, ...  | C++, C, Java, C#, Rust? |
| Functional | Scheme, Lisp, Clojure, Scala, ...             | Scala                   |
| Scripting  | Lua, PHP, JavaScript, Python, Perl, Ruby, ... | Python, JavaScript      |
| Other      | Fortran, Swift, Matlab, ...                   | Swift?, Fortran?        |

## Specify Rule

**scan by twice**. In order to success get:

- find data struct in same package

### TypeScript

1. PackageName will use resolvePath, package of `src/grammar/blbla.ts` is `@.grammar`
2. Function in file will use `default` as DataStructure.Name
3. export default `Object` in file will use `default` as FunctionName, and will belong to `default` DataStructure

### C# issues

- interpolated_string parse
  issue：[official grammar](https://github.com/dotnet/roslyn/blob/main/src/Compilers/CSharp/Portable/Generated/CSharp.Generated.g4)
  ，Antlr issues: [https://github.com/antlr/grammars-v4/issues/1146](https://github.com/antlr/grammars-v4/issues/1146)
- import analysis support
    - in C#, import `namespace` can use call in namespace

### C 

We use [https://github.com/shevek/jcpp](https://github.com/shevek/jcpp) to pre-process C code.

### Kotlin

- need to implementation `warpTargetFullType` to got Class in same package.

## Usage

1. add to ``dependencies``

```
dependencies {
    implementation 'com.phodal.chapi:chapi-ast-java:2.2.2'
    implementation 'com.phodal.chapi:chapi-domain:2.2.2'
}
```

### Usage

```kotlin
import chapi.domain.core.CodeDataStruct
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import org.archguard.scanner.core.sourcecode.LanguageSourceCodeAnalyser
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
}

```

## Examples

examples Java source code:

```java
package adapters.outbound.persistence.blog;

public class BlogPO implements PersistenceObject<Blog> {
    @Override
    public Blog toDomainModel() {

    }
}
```

examples output

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

Syntax Parse Identify Rules:

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

### Build Antlr Grammar

1. setup Antlr: `brew install antlr`
2. run compile: `./scripts/compile-antlr.sh`

### Data Structures

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

## Development（Chinese Version）

Env：Intellij IDEA、JDK 11+

1. Clone code：``git clone https://github.com/phodal/chapi``

2. Build：``./gradlew build``

### 参与开发

为了保证不易出现 bug，项目采用 TDD 的方式进行，即先编写对应的语法测试，然后实现代码。通过尽可能高的测试覆盖率，降低 bug 的出现。

项目主要由 domain + 各种语言的 AST + application 构建：

- domain，构建统一的代码模型
- 各语言 AST
- application，对外暴露的简易 API

每个 AST 项目的入口是 ``xxAnalyser``，返回的是一个 CodeContainer，即代码容器。在非 C# 语言里，等同于 CodeFile，即代码文件。

CodeContainer 内对应的领域模型如下所示：

```
// class-first or function-first
code_data_struct // 类、struct、interface 等
code_function    // 函数。如果是头等函数的语言（first-class function”），会用 NodeName = "default" 包在 code_data_struct 模型中

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

1. 寻找感兴趣的语言 / 添加新的语言 AST

通过 TDD
的方式一点点实现下面的功能（可以考虑按顺序），参照示例见 [JavaFullIdentListenerTest.kt](https://github.com/phodal/chapi/blob/master/chapi-ast-java/src/test/kotlin/chapi/ast/javaast/JavaFullIdentListenerTest.kt)：

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

用于发布时，使用标准的 [CHANGELOG.md](CHANGELOG.md)

`<type>[Language]: <message>`，示例：`feat(java): <grammars> init python & go grammars Phodal Huang 2020/2/2, 5:01 PM`

所有的 type 见：

- build: 影响构建系统或外部依赖关系的更改（示例范围：gulp，broccoli，npm）
- ci: 更改我们的持续集成文件和脚本（示例范围：Travis，Circle，BrowserStack，SauceLabs）
- docs: 仅文档更改
- feat: 一个新功能
- fix: 修复错误
- perf: 改进性能的代码更改
- refactor: 代码更改，既不修复错误也不添加功能
- style: 不影响代码含义的变化（空白，格式化，缺少分号等）
- test: 添加缺失测试或更正现有测试

License
---

[![Phodal's Idea](http://brand.phodal.com/shields/idea-small.svg)](http://ideas.phodal.com/)

@2020 A [Phodal Huang](https://www.phodal.com)'s [Idea](http://github.com/phodal/ideas). This code is distributed under
the MPL license. See `LICENSE` in this directory.

