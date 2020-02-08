package chapi.ast.typescriptast

import chapi.ast.antlr.TypeScriptParser
import chapi.ast.antlr.TypeScriptParserBaseVisitor
import domain.core.CodeDataStruct
import domain.core.CodeFile
import domain.core.CodeFunction

class TypeScriptIdentVisitor(private var node: TSIdentify) : TypeScriptParserBaseVisitor<Any>() {
    private var dataStructQueue = arrayOf<CodeDataStruct>()
    private var hasEnterClass = false

    private var nodeMap = mutableMapOf<String, CodeDataStruct>()
    private var codeFile: CodeFile = CodeFile(FullName = node.fileName)

    private var currentNode = CodeDataStruct()
    private var currentFunction = CodeFunction(IsConstructor = false)
    private var currentType: String = ""

    override fun visitClassDeclaration(ctx: TypeScriptParser.ClassDeclarationContext?): Any {
        val nodeName = ctx!!.Identifier().text
        currentNode = CodeDataStruct(
            Type = "Interface",
            NodeName = nodeName
        )

        val heritageCtx = ctx.classHeritage()
        if (heritageCtx.implementsClause() != null) {
//            val typeList = heritageCtx.implementsClause().classOrInterfaceTypeList()
        }

        if (heritageCtx.classExtendsClause() != null) {

        }

//        dataStructQueue += currentNode
        nodeMap[nodeName] = currentNode
//        return super.visitClassDeclaration(ctx)
        return node
    }

    override fun visitInterfaceDeclaration(ctx: TypeScriptParser.InterfaceDeclarationContext?): Any {
        val nodeName = ctx!!.Identifier().text
        val currentType = "Interface"

        currentNode = CodeDataStruct(
            Type = currentType,
            NodeName = nodeName
        )

        nodeMap[nodeName] = currentNode
//        return super.visitInterfaceDeclaration(ctx) // todo: figure why
        return node
    }

    fun getNodeInfo(): CodeFile {
        for (entry in nodeMap) {
            codeFile.DataStructures += entry.value
        }
        return codeFile
    }
}
