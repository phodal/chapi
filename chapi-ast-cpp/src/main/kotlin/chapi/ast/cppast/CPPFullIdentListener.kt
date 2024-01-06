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

        ctx?.declarator()?.let {
            method.Name = it.text
        }
        ctx?.declSpecifierSeq()?.let {
            method.ReturnType = it.text
        }

        val firstPtrDecl = ctx?.declarator()?.pointerDeclarator() ?: return
        val noPointerDeclarator = firstPtrDecl.noPointerDeclarator() ?: return

        val parameters = noPointerDeclarator.parametersAndQualifiers() ?: return

        method.Name = noPointerDeclarator.noPointerDeclarator().text

        parameters.parameterDeclarationClause()?.let {
            method.Parameters = buildParameters(it)
        }

        defaultNode.Functions += method
    }

    private fun buildParameters(parameterDeclaration: CPP14Parser.ParameterDeclarationClauseContext): List<CodeProperty> {
        return parameterDeclaration.parameterDeclarationList()?.let { listContext ->
            listContext.parameterDeclaration().map {
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
