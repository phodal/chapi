package chapi.ast.thrift

import chapi.ast.antlr.ThriftBaseListener
import chapi.ast.antlr.ThriftParser
import chapi.domain.core.CodeContainer

class ThriftFullIdentListener(fileName: String) : ThriftBaseListener() {
    private var codeContainer: CodeContainer = CodeContainer(FullName = fileName)

    override fun enterNamespace_(ctx: ThriftParser.Namespace_Context?) {
        val prefix = ctx!!.children[0].text
        val namespace = ctx.text.removePrefix(prefix).trim()
        codeContainer.PackageName = namespace
    }

    fun getNodeInfo(): CodeContainer {
        return codeContainer
    }
}
