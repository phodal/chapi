# Chapi

[![Build Status](https://travis-ci.org/phodal/chapi.svg?branch=master)](https://travis-ci.org/phodal/chapi)
[![codecov](https://codecov.io/gh/phodal/chapi/branch/master/graph/badge.svg)](https://codecov.io/gh/phodal/chapi)
[![Maintainability](https://api.codeclimate.com/v1/badges/2af5f5168a9ceb2ebe9b/maintainability)](https://codeclimate.com/github/phodal/chapi/maintainability)

> Chapi is a common language data structure parser, which will parse different language to same JSON object.

Languages Stages (Welcome to PR your usage languages)

| Features/Languages  |   Java |  Python  | Go  |  Kotlin | TypeScript | C     | C# |
|---------------------|--------|----------|-----|---------|------------|-------|----|
| syntax parse        |    ✅  |      ✅  |   ✅ |         |     ✅   |  🆕  |     |
| function call graph |    ✅  |          |      |         |            |       |     |
| arch/package graph  |    ✅  |          |      |         |            |       |     |
| real world validate |    ✅  |          |      |         |            |       |     |

Todo:

 - Migrate [Coca](https://github.com/phodal/coca) ast (done)
 - Pluggable
 - Multiple Languages （welcome to PR）

TBC:

 - SQL (refs: [antlr4-oracle](https://github.com/alris/antlr4-oracle) && [sqlgraph](https://github.com/dengdaiyemanren/sqlgraph))

## Data Structures

```
code_project
code_module

code_package
code_package_manager
code_dependency

code_file

code_annotation
code_data_struct
code_field
code_function
code_import
code_member
code_position
code_property

code_call
```

## Setup

1. setup Antlr: `brew install antlr`
2. run compile: `./scripts/compile-antlr.sh`

License
---

[![Phodal's Idea](http://brand.phodal.com/shields/idea-small.svg)](http://ideas.phodal.com/)

@ 2020 A [Phodal Huang](https://www.phodal.com)'s [Idea](http://github.com/phodal/ideas).  This code is distributed under the MPL license. See `LICENSE` in this directory.

