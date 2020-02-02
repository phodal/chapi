#!/usr/bin/env bash

cd grammars/java

antlr -Dlanguage=Java -listener JavaLexer.g4 -o  ../../chapi-ast-java/src/main/java/chapi/ast/antlr -package chapi.ast.antlr
antlr -Dlanguage=Java -listener JavaParser.g4 -o ../../chapi-ast-java/src/main/java/chapi/ast/antlr -package chapi.ast.antlr

cd ../..

cd grammars/typescript

antlr -Dlanguage=Java -listener TypeScriptLexer.g4 -o  ../../chapi-ast-typescript/src/main/java/chapi/ast/antlr -package chapi.ast.antlr
antlr -Dlanguage=Java -listener TypeScriptParser.g4 -o ../../chapi-ast-typescript/src/main/java/chapi/ast/antlr -package chapi.ast.antlr

cd ../..

cd grammars/go

antlr -Dlanguage=Java -listener GoLexer.g4 -o  ../../chapi-ast-go/src/main/java/chapi/ast/antlr -package chapi.ast.antlr
antlr -Dlanguage=Java -listener GoParser.g4 -o ../../chapi-ast-go/src/main/java/chapi/ast/antlr -package chapi.ast.antlr

cd ../..

cd grammars/python

antlr -Dlanguage=Java -listener PythonLexer.g4 -o  ../../chapi-ast-python/src/main/java/chapi/ast/antlr -package chapi.ast.antlr
antlr -Dlanguage=Java -listener PythonParser.g4 -o ../../chapi-ast-python/src/main/java/chapi/ast/antlr -package chapi.ast.antlr

cd ../..

cd grammars/c

antlr -Dlanguage=Java -listener C.g4 -o ../../chapi-ast-c/src/main/java/chapi/ast/antlr -package chapi.ast.antlr

cd ..

antlr -Dlanguage=Java -listener CommentLexer.g4 -o ../chapi-ast-comments/src/main/java/chapi/ast/antlr -package chapi.ast.antlr
