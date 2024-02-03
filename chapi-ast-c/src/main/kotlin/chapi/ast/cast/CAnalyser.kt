package chapi.ast.cast

import chapi.ast.antlr.CLexer
import chapi.ast.antlr.CParser
import chapi.domain.core.CodeContainer
import chapi.parser.Analyser
import org.anarres.cpp.*
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
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
     * Adds a source code to the program, for example, C header files.
     *
     */
    override fun addSource(code: String) {
        pp.addInput(LexerSource(InputStreamReader(code.byteInputStream()), true))
    }

    private val importRegex = Regex("""#include\s+(<[^>]+>|\"[^\"]+\")""")

    override fun analysis(code: String, filePath: String): CodeContainer {
        includesDirective = importRegex.findAll(code).map {
            it.groupValues[1]
        }.toMutableList()

        val output = preProcessing(code)

        val context = this.parse(output).compilationUnit()
        val listener = CFullIdentListener(filePath, includesDirective)

        ParseTreeWalker().walk(listener, context)

        return listener.getNodeInfo()
    }

    private fun preProcessing(code: String): String {
        addSource(code)

        val output = try {
            val buf = StringBuilder()
            while (true) {
                val tok = pp.token()
                if (tok.type == Token.EOF) break
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

        // pp close is source not close pp
        pp.close()
        return output
    }

    open fun parse(str: String): CParser =
        CharStreams.fromString(str)
            .let(::CLexer)
            .let(::CommonTokenStream)
            .let(::CParser)

}
