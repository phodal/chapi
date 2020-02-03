# Chapi

> Chapi is a common language data structure parser, which will parse different language to same JSON object.

Support languages (in plan):

 - Python
 - Java
 - Kotlin
 - Go
 - TypeScript / JavaScript
 - C / C++

Todo:

 - Migrate [Coca](https://github.com/phodal/coca) ast (doing)
 - Pluggable
 - Multiple Languages

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

