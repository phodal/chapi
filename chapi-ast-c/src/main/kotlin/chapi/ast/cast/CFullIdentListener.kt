package chapi.ast.cast

import chapi.ast.antlr.CBaseListener
import chapi.ast.antlr.CParser
import chapi.domain.core.CodeContainer
import chapi.domain.core.CodeDataStruct
import chapi.domain.core.CodeField
import chapi.domain.core.CodeImport
import domain.core.*

open class CFullIdentListener(fileName: String) : CBaseListener() {
    private var currentDataStruct = CodeDataStruct()
    private var codeContainer: CodeContainer =
        CodeContainer(FullName = fileName)


    override fun enterIncludeDeclaration(ctx: CParser.IncludeDeclarationContext?) {
        val importName = ctx!!.includeIdentifier().text
        val imp = CodeImport(
            Source = importName
        )
        codeContainer.Imports += imp
    }

    override fun enterStructOrUnionSpecifier(ctx: CParser.StructOrUnionSpecifierContext?) {
        val codeDataStruct = CodeDataStruct()
        if (ctx!!.Identifier() != null) {
            codeDataStruct.NodeName = ctx.Identifier().text
        }

        currentDataStruct = codeDataStruct

        if (ctx.structDeclarationList() != null) {
            val structDecl = ctx.structDeclarationList().structDeclaration()
            val specifierQualifierList = structDecl.specifierQualifierList()
            if (specifierQualifierList != null) {
                val key = specifierQualifierList.typeSpecifier().text
                val qualifierList = specifierQualifierList.specifierQualifierList()
                var value = ""
                if (qualifierList != null) {
                    value = qualifierList.text
                }

                val field = CodeField(
                    TypeType = key,
                    TypeValue = value
                )
                codeDataStruct.Fields += field
            }

        }

        codeContainer.DataStructures += codeDataStruct
    }


    override fun enterFunctionDefinition(ctx: CParser.FunctionDefinitionContext?) {

    }

    override fun exitFunctionDefinition(ctx: CParser.FunctionDefinitionContext?) {

    }

    fun getNodeInfo(): CodeContainer {
        return codeContainer
    }
}
