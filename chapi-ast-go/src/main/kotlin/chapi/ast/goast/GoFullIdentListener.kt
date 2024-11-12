package chapi.ast.goast

import chapi.ast.antlr.GoParser
import chapi.ast.antlr.GoParser.ExpressionContext
import chapi.ast.antlr.GoParser.PrimaryExprContext
import chapi.domain.core.*
import chapi.infra.Stack
import org.antlr.v4.runtime.RuleContext
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
        currentFunction.LocalVariables = localVars.map { entry ->
            CodeProperty(TypeValue = entry.key, DefaultValue = entry.value, TypeType = entry.value)
        }

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
            val parameterDecl = ctx.receiver().parameters().parameterDecl()
            val text = parameterDecl.getOrNull(0)?.identifierList()?.text ?: ""
            receiverForCall[text] = receiverName
        }

        currentFunction = CodeFunction(
            Name = ctx.IDENTIFIER().text,
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

        // if value starts with & or * should be a pointer
        if (value.startsWith("&") || value.startsWith("*")) {
            tyType = "Pointer"
            value = value.substring(1)
        }

        return Pair(value, tyType)
    }

    override fun exitMethodDecl(ctx: GoParser.MethodDeclContext?) {
        val receiverName = this.getStructNameFromReceiver(ctx?.receiver()?.parameters())
        currentFunction.LocalVariables = localVars.map { entry ->
            CodeProperty(TypeValue = entry.key, DefaultValue = entry.value, TypeType = entry.value)
        }

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
     * expression -> primaryExpr -> PrimaryExpr, then handle [GoFullIdentListener.handlePrimaryExprCall]
     */
    override fun enterExpression(ctx: GoParser.ExpressionContext?) {
        when (val firstChild = ctx?.getChild(0)) {
            is PrimaryExprContext -> {
                firstChild.getChild(1)?.let {
                    val codeCall = this.handlePrimaryExprCall(firstChild)

                    if (blockStack.count() > 0) {
                        lastBlock.FunctionCalls += codeCall
                    } else {
                        currentFunction.FunctionCalls += codeCall
                    }
                }
            }
        }
    }

    private fun handlePrimaryExprCall(primaryExprCtx: PrimaryExprContext): List<CodeCall> {
        return when (val arguments = primaryExprCtx.getChild(1)) {
            is GoParser.ArgumentsContext -> {
                codeCallFromExprList(primaryExprCtx.getChild(0), arguments)
            }

            else -> {
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
            if (localVars.containsKey(value) && localVars[value] != "") {
                return@map CodeProperty(TypeValue = localVars[value]!!, TypeType = typetype)
            }

            /// handle for fmt.print series function, if it.s a fmt.print series function, should return the value
            val isPrintFunc = goPrintFuncs.any { value.split("(").firstOrNull() == it }
            if (it.getChild(0) is PrimaryExprContext && isPrintFunc) {
                val content = value.split("(").lastOrNull()?.removeSuffix(")")
                val params = it.getChild(0).getChild(1) as? GoParser.ArgumentsContext
                if (params != null) {
                    val childElements = parseArguments(params)
                    val elementContent = childElements.joinToString(", ") { it.TypeValue }
                    return@map CodeProperty(TypeValue = elementContent, TypeType = typetype, Parameters = childElements)
                }

                return@map CodeProperty(TypeValue = content ?: "", TypeType = typetype)
            }

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
    private fun codeCallFromExprList(child: ParseTree, arguments: GoParser.ArgumentsContext): List<CodeCall> {
        val calls = mutableListOf<CodeCall>()

        val currentCall = CodeCall(NodeName = child.text).apply {
            Parameters = parseArguments(arguments)
            Package = wrapTarget(NodeName)
        }

        when (child) {
            is PrimaryExprContext -> {
                when (child.getChild(1)) {
                    is TerminalNodeImpl -> {
                        if (child.getChild(0) is PrimaryExprContext && child.childCount > 2) {
                            val primaryCalls = handlePrimaryExprCall(child.getChild(0) as PrimaryExprContext)
                            calls.addAll(primaryCalls)
                        }

                        val functionName = child.getChild(2).text
                        val nodeName = handleForPrimary(child).orEmpty()

                        // if nodeName ends with $.functionName, the functionName should be remove
                        if (nodeName.endsWith(".$functionName")) {
                            currentCall.NodeName = nodeName.substring(0, nodeName.length - functionName.length - 1)
                        } else {
                            currentCall.NodeName = nodeName
                        }

                        currentCall.apply {
                            FunctionName = child.getChild(2).text
                            Package = wrapTarget(nodeName)
                        }
                    }
                }
            }
        }

        calls.add(currentCall)
        return calls
    }

    var goPrintFuncs: List<String> = listOf(
        "fmt.Print",
        "fmt.Printf",
        "fmt.Println",

        "fmt.Sprint",
        "fmt.Sprintf",
        "fmt.Sprintln",

        "fmt.Fprint",
        "fmt.Fprintf",
        "fmt.Fprintln",

        "fmt.Errorf"
    )


    private fun handleForPrimary(child: PrimaryExprContext, isForLocalVar: Boolean = false): String? {
//        val possibleResult = handleForPrimary(first, isForLocalVar).orEmpty()
        if (child.primaryExpr()?.text?.contains(".") == true) {
            /// replace first.text to possibleResult
            /// d.Field -> Dao.Field
            val split = child.primaryExpr().text.split(".")
            val withoutFirst = split.drop(1).joinToString(".")
            // get first part and try to resolve in local var
            val first = split.first()
            if (localVars.containsKey(first)) {
                return "${localVars[first]}.$withoutFirst"
            }
        }

        val nodeName = when (val first = child.getChild(0)) {
            is GoParser.OperandContext -> {
                first.text
            }

            is PrimaryExprContext -> {
                if (first.primaryExpr() != null) {
                    handleForPrimary(first, isForLocalVar).orEmpty()
                } else {
                    val parent = child.parent
                    if (isForLocalVar && first.text == "fmt" && parent.text.startsWith("fmt.")) {
                        if (parent is PrimaryExprContext) {
                            val content = getValueFromPrintf(parent)
                            localVars.getOrDefault(first.text, content)
                        } else {
                            localVars.getOrDefault(first.text, first.text)
                        }
                    } else {
                        localVars.getOrDefault(first.text, first.text)
                    }
                }
            }

            else -> {
                first.text
            }
        }

        if (child.childCount > 1 && child.DOT() != null) {
            val identifier = child.IDENTIFIER()?.text ?: ""
            val fullName = "$nodeName.$identifier"
            if (receiverForCall.containsKey(fullName)) {
                return receiverForCall[fullName]!!
            }
            if (receiverForCall.containsKey(nodeName)) {
                val baseType = receiverForCall[nodeName]!!
                return "$baseType.$identifier"
            }

            return nodeName
        }

        if (receiverForCall.containsKey(nodeName)) {
            return receiverForCall[nodeName]!!
        }

        return nodeName
    }

    private fun getValueFromPrintf(parent: RuleContext): String {
        val child = parent.getChild(1)
        if (child !is GoParser.ArgumentsContext) {
            return child.text.removePrefix("(").removeSuffix(")")
        }

        val first = child.getChild(1)
        if (first is GoParser.ExpressionListContext) {
            return first.getChild(0).text.removePrefix("(").removeSuffix(")")
        }

        return child.text.removePrefix("(").removeSuffix(")")
    }

    override fun enterVarDecl(ctx: GoParser.VarDeclContext?) {
        ctx?.varSpec()?.forEach {
            it.identifierList().IDENTIFIER().forEach { terminalNode ->
                val nodeNameFromExpr = nodeNameFromExpr(it.expressionList(), isForLocalVar = true)
                localVars[terminalNode.text] = nodeNameFromExpr.ifEmpty {
                    it.type_()?.text ?: ""
                }
            }
        }
    }

    override fun enterShortVarDecl(ctx: GoParser.ShortVarDeclContext?) {
        ctx?.identifierList()?.IDENTIFIER()?.forEach {
            localVars[it.text] = nodeNameFromExpr(ctx.expressionList(), isForLocalVar = true)
        }
    }

    private fun nodeNameFromExpr(expressionListContext: GoParser.ExpressionListContext?, isForLocalVar: Boolean): String {
        expressionListContext?.expression()?.forEach {
            when (val firstChild = it.getChild(0)) {
                is PrimaryExprContext -> {
                    return handleForPrimary(firstChild, isForLocalVar).orEmpty()
                }

                else -> {
                    if (firstChild.text == "&") {
                        val second = it.getChild(1)
                        if (second is ExpressionContext) {
                            val expression = second.getChild(0)
                            if (expression is PrimaryExprContext) {
                                val typeDecl = expression.children.first().getChild(0)?.getChild(0)?.getChild(0)
                                if (typeDecl is GoParser.LiteralTypeContext && typeDecl.childCount > 0) {
                                    return typeDecl.getChild(0)!!.text
                                }
                            }
                        }

                        return getValueFromPrintf(it)
                    }

                    return firstChild.text
                }
            }
        }

        return expressionListContext?.text ?: ""
    }

    override fun enterConstDecl(ctx: GoParser.ConstDeclContext?) {
        ctx?.constSpec()?.forEach { constSpecContext ->
            constSpecContext.identifierList().IDENTIFIER().forEach { terminalNode ->
                localVars[terminalNode.text] = constSpecContext.type_()?.text ?: constSpecContext?.expressionList()?.text ?: ""
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
