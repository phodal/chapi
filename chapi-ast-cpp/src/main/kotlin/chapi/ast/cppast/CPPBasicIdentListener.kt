package chapi.ast.cppast

import chapi.ast.antlr.CPP14Parser
import chapi.ast.antlr.CPP14ParserBaseListener
import chapi.domain.core.*

class CPPBasicIdentListener(fileName: String, includes: MutableList<String>) : CPP14ParserBaseListener() {
    private var codeContainer: CodeContainer = CodeContainer(FullName = fileName)
    /// for example, Friend function, global function (`main`), etc.
    private var defaultNode = CodeDataStruct()
    private var currentFunction: CodeFunction? = null
    private var classes = mutableListOf<CodeDataStruct>()
    private var currentNode: CodeDataStruct? = null

    init {
        includes.forEach {
            val value = it
                .removeSurrounding("\"", "\"")
                .removeSurrounding("<", ">")

            val imp = CodeImport(
                Source = value,
                AsName = value
            )

            codeContainer.Imports += imp
        }

    }

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
            currentNode?.Functions = currentNode?.Functions?.plus(method) ?: listOf(method)
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

            it.classKey()?.let { keyContext ->
                currentNode?.Type = when (keyContext.text) {
                    "class" -> DataStructType.CLASS
                    "struct" -> DataStructType.STRUCT
                    "union" -> DataStructType.UNION
                    else -> DataStructType.CLASS
                }
            }

            currentNode?.NodeName = it.classHeadName()?.className()?.text ?: ""

            val extends = it.baseClause()?.baseSpecifierList()?.baseSpecifier()?.map { baseSpecifier ->
                 baseSpecifier.baseTypeSpecifier()?.classOrDeclType()?.className()?.text ?: ""
            } ?: listOf()

            currentNode?.Implements = extends
        }

        val fields = ctx?.memberSpecification()?.memberdeclaration()?.map {
            val type = it?.declSpecifierSeq()?.declSpecifier()?.firstOrNull()?.typeSpecifier()?.text

            it.memberDeclaratorList()?.memberDeclarator()?.map { memberDeclarator ->
                val name = memberDeclarator.declarator()?.text

                CodeField(TypeKey = name ?: "", TypeType = type ?: "", TypeValue = type ?: "")
            } ?: listOf()
        }?.flatten() ?: listOf()

        currentNode?.Fields = fields
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
