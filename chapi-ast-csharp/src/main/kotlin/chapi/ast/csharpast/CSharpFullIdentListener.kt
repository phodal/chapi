package chapi.ast.csharpast

import chapi.ast.antlr.CSharpParser
import domain.core.CodeFile
import domain.core.CodeImport

class CSharpFullIdentListener(fileName: String) : CSharpAstListener() {
    private var codeFile: CodeFile = CodeFile(FullName = fileName)

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

        codeFile.Imports += codeImport
    }

    fun getNodeInfo(): CodeFile {
        return codeFile
    }
}
