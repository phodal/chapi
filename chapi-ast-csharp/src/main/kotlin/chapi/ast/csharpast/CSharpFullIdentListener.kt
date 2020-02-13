package chapi.ast.csharpast

import chapi.ast.antlr.CSharpParser
import domain.core.CodeContainer
import domain.core.CodeImport
import domain.core.CodePackage

class CSharpFullIdentListener(fileName: String) : CSharpAstListener() {
    private var codeContainer: CodeContainer = CodeContainer(FullName = fileName)

    private var currentPackage: CodePackage = CodePackage()

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
                val nsName = ctx.namespace_declaration().text
                val codePackage = CodePackage(
                    Name = nsName
                )

                currentPackage = codePackage
                codeContainer.Packages += codePackage
            }
        }
    }

    fun getNodeInfo(): CodeContainer {
        return codeContainer
    }
}
