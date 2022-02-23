package chapi.ast.javaast

import chapi.ast.antlr.JavaParser
import chapi.domain.core.*
import chapi.infra.Stack
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.tree.ParseTree

data class TargetTypePackage(val targetType: String, val packageName: String) {}
data class JavaTargetType(var targetType: String = "", var callType: String = "") {}

open class JavaFullIdentListener(
    fileName: String,
    val classes: Array<String>
) : JavaAstListener() {
    private var currentCreatorNode: CodeDataStruct =
        CodeDataStruct()
    private var isOverrideMethod: Boolean = false
    private var fields = arrayOf<CodeField>()
    private var methodCalls = arrayOf<CodeCall>()
    private var methodMap = mutableMapOf<String, CodeFunction>()
    private var creatorMethodMap = mutableMapOf<String, CodeFunction>()
    private var localVars = mutableMapOf<String, String>()

    private var fieldsMap = mutableMapOf<String, String>()
    private var formalParameters = mutableMapOf<String, String>()
    private var currentClz: String = ""

    private var currentClzExtend: String = ""
    private var hasEnterClass: Boolean = false
    private var classNodes: Array<CodeDataStruct> = arrayOf()

    private var innerNode: CodeDataStruct =
        CodeDataStruct()
    private var classNodeStack =
        Stack<CodeDataStruct>()

    private var methodQueue: Array<CodeFunction> = arrayOf()

    private var imports: Array<CodeImport> = arrayOf()

    private var lastNode = CodeDataStruct()
    private var currentNode = CodeDataStruct()
    private var currentFunction = CodeFunction(IsConstructor = false)
    private var currentType: DataStructType = DataStructType.EMPTY

    private var codeContainer: CodeContainer =
        CodeContainer(FullName = fileName)

    override fun enterPackageDeclaration(ctx: JavaParser.PackageDeclarationContext?) {
        codeContainer.PackageName = ctx!!.qualifiedName()!!.text
    }

    override fun enterImportDeclaration(ctx: JavaParser.ImportDeclarationContext?) {
        val codeImport = CodeImport(Source = ctx!!.qualifiedName()!!.text)
        imports += codeImport

        codeContainer.Imports += codeImport
    }

    override fun enterClassDeclaration(ctx: JavaParser.ClassDeclarationContext?) {
        val isInnerNode = currentNode.NodeName != ""
        if (isInnerNode) {
            updateStructMethods()

            innerNode = CodeDataStruct()

            currentType = DataStructType.INNERSTRUCTURES
            innerNode.Type = currentType

            buildClassExtension(ctx, innerNode)

            classNodeStack.push(innerNode)

            lastNode.InnerStructures += innerNode
            lastNode = innerNode
        } else {
            currentType = DataStructType.CLASS
            currentNode.Type = currentType

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

        if (ctx!!.IDENTIFIER() != null) {
            currentClz = ctx.IDENTIFIER().text
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

    open fun buildImplements(ctx: JavaParser.ClassDeclarationContext): Array<String> {
        var implements = arrayOf<String>()
        for (_type in ctx.typeList().typeType()) {
            var target = this.warpTargetFullType(_type.text).targetType
            if (target == "") {
                target = _type.text
            }
            implements += target
        }
        return implements
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
        currentNode.Fields = fields
        currentNode.setMethodsFromMap(methodMap)

        classNodes += currentNode
        initClass()
    }

    private fun initClass() {
        currentClz = ""
        currentClzExtend = ""
        currentFunction = CodeFunction(IsConstructor = false)

        methodMap = mutableMapOf<String, CodeFunction>()
        methodCalls = arrayOf<CodeCall>()
        fields = arrayOf<CodeField>()
        isOverrideMethod = false
    }


    override fun enterInterfaceMethodDeclaration(ctx: JavaParser.InterfaceMethodDeclarationContext?) {
        val name = ctx!!.IDENTIFIER().text
        val typeType = ctx.typeTypeOrVoid().text

        val codePosition = CodePosition(
            StartLine = ctx.start.line,
            StartLinePosition = ctx.IDENTIFIER().symbol.startIndex,
            StopLine = ctx.stop.line,
            StopLinePosition = ctx.IDENTIFIER().symbol.stopIndex
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
        val name = ctx!!.IDENTIFIER().text
        val typeType = ctx.typeTypeOrVoid().text

        var codeFunction = CodeFunction(
            Name = name,
            ReturnType = typeType,
            Position = buildPosition(ctx),
            IsConstructor = false
        )

        val mayModifierCtx = ctx.parent.parent.getChild(0)
        if (mayModifierCtx::class.simpleName == "ModifierContext") {
            codeFunction.Annotations = this.buildAnnotationForMethod(mayModifierCtx as JavaParser.ModifierContext)
        }

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

    private fun buildMethodParameters(params: JavaParser.FormalParametersContext?): Array<CodeProperty> {
        var methodParams = arrayOf<CodeProperty>()
        if (params!!.getChild(1)::class.simpleName != "FormalParameterListContext") {
            return methodParams
        }

        val parameterList = params.getChild(1) as JavaParser.FormalParameterListContext
        for (param in parameterList.formalParameter()) {
            val paramCtx = param as JavaParser.FormalParameterContext
            val paramType = paramCtx.typeType().text
            val paramValue = paramCtx.variableDeclaratorId().IDENTIFIER().text
            localVars[paramValue] = paramType

            val parameter =
                CodeProperty(TypeValue = paramValue, TypeType = paramType)

            methodParams += parameter
        }

        return methodParams
    }

    override fun exitMethodDeclaration(ctx: JavaParser.MethodDeclarationContext?) {
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
                    targetType = (currentCtx as JavaParser.MethodCallContext).IDENTIFIER().text
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
        if (ctx.expressionList() != null) {
            var parameters = arrayOf<CodeProperty>()
            for (exprCtx in ctx.expressionList().expression()) {
                val parameter = CodeProperty(TypeType = "", TypeValue = exprCtx.text)
                parameters += parameter
            }

            codeCall.Parameters = parameters
        }
    }

    private fun buildMethodCallLocation(codeCall: CodeCall, ctx: JavaParser.MethodCallContext) {
        codeCall.Position = buildPosition(ctx)
    }

    private fun sendResultToMethodCallMap(codeCall: CodeCall) {
        methodCalls += codeCall
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
        _targetType: String?,
        ctx: JavaParser.MethodCallContext
    ) {
        var packageName = codeContainer.PackageName
        var methodName = callee

        var targetTypeStr = _targetType
        var targetType = this.warpTargetFullType(targetTypeStr)
        var callType = targetType.callType
        var fullType = targetType.targetType

        if (targetTypeStr == "super" || callee == "super") {
            callType = "super"
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
                    clz = ""
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
        if (currentType == DataStructType.CREATORCLASS) {
            creatorMethodMap[getMethodMapName(codeFunction)] = codeFunction
        } else {
            currentFunction = codeFunction
            methodQueue += currentFunction
            methodMap[getMethodMapName(codeFunction)] = codeFunction
        }
    }

    private fun getMethodMapName(method: CodeFunction): String {
        var name = method.Name
        if (name != "" && methodQueue.size > 1) {
            name = methodQueue[methodQueue.size - 1].Name
        }

        return codeContainer.PackageName + "." + currentClz + "." + name + ":" + method.Position.StartLine.toString()
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
        var callType = ""
        if (currentClz == targetType) {
            callType = "self"
            return JavaTargetType(
                targetType = codeContainer.PackageName + "." + targetType,
                callType = callType
            )
        }

        val pureTargetType = buildPureTargetType(targetType)
        if (pureTargetType != "") {
            for (imp in imports) {
                if (imp.Source.endsWith(pureTargetType)) {
                    callType = "chain"
                    return JavaTargetType(
                        targetType = imp.Source,
                        callType = callType
                    )
                }
            }
        }

        for (clz in classes) {
            if (clz.endsWith(".$pureTargetType")) {
                callType = "same package"
                return JavaTargetType(
                    targetType = clz,
                    callType = callType
                )
            }
        }

        if (pureTargetType == "super" || pureTargetType == "this") {
            for (imp in imports) {
                if (imp.Source.endsWith(currentClzExtend)) {
                    callType = "super"
                    return JavaTargetType(
                        targetType = imp.Source,
                        callType = callType
                    )
                }
            }
        }

        // todo: add identMap
        return JavaTargetType(callType = callType)
    }

    private fun buildPureTargetType(targetType: String?): String {
        val split = targetType!!.split(".")
        val pureTargetType = split[0].replace("[", "").replace("]", "")
        return pureTargetType
    }

    override fun exitFormalParameter(ctx: JavaParser.FormalParameterContext?) {
        val paramKey = ctx!!.variableDeclaratorId().IDENTIFIER().text
        formalParameters[paramKey] = ctx.typeType().text
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

            val typeTypeText = typeCtx.IDENTIFIER(0).text
            val typeValue = declCtx.variableDeclaratorId().IDENTIFIER().text
            fieldsMap[typeValue] = typeTypeText

            val field = CodeField(typeTypeText, typeValue, Modifiers = arrayOf())
            fields += field

            buildFieldCall(typeTypeText, ctx)
        }
    }

    private fun buildFieldCall(typeType: String, ctx: JavaParser.FieldDeclarationContext) {
        val target = warpTargetFullType(typeType).targetType
        if (target != "") {
            val position = buildPosition(ctx as ParserRuleContext)
            val methodCall = CodeCall(
                Package = removeTarget(target),
                Type = "field",
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
        currentNode.NodeName = ctx!!.IDENTIFIER().text
        currentNode.Type = DataStructType.INTERFACE
        currentNode.Package = codeContainer.PackageName

        if (ctx.EXTENDS() != null) {
            var extend = ""
            for (typeContext in ctx.typeList().typeType()) {
                extend = buildExtend(typeContext.text)
            }

            currentNode.Extend = extend
        }
    }

    override fun exitInterfaceDeclaration(ctx: JavaParser.InterfaceDeclarationContext?) {
        this.exitBody()
    }

    override fun enterAnnotation(ctx: JavaParser.AnnotationContext?) {
        val annotationName = ctx!!.qualifiedName().text
        isOverrideMethod = annotationName == "Override"

        if (!hasEnterClass) {
            val annotation = this.buildAnnotation(ctx)

            if (currentType == DataStructType.CREATORCLASS) {
                currentNode.Annotations += annotation
            } else {
                currentNode.Annotations += annotation
            }
        }
    }

    override fun enterLocalVariableDeclaration(ctx: JavaParser.LocalVariableDeclarationContext?) {
        val typ = ctx!!.getChild(0).text
        if (ctx.getChild(1) != null) {
            if (ctx.getChild(1).getChild(0) != null && ctx.getChild(1).getChild(0).getChild(0) != null) {
                val variableName = ctx.getChild(1).getChild(0).getChild(0).text
                localVars[variableName] = typ
            }
        }
    }

    override fun enterExpression(ctx: JavaParser.ExpressionContext?) {
        if (ctx!!.COLONCOLON() != null) {
            if (ctx.expression(0) == null) {
                return
            }

            val text = ctx.expression(0).text
            val methodName = ctx.IDENTIFIER().text
            val targetType = parseTargetType(text)

            val fullType = warpTargetFullType(targetType).targetType
            val position = buildPosition(ctx)
            val codeCall = CodeCall(
                Package = removeTarget(fullType),
                Type = "lambda",
                NodeName = targetType!!,
                FunctionName = methodName,
                Position = position
            )

            sendResultToMethodCallMap(codeCall)
        }
    }

    override fun enterConstructorDeclaration(ctx: JavaParser.ConstructorDeclarationContext?) {
        val name = ctx!!.IDENTIFIER().text
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
        val allIdentifier = ctx.createdName().IDENTIFIER()
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
            currentType = DataStructType.CREATORCLASS
            val creatorNode = CodeDataStruct(
                NodeName = text,
                Type = currentType,
                Package = codeContainer.PackageName
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

        classNodeStack.push(currentCreatorNode)

        currentType = classNodeStack.elements[classNodeStack.elements.size - 1].Type
        classNodeStack.pop()
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

    private fun buildCreatedCall(createdName: String?, ctx: JavaParser.CreatorContext) {
        var codeFunction = methodMap[getMethodMapName(currentFunction)]
        if (codeFunction == null) {
            return
        }

        val fullType = warpTargetFullType(createdName)
        val codePosition = buildPosition(ctx)

        val codeCall = CodeCall(
            Package = removeTarget(fullType = fullType.targetType),
            Type = "CreatorClass",
            NodeName = createdName!!,
            Position = codePosition
        )

        codeFunction.FunctionCalls += codeCall
        methodMap[getMethodMapName(currentFunction)] = codeFunction
    }

    fun getNodeInfo(): CodeContainer {
        codeContainer.DataStructures = classNodes
        return codeContainer
    }
}
