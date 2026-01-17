package chapi.ast.scalaast

import chapi.ast.antlr.ScalaParser
import chapi.domain.core.*
import chapi.infra.Stack
import org.antlr.v4.runtime.ParserRuleContext

class ScalaFullIdentListener(var fileName: String) : ScalaAstBaseListener() {
    private val structStack: Stack<CodeDataStruct> = Stack()
    private var currentDataStruct: CodeDataStruct = CodeDataStruct()
    private var currentFunction: CodeFunction = CodeFunction()
    private var codeContainer: CodeContainer = CodeContainer(
        FullName = fileName,
        Language = "scala",
        Kind = ContainerKind.SOURCE_FILE
    )

    private val imports: MutableList<CodeImport> = mutableListOf()
    private var localVars: MutableMap<String, String> = mutableMapOf()
    private var isInFunction: Boolean = false

    // ==================== Package & Import ====================

    override fun enterPackageDecl(ctx: ScalaParser.PackageDeclContext?) {
        val packageName = ctx!!.qualId().text
        codeContainer.PackageName = packageName
        codeContainer.DeclaredPackage = packageName
    }

    override fun enterImport_(ctx: ScalaParser.Import_Context?) {
        ctx?.importExpr()?.forEach { importExpr ->
            val importSource = importExpr.stableId().text
            val importSelectors = importExpr.importSelectors()

            if (importSelectors != null) {
                // Handle import with selectors: import org.apache.spark.sql.{DataFrame, Row, functions}
                importSelectors.importSelector().forEach { selector ->
                    val selectorName = selector.Id(0)?.text ?: "_"
                    val asName = if (selector.Id().size > 1) selector.Id(1).text else selectorName

                    if (selectorName != "_") {
                        val codeImport = CodeImport(
                            Source = "$importSource.$selectorName",
                            AsName = asName
                        )
                        imports.add(codeImport)
                        codeContainer.Imports += codeImport
                    }
                }

                // Handle wildcard import: import org.apache.spark.sql._
                if (importSelectors.text.endsWith("_") || importSelectors.children?.any { it.text == "_" } == true) {
                    val codeImport = CodeImport(
                        Source = "$importSource.*",
                        AsName = "*"
                    )
                    imports.add(codeImport)
                    codeContainer.Imports += codeImport
                }
            } else {
                // Simple import: import xxx.yyy.zzz.ClassName or import xxx.yyy.zzz._
                val lastPart = importExpr.getChild(importExpr.childCount - 1)
                val asName = when {
                    lastPart?.text == "_" -> "*"
                    importExpr.Id() != null -> importExpr.Id().text
                    else -> importSource.substringAfterLast('.')
                }

                val source = if (importExpr.Id() != null) {
                    "$importSource.${importExpr.Id().text}"
                } else if (lastPart?.text == "_") {
                    "$importSource.*"
                } else {
                    importSource
                }

                val codeImport = CodeImport(
                    Source = source,
                    AsName = asName
                )
                imports.add(codeImport)
                codeContainer.Imports += codeImport
            }
        }
    }

    // ==================== Class / Object / Trait ====================

    override fun enterObjectDef(ctx: ScalaParser.ObjectDefContext?) {
        val objectName = ctx!!.Id().text
        val codeDataStruct = CodeDataStruct(
            NodeName = objectName,
            Type = DataStructType.OBJECT,
            FilePath = codeContainer.FullName,
            Package = codeContainer.PackageName,
            Position = buildPosition(ctx)
        )

        updateStruct(codeDataStruct)
    }

    override fun exitObjectDef(ctx: ScalaParser.ObjectDefContext?) {
        exitCurrentStruct()
    }

    override fun enterClassDef(ctx: ScalaParser.ClassDefContext?) {
        val className = ctx!!.Id().text

        val codeDataStruct = CodeDataStruct(
            NodeName = className,
            Type = DataStructType.CLASS,
            FilePath = codeContainer.FullName,
            Package = codeContainer.PackageName,
            Position = buildPosition(ctx)
        )

        // Parse class parameters
        codeDataStruct.Parameters = this.buildParameters(ctx.classParamClauses())

        // Parse class parameters as fields (val/var)
        codeDataStruct.Fields = this.buildFieldsFromClassParams(ctx.classParamClauses())

        // Parse extends and with (implements)
        if (ctx.classTemplateOpt() != null) {
            val classTemplate = ctx.classTemplateOpt().classTemplate()
            if (classTemplate != null && classTemplate.classParents() != null) {
                val classParents = classTemplate.classParents()

                // Primary extend
                val extend = classParents.constr().annotType().text
                codeDataStruct.Extend = resolveFullType(extend)

                // With traits (implements)
                classParents.annotType()?.forEach { annotType ->
                    val traitName = annotType.text
                    codeDataStruct.Implements += resolveFullType(traitName)
                }
            }
        }

        // Parse annotations from modifiers
        codeDataStruct.Annotations = buildAnnotationsFromContext(ctx)

        updateStruct(codeDataStruct)
    }

    override fun exitClassDef(ctx: ScalaParser.ClassDefContext?) {
        exitCurrentStruct()
    }

    override fun enterTraitDef(ctx: ScalaParser.TraitDefContext?) {
        val traitName = ctx!!.Id().text

        val codeDataStruct = CodeDataStruct(
            NodeName = traitName,
            Type = DataStructType.TRAIT,
            FilePath = codeContainer.FullName,
            Package = codeContainer.PackageName,
            Position = buildPosition(ctx)
        )

        // Parse extends for traits
        if (ctx.traitTemplateOpt() != null) {
            val traitTemplate = ctx.traitTemplateOpt().traitTemplate()
            if (traitTemplate != null && traitTemplate.traitParents() != null) {
                val traitParents = traitTemplate.traitParents()
                val extends = traitParents.annotType()
                if (extends.isNotEmpty()) {
                    codeDataStruct.Extend = resolveFullType(extends[0].text)
                    // Additional traits as implements
                    extends.drop(1).forEach { annotType ->
                        codeDataStruct.Implements += resolveFullType(annotType.text)
                    }
                }
            }
        }

        updateStruct(codeDataStruct)
    }

    override fun exitTraitDef(ctx: ScalaParser.TraitDefContext?) {
        exitCurrentStruct()
    }

    private fun updateStruct(codeDataStruct: CodeDataStruct) {
        currentDataStruct = codeDataStruct

        if (structStack.elements.isNotEmpty()) {
            val lastStruct = structStack.elements.last()
            lastStruct.InnerStructures += currentDataStruct
        } else {
            codeContainer.DataStructures += currentDataStruct
        }

        structStack.push(currentDataStruct)
    }

    private fun exitCurrentStruct() {
        if (structStack.elements.isNotEmpty()) {
            structStack.pop()
            if (structStack.elements.isNotEmpty()) {
                currentDataStruct = structStack.elements.last()
            }
        }
    }

    // ==================== Function Definition ====================

    override fun enterFunDef(ctx: ScalaParser.FunDefContext?) {
        if (ctx == null) return

        isInFunction = true
        localVars = mutableMapOf()

        val funSig = ctx.funSig()
        if (funSig != null) {
            val funcName = funSig.Id().text
            val returnType = ctx.type_()?.text ?: "Unit"
            val parameters = buildFunctionParameters(funSig.paramClauses())
            val annotations = buildAnnotationsFromFunDef(ctx)

            currentFunction = CodeFunction(
                Name = funcName,
                ReturnType = resolveFullType(returnType),
                Parameters = parameters,
                Position = buildPosition(ctx),
                Annotations = annotations,
                Package = codeContainer.PackageName
            )
        }
    }

    override fun exitFunDef(ctx: ScalaParser.FunDefContext?) {
        if (currentFunction.Name.isNotEmpty()) {
            currentDataStruct.Functions += currentFunction
            currentFunction = CodeFunction()
        }
        isInFunction = false
        localVars = mutableMapOf()
    }

    override fun enterFunDcl(ctx: ScalaParser.FunDclContext?) {
        if (ctx == null) return

        val funSig = ctx.funSig()
        if (funSig != null) {
            val funcName = funSig.Id().text
            val returnType = ctx.type_()?.text ?: "Unit"
            val parameters = buildFunctionParameters(funSig.paramClauses())

            val function = CodeFunction(
                Name = funcName,
                ReturnType = resolveFullType(returnType),
                Parameters = parameters,
                Position = buildPosition(ctx),
                Package = codeContainer.PackageName
            )

            currentDataStruct.Functions += function
        }
    }

    private fun buildFunctionParameters(paramClauses: ScalaParser.ParamClausesContext?): List<CodeProperty> {
        val params = mutableListOf<CodeProperty>()
        paramClauses?.paramClause()?.forEach { paramClause ->
            paramClause.params()?.param()?.forEach { param ->
                val paramName = param.Id().text
                val paramType = param.paramType()?.text ?: ""

                params.add(CodeProperty(
                    TypeValue = paramName,
                    TypeType = resolveFullType(paramType)
                ))

                // Add to local vars for call resolution
                localVars[paramName] = paramType
            }
        }
        return params
    }

    // ==================== Field Definition (val/var) ====================

    override fun enterPatVarDef(ctx: ScalaParser.PatVarDefContext?) {
        if (ctx == null) return

        val patDef = ctx.patDef()
        val varDef = ctx.varDef()

        if (patDef != null) {
            // val definition - check parent for 'val' keyword
            val isVal = ctx.getChild(0)?.text == "val"
            buildFieldFromPatDef(patDef, isVal = isVal)
        } else if (varDef != null) {
            // var definition
            buildFieldFromVarDef(varDef)
        }
    }

    private fun buildFieldFromPatDef(patDef: ScalaParser.PatDefContext, isVal: Boolean) {
        val fieldType = patDef.type_()?.text ?: ""
        val fieldValue = patDef.expr()?.text ?: ""

        patDef.pattern2().forEach { pattern ->
            val fieldName = extractIdentifierFromPattern(pattern)
            if (fieldName.isNotEmpty()) {
                if (isInFunction) {
                    // Local variable
                    localVars[fieldName] = fieldType
                    currentFunction.LocalVariables += CodeProperty(
                        TypeValue = fieldName,
                        TypeType = resolveFullType(fieldType)
                    )
                } else {
                    // Class field
                    val field = CodeField(
                        TypeKey = fieldName,
                        TypeType = resolveFullType(fieldType),
                        TypeValue = fieldValue,
                        Modifiers = if (isVal) listOf("val") else listOf("var")
                    )
                    currentDataStruct.Fields += field
                }
            }
        }
    }

    private fun buildFieldFromVarDef(varDef: ScalaParser.VarDefContext) {
        val patDef = varDef.patDef()
        if (patDef != null) {
            buildFieldFromPatDef(patDef, isVal = false)
        } else {
            // ids ':' type_ '=' '_' form
            val fieldType = varDef.type_()?.text ?: ""
            varDef.ids()?.Id()?.forEach { id ->
                val fieldName = id.text
                if (isInFunction) {
                    localVars[fieldName] = fieldType
                } else {
                    val field = CodeField(
                        TypeKey = fieldName,
                        TypeType = resolveFullType(fieldType),
                        TypeValue = "_",
                        Modifiers = listOf("var")
                    )
                    currentDataStruct.Fields += field
                }
            }
        }
    }

    private fun extractIdentifierFromPattern(pattern: ScalaParser.Pattern2Context): String {
        return pattern.Id()?.text ?: pattern.pattern3()?.simplePattern()?.firstOrNull()?.let {
            it.Varid()?.text ?: it.stableId()?.text
        } ?: ""
    }

    // ==================== Function Calls ====================

    override fun enterSimpleExpr1(ctx: ScalaParser.SimpleExpr1Context?) {
        if (ctx == null || !isInFunction) return

        // Handle method calls: obj.method(args) or method(args)
        buildFunctionCallsFromExpr(ctx)
    }

    private fun buildFunctionCallsFromExpr(ctx: ScalaParser.SimpleExpr1Context) {
        // Pattern: simpleExpr1 '.' Id - method call on object
        // Pattern: simpleExpr1 argumentExprs - method call with args

        val stableId = ctx.stableId()
        val argumentExprs = ctx.argumentExprs()

        if (stableId != null && argumentExprs != null) {
            // Direct function call: functionName(args)
            val callInfo = parseStableId(stableId)
            val parameters = buildCallParameters(argumentExprs)

            val codeCall = CodeCall(
                NodeName = callInfo.nodeName,
                FunctionName = callInfo.functionName,
                Package = callInfo.packageName,
                Parameters = parameters,
                Position = buildPosition(ctx),
                Type = callInfo.callType
            )

            currentFunction.FunctionCalls += codeCall
        }
    }

    override fun enterPostfixExpr(ctx: ScalaParser.PostfixExprContext?) {
        if (ctx == null || !isInFunction) return

        // Extract function calls from postfix expressions
        val infixExpr = ctx.infixExpr()
        if (infixExpr != null) {
            extractCallsFromInfixExpr(infixExpr)
        }
    }

    private fun extractCallsFromInfixExpr(ctx: ScalaParser.InfixExprContext) {
        // Handle infix method calls: obj method arg
        val prefixExpr = ctx.prefixExpr()
        val id = ctx.Id()

        if (prefixExpr != null && id != null) {
            val methodName = id.text

            // Get the receiver object
            val receiverText = prefixExpr.text
            val callInfo = resolveCallTarget(receiverText)

            val codeCall = CodeCall(
                NodeName = callInfo.nodeName,
                FunctionName = methodName,
                Package = callInfo.packageName,
                Position = buildPosition(ctx),
                Type = callInfo.callType
            )

            currentFunction.FunctionCalls += codeCall
        }

        // Recursively process nested infix expressions
        ctx.infixExpr()?.firstOrNull()?.let { extractCallsFromInfixExpr(it) }
    }

    override fun enterSimpleExpr(ctx: ScalaParser.SimpleExprContext?) {
        if (ctx == null || !isInFunction) return

        // Handle 'new' expressions: new ClassName(args)
        // Check if first child is 'new' keyword
        if (ctx.childCount > 0 && ctx.getChild(0)?.text == "new") {
            val classTemplate = ctx.classTemplate()
            if (classTemplate != null && classTemplate.classParents() != null) {
                val constr = classTemplate.classParents().constr()
                val className = constr.annotType().text

                val parameters = constr.argumentExprs()?.flatMap { argExpr ->
                    buildCallParameters(argExpr)
                } ?: emptyList()

                val codeCall = CodeCall(
                    NodeName = className,
                    FunctionName = "<init>",
                    Package = resolvePackageForType(className),
                    Parameters = parameters,
                    Position = buildPosition(ctx),
                    Type = CallType.CREATOR
                )

                currentFunction.FunctionCalls += codeCall
            }
        }
    }

    private data class CallInfo(
        val nodeName: String,
        val functionName: String,
        val packageName: String,
        val callType: CallType
    )

    private fun parseStableId(stableId: ScalaParser.StableIdContext): CallInfo {
        val ids = stableId.Id()
        val text = stableId.text

        return when {
            ids.size >= 2 -> {
                // obj.method format
                val nodeName = ids.dropLast(1).joinToString(".") { it.text }
                val functionName = ids.last().text
                val packageName = resolvePackageForType(nodeName)

                CallInfo(nodeName, functionName, packageName, CallType.FUNCTION)
            }
            ids.size == 1 -> {
                // Simple method call
                val functionName = ids[0].text
                val varType = localVars[functionName]

                if (varType != null) {
                    CallInfo(varType, functionName, resolvePackageForType(varType), CallType.FUNCTION)
                } else {
                    // Could be a static call or imported function
                    val importInfo = findImportForName(functionName)
                    if (importInfo != null) {
                        CallInfo(importInfo.AsName, functionName, importInfo.Source.substringBeforeLast('.'), CallType.FUNCTION)
                    } else {
                        CallInfo("", functionName, codeContainer.PackageName, CallType.FUNCTION)
                    }
                }
            }
            else -> {
                CallInfo("", text, "", CallType.FUNCTION)
            }
        }
    }

    private fun resolveCallTarget(text: String): CallInfo {
        // Check if it's a local variable
        val varType = localVars[text]
        if (varType != null) {
            return CallInfo(varType, "", resolvePackageForType(varType), CallType.FUNCTION)
        }

        // Check if it's an imported type
        val importInfo = findImportForName(text)
        if (importInfo != null) {
            return CallInfo(text, "", importInfo.Source.substringBeforeLast('.'), CallType.FUNCTION)
        }

        // Check if it's a field
        val field = currentDataStruct.Fields.find { it.TypeKey == text }
        if (field != null) {
            return CallInfo(field.TypeType, "", resolvePackageForType(field.TypeType), CallType.FUNCTION)
        }

        return CallInfo(text, "", codeContainer.PackageName, CallType.FUNCTION)
    }

    private fun buildCallParameters(argumentExprs: ScalaParser.ArgumentExprsContext): List<CodeProperty> {
        val params = mutableListOf<CodeProperty>()

        argumentExprs.args()?.exprs()?.expr()?.forEach { expr ->
            params.add(CodeProperty(
                TypeValue = expr.text,
                TypeType = inferExpressionType(expr.text)
            ))
        }

        return params
    }

    private fun inferExpressionType(text: String): String {
        return when {
            text.matches(Regex("\".*\"")) -> "String"
            text.matches(Regex("\\d+")) -> "Int"
            text.matches(Regex("\\d+\\.\\d+")) -> "Double"
            text == "true" || text == "false" -> "Boolean"
            localVars.containsKey(text) -> localVars[text] ?: ""
            else -> ""
        }
    }

    // ==================== Annotations ====================

    private fun buildAnnotationsFromContext(ctx: ParserRuleContext): List<CodeAnnotation> {
        val annotations = mutableListOf<CodeAnnotation>()

        // Look for annotations in parent context (tmplDef -> topStat -> annotation)
        var parent = ctx.parent
        while (parent != null) {
            if (parent is ScalaParser.TopStatContext) {
                parent.annotation()?.forEach { annCtx ->
                    annotations.add(buildAnnotation(annCtx))
                }
                break
            }
            if (parent is ScalaParser.TemplateStatContext) {
                parent.annotation()?.forEach { annCtx ->
                    annotations.add(buildAnnotation(annCtx))
                }
                break
            }
            parent = parent.parent
        }

        return annotations
    }

    private fun buildAnnotationsFromFunDef(ctx: ScalaParser.FunDefContext): List<CodeAnnotation> {
        val annotations = mutableListOf<CodeAnnotation>()

        // Look for annotations in parent context
        var parent = ctx.parent
        while (parent != null) {
            if (parent is ScalaParser.Def_Context) {
                // Annotations might be before def_
                break
            }
            if (parent is ScalaParser.TemplateStatContext) {
                parent.annotation()?.forEach { annCtx ->
                    annotations.add(buildAnnotation(annCtx))
                }
                break
            }
            if (parent is ScalaParser.BlockStatContext) {
                parent.annotation()?.forEach { annCtx ->
                    annotations.add(buildAnnotation(annCtx))
                }
                break
            }
            parent = parent.parent
        }

        return annotations
    }

    private fun buildAnnotation(ctx: ScalaParser.AnnotationContext): CodeAnnotation {
        val annotationName = ctx.simpleType().text
        val keyValues = mutableListOf<AnnotationKeyValue>()

        ctx.argumentExprs()?.forEach { argExpr ->
            argExpr.args()?.exprs()?.expr()?.forEachIndexed { index, expr ->
                keyValues.add(AnnotationKeyValue(
                    Key = "value$index",
                    Value = expr.text
                ))
            }
        }

        return CodeAnnotation(
            Name = annotationName,
            KeyValues = keyValues,
            Position = buildPosition(ctx)
        )
    }

    // ==================== Helper Methods ====================

    private fun buildFieldsFromClassParams(classParamClauses: ScalaParser.ClassParamClausesContext?): List<CodeField> {
        val fields = mutableListOf<CodeField>()

        classParamClauses?.classParamClause()?.forEach { paramClause ->
            paramClause.classParams()?.classParam()?.forEach { classParam ->
                // Check if the param has val or var by looking at children text
                val hasVal = classParam.children?.any { it.text == "val" } == true
                val hasVar = classParam.children?.any { it.text == "var" } == true

                if (hasVal || hasVar) {
                    val fieldName = classParam.Id().text
                    val fieldType = classParam.paramType().text
                    val annotations = classParam.annotation()?.map { buildAnnotation(it) } ?: emptyList()
                    val modifiers = mutableListOf<String>()

                    if (hasVal) modifiers.add("val")
                    if (hasVar) modifiers.add("var")

                    classParam.modifier()?.forEach { mod ->
                        modifiers.add(mod.text)
                    }

                    fields.add(CodeField(
                        TypeKey = fieldName,
                        TypeType = resolveFullType(fieldType),
                        Annotations = annotations,
                        Modifiers = modifiers
                    ))
                }
            }
        }

        return fields
    }

    private fun resolveFullType(typeName: String): String {
        if (typeName.isEmpty()) return typeName

        // Remove generic type parameters for matching
        val baseType = typeName.substringBefore('[').substringBefore('(')

        // Check imports
        val importInfo = findImportForName(baseType)
        if (importInfo != null) {
            return typeName.replace(baseType, importInfo.Source.substringBeforeLast('.') + "." + baseType)
        }

        // Scala built-in types
        val scalaBuiltins = setOf(
            "Int", "Long", "Short", "Byte", "Float", "Double", "Boolean", "Char",
            "String", "Unit", "Any", "AnyVal", "AnyRef", "Nothing", "Null",
            "Array", "List", "Set", "Map", "Seq", "Option", "Either", "Try",
            "Future", "Promise", "Vector", "Stream", "Iterator", "Iterable"
        )

        if (scalaBuiltins.contains(baseType)) {
            return "scala.$typeName"
        }

        return typeName
    }

    private fun resolvePackageForType(typeName: String): String {
        if (typeName.isEmpty()) return ""

        val baseType = typeName.substringBefore('[').substringBefore('(')

        val importInfo = findImportForName(baseType)
        if (importInfo != null) {
            return importInfo.Source.substringBeforeLast('.')
        }

        return codeContainer.PackageName
    }

    private fun findImportForName(name: String): CodeImport? {
        return imports.find {
            it.AsName == name || it.Source.endsWith(".$name") || it.AsName == "*"
        }
    }

    private fun buildPosition(ctx: ParserRuleContext): CodePosition {
        return CodePosition(
            StartLine = ctx.start.line,
            StartLinePosition = ctx.start.charPositionInLine,
            StopLine = ctx.stop?.line ?: ctx.start.line,
            StopLinePosition = ctx.stop?.charPositionInLine ?: ctx.start.charPositionInLine
        )
    }

    fun getNodeInfo(): CodeContainer {
        return this.codeContainer
    }
}
