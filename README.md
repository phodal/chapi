# Chapi

[![Build Status](https://travis-ci.org/phodal/chapi.svg?branch=master)](https://travis-ci.org/phodal/chapi)
[![codecov](https://codecov.io/gh/phodal/chapi/branch/master/graph/badge.svg)](https://codecov.io/gh/phodal/chapi)
[![Maintainability](https://api.codeclimate.com/v1/badges/2af5f5168a9ceb2ebe9b/maintainability)](https://codeclimate.com/github/phodal/chapi/maintainability)
![Java CI](https://github.com/phodal/chapi/workflows/Java%20CI/badge.svg)

> Chapi is a common language data structure parser, which will parse different language to same JSON object.

Languages Stages (Welcome to PR your usage languages)

| Features/Languages  |   Java |  Python  | Go  |  Kotlin | TypeScript | C     | C# | Scala |
|---------------------|--------|----------|-----|---------|------------|-------|----|-------|
| syntax parse        |    ✅  |      ✅  |   ✅ |   TBC   |     ✅     | TBC   |  🆕 | 🆕 |
| function call graph |    ✅  |          |      |         |            |       |     |   |
| arch/package graph  |    ✅  |          |      |         |            |       |     |   |
| real world validate |    ✅  |          |      |         |            |       |     |   |

Language Family [wiki](https://en.wikipedia.org/wiki/First-class_function)

|            | Languages     |  plan support    |
|------------|---------------|-------------|
| Algol      | Ada, Delphi, Pascal, ALGOL, ... |  Pascal? |
| C family	 | C#, Java, Go, C, C++,  Objective-C, Rust, ... | C, Java, C#, Rust? |
| Functional | Scheme, Lisp, Clojure, Scala, ...| Scala  |
| Scripting  | Lua, PHP, JavaScript, Python, Perl, Ruby, ... | Python, JavaScript |
| Other      | Fortran, Swift, Matlab, ...| Swift?, Fortran? |

Todo:

 - [x] Migrate [Coca](https://github.com/phodal/coca) ast
 - [ ] Pluggable
 - [ ] Publish to Maven / GitHub

TBC:

 - SQL (refs: [antlr4-oracle](https://github.com/alris/antlr4-oracle) && [sqlgraph](https://github.com/dengdaiyemanren/sqlgraph))

## Usage


### Import by Jitpack

```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation 'com.github.phodal:chapi:Tag'
}
```

### Import by GitHub Packages


```
<dependency>
  <groupId>com.phodal</groupId>
  <artifactId>chapi-domain</artifactId>
  <version>0.0.3</version>
</dependency>

<dependency>
  <groupId>com.phodal</groupId>
  <artifactId>chapi-ast-java</artifactId>
  <version>0.0.3</version>
</dependency>
```

### Usage

```
import chapi.domain.core.CodeCall
import chapi.domain.core.CodeDataStruct
import chapi.app.analyser

...
val nodes = ChapiAnalyser().analysisByPath(path.absolutePath)
...
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


### Setup

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

Refs
---

Goal: source code data model for different language & different language family from [Language support](https://en.wikipedia.org/wiki/First-class_function)

License
---

[![Phodal's Idea](http://brand.phodal.com/shields/idea-small.svg)](http://ideas.phodal.com/)

@ 2020 A [Phodal Huang](https://www.phodal.com)'s [Idea](http://github.com/phodal/ideas).  This code is distributed under the MPL license. See `LICENSE` in this directory.

