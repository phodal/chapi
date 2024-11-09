package chapi.ast.goast

import chapi.ast.antlr.GoParser
import chapi.domain.core.*
import chapi.infra.Stack
import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.tree.TerminalNodeImpl

/**
 * core logic, please goto [GoFullIdentListener.enterExpression] to see how to use it
 */
class GoFullIdentListener(var fileName: String) : GoAstListener() {
    private var codeContainer: CodeContainer = CodeContainer(FullName = fileName)

    private var defaultNode = CodeDataStruct()
    private var structMap = mutableMapOf<String, CodeDataStruct>()
    private var localVars = mutableMapOf<String, String>()

    /**
     * Only for resolve receiver for method call
     */
    private var receiverForCall = mutableMapOf<String, String>()

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
            UsageName = listOf(ctx.IDENTIFIER()?.text ?: "")
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


    private var blockStack = Stack<CodeFunction>()
    private var lastBlock = CodeFunction(Type = FunctionType.Block, Name = "chapi_block")

    override fun enterBlock(ctx: GoParser.BlockContext?) {
        if (ctx?.parent !is GoParser.StatementContext) return

        lastBlock = CodeFunction(Type = FunctionType.Block, Name = "chapi_block" + "${blockStack.count()}")
        blockStack.push(lastBlock)
    }

    override fun exitBlock(ctx: GoParser.BlockContext?) {
        if (ctx?.parent !is GoParser.StatementContext) return

        val popBlock = blockStack.pop()!!
        if (blockStack.count() > 0) {
            blockStack.peek()!!.InnerFunctions += popBlock
        } else {
            currentFunction.InnerFunctions += popBlock
        }
    }

    override fun enterTypeDecl(ctx: GoParser.TypeDeclContext?) {
        ctx?.typeSpec()?.forEach {
            buildTypeSpec(it)
        }
    }

    override fun enterMethodDecl(ctx: GoParser.MethodDeclContext?) {
        val receiverName = this.getStructNameFromReceiver(ctx!!.receiver()?.parameters())
        if (ctx.receiver() != null) {
            val text = ctx.receiver().parameters().parameterDecl()[0].identifierList().text
            receiverForCall[text] = receiverName
        }

        currentFunction = CodeFunction(
            Name = ctx!!.IDENTIFIER().text,
            MultipleReturns = buildReturnTypeFromSignature(ctx.signature()),
            Parameters = buildParameters(ctx.signature().parameters())
        )
    }

    fun buildParameters(parametersCtx: GoParser.ParametersContext?): List<CodeProperty> {
        return parametersCtx?.parameterDecl()?.map {
            val (ident, typetype) = processingType(it)

            localVars[ident] = typetype
            CodeProperty(
                TypeValue = ident,
                TypeType = typetype
            )
        } ?: listOf()
    }

    private fun processingType(it: GoParser.ParameterDeclContext): Pair<String, String> {
        val typeValue = it.identifierList()?.text ?: ""
        val typeType = it.type_()?.text ?: ""

        return processingStringType(typeValue, typeType)
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
        val identifyName = typeSpec.IDENTIFIER()?.text ?: ""
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

    private fun buildStructFields(structTypeCtx: GoParser.StructTypeContext): List<CodeField> {
        return structTypeCtx.fieldDecl()
            .map { field ->
                CodeField(
                    TypeType = field.type_()?.text ?: "",
                    TypeValue = field.identifierList()?.text ?: ""
                )
            }
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
                firstChild.getChild(1)?.let {
                    val codeCall = this.handlePrimaryExprCtx(firstChild)

                    if (blockStack.count() > 0) {
                        lastBlock.FunctionCalls += codeCall
                    } else {
                        currentFunction.FunctionCalls += codeCall
                    }
                }
            }
        }
    }

    private fun handlePrimaryExprCtx(primaryExprCtx: GoParser.PrimaryExprContext): List<CodeCall> {
        return when (val arguments = primaryExprCtx.getChild(1)) {
            is GoParser.ArgumentsContext -> {
                val codeCall = codeCallFromExprList(primaryExprCtx.getChild(0), arguments)
                listOf(codeCall)
            }

            else -> {
                println("${arguments.javaClass} not implemented ${arguments.text}")
                listOf()
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

    private fun parseArguments(child: GoParser.ArgumentsContext): List<CodeProperty> {
        return child.expressionList()?.expression()?.map {
            val (value, typetype) = processingStringType(it.text, "")
            CodeProperty(TypeValue = value, TypeType = typetype)
        } ?: listOf()
    }

    /**
     * ```antlr
     * primaryExpr:
     * 	operand
     * 	| conversion
     * 	| methodExpr
     * 	| primaryExpr ((DOT IDENTIFIER) | index | slice_ | typeAssertion | arguments);
     * ```
     */
    private fun codeCallFromExprList(child: ParseTree, arguments: GoParser.ArgumentsContext): CodeCall {
        val codeCall = CodeCall(NodeName = child.text)
        codeCall.Parameters = parseArguments(arguments)
        codeCall.Package = wrapTarget(codeCall.NodeName)

        when (child) {
            is GoParser.PrimaryExprContext -> {
                when (child.getChild(1)) {
                    is TerminalNodeImpl -> {
                        // TerminalNodeImpl => primaryExpr '.' IDENTIFIER
                        val nodeName = nodeNameFromPrimary(child)

                        codeCall.NodeName = nodeName
                        codeCall.FunctionName = child.getChild(2).text
                        codeCall.Package = wrapTarget(codeCall.NodeName)
                    }
                }
            }
        }

        return codeCall
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

        if (receiverForCall.containsKey(nodeName)) {
            return receiverForCall[nodeName]!!
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
