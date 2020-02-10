package chapi.ast.typescriptast

import chapi.ast.antlr.TypeScriptParser
import chapi.ast.antlr.TypeScriptParserBaseListener
import chapi.ast.antlr.TypeScriptParserBaseVisitor
import domain.core.CodeDataStruct
import domain.core.CodeFile
import domain.core.CodeFunction

class TypeScriptFullIdentListener(private var node: TSIdentify) : TypeScriptParserBaseListener() {
    private var dataStructQueue = arrayOf<CodeDataStruct>()
    private var hasEnterClass = false

    private var nodeMap = mutableMapOf<String, CodeDataStruct>()
    private var codeFile: CodeFile = CodeFile(FullName = node.fileName)

    private var currentNode = CodeDataStruct()
    private var currentFunction = CodeFunction(IsConstructor = false)
    private var currentType: String = ""

    override fun enterClassDeclaration(ctx: TypeScriptParser.ClassDeclarationContext?) {
        val nodeName = ctx!!.Identifier().text
        currentNode = CodeDataStruct(
            Type = "Interface",
            NodeName = nodeName
        )

        val heritageCtx = ctx.classHeritage()
        if (heritageCtx.implementsClause() != null) {

        }

        if (heritageCtx.classExtendsClause() != null) {

        }

        nodeMap[nodeName] = currentNode
    }

    override fun enterInterfaceDeclaration(ctx: TypeScriptParser.InterfaceDeclarationContext?) {
        val nodeName = ctx!!.Identifier().text
        val currentType = "Interface"

        currentNode = CodeDataStruct(
            Type = currentType,
            NodeName = nodeName
        )

        nodeMap[nodeName] = currentNode
    }

    fun getNodeInfo(): CodeFile {
        for (entry in nodeMap) {
            codeFile.DataStructures += entry.value
        }
        return codeFile
    }
}
