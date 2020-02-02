#!/usr/bin/env bash

cd grammars/java

antlr -Dlanguage=Java -listener JavaLexer.g4 -o  ../../chapi-ast-java/src/main/java/chapi/ast/antlr
antlr -Dlanguage=Java -listener JavaParser.g4 -o ../../chapi-ast-java/src/main/java/chapi/ast/antlr

cd ../..

cd grammars/typescript

antlr -Dlanguage=Java -listener TypeScriptLexer.g4 -o  ../../chapi-ast-typescript/src/main/java/chapi/ast/antlr
antlr -Dlanguage=Java -listener TypeScriptParser.g4 -o ../../chapi-ast-typescript/src/main/java/chapi/ast/antlr
