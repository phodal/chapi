package chapi.ast.cppast

import chapi.ast.antlr.CPPBaseListener
import chapi.ast.antlr.CPPParser
import chapi.domain.core.CodeContainer
import chapi.domain.core.CodeDataStruct
import chapi.domain.core.CodeFunction
import chapi.domain.core.CodeProperty

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

            if (parametersandqualifiers.parameterdeclarationclause() != null) {
                buildParameters(parametersandqualifiers.parameterdeclarationclause(), method)
            }

            defaultNode.Functions += method
        }
    }

    private fun buildParameters(paramDecl: CPPParser.ParameterdeclarationclauseContext, method: CodeFunction) {
        if (paramDecl.parameterdeclarationlist() != null) {
            buildParameter(paramDecl.parameterdeclarationlist()!!, method)
        }
    }

    private fun buildParameter(
        paramDeclCtx: CPPParser.ParameterdeclarationlistContext,
        method: CodeFunction
    ) {
        if (paramDeclCtx.parameterdeclarationlist() != null) {
            buildParameter(paramDeclCtx.parameterdeclarationlist(), method)
        }
        val paramDecl = paramDeclCtx.parameterdeclaration()
        if (paramDecl != null) {
            val declspecifierseq = paramDecl.declspecifierseq()
            val type = declspecifierseq.declspecifier().typespecifier().text
            val param = CodeProperty(TypeType = type, TypeValue = "")

            if (paramDecl.declarator() != null) {
                param.TypeValue = paramDecl.declarator().text
            }

            method.Parameters += param
        }
    }

    fun getNodeInfo(): CodeContainer {
        if (defaultNode.Functions.isNotEmpty()) {
            codeContainer.DataStructures += defaultNode
        }

        return codeContainer
    }
}
