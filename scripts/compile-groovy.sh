#!/usr/bin/env bash

cd grammars/groovy

antlr -Dlanguage=Java -listener -visitor GroovyLexer.g4 -o  ../../chapi-ast-groovy/src/main/java/chapi/ast/antlr -package chapi.ast.antlr
antlr -Dlanguage=Java -listener -visitor GroovyParser.g4 -o ../../chapi-ast-groovy/src/main/java/chapi/ast/antlr -package chapi.ast.antlr
