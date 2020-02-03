package chapi.ast.javaast

import chapi.ast.antlr.JavaParser
import chapi.ast.antlr.JavaParserBaseListener
import domain.core.CodeDataStruct
import domain.core.CodeFile
import domain.core.CodeFunction
import domain.core.CodeImport

class JavaIdentListener(fileName: String) : JavaParserBaseListener() {
    private var currentClz: String = ""
    private var currentClzExtend: String = ""
    private var hasEnterClass: Boolean = false

    private var classNodes: Array<CodeDataStruct> = arrayOf()
    private var classNodeQueue: Array<CodeDataStruct> = arrayOf()

    private var imports: Array<CodeImport> = arrayOf()

    private var currentNode = CodeDataStruct()
    private var currentFunction = CodeFunction()
    private var currentType: String = ""

    private var codeFile: CodeFile = CodeFile(FullName = fileName)

    override fun enterPackageDeclaration(ctx: JavaParser.PackageDeclarationContext?) {
        super.enterPackageDeclaration(ctx)
        codeFile.PackageName = ctx?.qualifiedName()!!.text
    }

    override fun enterImportDeclaration(ctx: JavaParser.ImportDeclarationContext?) {
        super.enterImportDeclaration(ctx)
        val codeImport = CodeImport(Source = ctx!!.qualifiedName()!!.text)
        imports += codeImport

        codeFile.Imports += codeImport
    }

    override fun enterClassDeclaration(ctx: JavaParser.ClassDeclarationContext?) {
        super.enterClassDeclaration(ctx)

        if (currentNode.NodeName != "") {
            classNodeQueue += currentNode
            currentType = "InnerStructures"
        } else {
            currentType = "NodeName"
        }

        hasEnterClass = true
        currentClzExtend = ""

        if (ctx!!.IDENTIFIER() != null) {
            currentClz = ctx.IDENTIFIER().text
            currentNode.NodeName = currentClz
        }

        if (ctx.EXTENDS() != null) {
            currentClzExtend = ctx.IDENTIFIER().text
            this.buildExtend(currentClzExtend)
        }

        if (ctx!!.IMPLEMENTS() != null) {
            for (_type in ctx.typeList().typeType()) {
                val typeText = _type.text
                this.buildImplement(typeText)
            }
        }

        currentNode.Type = currentType
    }

    override fun exitClassBody(ctx: JavaParser.ClassBodyContext?) {
        super.exitClassBody(ctx)

        hasEnterClass = false
        this.exitBodyAction()
    }

    private fun exitBodyAction() {
        classNodes += currentNode
    }

    private fun buildImplement(typeText: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun buildExtend(currentClzExtend: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getNodeInfo(): CodeFile {
        codeFile.DataStructures = classNodes
        return codeFile
    }
}
