#!/usr/bin/env bash

cd grammars/java

antlr -Dlanguage=Java -listener -visitor JavaLexer.g4 -o  ../../chapi-ast-java/src/main/java/chapi/ast/antlr -package chapi.ast.antlr
antlr -Dlanguage=Java -listener -visitor JavaParser.g4 -o ../../chapi-ast-java/src/main/java/chapi/ast/antlr -package chapi.ast.antlr

cd ../..

cd grammars/typescript

antlr -Dlanguage=Java -listener -visitor TypeScriptLexer.g4 -o  ../../chapi-ast-typescript/src/main/java/chapi/ast/antlr -package chapi.ast.antlr
antlr -Dlanguage=Java -listener -visitor TypeScriptParser.g4 -o ../../chapi-ast-typescript/src/main/java/chapi/ast/antlr -package chapi.ast.antlr

cd ../..

cd grammars/go

antlr -Dlanguage=Java -listener -visitor GoLexer.g4 -o  ../../chapi-ast-go/src/main/java/chapi/ast/antlr -package chapi.ast.antlr
antlr -Dlanguage=Java -listener -visitor GoParser.g4 -o ../../chapi-ast-go/src/main/java/chapi/ast/antlr -package chapi.ast.antlr

cd ../..

cd grammars/python

antlr -Dlanguage=Java -listener -visitor PythonLexer.g4 -o  ../../chapi-ast-python/src/main/java/chapi/ast/antlr -package chapi.ast.antlr
antlr -Dlanguage=Java -listener -visitor PythonParser.g4 -o ../../chapi-ast-python/src/main/java/chapi/ast/antlr -package chapi.ast.antlr

cd ../..

cd grammars/c

antlr -Dlanguage=Java -listener C.g4 -o ../../chapi-ast-c/src/main/java/chapi/ast/antlr -package chapi.ast.antlr

cd ../..

cd grammars/kotlin

antlr -Dlanguage=Java -listener UnicodeClasses.g4 -o ../../chapi-ast-kotlin/src/main/java/chapi/ast/antlr -package chapi.ast.antlr
antlr -Dlanguage=Java -listener KotlinLexer.g4 -o ../../chapi-ast-kotlin/src/main/java/chapi/ast/antlr -package chapi.ast.antlr
antlr -Dlanguage=Java -listener KotlinParser.g4 -o ../../chapi-ast-kotlin/src/main/java/chapi/ast/antlr -package chapi.ast.antlr

cd ../..
