package chapi.ast.javaast

import chapi.ast.antlr.JavaParser
import chapi.domain.core.*
import chapi.infra.Stack
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.tree.ParseTree

data class TargetTypePackage(val targetType: String, val packageName: String)
data class JavaTargetType(var targetType: String = "", var callType: CallType = CallType.FUNCTION)

open class JavaFullIdentListener(fileName: String, val classes: List<String>) : JavaAstListener() {
    private var isEnterFunction: Boolean = false
    private var currentAnnotations: MutableList<CodeAnnotation> = mutableListOf()

    private var currentCreatorNode: CodeDataStruct = CodeDataStruct()
    private var isOverrideMethod: Boolean = false
    private var fields: MutableList<CodeField> = mutableListOf()
    private var methodCalls: MutableList<CodeCall> = mutableListOf()
    private var methodMap = mutableMapOf<String, CodeFunction>()
    private var creatorMethodMap = mutableMapOf<String, CodeFunction>()
    private var localVars = mutableMapOf<String, String>()

    private var fieldsMap = mutableMapOf<String, String>()
    private var formalParameters = mutableMapOf<String, String>()
    private var currentClz: String = ""

    private var currentClzExtend: String = ""
    private var hasEnterClass: Boolean = false
    private var classNodes: MutableList<CodeDataStruct> = mutableListOf()

    private var innerNode: CodeDataStruct = CodeDataStruct()
    private var classNodeStack = Stack<CodeDataStruct>()

    private var methodQueue: MutableList<CodeFunction> = mutableListOf()

    private var imports: MutableList<CodeImport> = mutableListOf()
    // Index for fast import lookup by class name (O(1) instead of O(n))
    private var importsByClassName: MutableMap<String, CodeImport> = mutableMapOf()
    private var importsByFullSource: MutableMap<String, CodeImport> = mutableMapOf()

    private var lastNode = CodeDataStruct()
    private var currentNode = CodeDataStruct()
    private var currentFunction = CodeFunction(IsConstructor = false)
    private var currentType: DataStructType = DataStructType.EMPTY

    private var codeContainer: CodeContainer =
        CodeContainer(
            FullName = fileName,
            Language = "java",
            Kind = ContainerKind.SOURCE_FILE
        )

    override fun enterPackageDeclaration(ctx: JavaParser.PackageDeclarationContext?) {
        val packageName = ctx!!.qualifiedName()!!.text
        codeContainer.PackageName = packageName
        codeContainer.DeclaredPackage = packageName
    }

    override fun enterImportDeclaration(ctx: JavaParser.ImportDeclarationContext?) {
        val fullSource = ctx!!.qualifiedName()!!.text
        val isStatic = ctx.STATIC() != null
        val isWildcard = ctx.MUL() != null
        
        var codeImport = CodeImport(Source = fullSource)

        if (isStatic && isWildcard) {
            // Static wildcard import: import static pkg.Class.*;
            codeImport.Kind = ImportKind.STATIC
            codeImport.Scope = "static"
            codeImport.UsageName = listOf("*")
            // Source remains as fullSource for static wildcard imports
        } else if (isStatic) {
            // Single static member import: import static pkg.Class.member;
            val sourceSplit = fullSource.split(".")
            codeImport.UsageName = listOf(sourceSplit.last())
            codeImport.Source = sourceSplit.dropLast(1).joinToString(".")
            codeImport.Kind = ImportKind.STATIC
            codeImport.Scope = "static"
            codeImport.Specifiers = listOf(ImportSpecifier(
                OriginalName = sourceSplit.last(),
                LocalName = sourceSplit.last()
            ))
        } else if (isWildcard) {
            codeImport.Kind = ImportKind.WILDCARD
            codeImport.UsageName = listOf("*")
        } else {
            // Named import: import pkg.Class;
            codeImport.Kind = ImportKind.NAMED
            val className = fullSource.substringAfterLast('.')
            codeImport.UsageName = listOf(className)
            codeImport.Specifiers = listOf(ImportSpecifier(
                OriginalName = className,
                LocalName = className
            ))
        }
        
        codeImport.PathSegments = fullSource.split(".")

        imports.add(codeImport)
        codeContainer.Imports += codeImport
        
        // Build import indexes for fast lookup
        val className = fullSource.substringAfterLast('.')
        importsByClassName[className] = codeImport
        importsByFullSource[fullSource] = codeImport
        // Also index by source for static imports
        if (isStatic) {
            importsByFullSource[codeImport.Source] = codeImport
        }
    }

    override fun enterClassDeclaration(ctx: JavaParser.ClassDeclarationContext?) {
        val isInnerNode = currentNode.NodeName != ""
        if (isInnerNode) {
            updateStructMethods()

            innerNode = CodeDataStruct(Position = buildPosition(ctx!!))

            currentType = DataStructType.INNER_STRUCTURES
            innerNode.Type = currentType

            buildClassExtension(ctx, innerNode)

            classNodeStack.push(innerNode)

            lastNode.InnerStructures += innerNode
            lastNode = innerNode
        } else {
            currentType = DataStructType.CLASS
            currentNode.Type = currentType
            currentNode.Position = buildPosition(ctx!!)

            hasEnterClass = true
            buildClassExtension(ctx, currentNode)

            lastNode = currentNode
            classNodeStack.push(currentNode)
        }
    }

    private fun updateStructMethods() {
        lastNode.setMethodsFromMap(methodMap)
        methodMap = mutableMapOf()
    }

    private fun buildClassExtension(ctx: JavaParser.ClassDeclarationContext?, classNode: CodeDataStruct) {
        classNode.Package = codeContainer.PackageName
        classNode.FilePath = codeContainer.FullName

        if (ctx!!.identifier() != null) {
            currentClz = ctx.identifier().text
            classNode.NodeName = currentClz
        }

        currentClzExtend = ""
        if (ctx.EXTENDS() != null) {
            currentClzExtend = ctx.typeType().text
            classNode.Extend = this.buildExtend(currentClzExtend)
        }

        if (ctx.IMPLEMENTS() != null) {
            classNode.Implements = buildImplements(ctx)
        }
    }

    open fun buildImplements(ctx: JavaParser.ClassDeclarationContext): List<String> {
        return ctx.typeList().map {
            var target = this.warpTargetFullType(it.text).targetType
            if (target == "") {
                target = it.text
            }

            target
        }
    }

    override fun exitClassDeclaration(ctx: JavaParser.ClassDeclarationContext?) {
        classNodeStack.pop()
        if (classNodeStack.count() == 0) {
            this.exitBody()
        } else {
            updateStructMethods()
        }
    }

    private fun exitBody() {
        currentNode.Fields = fields.toList()
        currentNode.setMethodsFromMap(methodMap)

        classNodes.add(currentNode)
        initClass()
    }

    private fun initClass() {
        currentClz = ""
        currentClzExtend = ""
        currentFunction = CodeFunction(IsConstructor = false)

        methodMap = mutableMapOf()
        methodCalls = mutableListOf()
        fields = mutableListOf()
        isOverrideMethod = false
    }


    override fun enterInterfaceMethodDeclaration(ctx: JavaParser.InterfaceMethodDeclarationContext?) {
        val name = ctx!!.interfaceCommonBodyDeclaration().identifier().text
        val typeType = ctx.interfaceCommonBodyDeclaration().typeTypeOrVoid().text

        val codePosition = CodePosition(
            StartLine = ctx.start.line,
            StartLinePosition = ctx.start.charPositionInLine,
            StopLine = ctx.stop.line,
            StopLinePosition = ctx.stop.charPositionInLine
        )

        val codeFunction = CodeFunction(
            Name = name,
            ReturnType = typeType,
            Position = codePosition,
            IsConstructor = false
        )

        val mayModifierCtx = ctx.parent.parent.getChild(0)
        if (mayModifierCtx::class.simpleName == "ModifierContext") {
            codeFunction.Annotations = this.buildAnnotationForMethod(mayModifierCtx as JavaParser.ModifierContext)
        }

        this.updateCodeFunction(codeFunction)
    }

    override fun enterMethodDeclaration(ctx: JavaParser.MethodDeclarationContext?) {
        val name = ctx!!.identifier().text
        val typeType = ctx.typeTypeOrVoid().text

        this.isEnterFunction = true

        val codeFunction = CodeFunction(
            Name = name,
            ReturnType = typeType,
            Position = buildPosition(ctx),
            IsConstructor = false,
            BodyHash = ctx.methodBody().text.hashCode()
        )

        codeFunction.Annotations = this.currentAnnotations

        val params = ctx.formalParameters()
        if (params != null) {
            if (params.getChild(0) == null || params.text == "()" || params.getChild(0) == null) {
                this.updateCodeFunction(codeFunction)
                return
            }

            codeFunction.Parameters = this.buildMethodParameters(params)
        }

        this.updateCodeFunction(codeFunction)
    }

    private fun buildMethodParameters(params: JavaParser.FormalParametersContext?): List<CodeProperty> {
        if (params == null) return emptyList()
        
        val methodParams = mutableListOf<CodeProperty>()
        
        // New grammar structure: formalParameters can contain formalParameter directly
        // and/or formalParameterList
        val formalParam = params.formalParameter()
        val formalParamLists = params.formalParameterList()
        
        // Process direct formalParameter (if any)
        if (formalParam != null) {
            val varDeclId = formalParam.variableDeclaratorId()
            if (varDeclId != null) {
                val paramType = formalParam.typeType().text
                val paramValue = varDeclId.identifier().text
                localVars[paramValue] = paramType
                
                val parameter = CodeProperty(
                    TypeValue = paramValue, 
                    TypeType = paramType,
                    TypeRef = JavaTypeRefBuilder.build(formalParam.typeType())
                )
                methodParams.add(parameter)
            }
        }
        
        // Process formalParameterList (if any)
        for (paramList in formalParamLists) {
            for (param in paramList.formalParameter()) {
                val varDeclId = param.variableDeclaratorId()
                if (varDeclId != null) {
                    val paramType = param.typeType().text
                    val paramValue = varDeclId.identifier().text
                    localVars[paramValue] = paramType
                    
                    val parameter = CodeProperty(
                        TypeValue = paramValue, 
                        TypeType = paramType,
                        TypeRef = JavaTypeRefBuilder.build(param.typeType())
                    )
                    methodParams.add(parameter)
                }
            }
        }

        return methodParams
    }

    override fun exitMethodDeclaration(ctx: JavaParser.MethodDeclarationContext?) {
        this.isEnterFunction = false
        this.currentAnnotations = mutableListOf()
        if (localVars.isNotEmpty()) {
            addLocalVarsToFunction()
        }
    }

    private fun addLocalVarsToFunction() {
        val currentMethodName = getMethodMapName(currentFunction)
        val mapFunc = methodMap[currentMethodName]
        if (mapFunc != null) {
            mapFunc.addVarsFromMap(localVars)
            methodMap[currentMethodName] = mapFunc
        }
    }

    override fun enterMethodCall(ctx: JavaParser.MethodCallContext?) {
        val codeCall = CodeCall()

        val targetCtx = ctx!!.parent.getChild(0)
        var targetType = this.parseTargetType(targetCtx.text)

        if (targetCtx.getChild(0) != null) {
            val currentCtx = targetCtx.getChild(0)
            when (currentCtx::class.simpleName) {
                "MethodCallContext" -> {
                    targetType = (currentCtx as JavaParser.MethodCallContext).identifier().text
                }
            }
        }

        val callee = ctx.getChild(0).text

        buildMethodCallLocation(codeCall, ctx)
        buildMethodCallMethodInfo(codeCall, callee, targetType, ctx)
        buildMethodCallParameters(codeCall, ctx)

        sendResultToMethodCallMap(codeCall)
    }

    private fun buildMethodCallParameters(codeCall: CodeCall, ctx: JavaParser.MethodCallContext) {
        // Get arguments from the method call
        val arguments = ctx.arguments()
        if (arguments != null) {
            val exprList = arguments.expressionList()
            codeCall.Parameters = exprList?.expression()?.map {
                CodeProperty(TypeType = "", TypeValue = it.text)
            } ?: listOf()
        }
    }

    private fun buildMethodCallLocation(codeCall: CodeCall, ctx: JavaParser.MethodCallContext) {
        codeCall.Position = buildPosition(ctx)
    }

    private fun sendResultToMethodCallMap(codeCall: CodeCall) {
        methodCalls.add(codeCall)

        val currentMethodName = getMethodMapName(currentFunction)
        val method = methodMap[currentMethodName]
        if (method != null) {
            method.FunctionCalls += codeCall
            methodMap[currentMethodName] = method
        }
    }

    private fun buildMethodCallMethodInfo(
        codeCall: CodeCall,
        callee: String = "",
        targetType: String?,
        ctx: JavaParser.MethodCallContext
    ) {
        var packageName = codeContainer.PackageName
        var methodName = callee

        var targetTypeStr = targetType
        val wrapTargetType = this.warpTargetFullType(targetTypeStr)
        var callType = wrapTargetType.callType
        val fullType = wrapTargetType.targetType

        if (targetTypeStr == "super" || callee == "super") {
            callType = CallType.SUPER
            targetTypeStr = currentClzExtend
        }
        codeCall.Type = callType

        if (fullType != "") {
            packageName = removeTarget(fullType)
            methodName = callee
        } else {
            val targetTypePackage = this.handleEmptyFullType(ctx, targetTypeStr!!, methodName, packageName)
            if (targetTypePackage.packageName != "") {
                packageName = targetTypePackage.packageName
            }
            if (targetTypePackage.targetType != "") {
                targetTypeStr = targetTypePackage.targetType
            }
        }

        // 处理链试调用
        if (this.isChainCall(targetTypeStr)) {
            val split = targetTypeStr!!.split(".")
            targetTypeStr = parseTargetType(split[0])

            val splitQuote = targetTypeStr!!.split("(")
            targetTypeStr = splitQuote[0]
        }

        codeCall.Package = packageName
        codeCall.FunctionName = methodName
        codeCall.NodeName = targetTypeStr ?: ""
    }

    private fun handleEmptyFullType(
        ctx: JavaParser.MethodCallContext,
        targetTypeObj: String,
        methodName: String,
        packageName: String
    ): TargetTypePackage {
        var pkgName = packageName
        var target: String = targetTypeObj

        if (ctx.text == target) {
            var clz: String = currentClz
            // 处理 static 方法，如 now()
            for (imp in imports) {
                if (imp.Source.endsWith(".$methodName")) {
                    pkgName = imp.Source
                    clz = target
                } else if (imp.UsageName.contains(methodName)) {
                    val splitImp = imp.Source.split(".")
                    clz = splitImp.last()

                    val maybePackageName = splitImp.dropLast(1).joinToString(".")
                    pkgName = maybePackageName
                }
            }

            target = clz
        } else {
            if (target.contains("this.")) {
                val newType = target.replace("this.", "")
                for (field in fields) {
                    if (field.TypeValue == newType) {
                        target = field.TypeType
                    }
                }
            }
        }

        return TargetTypePackage(
            targetType = target,
            packageName = pkgName
        )
    }

    private fun removeTarget(fullType: String): String {
        val split = fullType.split(".")
        return split.dropLast(1).joinToString(".")
    }

    private fun parseTargetType(target: String?): String? {
        var targetType = target

        val fieldType = fieldsMap[targetType]
        val localVarType = localVars[targetType]
        val formalType = formalParameters[targetType]

        when {
            fieldType != null && fieldType != "" -> {
                targetType = fieldType
            }

            formalType != null && formalType != "" -> {
                targetType = formalType
            }

            localVarType != null && localVarType != "" -> {
                targetType = localVarType
            }
        }

        return targetType
    }

    private fun updateCodeFunction(codeFunction: CodeFunction) {
        if (currentType == DataStructType.CREATOR_CLASS) {
            creatorMethodMap[getMethodMapName(codeFunction)] = codeFunction
        } else {
            currentFunction = codeFunction
            methodQueue.add(currentFunction)
            methodMap[getMethodMapName(codeFunction)] = codeFunction
        }
    }

    private fun getMethodMapName(method: CodeFunction): String {
        var name = method.Name
        if (name != "" && methodQueue.size > 1) {
            name = methodQueue[methodQueue.size - 1].Name
        }

        return "${codeContainer.PackageName}.${currentClz}.${name}:${method.Position.StartLine}"
    }

    private fun buildExtend(extendName: String): String {
        var extend = extendName
        val target: String = this.warpTargetFullType(extendName).targetType
        if (target != "") {
            extend = target
        }
        return extend
    }

    private fun warpTargetFullType(targetType: String?): JavaTargetType {
        // first, parse from local type
        val callType: CallType = CallType.FUNCTION
        if (currentClz == targetType) {
            return JavaTargetType(
                targetType = "${codeContainer.PackageName}.${targetType}",
                callType = CallType.SELF
            )
        }

        // second, parse from import using index (O(1) lookup)
        val pureTargetType = buildPureTargetType(targetType)
        if (pureTargetType.isNotEmpty()) {
            // Fast lookup using index
            val importByClassName = importsByClassName[pureTargetType]
            if (importByClassName != null) {
                val isStatic = importByClassName.UsageName.isNotEmpty() && importByClassName.UsageName.contains(pureTargetType)
                return JavaTargetType(
                    targetType = importByClassName.Source,
                    callType = if (isStatic) CallType.STATIC else CallType.CHAIN
                )
            }
        }

        // third, parse in same package
        for (clz in classes) {
            if (clz.endsWith(".$pureTargetType")) {
                return JavaTargetType(
                    targetType = clz,
                    callType = CallType.SAME_PACKAGE
                )
            }
        }

        // others, may be from parent
        if (pureTargetType == "super" || pureTargetType == "this") {
            val importByExtend = importsByClassName[currentClzExtend]
            if (importByExtend != null) {
                return JavaTargetType(
                    targetType = importByExtend.Source,
                    callType = CallType.SUPER
                )
            }
        }

        // todo: add identMap
        return JavaTargetType(callType = callType)
    }

    // remove array from types
    private fun buildPureTargetType(targetType: String?): String {
        val split = targetType!!.split(".")
        // remove for array
        val pureTargetType = split[0].replace("[", "").replace("]", "")
        return pureTargetType
    }

    override fun exitFormalParameter(ctx: JavaParser.FormalParameterContext?) {
        if (ctx == null) return
        val varDeclId = ctx.variableDeclaratorId()
        if (varDeclId != null) {
            val paramKey = varDeclId.identifier().text
            formalParameters[paramKey] = ctx.typeType().text
        }
    }

    override fun enterFieldDeclaration(ctx: JavaParser.FieldDeclarationContext?) {
        super.enterFieldDeclaration(ctx)

        val declarators = ctx!!.variableDeclarators()
        val typeType = declarators.parent.getChild(0)

        for (declCtx in declarators.variableDeclarator()) {
            var typeCtx: JavaParser.ClassOrInterfaceTypeContext? = buildTypeCtxByIndex(typeType, null, 0)
            if (typeType!!.childCount > 1) {
                typeCtx = buildTypeCtxByIndex(typeType, typeCtx, 1)
            }

            if (typeCtx == null) {
                continue
            }

            // Get the type identifier from classOrInterfaceType
            // classOrInterfaceType now wraps classType, which has typeIdentifier
            val classType = typeCtx.classType()
            val typeTypeText = if (classType != null && classType.typeIdentifier().isNotEmpty()) {
                classType.typeIdentifier(0).text
            } else {
                typeCtx.text
            }
            val typeKey = declCtx.variableDeclaratorId().identifier().text
            val typeValue = declCtx.variableInitializer()?.text ?: ""

            fieldsMap[typeKey] = typeTypeText

            // Build TypeRef from the field's typeType context
            val fieldTypeCtx = (typeType as? JavaParser.TypeTypeContext)
            val typeRef = fieldTypeCtx?.let { JavaTypeRefBuilder.build(it) }
                ?: JavaTypeRefBuilder.buildFromString(typeTypeText)
            
            val field = CodeField(
                typeTypeText,
                typeValue,
                typeKey,
                Modifiers = listOf(),
                Annotations = this.currentAnnotations.toList(),
                TypeRef = typeRef
            )
            fields.add(field)

            buildFieldCall(typeTypeText, ctx)
        }

        this.currentAnnotations = mutableListOf()
    }

    private fun buildFieldCall(typeType: String, ctx: JavaParser.FieldDeclarationContext) {
        val target = warpTargetFullType(typeType).targetType
        if (target != "") {
            val position = buildPosition(ctx as ParserRuleContext)
            val methodCall = CodeCall(
                Package = removeTarget(target),
                Type = CallType.FIELD,
                NodeName = typeType,
                Position = position
            )

            currentNode.FunctionCalls += methodCall
        }
    }

    private fun buildTypeCtxByIndex(
        typeType: ParseTree,
        typeCtx: JavaParser.ClassOrInterfaceTypeContext?,
        index: Int
    ): JavaParser.ClassOrInterfaceTypeContext? {
        var newTypeCtx = typeCtx
        val child = typeType.getChild(index)
        when (child::class.simpleName) {
            "ClassOrInterfaceTypeContext" -> {
                newTypeCtx = child as JavaParser.ClassOrInterfaceTypeContext
            }
        }

        return newTypeCtx
    }

    override fun enterInterfaceDeclaration(ctx: JavaParser.InterfaceDeclarationContext?) {
        hasEnterClass = true
        currentType = DataStructType.INTERFACE
        currentNode.NodeName = ctx!!.identifier().text
        currentNode.Type = DataStructType.INTERFACE
        currentNode.Package = codeContainer.PackageName

        if (ctx.EXTENDS() != null) {
            var extend = ""
            for (typeContext in ctx.typeList()) {
                extend = buildExtend(typeContext.text)
            }

            currentNode.Extend = extend
        }
    }

    override fun exitInterfaceDeclaration(ctx: JavaParser.InterfaceDeclarationContext?) {
        this.exitBody()
    }

    override fun enterAnnotation(ctx: JavaParser.AnnotationContext?) {
        val annotationName = annotationName(ctx!!)
        if (annotationName.isEmpty()) {
            return
        }

        isOverrideMethod = annotationName == "Override"

        if (!hasEnterClass) {
            val annotation = this.buildAnnotation(ctx)

            if (currentType == DataStructType.CREATOR_CLASS) {
                currentNode.Annotations += annotation
            } else {
                currentNode.Annotations += annotation
            }
        } else {
            currentAnnotations.add(this.buildAnnotation(ctx))
        }
    }

    override fun enterLocalVariableDeclaration(ctx: JavaParser.LocalVariableDeclarationContext?) {
        val typ = getTypeType(ctx!!)
        // variableDeclarators,variableDeclarator, variableDeclaratorId, identifier
        ctx.getChild(1)?.getChild(0)?.getChild(0)?.let {
            val variableName = it.text
            localVars[variableName] = typ
        }
    }

    private fun getTypeType(ctx: JavaParser.LocalVariableDeclarationContext?): String {
        return when (val child = ctx!!.getChild(0)) {
            is JavaParser.VariableModifierContext -> {
                child.getChild(1)?.text ?: ""
            }

            is JavaParser.TypeTypeContext -> child.text
            else -> ""
        }
    }

    // Handle method reference expressions (::)
    override fun enterMethodReferenceExpression(ctx: JavaParser.MethodReferenceExpressionContext?) {
        if (ctx == null) return
        
        // Get the identifier for the method name (if it's not a constructor reference)
        val identifier = ctx.identifier()
        if (identifier == null && ctx.NEW() == null) return
        
        val methodName = identifier?.text ?: "new"
        
        // Get the target type (left side of ::)
        val expression = ctx.expression()
        val typeType = ctx.typeType()
        val classType = ctx.classType()
        
        val targetText = when {
            expression != null -> expression.text
            typeType != null -> typeType.text
            classType != null -> classType.text
            else -> return
        }
        
        val targetType = parseTargetType(targetText)
        val fullType = warpTargetFullType(targetType).targetType
        val position = buildPosition(ctx)
        
        val codeCall = CodeCall(
            Package = removeTarget(fullType),
            Type = CallType.LAMBDA,
            NodeName = targetType ?: "",
            FunctionName = methodName,
            Position = position
        )

        sendResultToMethodCallMap(codeCall)
    }

    override fun enterConstructorDeclaration(ctx: JavaParser.ConstructorDeclarationContext?) {
        val name = ctx!!.identifier().text
        val position = buildPosition(ctx)

        val codeFunction = CodeFunction(
            Name = name,
            ReturnType = "",
            Override = isOverrideMethod,
            Annotations = currentFunction.Annotations,
            Position = position,
            IsConstructor = true
        )
        val paramCtx = ctx.formalParameters()
        if (paramCtx != null) {
            codeFunction.Parameters = buildMethodParameters(ctx.formalParameters())
        }

        this.updateCodeFunction(codeFunction)
    }

    override fun enterCreator(ctx: JavaParser.CreatorContext?) {
        val variableName = ctx!!.getParent().getParent().getChild(0).text
        val allIdentifier = ctx.createdName().identifier()
        for (identifier in allIdentifier!!) {
            val createdName = identifier.text
            localVars[variableName] = createdName

//            this.buildCreatedCall(createdName, ctx)
            // todo: buildCreatedCall

            // todo: handle enter class in parallel
            if (currentFunction.Name == "") {
                return
            }

            if (ctx.classCreatorRest() == null) {
                return
            }

            if (ctx.classCreatorRest().classBody() == null) {
                return
            }

            val text = ctx.createdName().text
            currentType = DataStructType.CREATOR_CLASS
            val creatorNode = CodeDataStruct(
                NodeName = text,
                Type = currentType,
                Package = codeContainer.PackageName,
                Position = buildPosition(ctx)
            )

            currentCreatorNode = creatorNode
        }
    }

    override fun exitCreator(ctx: JavaParser.CreatorContext?) {
        if (currentCreatorNode.NodeName == "") {
            return
        }

        addToCreatorNodeMethod()
        addToParentClassMethodInner()

    }

    private fun addToCreatorNodeMethod() {
        currentCreatorNode.Fields = fields
        currentCreatorNode.setMethodsFromMap(creatorMethodMap)
        creatorMethodMap = mutableMapOf()

        if (classNodeStack.peek() != null) {
            currentType = classNodeStack.peek()!!.Type
        }
    }

    private fun addToParentClassMethodInner() {
        val currentNodeMethodName = getMethodMapName(currentFunction)
        val method = methodMap[currentNodeMethodName]
        if (method != null) {
            method.InnerStructures += currentCreatorNode
            methodMap[currentNodeMethodName] = method
        }
    }

    fun getNodeInfo(): CodeContainer {
        codeContainer.DataStructures = classNodes
        return codeContainer
    }

    override fun enterEnumDeclaration(ctx: JavaParser.EnumDeclarationContext?) {
        currentType = DataStructType.ENUM
        currentNode.Type = currentType
        currentNode.Position = buildPosition(ctx!!)

        hasEnterClass = true
        buildEnumExtension(ctx, currentNode)

        lastNode = currentNode
        classNodeStack.push(currentNode)
    }

    override fun exitEnumDeclaration(ctx: JavaParser.EnumDeclarationContext?) {
        classNodeStack.pop()
        if (classNodeStack.count() == 0) {
            this.exitBody()
        } else {
            updateStructMethods()
        }
    }

    private fun buildEnumExtension(ctx: JavaParser.EnumDeclarationContext?, classNode: CodeDataStruct) {
        classNode.Package = codeContainer.PackageName
        classNode.FilePath = codeContainer.FullName

        if (ctx!!.identifier() != null) {
            currentClz = ctx.identifier().text
            classNode.NodeName = currentClz
        }

        currentClzExtend = ""

        if (ctx.IMPLEMENTS() != null) {
            classNode.Implements = buildEnumImplements(ctx)
        }
    }

    open fun buildEnumImplements(ctx: JavaParser.EnumDeclarationContext): List<String> {
        val type = ctx.typeList()
        var target = this.warpTargetFullType(type.text).targetType
        if (target == "") {
            target = type.text
        }
        return listOf(target)
    }
}
