package chapi.ast.goast

import chapi.ast.antlr.GoParser
import chapi.domain.core.*
import org.antlr.v4.runtime.tree.TerminalNodeImpl

class GoFullIdentListener(var fileName: String) : GoAstListener() {
    private var codeContainer: CodeContainer = CodeContainer(FullName = fileName)

    private var defaultNode = CodeDataStruct()
    private var structMap = mutableMapOf<String, CodeDataStruct>()
    private var localVars = mutableMapOf<String, String>()

    private var currentFunction = CodeFunction(IsConstructor = false)

    override fun enterImportDecl(ctx: GoParser.ImportDeclContext?) {
        super.enterImportDecl(ctx)
    }

    override fun enterPackageClause(ctx: GoParser.PackageClauseContext?) {
        codeContainer.PackageName = ctx?.IDENTIFIER()?.text ?: ""
    }

    override fun enterImportSpec(ctx: GoParser.ImportSpecContext?) {
        val originSource = ctx!!.importPath().text
        val sourceName = originSource.replace("\"", "")

        val codeImport = CodeImport(
            Source = sourceName,
            AsName = ctx.DOT()?.text ?: "",
            UsageName = arrayOf(ctx.IDENTIFIER()?.text ?: "")
        )

        codeContainer.Imports += codeImport
    }

    override fun enterFunctionDecl(ctx: GoParser.FunctionDeclContext?) {
        val funcName = ctx!!.IDENTIFIER().text

        val codeFunction = CodeFunction(
            Name = funcName,
            Package = codeContainer.PackageName
        )

        codeFunction.Parameters = this.buildParameters(ctx.signature().parameters())
        codeFunction.MultipleReturns = this.buildReturnTypeFromSignature(ctx.signature())

        currentFunction = codeFunction
    }

    override fun exitFunctionDecl(ctx: GoParser.FunctionDeclContext?) {
        currentFunction.addVarsFromMap(localVars)
        defaultNode.Functions += currentFunction
        currentFunction = CodeFunction()
    }

    override fun enterTypeDecl(ctx: GoParser.TypeDeclContext?) {
        ctx?.typeSpec()?.forEach {
            buildTypeSpec(it)
        }
    }

    override fun enterMethodDecl(ctx: GoParser.MethodDeclContext?) {

        currentFunction = CodeFunction(
            Name = ctx!!.IDENTIFIER().text,
            MultipleReturns = buildReturnTypeFromSignature(ctx.signature()),
            Parameters = buildParameters(ctx.signature().parameters())
        )
    }

    override fun exitMethodDecl(ctx: GoParser.MethodDeclContext?) {
        val receiverName = this.getStructNameFromReceiver(ctx?.receiver()?.parameters())
        currentFunction.addVarsFromMap(localVars)
        this.addReceiverToStruct(receiverName, currentFunction)
        currentFunction = CodeFunction()
    }

    private fun addReceiverToStruct(receiverName: String, codeFunction: CodeFunction) {
        structMap.getOrPut(receiverName) {
            createStructByName(receiverName)
        }.Functions += codeFunction
    }

    private fun buildTypeSpec(typeSpec: GoParser.TypeSpecContext) {
        val identifyName = typeSpec.IDENTIFIER().text
        typeSpec.type_().typeLit()?.let {
            when (val typeChild = it.getChild(0)) {
                is GoParser.StructTypeContext -> {
                    buildStruct(identifyName, typeChild)
                }

                else -> {

                }
            }
        }
    }

    private fun buildStruct(identifyName: String, typeChild: GoParser.StructTypeContext) {
        val struct = createStructByName(identifyName)
        structMap.getOrPut(identifyName) { struct }.Fields = buildStructFields(typeChild)
    }

    private fun createStructByName(identifyName: String): CodeDataStruct {
        return CodeDataStruct(
            NodeName = identifyName,
            Type = DataStructType.STRUCT,
            Package = codeContainer.PackageName,
            FilePath = codeContainer.FullName
        )
    }

    private fun buildStructFields(structTypeCtx: GoParser.StructTypeContext): Array<CodeField> {
        return structTypeCtx.fieldDecl()
            .map { field ->
                CodeField(
                    TypeType = field.type_().text,
                    TypeValue = field.identifierList().text
                )
            }.toTypedArray()
    }

    override fun enterExpression(ctx: GoParser.ExpressionContext?) {
        when (val firstChild = ctx!!.getChild(0)) {
            is GoParser.PrimaryExprContext -> {
                firstChild.getChild(1)?.let {
                    this.buildPrimaryExprCtx(firstChild)
                }
            }
        }
    }

    private fun buildPrimaryExprCtx(primaryExprCtx: GoParser.PrimaryExprContext) {
        when (val child = primaryExprCtx.getChild(1)) {
            is GoParser.ArgumentsContext -> {
                val codeCall = codeCallFromExprList(primaryExprCtx)
                codeCall.Parameters = parseArguments(child)

                currentFunction.FunctionCalls += codeCall
            }

            else -> {
                println("${child.javaClass} not implemented")
            }
        }
    }

    private fun parseArguments(child: GoParser.ArgumentsContext): Array<CodeProperty> {
        return child.expressionList()?.expression()?.map {
            CodeProperty(TypeValue = it.text, TypeType = "")
        }?.toTypedArray() ?: arrayOf()
    }

    private fun codeCallFromExprList(primaryExprCtx: GoParser.PrimaryExprContext): CodeCall {
        return when (val child = primaryExprCtx.getChild(0)) {
            is GoParser.OperandContext -> {
                CodeCall(NodeName = child.text)
            }

            is GoParser.MethodDeclContext -> {
                CodeCall(NodeName = child.text)
            }

            is GoParser.PrimaryExprContext -> {
                CodeCall(NodeName = child.text)
                when (child.getChild(1)) {
                    is TerminalNodeImpl -> {
                        // primaryExpr '.' IDENTIFIER
                        val nodeName = when (val first = child.getChild(0)) {
                            is GoParser.OperandContext -> {
                                first.text
                            }

                            is GoParser.PrimaryExprContext -> {
                                localVars.getOrDefault(first.text, first.text)
                            }

                            else -> {
                                first.text
                            }
                        }

                        CodeCall(
                            NodeName = nodeName,
                            FunctionName = child.getChild(2).text
                        )
                    }

                    else -> {
                        CodeCall(NodeName = child.text)
                    }
                }
            }

            else -> {
                println("${child.javaClass} not implemented")
                CodeCall(NodeName = child.text)
            }
        }
    }


    override fun enterVarDecl(ctx: GoParser.VarDeclContext?) {
        ctx?.varSpec()?.forEach { varSpec ->
            varSpec.identifierList().IDENTIFIER().forEach { terminalNode ->
                localVars[terminalNode.text] = varSpec.type_()?.text ?: ""
            }
        }
    }

    override fun enterShortVarDecl(ctx: GoParser.ShortVarDeclContext?) {
        ctx?.identifierList()?.IDENTIFIER()?.forEach {
            localVars[it.text] = ctx.expressionList()?.text ?: ""
        }
    }

    override fun enterConstDecl(ctx: GoParser.ConstDeclContext?) {
        ctx?.constSpec()?.forEach { constSpecContext ->
            constSpecContext.identifierList().IDENTIFIER().forEach { terminalNode ->
                localVars[terminalNode.text] = constSpecContext.type_()?.text ?: ""
            }
        }
    }

    fun getNodeInfo(): CodeContainer {
        for (entry in structMap) {
            codeContainer.DataStructures += entry.value
        }

        if (defaultNode.Functions.isNotEmpty()) {
            codeContainer.DataStructures += defaultNode
        }

        return codeContainer
    }
}
