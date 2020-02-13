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
        when (usingType) {
            "UsingNamespaceDirectiveContext" -> {
                val codeImport = CodeImport()
                val directiveCtx = usingCtx as CSharpParser.UsingNamespaceDirectiveContext
                val sourceName = directiveCtx.namespace_or_type_name().text

                codeImport.Source = sourceName
                codeFile.Imports += codeImport
            }
            else -> {
                println(usingType)
            }
        }
    }

    fun getNodeInfo(): CodeFile {
        return codeFile
    }
}
