#!/usr/bin/env bash

cd grammars

antlr -Dlanguage=Java -listener JavaLexer.g4 -o  ../chapi-ast-java/src/main/java/chapi/ast/antlr
antlr -Dlanguage=Java -listener JavaParser.g4 -o ../chapi-ast-java/src/main/java/chapi/ast/antlr
