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
    private var codeContainer: CodeContainer = CodeContainer(
        FullName = fileName,
        Language = "go",
        Kind = ContainerKind.SOURCE_FILE
    )

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
        val packageName = ctx?.packageName()?.identifier()?.IDENTIFIER()?.text ?: ""
        codeContainer.PackageName = packageName
        codeContainer.DeclaredPackage = packageName
    }

    override fun enterImportSpec(ctx: GoParser.ImportSpecContext?) {
        val originSource = ctx!!.importPath().text
        val sourceName = originSource.replace("\"", "")

        val packageAlias = ctx.packageName()?.identifier()?.IDENTIFIER()?.text
        val hasDot = ctx.DOT() != null
        
        // Determine import kind
        val kind = when {
            hasDot -> ImportKind.DOT  // import . "pkg" - imports into current namespace
            packageAlias == "_" -> ImportKind.SIDE_EFFECT  // import _ "pkg" - blank import
            packageAlias != null -> ImportKind.NAMED  // import alias "pkg"
            else -> ImportKind.NAMED  // import "pkg"
        }

        val codeImport = CodeImport(
            Source = sourceName,
            AsName = ctx.DOT()?.text ?: packageAlias ?: "",
            UsageName = listOf(packageAlias ?: sourceName.substringAfterLast('/')),
            Kind = kind,
            PathSegments = sourceName.split("/")
        )
        
        // Add specifier for the imported package
        if (kind == ImportKind.NAMED) {
            val originalName = sourceName.substringAfterLast('/')
            val localName = packageAlias ?: originalName
            codeImport.Specifiers = listOf(ImportSpecifier(
                OriginalName = originalName,
                LocalName = localName
            ))
        }

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
            Parameters = buildParameters(ctx.signature()?.parameters())
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
        val typeDef = typeSpec.typeDef()
        val identifyName = typeDef?.IDENTIFIER()?.text ?: ""
        typeDef?.type_()?.typeLit()?.let {
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
     *
     * New grammar structure:
     * primaryExpr: (operand | conversion | methodExpr) (DOT IDENTIFIER | index | slice_ | typeAssertion | arguments)*
     * So child(0) is operand/conversion/methodExpr, and subsequent children are DOT, IDENTIFIER, arguments, etc.
     */
    override fun enterExpression(ctx: GoParser.ExpressionContext?) {
        when (val firstChild = ctx?.getChild(0)) {
            is PrimaryExprContext -> {
                // Check if there are any suffixes (DOT, arguments, etc.) after the first child
                if (firstChild.childCount > 1) {
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

    /**
     * Handle the new flat primaryExpr grammar structure.
     * 
     * New grammar: primaryExpr: (operand | conversion | methodExpr) (DOT IDENTIFIER | index | slice_ | typeAssertion | arguments)*
     * 
     * Example: d.db.Raw(sql).Rows() becomes a MethodExpr structure:
     * - MethodExprContext (with type=d.db and method=Raw), ArgumentsContext, DOT, IDENTIFIER("Rows"), ArgumentsContext
     * 
     * This should produce two calls:
     * 1. NodeName="Dao.db", FunctionName="Raw", Parameters=["sql"]
     * 2. NodeName="Dao.db", FunctionName="Rows", Parameters=[]
     * 
     * Note: For chained calls, we keep the same base NodeName (the receiver type) for all method calls in the chain.
     */
    private fun handlePrimaryExprCall(primaryExprCtx: PrimaryExprContext): List<CodeCall> {
        val calls = mutableListOf<CodeCall>()
        
        // Build a list of path segments and find argument positions
        // Each segment is either from operand or IDENTIFIER after DOT
        val pathSegments = mutableListOf<String>()
        
        // Track the base path for chained calls - this is the initial receiver before any method calls
        var basePath: String? = null
        
        // Process all children to find function calls (arguments)
        var i = 0
        while (i < primaryExprCtx.childCount) {
            val child = primaryExprCtx.getChild(i)
            
            when {
                // First child - operand/conversion/methodExpr
                i == 0 -> {
                    when (child) {
                        is GoParser.OperandContext -> {
                            // Operand can be literal, operandName (which can be qualifiedIdent), or (expression)
                            pathSegments.add(child.text)
                        }
                        is GoParser.ConversionContext -> pathSegments.add(child.text)
                        is GoParser.MethodExprContext -> {
                            // MethodExpr: type DOT methodName
                            // type could be a TypeName with QualifiedIdent (e.g., d.db)
                            // We need to extract the full path
                            val type = child.type_()
                            val methodName = child.IDENTIFIER()?.text ?: ""
                            
                            // Get all identifiers from the type
                            val typeName = type?.typeName()
                            val qualifiedIdent = typeName?.qualifiedIdent()
                            if (qualifiedIdent != null) {
                                // QualifiedIdent is: IDENTIFIER DOT IDENTIFIER
                                qualifiedIdent.IDENTIFIER().forEach { ident ->
                                    pathSegments.add(ident.text)
                                }
                            } else {
                                // Simple type name
                                val typeText = type?.text ?: ""
                                if (typeText.isNotEmpty()) {
                                    pathSegments.add(typeText)
                                }
                            }
                            
                            if (methodName.isNotEmpty()) {
                                pathSegments.add(methodName)
                            }
                        }
                    }
                }
                // DOT followed by IDENTIFIER
                child is TerminalNodeImpl && child.text == "." -> {
                    // Next child should be IDENTIFIER
                    if (i + 1 < primaryExprCtx.childCount) {
                        val nextChild = primaryExprCtx.getChild(i + 1)
                        if (nextChild is TerminalNodeImpl && nextChild.text != ".") {
                            pathSegments.add(nextChild.text)
                            i++ // Skip the IDENTIFIER we just processed
                        }
                    }
                }
                // Arguments - this is a function call
                child is GoParser.ArgumentsContext -> {
                    if (pathSegments.isNotEmpty()) {
                        val functionName = pathSegments.last()
                        val targetPath = if (pathSegments.size > 1) {
                            pathSegments.dropLast(1).joinToString(".")
                        } else {
                            // When there's only one segment (the function name), 
                            // this is a standalone function call without a receiver
                            ""
                        }
                        
                        // For chained calls, use the base path if we have one
                        val effectivePath = basePath ?: targetPath
                        
                        // Create the call
                        val targetNode = primaryExprCtx.getChild(0)
                        calls.addAll(codeCallFromExprListWithPath(targetNode, child, functionName, effectivePath))
                        
                        // Store the base path for subsequent chained calls
                        if (basePath == null && effectivePath.isNotEmpty()) {
                            basePath = effectivePath
                        }
                        
                        // Clear path segments but keep the base for next call in chain
                        // The next IDENTIFIER after this will be added to continue the chain
                        pathSegments.clear()
                    }
                }
                // Skip index, slice_, typeAssertion for now (they don't create function calls)
                child is GoParser.IndexContext -> {}
                child is GoParser.Slice_Context -> {}
                child is GoParser.TypeAssertionContext -> {}
            }
            i++
        }
        
        return calls
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
    private fun codeCallFromExprListWithPath(child: ParseTree, arguments: GoParser.ArgumentsContext, functionName: String, targetPath: String): List<CodeCall> {
        val calls = mutableListOf<CodeCall>()

        val currentCall = CodeCall(NodeName = targetPath).apply {
            Parameters = parseArguments(arguments)
            FunctionName = functionName
        }

        // Resolve local variables and receivers in the target path
        if (targetPath.isNotEmpty()) {
            val parts = targetPath.split(".")
            val firstPart = parts.first()

            // Check both localVars and receiverForCall
            val resolvedFirst = when {
                localVars.containsKey(firstPart) -> localVars[firstPart]!!
                receiverForCall.containsKey(firstPart) -> receiverForCall[firstPart]!!
                else -> null
            }

            if (resolvedFirst != null) {
                // Replace first part with its resolved type
                val remainingParts = parts.drop(1)
                val resolvedPath = if (remainingParts.isNotEmpty()) {
                    "$resolvedFirst.${remainingParts.joinToString(".")}"
                } else {
                    resolvedFirst
                }
                currentCall.NodeName = resolvedPath
                currentCall.Package = wrapTarget(resolvedPath)
            } else {
                currentCall.Package = wrapTarget(targetPath)
            }
        }

        calls.add(currentCall)
        return calls
    }

    private fun codeCallFromExprList(child: ParseTree, arguments: GoParser.ArgumentsContext, functionName: String = ""): List<CodeCall> {
        val calls = mutableListOf<CodeCall>()

        val currentCall = CodeCall(NodeName = child.text).apply {
            Parameters = parseArguments(arguments)
            Package = wrapTarget(NodeName)
        }

        when (child) {
            is PrimaryExprContext -> {
                // New grammar: child(0) is operand/conversion/methodExpr
                // If we have a functionName passed in, use it
                if (functionName.isNotEmpty()) {
                    val nodeName = handleForPrimary(child).orEmpty()
                    currentCall.NodeName = nodeName
                    currentCall.FunctionName = functionName
                    currentCall.Package = wrapTarget(nodeName)
                } else {
                    // Old logic for compatibility
                    when (child.getChild(1)) {
                        is TerminalNodeImpl -> {
                            if (child.getChild(0) is PrimaryExprContext && child.childCount > 2) {
                                val primaryCalls = handlePrimaryExprCall(child.getChild(0) as PrimaryExprContext)
                                calls.addAll(primaryCalls)
                            }

                            val funcName = child.getChild(2).text
                            val nodeName = handleForPrimary(child).orEmpty()

                            // if nodeName ends with $.functionName, the functionName should be remove
                            if (nodeName.endsWith(".$funcName")) {
                                currentCall.NodeName = nodeName.substring(0, nodeName.length - funcName.length - 1)
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

            is GoParser.OperandContext -> {
                // Direct operand call (e.g., function())
                currentCall.NodeName = child.text
                currentCall.FunctionName = if (functionName.isNotEmpty()) functionName else child.text
                currentCall.Package = wrapTarget(child.text)
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
        // Build the full method call path from the primaryExpr children
        // Structure: (operand | conversion | methodExpr) (DOT IDENTIFIER | ... | arguments)*
        val pathParts = mutableListOf<String>()
        
        for (i in 0 until child.childCount) {
            val c = child.getChild(i)
            when {
                c is GoParser.OperandContext -> pathParts.add(c.text)
                c is GoParser.ConversionContext -> pathParts.add(c.text)
                c is GoParser.MethodExprContext -> {
                    val typeName = c.type_()?.text ?: ""
                    val methodName = c.IDENTIFIER()?.text ?: ""
                    if (typeName.isNotEmpty()) pathParts.add(typeName)
                    if (methodName.isNotEmpty()) pathParts.add(methodName)
                }
                c is TerminalNodeImpl && c.text != "." && c.text != "(" && c.text != ")" -> {
                    // IDENTIFIER after DOT
                    pathParts.add(c.text)
                }
                c is GoParser.ArgumentsContext -> {
                    // Stop at arguments - we've collected the full path
                    break
                }
            }
        }
        
        val fullPath = pathParts.joinToString(".")
        
        // For fmt.Sprintf and similar, extract the value when assigning to local var
        if (isForLocalVar && goPrintFuncs.contains(fullPath)) {
            val args = child.arguments()?.firstOrNull()
            if (args != null) {
                return getValueFromPrintf(child)
            }
        }
        
        // Check if we should resolve a local variable
        val firstPart = pathParts.firstOrNull() ?: ""
        if (firstPart.isNotEmpty() && localVars.containsKey(firstPart) && pathParts.size > 1) {
            // When assigning to a local var, don't append the function name
            // The local var should store the receiver type, not the full method path
            if (isForLocalVar) {
                return localVars[firstPart]!!
            }
            val remainingParts = pathParts.drop(1).joinToString(".")
            return "${localVars[firstPart]}.$remainingParts"
        }

        val nodeName = when (val first = child.getChild(0)) {
            is GoParser.OperandContext -> {
                first.text
            }

            is PrimaryExprContext -> {
                // Recursive case - though this shouldn't happen in new grammar
                handleForPrimary(first, isForLocalVar).orEmpty()
            }
            
            is GoParser.MethodExprContext -> {
                // Handle MethodExprContext for the new grammar
                // MethodExpr: type DOT methodName
                val methodName = first.IDENTIFIER()?.text ?: ""
                val typeName = first.type_()?.text ?: ""
                val fullCall = if (typeName.isNotEmpty()) "$typeName.$methodName" else methodName
                
                // For local variable assignment from fmt.Sprintf, etc., extract the value
                if (isForLocalVar && goPrintFuncs.contains(fullCall)) {
                    // Get the arguments of the print function
                    val args = child.arguments()?.firstOrNull()
                    if (args != null) {
                        return getValueFromPrintf(child)
                    }
                }
                
                localVars.getOrDefault(typeName, typeName)
            }

            else -> {
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

        if (child.childCount > 1 && child.DOT() != null && child.DOT().isNotEmpty()) {
            val identifier = child.IDENTIFIER()?.firstOrNull()?.text ?: ""
            val fullName = "$nodeName.$identifier"
            if (receiverForCall.containsKey(fullName)) {
                return receiverForCall[fullName]!!
            }
            if (receiverForCall.containsKey(nodeName)) {
                val baseType = receiverForCall[nodeName]!!
                // When assigning to a local var, don't append the function name
                // The local var should store the receiver type, not the full method path
                if (isForLocalVar) {
                    return baseType
                }
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
        // In new grammar, arguments can be at different positions
        // Find the ArgumentsContext regardless of position
        var argsContext: GoParser.ArgumentsContext? = null
        for (i in 0 until parent.childCount) {
            val c = parent.getChild(i)
            if (c is GoParser.ArgumentsContext) {
                argsContext = c
                break
            }
        }
        
        if (argsContext == null) {
            // Fallback to old logic
            val child = parent.getChild(1)
            if (child !is GoParser.ArgumentsContext) {
                return child.text.removePrefix("(").removeSuffix(")")
            }
            argsContext = child
        }
        
        // ArgumentsContext: ( expressionList? ... )
        // Child 0 is '(' terminal, child 1 is expressionList if present
        val first = argsContext.getChild(1)
        if (first is GoParser.ExpressionListContext) {
            return first.getChild(0).text.removePrefix("(").removeSuffix(")")
        }

        return argsContext.text.removePrefix("(").removeSuffix(")")
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
