package chapi.ast.javaast

import chapi.ast.antlr.JavaParser
import chapi.ast.antlr.JavaParserBaseListener
import domain.core.*
import domain.infra.Stack
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.tree.ParseTree

class JavaIdentListener(fileName: String) : JavaParserBaseListener() {
    private var currentCreatorNode: CodeDataStruct = CodeDataStruct()
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

    private var innerNode: CodeDataStruct = CodeDataStruct()
    private var classNodeStack = Stack<CodeDataStruct>()
    private var classNodeMap = mutableMapOf<String, CodeDataStruct>()

    private var methodQueue: Array<CodeFunction> = arrayOf()

    private var imports: Array<CodeImport> = arrayOf()

    private var lastNode = CodeDataStruct()
    private var currentNode = CodeDataStruct()
    private var currentFunction = CodeFunction(IsConstructor = false)
    private var currentNodeFunction = CodeFunction(IsConstructor = false)
    private var currentType: String = ""

    private var codeFile: CodeFile = CodeFile(FullName = fileName)

    override fun enterPackageDeclaration(ctx: JavaParser.PackageDeclarationContext?) {
        super.enterPackageDeclaration(ctx)
        codeFile.PackageName = ctx?.qualifiedName()!!.text
    }

    override fun enterImportDeclaration(ctx: JavaParser.ImportDeclarationContext?) {
        val codeImport = CodeImport(Source = ctx!!.qualifiedName()!!.text)
        imports += codeImport

        codeFile.Imports += codeImport
    }

    override fun enterClassDeclaration(ctx: JavaParser.ClassDeclarationContext?) {
        println("enterClassDeclaration")
        val isInnerNode = currentNode.NodeName != ""
        if (isInnerNode) {
            updateStructMethods()

            innerNode = CodeDataStruct()

            currentType = "InnerStructures"
            innerNode.Type = currentType

            buildClassExtension(ctx, innerNode)

            classNodeStack.push(innerNode)

            lastNode.InnerStructures += innerNode
            lastNode = innerNode
        } else {
            currentType = "Class"
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

    private fun buildImplements(ctx: JavaParser.ClassDeclarationContext): Array<String> {
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
        if (currentType == "CreatorClass") {
            currentNode.Fields = fields
            currentNode.setMethodsFromMap(creatorMethodMap)
            creatorMethodMap = mutableMapOf()

            if (classNodeStack.peek() != null) {
                currentType = classNodeStack.peek()!!.Type
            }
        } else {
            currentNode.Fields = fields
            currentNode.setMethodsFromMap(methodMap)
        }

        classNodes += currentNode
        initClass()
    }

    private fun initClass() {
        currentClz = ""
        currentClzExtend = ""
        currentFunction = CodeFunction(IsConstructor = false)
//        currentNode.FunctionCalls = arrayOf()

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
            this.buildAnnotationForMethod(mayModifierCtx as JavaParser.ModifierContext, codeFunction)
        }

        this.updateCodeFunction(codeFunction)
    }

    override fun enterMethodDeclaration(ctx: JavaParser.MethodDeclarationContext?) {
        println("enterMethodDeclaration")
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
            this.buildAnnotationForMethod(mayModifierCtx as JavaParser.ModifierContext, codeFunction)
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

    private fun buildAnnotationForMethod(
        modifierCtx: JavaParser.ModifierContext,
        codeFunction: CodeFunction
    ) {
        if (modifierCtx.classOrInterfaceModifier() != null) {
            val childCtx = modifierCtx.classOrInterfaceModifier().getChild(0)
            if (childCtx::class.simpleName == "AnnotationContext") {
                val annotation = this.buildAnnotation(childCtx as JavaParser.AnnotationContext)
                codeFunction.Annotations += annotation
            }
        }
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

            val parameter = CodeProperty(TypeValue = paramValue, TypeType = paramType)

            methodParams += parameter
        }

        return methodParams
    }

    override fun exitMethodDeclaration(ctx: JavaParser.MethodDeclarationContext?) {
//        currentFunction = CodeFunction(IsConstructor = false)
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
        buildMethodCallMethodInformation(codeCall, callee, targetType)
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

    private fun buildPosition(ctx: ParserRuleContext): CodePosition {
        val position = CodePosition()
        position.StartLine = ctx.start.line
        position.StartLinePosition = ctx.start.charPositionInLine
        position.StopLine = ctx.stop.line
        position.StopLinePosition = ctx.stop.charPositionInLine
        return position
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

    private fun buildMethodCallMethodInformation(codeCall: CodeCall, callee: String = "", _targetType: String?) {
        var packageName = codeFile.PackageName
        var methodName = callee

        var targetTypeStr = _targetType
        val targetType = this.warpTargetFullType(targetTypeStr)
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
            // HandleEmptyFullType
        }

        // 处理链试调用
        if (this.isChainCall(targetTypeStr)) {
            val split = targetTypeStr!!.split(".")
            targetTypeStr = split[0]
            targetTypeStr = parseTargetType(targetTypeStr)
        }

        codeCall.Package = packageName
        codeCall.FunctionName = methodName
        codeCall.NodeName = targetTypeStr ?: ""
    }

    private fun isChainCall(targetTypeStr: String?): Boolean {
        return targetTypeStr!!.contains("(") && targetTypeStr.contains(")") && targetTypeStr.contains(".")
    }

    private fun removeTarget(fullType: String): String {
        val split = fullType.split(".")
        return split.dropLast(1).joinToString(".")
    }

    private fun parseTargetType(target: String?): String? {
        var targetType = target

        val formalType = formalParameters[targetType]
        val localVarType = localVars[targetType]

        if (formalType != null && formalType != "") {
            targetType = formalType
        } else if (localVarType != null && localVarType != "") {
            targetType = localVarType
        }

        return targetType
    }

    private fun updateCodeFunction(codeFunction: CodeFunction) {
        if (currentType == "CreatorClass") {
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

        return codeFile.PackageName + "." + currentClz + "." + name + ":" + method.Position.StartLine.toString()
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
            return JavaTargetType(targetType = codeFile.PackageName + "." + targetType, callType = callType)
        }

        val split = targetType!!.split(".")
        var first = split[0]
        val pureTargetType = first.replace("[", "").replace("]", "")

        if (pureTargetType != "") {
            for (imp in imports) {
                if (imp.Source.endsWith(pureTargetType)) {
                    callType = "chain"
                    return JavaTargetType(targetType = imp.Source, callType = callType)
                }
            }
        }

        // todo: add ident clzs

        if (pureTargetType == "super" || pureTargetType == "this") {
            for (imp in imports) {
                if (imp.Source.endsWith(currentClzExtend)) {
                    callType = "super"
                    return JavaTargetType(targetType = imp.Source, callType = callType)
                }
            }
        }

        // todo: add identMap
        return JavaTargetType(callType = callType)
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

            val field = CodeField(typeTypeText, typeValue, arrayOf<String>())
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
        var child = typeType.getChild(index)
        when (child::class.simpleName) {
            "ClassOrInterfaceTypeContext" -> {
                newTypeCtx = child as JavaParser.ClassOrInterfaceTypeContext
            }
        }

        return newTypeCtx
    }

    override fun enterInterfaceDeclaration(ctx: JavaParser.InterfaceDeclarationContext?) {
        hasEnterClass = true
        currentType = "Interface"
        currentNode.NodeName = ctx!!.IDENTIFIER().text
        currentNode.Type = "Interface"

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

            if (currentType == "CreatorClass") {
                currentNode.Annotations += annotation
            } else {
                currentNode.Annotations += annotation
            }
        }
    }

    private fun buildAnnotation(ctx: JavaParser.AnnotationContext): CodeAnnotation {
        var annotationName = ctx.qualifiedName().text
        val codeAnnotation = CodeAnnotation(
            Name = annotationName
        )
        if (ctx.elementValuePairs() != null) {
            for (pairContext in ctx.elementValuePairs().elementValuePair()) {
                val key = pairContext.IDENTIFIER().text
                val value = pairContext.elementValue().text

                codeAnnotation.KeyValues += AnnotationKeyValue(key, value)
            }
        } else if (ctx.elementValue() != null) {
            val value = ctx.elementValue().text
            codeAnnotation.KeyValues += AnnotationKeyValue(value, value)
        }

        return codeAnnotation
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
        println("enterCreator")
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
            currentType = "CreatorClass"
            val creatorNode = CodeDataStruct(
                Package = codeFile.PackageName,
                NodeName = text,
                Type = currentType
            )

            currentCreatorNode = creatorNode
            classNodeStack.push(currentCreatorNode)
        }
    }

    override fun exitCreator(ctx: JavaParser.CreatorContext?) {
        if (currentCreatorNode.NodeName == "") {
            return
        }

        val currentNodeMethodName = getMethodMapName(currentFunction)
        val method = methodMap[currentNodeMethodName]
        if (method != null) {
            method.InnerStructures += currentCreatorNode
            methodMap[currentNodeMethodName] = method
        }

        currentType = classNodeStack.elements[classNodeStack.elements.size - 1].Type
        classNodeStack.pop()
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

    fun getNodeInfo(): CodeFile {
        codeFile.DataStructures = classNodes
        return codeFile
    }
}
