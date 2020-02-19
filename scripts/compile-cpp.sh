#!/usr/bin/env bash

cd grammars/cpp

antlr -Dlanguage=Java -listener -visitor CPP14.g4 -o  ../../chapi-ast-cpp/src/main/java/chapi/ast/antlr -package chapi.ast.antlr
