package chapi.ast.cppast

import chapi.ast.antlr.CPP14Parser
import chapi.ast.antlr.CPP14ParserBaseListener
import chapi.domain.core.CodeContainer
import chapi.domain.core.CodeDataStruct
import chapi.domain.core.CodeFunction

class CPPFullIdentListener(fileName: String) : CPP14ParserBaseListener() {
    private var codeContainer: CodeContainer = CodeContainer(FullName = fileName)
    private var defaultNode = CodeDataStruct()

    override fun enterFunctionDefinition(ctx: CPP14Parser.FunctionDefinitionContext?) {
        val method = CodeFunction()
        val context = ctx!!

        if (context.declSpecifierSeq() != null) {
            method.ReturnType = context.declSpecifierSeq().text
        }

        val firstPtrDecl = context.declarator().pointerDeclarator()
        if (firstPtrDecl != null) {
            if (firstPtrDecl.noPointerDeclarator() != null) {
                tryFunctionBuild(firstPtrDecl, method)
            }
        }
    }

    private fun tryFunctionBuild(firstPtrDecl: CPP14Parser.PointerDeclaratorContext, method: CodeFunction) {
        val parametersAndQualifiersContext = firstPtrDecl.noPointerDeclarator().parametersAndQualifiers()
        if (parametersAndQualifiersContext != null) {
            val functionName = firstPtrDecl.noPointerDeclarator().noPointerDeclarator().text
            method.Name = functionName

            if (parametersAndQualifiersContext.parameterDeclarationClause() != null) {
                buildParameters(parametersAndQualifiersContext.parameterDeclarationClause(), method)
            }

            defaultNode.Functions += method
        }

    }

    private fun buildParameters(parameterDeclarationClause: CPP14Parser.ParameterDeclarationClauseContext, method: CodeFunction) {
        if (parameterDeclarationClause.parameterDeclarationList() != null) {
            buildParameter(parameterDeclarationClause.parameterDeclarationList()!!, method)
        }
    }

    private fun buildParameter(parameterDeclarationList: CPP14Parser.ParameterDeclarationListContext, method: CodeFunction) {
//        if (parameterDeclarationList.parameterDeclaration() != null) {
//            buildParameter(parameterDeclarationList.parameterDeclarationList(), method)
//            parameterDeclarationList.parameterDeclaration().forEach {
//                buildParameter(it, method)
//            }
//        }
        val parameterDeclaration = parameterDeclarationList.parameterDeclaration()
        if (parameterDeclaration != null) {
//            val declSpecifierSeq = parameterDeclaration.declSpecifierSeq()
//            val declSpecifierSeq = parameterDeclaration
//            val type = declSpecifierSeq.declSpecifier().typeSpecifier().text
//            val param = CodeProperty(TypeType = type, TypeValue = "")
//
//            if (parameterDeclaration.declarator() != null) {
//                param.TypeValue = parameterDeclaration.declarator().text
//            }
//            method.Parameters += param
        }
    }


    fun getNodeInfo(): CodeContainer {
        if (defaultNode.Functions.isNotEmpty()) {
            codeContainer.DataStructures += defaultNode
        }

        return codeContainer
    }
}
