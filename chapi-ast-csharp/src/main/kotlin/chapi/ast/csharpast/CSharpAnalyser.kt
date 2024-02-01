package chapi.ast.csharpast

import chapi.ast.antlr.CSharpLexer
import chapi.ast.antlr.CSharpParser
import chapi.ast.antlr.CSharpPreprocessorParser
import chapi.domain.core.CodeContainer
import chapi.parser.Analyser
import org.antlr.v4.runtime.*
import org.antlr.v4.runtime.tree.ParseTreeWalker

open class CSharpAnalyser : Analyser {
    override fun analysis(code: String, filePath: String): CodeContainer {
        val context = this.parse(code).compilation_unit()
        val listener = CSharpFullIdentListener(fileName = filePath)

        ParseTreeWalker().walk(listener, context)

        return listener.getNodeInfo()
    }

    private fun parse(str: String): CSharpParser {
        val codeTokens: MutableList<Token> = mutableListOf()
        val commentTokens: MutableList<Token> = mutableListOf()

        val preprocessorLexer = CSharpLexer(CharStreams.fromString(str))
        val tokens = preprocessorLexer.allTokens
        val directiveTokens: MutableList<Token> = mutableListOf()
        var directiveTokenSource = ListTokenSource(directiveTokens)
        var directiveTokenStream = CommonTokenStream(directiveTokenSource, CSharpLexer.DIRECTIVE)
        val preprocessorParser = CSharpPreprocessorParser(directiveTokenStream)

        var index = 0
        var compiledTokens = true
        while (index < tokens.size) {
            val token = tokens[index]
            when {
                token.type == CSharpLexer.SHARP -> {
                    directiveTokens.clear()
                    var directiveTokenIndex = index + 1
                    // Collect all preprocessor directive tokens.
                    while (directiveTokenIndex < tokens.size &&
                        tokens[directiveTokenIndex].type != CSharpLexer.EOF &&
                        tokens[directiveTokenIndex].type != CSharpLexer.DIRECTIVE_NEW_LINE &&
                        tokens[directiveTokenIndex].type != CSharpLexer.SHARP
                    ) {
                        if (tokens[directiveTokenIndex].channel == CSharpLexer.COMMENTS_CHANNEL) {
                            commentTokens.add(tokens[directiveTokenIndex])
                        } else if (tokens[directiveTokenIndex].channel != Lexer.HIDDEN) {
                            directiveTokens.add(tokens[directiveTokenIndex])
                        }
                        directiveTokenIndex++
                    }

                    directiveTokenSource = ListTokenSource(directiveTokens)
                    directiveTokenStream = CommonTokenStream(directiveTokenSource, CSharpLexer.DIRECTIVE)
                    preprocessorParser.inputStream = directiveTokenStream
                    preprocessorParser.reset()
                    // Parse condition in preprocessor directive (based on CSharpPreprocessorParser.g4 grammar).
                    val directive = preprocessorParser.preprocessor_directive()

                    // if true than next code is valid and not ignored.
                    compiledTokens = directive.value
                    val directiveStr = tokens[index + 1].text.trim { it <= ' ' }
                    if ("line" == directiveStr || "error" == directiveStr || "warning" == directiveStr || "define" == directiveStr || "endregion" == directiveStr || "endif" == directiveStr || "pragma" == directiveStr) {
                        compiledTokens = true
                    }
                    var conditionalSymbol: String?
                    if ("define" == tokens[index + 1].text) {
                        // add to the conditional symbols
                        conditionalSymbol = tokens[index + 2].text
                        preprocessorParser.ConditionalSymbols.add(conditionalSymbol)
                    }
                    if ("undef" == tokens[index + 1].text) {
                        conditionalSymbol = tokens[index + 2].text
                        preprocessorParser.ConditionalSymbols.remove(conditionalSymbol)
                    }
                    index = directiveTokenIndex - 1
                }
                token.channel == CSharpLexer.COMMENTS_CHANNEL -> {
                    commentTokens.add(token) // Collect comment tokens (if required).
                }
                token.channel != Lexer.HIDDEN && token.type != CSharpLexer.DIRECTIVE_NEW_LINE && compiledTokens -> {
                    codeTokens.add(token) // Collect code tokens.
                }
            }

            index++
        }

        // At the second stage, tokens are parsed in the usual way.
        val codeTokenSource = ListTokenSource(tokens)
        val codeTokenStream = CommonTokenStream(codeTokenSource)
        val parser = CSharpParser(codeTokenStream)
        return parser
    }
}
