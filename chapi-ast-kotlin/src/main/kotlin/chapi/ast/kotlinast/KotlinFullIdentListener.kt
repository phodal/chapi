package chapi.ast.kotlinast

import chapi.ast.antlr.KotlinParser
import chapi.domain.core.CodeContainer
import chapi.domain.core.CodeDataStruct
import chapi.domain.core.DataStructType

class KotlinFullIdentListener(fileName: String) : KotlinAstListener() {
    private var classNodes: Array<CodeDataStruct> = emptyArray()
    private lateinit var currentType: DataStructType
    private var currentClz: String = "";
    private var hasEnterClass: Boolean = false
    private var currentNode = CodeDataStruct()
    private var codeContainer: CodeContainer = CodeContainer(FullName = fileName)

    override fun enterFunctionDeclaration(ctx: KotlinParser.FunctionDeclarationContext?) {
        super.enterFunctionDeclaration(ctx)
    }

    override fun enterClassDeclaration(ctx: KotlinParser.ClassDeclarationContext?) {
        currentType = DataStructType.CLASS

        hasEnterClass = true

        currentNode.Package = codeContainer.PackageName
        currentNode.FilePath = codeContainer.FullName

        if (ctx!!.simpleIdentifier().Identifier() != null) {
            currentClz = ctx.simpleIdentifier().Identifier().text
            currentNode.NodeName = currentClz
        }
    }

    override fun exitClassDeclaration(ctx: KotlinParser.ClassDeclarationContext?) {
        classNodes += currentNode
    }

    fun getNodeInfo(): CodeContainer {
        codeContainer.DataStructures = classNodes
        return codeContainer
    }
}
