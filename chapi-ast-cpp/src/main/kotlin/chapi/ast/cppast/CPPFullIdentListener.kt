package chapi.ast.cppast

import chapi.ast.antlr.CPP14Parser
import chapi.ast.antlr.CPP14ParserBaseListener
import chapi.domain.core.CodeContainer
import chapi.domain.core.CodeDataStruct
import chapi.domain.core.CodeFunction
import chapi.domain.core.CodeProperty

class CPPFullIdentListener(fileName: String) : CPP14ParserBaseListener() {
    private var codeContainer: CodeContainer = CodeContainer(FullName = fileName)
    private var defaultNode = CodeDataStruct()

    override fun enterFunctionDefinition(ctx: CPP14Parser.FunctionDefinitionContext?) {
        val method = CodeFunction()
        val context = ctx!!

        if (context.declSpecifierSeq() != null) {
            method.ReturnType = context.declSpecifierSeq().text
        }

        val firstPtrDecl = context.declarator().pointerDeclarator() ?: return
        if (firstPtrDecl.noPointerDeclarator() != null) {
            tryFunctionBuild(firstPtrDecl, method)
        }
    }

    private fun tryFunctionBuild(firstPtrDecl: CPP14Parser.PointerDeclaratorContext, method: CodeFunction) {
        val parametersAndQualifiersContext = firstPtrDecl.noPointerDeclarator().parametersAndQualifiers() ?: return
        method.Name = firstPtrDecl.noPointerDeclarator().noPointerDeclarator().text

        parametersAndQualifiersContext.parameterDeclarationClause()?.let {
            method.Parameters = buildParameters(it)
        }

        defaultNode.Functions += method
    }

    private fun buildParameters(parameterDeclaration: CPP14Parser.ParameterDeclarationClauseContext): List<CodeProperty> {
        return parameterDeclaration.parameterDeclarationList()?.let {
            it.parameterDeclaration().map {
                val type = it.declSpecifierSeq().declSpecifier().firstOrNull()?.typeSpecifier()?.text
                val name = it.declarator()?.text

                CodeProperty(TypeValue = name ?: "", TypeType = type ?: "")
            }
        } ?: listOf()
    }


    fun getNodeInfo(): CodeContainer {
        if (defaultNode.Functions.isNotEmpty()) {
            codeContainer.DataStructures += defaultNode
        }

        return codeContainer
    }
}
