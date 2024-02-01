package chapi.ast.cppast

import chapi.ast.antlr.CPP14Lexer
import chapi.ast.antlr.CPP14Parser
import chapi.domain.core.CodeContainer
import chapi.parser.Analyser
import org.anarres.cpp.*
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker

open class CPPAnalyser: Analyser  {
    val pp = Preprocessor()

    init {
        pp.addFeature(Feature.DIGRAPHS);
        pp.addFeature(Feature.TRIGRAPHS);
        pp.addWarning(Warning.IMPORT);

        pp.listener = DefaultPreprocessorListener()
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


    override fun analysis(code: String, filePath: String): CodeContainer {
        val processedCode = preProcessing(code)
        val context = this.parse(processedCode).translationUnit()
        val listener = CPPBasicIdentListener(fileName = filePath)

        ParseTreeWalker().walk(listener, context)

        val nodeInfo = listener.getNodeInfo()
        return nodeInfo
    }

    open fun parse(str: String): CPP14Parser =
        CharStreams.fromString(str)
            .let(::CPP14Lexer)
            .let(::CommonTokenStream)
            .let(::CPP14Parser)
}
