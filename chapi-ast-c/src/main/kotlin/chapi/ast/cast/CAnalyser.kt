package chapi.ast.cast

import chapi.ast.antlr.CLexer
import chapi.ast.antlr.CParser
import chapi.ast.antlr.CPreprocessorParser
import chapi.domain.core.CodeContainer
import chapi.parser.Analyser
import org.anarres.cpp.*
import org.antlr.v4.runtime.*
import org.antlr.v4.runtime.Token
import org.antlr.v4.runtime.tree.ParseTreeWalker
import java.io.InputStreamReader

open class CAnalyser : Analyser {
    private var includesDirective: MutableList<String> = mutableListOf()
    val pp = Preprocessor()

    init {
        pp.addFeature(Feature.DIGRAPHS);
        pp.addFeature(Feature.TRIGRAPHS);
        pp.addWarning(Warning.IMPORT);

        pp.listener = DefaultPreprocessorListener()
    }

    /**
     * Adds a source code to the program.
     *
     * This method takes a string representation of the source code and adds it to the program. The source code is read from
     * an input stream created from the given string. The input stream is then passed to the LexerSource object, which
     * tokenizes the code and adds it to the program's input.
     *
     * @param code The string representation of the source code to be added.
     *
     * @throws IOException if an I/O error occurs while reading the source code.
     *
     * @see LexerSource
     */
    fun addSource(code: String) {
        pp.addInput(LexerSource(InputStreamReader(code.byteInputStream()), true))
    }

    override fun analysis(code: String, filePath: String): CodeContainer {
        addSource(code)

        val output = try {
            val buf = StringBuilder()
            while (true) {
                val tok = pp.token()
                if (tok.type == org.anarres.cpp.Token.EOF) break
                buf.append(tok.text)
            }

            val output = buf.toString()
            output.ifEmpty {
                code
            }
        } catch (e: Exception) {
            e.printStackTrace()
            code
        }

        pp.close()

        val context = this.parse(output).compilationUnit()
        val listener = CFullIdentListener(filePath, includesDirective)

        ParseTreeWalker().walk(listener, context)

        return listener.getNodeInfo()
    }

    // based on: https://gist.github.com/KvanTTT/d95579de257531a3cc15
    open fun parse(str: String): CParser {
        val codeTokens: MutableList<Token> = mutableListOf()
        val commentTokens: MutableList<Token> = mutableListOf()

        val preprocessorLexer = CLexer(CharStreams.fromString(str))
        // Collect all tokens with lexer (CLexer.g4).
        val tokens = preprocessorLexer.allTokens
        val directiveTokens: MutableList<Token> = mutableListOf()
        var directiveTokenSource = ListTokenSource(directiveTokens)
        var directiveTokenStream = CommonTokenStream(directiveTokenSource, CLexer.DIRECTIVE)
        val preprocessorParser = CPreprocessorParser(directiveTokenStream)

        var index = 0
        var compiledTokens = true
        while (index < tokens.size) {
            val token = tokens[index]
            when {
                token.type == CLexer.SHARP -> {
                    directiveTokens.clear()
                    var directiveTokenIndex = index + 1
                    // Collect all preprocessor directive tokens.
                    while (directiveTokenIndex < tokens.size &&
                        tokens[directiveTokenIndex].type != CLexer.EOF &&
                        tokens[directiveTokenIndex].type != CLexer.DIRECTIVE_NEW_LINE &&
                        tokens[directiveTokenIndex].type != CLexer.SHARP
                    ) {
                        if (tokens[directiveTokenIndex].channel == CLexer.COMMENTS_CHANNEL) {
                            commentTokens.add(tokens[directiveTokenIndex])
                        } else if (tokens[directiveTokenIndex].channel != Lexer.HIDDEN) {
                            directiveTokens.add(tokens[directiveTokenIndex])
                        }
                        directiveTokenIndex++
                    }

                    directiveTokenSource = ListTokenSource(directiveTokens)
                    directiveTokenStream = CommonTokenStream(directiveTokenSource, CLexer.DIRECTIVE)
                    preprocessorParser.inputStream = directiveTokenStream
                    preprocessorParser.reset()

                    // Parse condition in preprocessor directive (based on CSharpPreprocessorParser.g4 grammar).
                    try {
                        val directive = preprocessorParser.preprocessor_directive()
                        if (directive.value != null) {
                            // if true than next code is valid and not ignored.
                            compiledTokens = directive.value
                            index = directiveTokenIndex - 1
                        }
                    } catch (e: RecognitionException) {
                        // Ignore invalid preprocessor directives.
                    }
                }

                token.channel == CLexer.COMMENTS_CHANNEL -> {
                    commentTokens.add(token) // Collect comment tokens (if required).
                }

                token.channel != Lexer.HIDDEN && token.type != CLexer.DIRECTIVE_NEW_LINE && compiledTokens -> {
                    codeTokens.add(token) // Collect code tokens.
                }
            }

            index++
        }

        includesDirective = preprocessorParser.IncludeSymbols.toMutableList()

//        codeTokens.map {
//            print(it.text + " ")
//        }
        // At the second stage, tokens are parsed in the usual way.
        val codeTokenSource = ListTokenSource(codeTokens)

        val codeTokenStream = CommonTokenStream(codeTokenSource)
        val parser = CParser(codeTokenStream)
        return parser
    }
}
