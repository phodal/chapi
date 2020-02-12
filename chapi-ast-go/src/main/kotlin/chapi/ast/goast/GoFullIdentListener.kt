package chapi.ast.goast

import chapi.ast.antlr.GoParser
import domain.core.*
import org.antlr.v4.runtime.tree.ParseTree

class GoFullIdentListener(var fileName: String) : GoAstListener() {
    private var codeFile: CodeFile = CodeFile(FullName = fileName)

    private var currentNode = CodeDataStruct()
    private var defaultNode = CodeDataStruct()
    private var structMap = mutableMapOf<String, CodeDataStruct>()

    private var currentFunction = CodeFunction(IsConstructor = false)

    override fun enterImportDecl(ctx: GoParser.ImportDeclContext?) {
        super.enterImportDecl(ctx)
    }

    override fun enterPackageClause(ctx: GoParser.PackageClauseContext?) {
        codeFile.PackageName = ctx!!.IDENTIFIER().text
    }

    override fun enterImportSpec(ctx: GoParser.ImportSpecContext?) {
        val originSource = ctx!!.importPath().text
        val sourceName = originSource.replace("\"", "")
        val codeImport = CodeImport(Source = sourceName)

        if (ctx.DOT() != null) {
            codeImport.AsName = "."
        }

        if (ctx.IDENTIFIER() != null) {
            codeImport.UsageName += ctx.IDENTIFIER().text
        }

        codeFile.Imports += codeImport
    }

    override fun enterTypeDecl(ctx: GoParser.TypeDeclContext?) {
        val typeSpecs = ctx!!.typeSpec()
        for (typeSpec in typeSpecs) {
            buildTypeSpec(typeSpec)
        }
    }

    private fun buildTypeSpec(typeSpec: GoParser.TypeSpecContext) {
        val identifyName = typeSpec.IDENTIFIER().text
        val typeLit = typeSpec.type_().typeLit()
        if (typeLit != null) {
            val typeChild = typeLit.getChild(0)
            when (typeChild::class.java.simpleName) {
                "StructTypeContext" -> {
                    buildStruct(identifyName, typeChild)


                }
                else -> {

                }
            }
        }
    }

    private fun buildStruct(identifyName: String, typeChild: ParseTree?) {
        val struct = CodeDataStruct(
            NodeName = identifyName
        )
        val structTypeCtx = typeChild as GoParser.StructTypeContext
        for (fieldDeclContext in structTypeCtx.fieldDecl()) {
            val typeValue = fieldDeclContext.identifierList().text
            val typeType = fieldDeclContext.type_().text

            val field = CodeField(
                TypeType = typeType,
                TypeValue = typeValue
            )

            struct.Fields += field
        }

        structMap[identifyName] = struct
    }

    fun getNodeInfo(): CodeFile {
        for (entry in structMap) {
            codeFile.DataStructures += entry.value
        }
        return codeFile
    }
}
