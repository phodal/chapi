package chapi.ast.cast

import chapi.ast.antlr.CLexer
import chapi.ast.antlr.CParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker

open class CIdentApp() {
    open fun analysis(str: String) {
        val context = this.parse(str).compilationUnit()
        val listener = CIdentListener()

        ParseTreeWalker().walk(listener, context)

        listener.getNodeInfo()
    }

    open fun parse(str: String): CParser {
        val fromString = CharStreams.fromString(str)
        val tokenSource = CLexer(fromString)
        val commonTokenStream = CommonTokenStream(tokenSource)
        val parser = CParser(commonTokenStream)
        return parser
    }

}
