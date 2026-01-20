package chapi.ast.dartast

import chapi.ast.antlr.Dart2Parser
import chapi.domain.core.*

/**
 * Full identifier listener for Dart code.
 * Extends basic listener with method call analysis, expression handling, and field extraction.
 */
open class DartFullIdentListener(filePath: String) : DartBasicIdentListener(filePath) {

    private var currentFunctionCalls: MutableList<CodeCall> = mutableListOf()
    private var isInFunction: Boolean = false
    private var localVariables: MutableMap<String, String> = mutableMapOf()

    override fun enterMethodSignature(ctx: Dart2Parser.MethodSignatureContext?) {
        super.enterMethodSignature(ctx)
        // Don't set isInFunction here - wait for function body
    }

    override fun exitMethodSignature(ctx: Dart2Parser.MethodSignatureContext?) {
        super.exitMethodSignature(ctx)
    }

    override fun enterFunctionBody(ctx: Dart2Parser.FunctionBodyContext?) {
        isInFunction = true
        currentFunctionCalls.clear()
        localVariables.clear()
    }

    override fun exitFunctionBody(ctx: Dart2Parser.FunctionBodyContext?) {
        // Function calls collected during body processing are stored in currentFunctionCalls
        // They can be attached to the current function if needed
        isInFunction = false
    }

    // Handle class member declarations for fields
    override fun enterClassMemberDeclaration(ctx: Dart2Parser.ClassMemberDeclarationContext?) {
        val declaration = ctx?.declaration() ?: return

        // Extract field declarations
        extractFieldsFromDeclaration(declaration)
    }

    private fun extractFieldsFromDeclaration(ctx: Dart2Parser.DeclarationContext) {
        // Handle various field declaration patterns

        // Static const fields
        ctx.staticFinalDeclarationList()?.staticFinalDeclaration()?.forEach { decl ->
            val fieldName = decl.identifier()?.text ?: return@forEach
            val fieldType = ctx.type()?.text ?: "var"

            addFieldToCurrentClass(fieldName, fieldType, isStatic = true, isFinal = true)
        }

        // Initialized identifier lists (regular fields)
        ctx.initializedIdentifierList()?.initializedIdentifier()?.forEach { initId ->
            val fieldName = initId.identifier()?.text ?: return@forEach
            val fieldType = extractTypeFromDeclaration(ctx)

            val isStatic = ctx.STATIC_() != null
            val isLate = ctx.LATE_() != null
            val isFinal = ctx.FINAL_() != null

            addFieldToCurrentClass(fieldName, fieldType, isStatic, isFinal, isLate)
        }
    }

    private fun extractTypeFromDeclaration(ctx: Dart2Parser.DeclarationContext): String {
        return ctx.varOrType()?.type()?.text
            ?: ctx.varOrType()?.VAR_()?.text
            ?: ctx.type()?.text
            ?: "var"
    }

    /**
     * Add a field to the current class being parsed.
     * Creates a CodeField and adds it to the current node's fields.
     */
    protected fun addFieldToCurrentClass(
        name: String,
        type: String,
        isStatic: Boolean = false,
        isFinal: Boolean = false,
        isLate: Boolean = false
    ) {
        if (!hasEnterClass) return

        val modifiers = mutableListOf<String>()
        if (isStatic) modifiers.add("static")
        if (isFinal) modifiers.add("final")
        if (isLate) modifiers.add("late")

        val field = CodeField(
            TypeKey = name,
            TypeType = type,
            TypeValue = "",
            Modifiers = modifiers
        )

        currentNode.Fields += field
    }

    // Handle new expressions
    override fun enterNewExpression(ctx: Dart2Parser.NewExpressionContext?) {
        if (!isInFunction) return

        val constructorDesignation = ctx?.constructorDesignation() ?: return
        val call = CodeCall(
            NodeName = constructorDesignation.text,
            Type = CallType.CREATOR
        )
        call.Position = buildPosition(ctx)

        ctx.arguments()?.argumentList()?.let { argList ->
            call.Parameters = parseCallArguments(argList)
        }

        currentFunctionCalls.add(call)
    }

    // Handle const object expressions
    override fun enterConstObjectExpression(ctx: Dart2Parser.ConstObjectExpressionContext?) {
        if (!isInFunction) return

        val constructorDesignation = ctx?.constructorDesignation() ?: return
        val call = CodeCall(
            NodeName = constructorDesignation.text,
            Type = CallType.CREATOR
        )
        call.Position = buildPosition(ctx)

        ctx.arguments()?.argumentList()?.let { argList ->
            call.Parameters = parseCallArguments(argList)
        }

        currentFunctionCalls.add(call)
    }

    // Handle constructor invocations
    override fun enterConstructorInvocation(ctx: Dart2Parser.ConstructorInvocationContext?) {
        if (!isInFunction) return

        val typeName = ctx?.typeName()?.text ?: return
        val identifier = ctx.identifier()?.text ?: ""

        val call = CodeCall(
            NodeName = if (identifier.isNotEmpty()) "$typeName.$identifier" else typeName,
            Type = CallType.CREATOR
        )
        call.Position = buildPosition(ctx)

        ctx.arguments()?.argumentList()?.let { argList ->
            call.Parameters = parseCallArguments(argList)
        }

        currentFunctionCalls.add(call)
    }

    // Handle postfix expressions (method calls like obj.method())
    override fun enterPostfixExpression(ctx: Dart2Parser.PostfixExpressionContext?) {
        if (!isInFunction) return

        // Extract method call chain
        extractMethodCallChain(ctx)
    }

    private fun extractMethodCallChain(ctx: Dart2Parser.PostfixExpressionContext?) {
        if (ctx == null) return

        val primary = ctx.primary()
        val selectors = ctx.selector()

        if (primary == null || selectors.isNullOrEmpty()) return

        var currentTarget = primary.text

        for (selector in selectors) {
            // First update target if there's an identifier selector
            selector.assignableSelector()?.let { assignable ->
                assignable.identifier()?.let { id ->
                    currentTarget = "$currentTarget.${id.text}"
                }
            }

            // Check if this selector includes arguments (function call)
            selector.argumentPart()?.let { argPart ->
                val call = CodeCall(
                    NodeName = currentTarget,
                    Type = CallType.FUNCTION
                )
                call.Position = buildPosition(selector)

                argPart.arguments()?.argumentList()?.let { argList ->
                    call.Parameters = parseCallArguments(argList)
                }

                currentFunctionCalls.add(call)
            }
        }
    }

    private fun parseCallArguments(ctx: Dart2Parser.ArgumentListContext): List<CodeProperty> {
        val params = mutableListOf<CodeProperty>()

        // Expression list
        ctx.expressionList()?.expr()?.forEach { expr ->
            params.add(CodeProperty(TypeType = "dynamic", TypeValue = expr.text))
        }

        // Named arguments
        ctx.namedArgument()?.forEach { named ->
            val name = named.label()?.identifier()?.text ?: ""
            val value = named.expr()?.text ?: ""
            params.add(CodeProperty(TypeType = value, TypeValue = name))
        }

        return params
    }

    // Handle local variable declarations
    override fun enterLocalVariableDeclaration(ctx: Dart2Parser.LocalVariableDeclarationContext?) {
        ctx?.initializedVariableDeclaration()?.let { varDecl ->
            val declaredId = varDecl.declaredIdentifier()
            val varName = declaredId?.identifier()?.text ?: return
            val varType = declaredId.finalConstVarOrType()?.text ?: "var"

            localVariables[varName] = varType
        }
    }

    // Handle cascade expressions
    override fun enterCascade(ctx: Dart2Parser.CascadeContext?) {
        if (!isInFunction) return
        if (ctx == null) return

        // Cascade sections represent method calls on the same target
        val cascadeSection = ctx.cascadeSection()
        if (cascadeSection != null) {
            extractCascadeCall(cascadeSection)
        }
    }

    private fun extractCascadeCall(ctx: Dart2Parser.CascadeSectionContext) {
        val selector = ctx.cascadeSelector() ?: return

        selector.identifier()?.let { id ->
            val call = CodeCall(
                NodeName = id.text,
                Type = CallType.FUNCTION
            )
            call.Position = buildPosition(ctx)
            currentFunctionCalls.add(call)
        }
    }

    // Handle throw expressions
    override fun enterThrowExpression(ctx: Dart2Parser.ThrowExpressionContext?) {
        if (!isInFunction) return
        ctx ?: return

        val call = CodeCall(
            NodeName = "throw",
            Type = CallType.FUNCTION
        )
        call.Position = buildPosition(ctx)
        currentFunctionCalls.add(call)
    }

    // Handle super calls
    override fun enterInitializerListEntry(ctx: Dart2Parser.InitializerListEntryContext?) {
        // Super constructor calls in initializer lists
        if (ctx?.SUPER_() != null) {
            val identifier = ctx.identifier()?.text
            val call = CodeCall(
                NodeName = if (identifier != null) "super.$identifier" else "super",
                Type = CallType.SUPER
            )
            call.Position = buildPosition(ctx)
            currentFunctionCalls.add(call)
        }
    }

    /**
     * Get the function calls collected during parsing.
     */
    fun getFunctionCalls(): List<CodeCall> = currentFunctionCalls.toList()
}
