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
        isInFunction = true
        currentFunctionCalls.clear()
        localVariables.clear()
    }

    override fun exitMethodSignature(ctx: Dart2Parser.MethodSignatureContext?) {
        super.exitMethodSignature(ctx)
        isInFunction = false
    }

    override fun enterFunctionBody(ctx: Dart2Parser.FunctionBodyContext?) {
        // Reset for function body processing
    }

    override fun exitFunctionBody(ctx: Dart2Parser.FunctionBodyContext?) {
        // Attach collected function calls
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

    private fun addFieldToCurrentClass(
        name: String,
        type: String,
        isStatic: Boolean = false,
        isFinal: Boolean = false,
        isLate: Boolean = false
    ) {
        // This will be handled by the parent class's current node
    }

    // Handle function calls in expressions
    override fun enterArgumentPart(ctx: Dart2Parser.ArgumentPartContext?) {
        if (!isInFunction) return

        // Extract function call information from the parent context
        val parent = ctx?.parent
        if (parent is Dart2Parser.SelectorContext || parent is Dart2Parser.PostfixExpressionContext) {
            // Function call detected
            extractFunctionCall(ctx)
        }
    }

    private fun extractFunctionCall(ctx: Dart2Parser.ArgumentPartContext) {
        // Build function call from context
        val call = CodeCall()
        call.Position = buildPosition(ctx)

        // Parse arguments
        ctx.arguments()?.argumentList()?.let { argList ->
            call.Parameters = parseCallArguments(argList)
        }

        currentFunctionCalls.add(call)
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

    // Handle primary expressions (identifiers, literals, etc.)
    override fun enterPrimary(ctx: Dart2Parser.PrimaryContext?) {
        // Track identifiers for variable resolution
        ctx?.identifier()?.let { id ->
            // Could be a variable reference or function call
        }
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

            // Update target for chained calls
            selector.assignableSelector()?.let { assignable ->
                assignable.identifier()?.let { id ->
                    currentTarget = "$currentTarget.${id.text}"
                }
            }
        }
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

    // Handle await expressions
    override fun enterAwaitExpression(ctx: Dart2Parser.AwaitExpressionContext?) {
        // Mark that we're in an async context
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
}
