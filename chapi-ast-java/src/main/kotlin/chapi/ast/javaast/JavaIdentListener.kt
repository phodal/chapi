package chapi.ast.javaast

import chapi.ast.antlr.JavaParser
import chapi.ast.antlr.JavaParserBaseListener
import domain.core.*
import domain.infra.Stack
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.tree.ParseTree

class JavaIdentListener(fileName: String) : JavaParserBaseListener() {
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
        methodMap = mutableMapOf<String, CodeFunction>()
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
            var target = this.warpTargetFullType(_type.text)
            if (target == "") {
                target = _type.text
            }
            implements += target
        }
        return implements
    }

    override fun exitClassBody(ctx: JavaParser.ClassBodyContext?) {
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
        currentFunction = CodeFunction()
//        currentNode.FunctionCalls = arrayOf()

        methodMap = mutableMapOf<String, CodeFunction>()
        methodCalls = arrayOf<CodeCall>()
        fields = arrayOf<CodeField>()
        isOverrideMethod = false
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
            Position = codePosition
        )

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
        val parameterList = params!!.getChild(1) as JavaParser.FormalParameterListContext
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
        currentFunction = CodeFunction()
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

    private fun buildMethodCallMethodInformation(codeCall: CodeCall, callee: String = "", targetType: String?) {
        val packageName = codeFile.PackageName

        codeCall.Package = packageName
        codeCall.FunctionName = callee
        codeCall.NodeName = targetType ?: ""
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

    private fun buildExtend(extendName: String): String {
        var extend = extendName
        val target: String = this.warpTargetFullType(extendName)
        if (target != "") {
            extend = target
        }
        return extend
    }

    private fun warpTargetFullType(targetType: String): String {
        var callType = ""
        if (currentClz == targetType) {
            callType = "self"
            return codeFile.PackageName + "." + targetType
        }

        val split = targetType.split(".")
        var first = split[0]
        val pureTargetType = first.replace("[", "").replace("]", "")

        if (pureTargetType != "") {
            for (imp in imports) {
                if (imp.Source.endsWith(pureTargetType)) {
                    callType = "chain"
                    return imp.Source
                }
            }
        }

        // todo: add ident clzs

        if (pureTargetType == "super" || pureTargetType == "this") {
            for (imp in imports) {
                if (imp.Source.endsWith(currentClzExtend)) {
                    callType = "super"
                    return imp.Source
                }
            }
        }

        // todo: add identMap
        return ""
    }

    override fun exitFormalParameter(ctx: JavaParser.FormalParameterContext?) {
        super.exitFormalParameter(ctx)
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

            val typeType = typeCtx.IDENTIFIER(0).text
            val typeValue = declCtx.variableDeclaratorId().IDENTIFIER().text
            fieldsMap[typeValue] = typeType

            val field = CodeField(typeType, typeValue, arrayOf<String>())
            fields += field

            buildFieldCall(typeType, ctx)
        }
    }

    private fun buildFieldCall(typeType: String, ctx: JavaParser.FieldDeclarationContext) {
        val target = warpTargetFullType(typeType)
        if (target != "") {
            val position = buildPosition(ctx as ParserRuleContext)
            val methodCall = CodeCall(
                Package = "", // todo: add really package
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

    fun getNodeInfo(): CodeFile {
        codeFile.DataStructures = classNodes
        return codeFile
    }
}
