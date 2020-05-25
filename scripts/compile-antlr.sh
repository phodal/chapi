#!/usr/bin/env bash

cd grammars/java

antlr -Dlanguage=Java -listener -visitor JavaLexer.g4 -o  ../../chapi-ast-java/src/main/java/chapi/ast/antlr -package chapi.ast.antlr
antlr -Dlanguage=Java -listener -visitor JavaParser.g4 -o ../../chapi-ast-java/src/main/java/chapi/ast/antlr -package chapi.ast.antlr

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

antlr -Dlanguage=Java -listener -visitor C.g4 -o ../../chapi-ast-c/src/main/java/chapi/ast/antlr -package chapi.ast.antlr

cd ../..

cd grammars/kotlin

antlr -Dlanguage=Java -listener -visitor UnicodeClasses.g4 -o ../../chapi-ast-kotlin/src/main/java/chapi/ast/antlr -package chapi.ast.antlr
antlr -Dlanguage=Java -listener -visitor KotlinLexer.g4 -o ../../chapi-ast-kotlin/src/main/java/chapi/ast/antlr -package chapi.ast.antlr
antlr -Dlanguage=Java -listener -visitor KotlinParser.g4 -o ../../chapi-ast-kotlin/src/main/java/chapi/ast/antlr -package chapi.ast.antlr

cd ../..

cd grammars/csharp

antlr -Dlanguage=Java -listener -visitor CSharpLexer.g4 -o ../../chapi-ast-csharp/src/main/java/chapi/ast/antlr -package chapi.ast.antlr
antlr -Dlanguage=Java -listener -visitor CSharpPreprocessorParser.g4 -o ../../chapi-ast-csharp/src/main/java/chapi/ast/antlr -package chapi.ast.antlr
antlr -Dlanguage=Java -listener -visitor CSharpParser.g4 -o ../../chapi-ast-csharp/src/main/java/chapi/ast/antlr -package chapi.ast.antlr

cd ../..

cd grammars/scala

antlr -Dlanguage=Java -listener -visitor Scala.g4 -o ../../chapi-ast-scala/src/main/java/chapi/ast/antlr -package chapi.ast.antlr
