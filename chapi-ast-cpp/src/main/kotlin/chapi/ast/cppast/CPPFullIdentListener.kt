package chapi.ast.cppast

import chapi.ast.antlr.CPPBaseListener
import chapi.ast.antlr.CPPParser
import chapi.domain.core.CodeContainer
import chapi.domain.core.CodeDataStruct
import chapi.domain.core.CodeFunction

class CPPFullIdentListener(fileName: String) : CPPBaseListener() {
    private var codeContainer: CodeContainer = CodeContainer(FullName = fileName)
    private var defaultNode = CodeDataStruct()

    override fun enterFunctiondefinition(ctx: CPPParser.FunctiondefinitionContext?) {
        val context = ctx!!
        val firstPtrDecl = context.declarator().ptrdeclarator()
        if (firstPtrDecl != null) {
            if (firstPtrDecl.noptrdeclarator() != null) {
                tryFunctionBuild(firstPtrDecl)
            }
        }
    }

    private fun tryFunctionBuild(firstPtrDecl: CPPParser.PtrdeclaratorContext) {
        val parametersandqualifiers = firstPtrDecl.noptrdeclarator().parametersandqualifiers()
        if (parametersandqualifiers != null) {
            val codeFunction = CodeFunction()

            val functionName = firstPtrDecl.noptrdeclarator().noptrdeclarator().text
            codeFunction.Name = functionName
            defaultNode.Functions += codeFunction
        }
    }

    fun getNodeInfo(): CodeContainer {
        if (defaultNode.Functions.isNotEmpty()) {
            codeContainer.DataStructures += defaultNode
        }

        return codeContainer
    }
}
