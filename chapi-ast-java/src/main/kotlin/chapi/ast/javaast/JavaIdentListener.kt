package chapi.ast.javaast

import chapi.ast.antlr.JavaParser
import chapi.ast.antlr.JavaParserBaseListener
import domain.core.*

class JavaIdentListener(fileName: String) : JavaParserBaseListener() {
    private val methodMap: HashMap<String, CodeFunction> = HashMap<String, CodeFunction>()
    private var currentClz: String = ""
    private var currentClzExtend: String = ""
    private var hasEnterClass: Boolean = false

    private var classNodes: Array<CodeDataStruct> = arrayOf()
    private var classNodeQueue: Array<CodeDataStruct> = arrayOf()

    private var methodQueue: Array<CodeFunction> = arrayOf()

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

        if (ctx.IMPLEMENTS() != null) {
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
        currentNode.setMethodFromMap(methodMap)

        classNodes += currentNode
    }

    override fun enterMethodDeclaration(ctx: JavaParser.MethodDeclarationContext?) {
        super.enterMethodDeclaration(ctx)

        var name = ctx!!.IDENTIFIER().text
        var typeType = ctx.typeTypeOrVoid().text

        val codePosition = CodePosition(
            StartLine = ctx.start.line,
            StartLinePosition = ctx.IDENTIFIER().symbol.startIndex,
            StopLine = ctx.stop.line,
            StopLinePosition = ctx.IDENTIFIER().symbol.stopIndex
        )

        val codeFunction = CodeFunction(
            Name = name,
            ReturnType = typeType,
            Position = codePosition
        )

        if (ctx.formalParameters() != null) {
            //
        }

        this.updateCodeFunction(codeFunction)
    }

    override fun exitMethodDeclaration(ctx: JavaParser.MethodDeclarationContext?) {
        super.exitMethodDeclaration(ctx)

        currentFunction = CodeFunction()
    }

    private fun updateCodeFunction(codeFunction: CodeFunction) {
        currentFunction = codeFunction
        methodQueue += currentFunction
        methodMap[getMethodMapName(codeFunction)] = codeFunction
    }

    private fun getMethodMapName(function: CodeFunction): String {
        var name = function.Name
        if (name != "" && methodQueue.size > 1) {
            name = methodQueue[methodQueue.size - 1].Name
        }

        return codeFile.PackageName + "." + currentClz + "." + name + ":" + function.Position.StartLine.toString()
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

