package chapi.ast.cast

import chapi.ast.antlr.CLexer
import chapi.ast.antlr.CParser
import chapi.domain.core.CodeContainer
import chapi.parser.Analyser
import org.antlr.v4.runtime.*
import org.antlr.v4.runtime.tree.ParseTreeWalker

open class CAnalyser : Analyser {
    override fun analysis(code: String, filePath: String): CodeContainer {
        val context = this.parse(code).compilationUnit()
        val listener = CFullIdentListener(filePath)

        ParseTreeWalker().walk(listener, context)

        return listener.getNodeInfo()
    }

    open fun parse(str: String): CParser =
        CharStreams.fromString(str)
            .let(::CLexer)
            .let(::CommonTokenStream)
            .let(::CParser)

    // based on: https://gist.github.com/KvanTTT/d95579de257531a3cc15
//    private fun parse(str: String): CParser {
//        val codeTokens: MutableList<Token> = mutableListOf()
//        val commentTokens: MutableList<Token> = mutableListOf()
//
//        val preprocessorLexer = CPreprocessorLexer(CharStreams.fromString(str))
//        // Collect all tokens with lexer (CLexer.g4).
//        val tokens = preprocessorLexer.allTokens
//        val directiveTokens: MutableList<Token> = mutableListOf()
//        var directiveTokenSource = ListTokenSource(directiveTokens)
//        var directiveTokenStream = CommonTokenStream(directiveTokenSource, CPreprocessorLexer.DIRECTIVE)
//        val preprocessorParser = CPreprocessorParser(directiveTokenStream)
//
//        var index = 0
//        var compiledTokens = true
//        while (index < tokens.size) {
//            val token = tokens[index]
//            when {
//                token.type == CPreprocessorLexer.SHARP -> {
//                    directiveTokens.clear()
//                    var directiveTokenIndex = index + 1
//                    // Collect all preprocessor directive tokens.
//                    while (directiveTokenIndex < tokens.size &&
//                        tokens[directiveTokenIndex].type != CPreprocessorLexer.EOF &&
//                        tokens[directiveTokenIndex].type != CPreprocessorLexer.DIRECTIVE_NEW_LINE &&
//                        tokens[directiveTokenIndex].type != CPreprocessorLexer.SHARP
//                    ) {
//                        if (tokens[directiveTokenIndex].channel == CPreprocessorLexer.COMMENTS_CHANNEL) {
//                            commentTokens.add(tokens[directiveTokenIndex])
//                        } else if (tokens[directiveTokenIndex].channel != Lexer.HIDDEN) {
//                            directiveTokens.add(tokens[directiveTokenIndex])
//                        }
//                        directiveTokenIndex++
//                    }
//
//                    directiveTokenSource = ListTokenSource(directiveTokens)
//                    directiveTokenStream = CommonTokenStream(directiveTokenSource, CPreprocessorLexer.DIRECTIVE)
//                    preprocessorParser.inputStream = directiveTokenStream
//                    preprocessorParser.reset()
//
//                    // Parse condition in preprocessor directive (based on CSharpPreprocessorParser.g4 grammar).
//                    val directive = preprocessorParser.preprocessor_directive()
//                    // if true than next code is valid and not ignored.
//                    compiledTokens = directive.value
//                    index = directiveTokenIndex - 1
//                }
//
//                token.channel == CPreprocessorLexer.COMMENTS_CHANNEL -> {
//                    commentTokens.add(token) // Collect comment tokens (if required).
//                }
//
//                token.channel != Lexer.HIDDEN && token.type != CPreprocessorLexer.DIRECTIVE_NEW_LINE && compiledTokens -> {
//                    codeTokens.add(token) // Collect code tokens.
//                }
//            }
//
//            index++
//        }
//
////        codeTokens.map {
////            print(it.text + " ")
////        }
//        // At the second stage, tokens are parsed in the usual way.
//        val codeTokenSource = ListTokenSource(tokens)
//
//        println(codeTokenSource)
//
//        val codeTokenStream = CommonTokenStream(codeTokenSource)
//        val parser = CParser(codeTokenStream)
//        return parser
//    }
}
