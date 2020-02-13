package chapi.ast.csharpast

import chapi.ast.antlr.CSharpParser
import domain.core.CodeContainer
import domain.core.CodeImport
import domain.core.CodePackage
import domain.infra.Stack

class CSharpFullIdentListener(val fileName: String) : CSharpAstListener() {
    private var codeContainer: CodeContainer = CodeContainer(FullName = fileName)

    private var currentContainer: CodeContainer = codeContainer
    private var containerStack: Stack<CodeContainer> = Stack<CodeContainer>()

    private var currentPackage: CodePackage = CodePackage()

    override fun enterCompilation_unit(ctx: CSharpParser.Compilation_unitContext?) {
        containerStack.push(codeContainer)
    }

    override fun enterUsing_directives(ctx: CSharpParser.Using_directivesContext?) {
        for (usingCtx in ctx!!.using_directive()) {
            handleDirective(usingCtx!!)
        }
    }

    private fun handleDirective(usingCtx: CSharpParser.Using_directiveContext) {
        val usingType = usingCtx::class.java.simpleName
        val codeImport = CodeImport()

        when (usingType) {
            "UsingNamespaceDirectiveContext" -> {
                val directiveCtx = usingCtx as CSharpParser.UsingNamespaceDirectiveContext
                codeImport.Source = directiveCtx.namespace_or_type_name().text
            }
            "UsingAliasDirectiveContext" -> {
                val alisCtx = usingCtx as CSharpParser.UsingAliasDirectiveContext

                codeImport.Source = alisCtx.namespace_or_type_name().text
                codeImport.AsName = alisCtx.identifier().text
            }
            else -> {
                println(usingType)
            }
        }

        codeContainer.Imports += codeImport
    }

    override fun enterNamespace_member_declaration(ctx: CSharpParser.Namespace_member_declarationContext?) {
        val namespaceDeclaration = ctx!!.namespace_declaration()
        if (namespaceDeclaration != null) {
            if (namespaceDeclaration.qualified_identifier() != null) {
                val nsName = ctx.namespace_declaration().qualified_identifier().text
                val container = CodeContainer(
                    FullName = fileName,
                    PackageName = nsName
                )

                currentContainer = container

                val lastContainer = containerStack.elements.last()
                lastContainer.Containers += currentContainer

                containerStack.push(currentContainer)

                currentContainer = CodeContainer(FullName = this.fileName)
            }
        }
    }

    override fun exitNamespace_member_declaration(ctx: CSharpParser.Namespace_member_declarationContext?) {

    }

    fun getNodeInfo(): CodeContainer {
        return codeContainer
    }
}
