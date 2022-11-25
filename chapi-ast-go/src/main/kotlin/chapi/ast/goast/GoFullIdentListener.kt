package chapi.ast.goast

import chapi.ast.antlr.GoParser
import chapi.domain.core.*
import org.antlr.v4.runtime.tree.TerminalNodeImpl

/**
 * core logic, please goto [GoFullIdentListener.enterExpression] to see how to use it
 */
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

    fun buildParameters(parametersCtx: GoParser.ParametersContext?): Array<CodeProperty> {
        return parametersCtx?.parameterDecl()?.map {
            val (ident, typetype) = processingType(it)

            localVars[ident] = typetype
            CodeProperty(
                TypeValue = ident,
                TypeType = typetype
            )
        }?.toTypedArray() ?: return arrayOf()
    }

    private fun processingType(it: GoParser.ParameterDeclContext): Pair<String, String> {
        val typeValue = it.identifierList()?.text ?: ""
        val typeType = it.type_()?.text ?: ""

        val pair = processingStringType(typeValue, typeType)

        return Pair(pair.first, pair.second)
    }

    private fun processingStringType(typeValue: String, typeType: String): Pair<String, String> {
        var value = typeValue
        var tyType = typeType

        if (value.startsWith("\"") && value.endsWith("\"")) {
            value = value.substring(1, value.length - 1)

            if (tyType == "") {
                tyType = "string"
            }
        }

        // if match ident "." ident, should have a function call
        if (value.matches(Regex("[a-zA-Z0-9_]+\\.[a-zA-Z0-9_]+"))) {
            tyType = "FunctionCall"
        }

        return Pair(value, tyType)
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
                    TypeType = field.type_()?.text ?: "",
                    TypeValue = field.identifierList()?.text ?: ""
                )
            }.toTypedArray()
    }

    /**
     * !!!IMPORTANT
     * lookup all primaryExpr, then find the method call,
     * examples:
     * 1. http.Status will be Primary Dot IDENTIFIER
     * 2. http.Status() will be Primary Dot arguments
     * 3. http[0] will be Primary Index
     * So, we just look up expression, the find the primaryExpr, will handle all cases
     * expression -> primaryExpr -> PrimaryExpr, then handle [GoFullIdentListener.handlePrimaryExprCtx]
     */
    override fun enterExpression(ctx: GoParser.ExpressionContext?) {
        when (val firstChild = ctx?.getChild(0)) {
            is GoParser.PrimaryExprContext -> {
//                process
                firstChild.getChild(1)?.let { this.handlePrimaryExprCtx(firstChild) }
            }
        }
    }

    private fun handlePrimaryExprCtx(primaryExprCtx: GoParser.PrimaryExprContext) {
        when (val child = primaryExprCtx.getChild(1)) {
            is GoParser.ArgumentsContext -> {
                val codeCall = codeCallFromExprList(primaryExprCtx)
                codeCall.Parameters = parseArguments(child)
                codeCall.Package = wrapTarget(codeCall.NodeName)

                currentFunction.FunctionCalls += codeCall
            }

            else -> {
//                println("${child.javaClass} not implemented ${child.text}")
            }
        }
    }

    /**
     * 1. lookup local vars by [GoFullIdentListener.localVars]
     * 2. lookup imports by [CodeContainer.Imports]
     */
    private fun wrapTarget(nodeName: String): String {
        var sourceNode = nodeName
        if (sourceNode.startsWith("*")) {
            sourceNode = sourceNode.substring(1)
        }

        if (sourceNode.contains(".")) {
            val split = sourceNode.split(".")
            sourceNode = split.first()
        }

        codeContainer.Imports.forEach {
            if (it.Source.endsWith("/${sourceNode}")) {
                return it.Source
            }
        }

        return ""
    }

    private fun parseArguments(child: GoParser.ArgumentsContext): Array<CodeProperty> {
        return child.expressionList()?.expression()?.map {
            val (value, typetype) = processingStringType(it.text, "")
            CodeProperty(TypeValue = value, TypeType = typetype)
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
                        // TerminalNodeImpl => primaryExpr '.' IDENTIFIER
                        val nodeName = nodeNameFromPrimary(child)

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
                println("${child.javaClass} not implemented -> ${child.text}")
                CodeCall(NodeName = child.text)
            }
        }
    }

    private fun nodeNameFromPrimary(child: GoParser.PrimaryExprContext): String {
        val nodeName = when (val first = child.getChild(0)) {
            is GoParser.OperandContext -> {
                first.text
            }

            is GoParser.PrimaryExprContext -> {
                if (first.primaryExpr() != null) {
                    nodeNameFromPrimary(first)
                } else {
                    localVars.getOrDefault(first.text, first.text)
                }
            }

            else -> {
                first.text
            }
        }
        return nodeName
    }


    override fun enterVarDecl(ctx: GoParser.VarDeclContext?) {
        ctx?.varSpec()?.forEach {
            it.identifierList().IDENTIFIER().forEach { terminalNode ->
                localVars[terminalNode.text] = it.type_()?.text ?: ""
            }
        }
    }

    override fun enterShortVarDecl(ctx: GoParser.ShortVarDeclContext?) {
        ctx?.identifierList()?.IDENTIFIER()?.forEach {
            localVars[it.text] = nodeNameFromExpr(ctx)
        }
    }

    private fun nodeNameFromExpr(ctx: GoParser.ShortVarDeclContext): String {
        ctx.expressionList().expression()?.forEach {
            when (val firstChild = it.getChild(0)) {
                is GoParser.PrimaryExprContext -> {
                    return nodeNameFromPrimary(firstChild)
                }
            }
        }

        return ""
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
