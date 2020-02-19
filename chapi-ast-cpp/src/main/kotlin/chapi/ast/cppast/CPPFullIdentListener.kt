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
        val method = CodeFunction()
        val context = ctx!!

        if (context.declspecifierseq() != null) {
            method.ReturnType = context.declspecifierseq().text
        }

        val firstPtrDecl = context.declarator().ptrdeclarator()
        if (firstPtrDecl != null) {
            if (firstPtrDecl.noptrdeclarator() != null) {
                tryFunctionBuild(firstPtrDecl, method)
            }
        }
    }

    private fun tryFunctionBuild(firstPtrDecl: CPPParser.PtrdeclaratorContext, method: CodeFunction) {
        val parametersandqualifiers = firstPtrDecl.noptrdeclarator().parametersandqualifiers()
        if (parametersandqualifiers != null) {

            val functionName = firstPtrDecl.noptrdeclarator().noptrdeclarator().text
            method.Name = functionName
            defaultNode.Functions += method
        }


    }

    fun getNodeInfo(): CodeContainer {
        if (defaultNode.Functions.isNotEmpty()) {
            codeContainer.DataStructures += defaultNode
        }

        return codeContainer
    }
}
