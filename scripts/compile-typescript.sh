#!/usr/bin/env bash

cd grammars/typescript

antlr -Dlanguage=Java -listener -visitor TypeScriptLexer.g4 -o  ../../chapi-ast-typescript/src/main/java/chapi/ast/antlr -package chapi.ast.antlr
antlr -Dlanguage=Java -listener -visitor TypeScriptParser.g4 -o ../../chapi-ast-typescript/src/main/java/chapi/ast/antlr -package chapi.ast.antlr
