package chapi.ast.pythonast

import chapi.ast.antlr.PythonParser
import domain.core.CodeDataStruct
import domain.core.CodeFile

class PythonFullIdentListener(var fileName: String) : PythonAstBaseListener() {
    private var hasEnterClass = false
    private var codeFile: CodeFile = CodeFile(FullName = fileName)

    private var currentNode: CodeDataStruct = CodeDataStruct()

    override fun enterClassdef(ctx: PythonParser.ClassdefContext?) {
        hasEnterClass = true
        currentNode = CodeDataStruct(
            NodeName = ctx!!.name().text
        )

        if (ctx.arglist() != null) {
            for (argumentContext in ctx.arglist().argument()) {
                currentNode.MultipleExtend += argumentContext.text
            }
        }
    }

    override fun exitClassdef(ctx: PythonParser.ClassdefContext?) {
        hasEnterClass = false
        codeFile.DataStructures += currentNode
        currentNode = CodeDataStruct()
    }

    fun getNodeInfo(): CodeFile {
        return this.codeFile
    }
}
