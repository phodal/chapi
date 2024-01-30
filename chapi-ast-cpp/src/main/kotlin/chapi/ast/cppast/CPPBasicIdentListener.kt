package chapi.ast.cppast

import chapi.ast.antlr.CPP14Parser
import chapi.ast.antlr.CPP14ParserBaseListener
import chapi.domain.core.CodeContainer
import chapi.domain.core.CodeDataStruct
import chapi.domain.core.CodeFunction
import chapi.domain.core.CodeProperty

class CPPBasicIdentListener(fileName: String) : CPP14ParserBaseListener() {
    private var codeContainer: CodeContainer = CodeContainer(FullName = fileName)
    /// for example, Friend function, global function (`main`), etc.
    private var defaultNode = CodeDataStruct()
    private var currentFunction: CodeFunction? = null
    private var classes = mutableListOf<CodeDataStruct>()
    private var currentNode: CodeDataStruct? = null

    override fun enterNamespaceDefinition(ctx: CPP14Parser.NamespaceDefinitionContext?) {
        ctx?.Identifier()?.let {
            codeContainer.PackageName = it.text
        }
    }

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

        if (currentNode != null) {
            currentNode?.Functions = currentNode?.Functions?.plus(method)!!
        } else {
            defaultNode.Functions += method
        }
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

    override fun enterClassSpecifier(ctx: CPP14Parser.ClassSpecifierContext?) {
        ctx?.classHead()?.let {
            currentNode = CodeDataStruct()
            currentNode?.NodeName = it.classHeadName()?.className()?.text ?: ""

            val extends = it.baseClause()?.baseSpecifierList()?.baseSpecifier()?.map { baseSpecifier ->
                 baseSpecifier.baseTypeSpecifier()?.classOrDeclType()?.className()?.text ?: ""
            } ?: listOf()

            currentNode?.Implements = extends
        }

        ctx?.memberSpecification()?.memberdeclaration()?.let {
            // TODO: add member
        }
    }

    override fun exitClassSpecifier(ctx: CPP14Parser.ClassSpecifierContext?) {
        currentNode?.let {
            classes.add(it)
        }
    }

    fun getNodeInfo(): CodeContainer {
        if (defaultNode.Functions.isNotEmpty()) {
            codeContainer.DataStructures += defaultNode
        }

        if (classes.isNotEmpty()) {
            codeContainer.DataStructures += classes
        }

        return codeContainer
    }
}
