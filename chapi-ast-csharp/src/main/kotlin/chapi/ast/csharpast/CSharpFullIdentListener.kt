package chapi.ast.csharpast

import chapi.ast.antlr.CSharpParser
import chapi.domain.core.*
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.tree.TerminalNodeImpl

class CSharpFullIdentListener(fileName: String) : CSharpAstListener(fileName) {
    // Track local variables for type resolution
    private var localVars = mutableMapOf<String, String>()
    // Track class fields for type resolution
    private var fieldsMap = mutableMapOf<String, String>()
    
    private fun handleClassMember(memberCtx: CSharpParser.Class_member_declarationContext?) {
        val memberDeclaration = memberCtx!!.common_member_declaration() ?: return
        val firstChild = memberDeclaration.getChild(0) ?: return

        var returnType = "";
        when (firstChild) {
            is TerminalNodeImpl -> {
                returnType = firstChild.text
            }
        }

        val annotations = parseAnnotations(memberCtx.attributes())
        val modifiers = buildFunctionModifiers(memberCtx)

        if (memberDeclaration.typed_member_declaration() != null) {
            val typedMember = memberDeclaration.typed_member_declaration()

            val methodDeclaration = typedMember.method_declaration()
            if (methodDeclaration != null) {
                val typeContext = typedMember.type_()
                if (typeContext != null) {
                    returnType = typeContext.text
                }

                currentFunction = createFunction(
                    returnType,
                    annotations,
                    currentStruct.Package,
                    modifiers,
                    methodDeclaration.method_member_name().text,
                    methodDeclaration.formal_parameter_list()
                )
            }

            val propertyDecl = typedMember.property_declaration()
            if (propertyDecl != null) {
                val typeContext = typedMember.type_()
                if (typeContext != null) {
                    returnType = typeContext.text
                }

                currentFunction = createFunction(
                    returnType,
                    annotations,
                    currentStruct.Package,
                    modifiers,
                    propertyDecl.member_name().text,
                    null
                )
            }

        }

        if (memberDeclaration.constructor_declaration() != null) {
            val constructorDecl = memberDeclaration.constructor_declaration()

            currentFunction = createFunction(
                returnType,
                annotations,
                currentStruct.Package,
                modifiers,
                constructorDecl.identifier().text,
                constructorDecl.formal_parameter_list()
            )
        }

        val methodDecl = memberDeclaration.method_declaration()
        if (methodDecl != null) {
            currentFunction = createFunction(
                returnType,
                annotations,
                currentStruct.Package,
                modifiers,
                methodDecl.method_member_name().text,
                methodDecl.formal_parameter_list()
            )
        }
    }

    override fun enterConstructor_declaration(ctx: CSharpParser.Constructor_declarationContext?) {
        buildForMethodDecl(ctx)
    }

    override fun enterTyped_member_declaration(ctx: CSharpParser.Typed_member_declarationContext?) {
        buildForMethodDecl(ctx)
    }

    override fun exitTyped_member_declaration(ctx: CSharpParser.Typed_member_declarationContext?) {
        if (currentFunction.Name.isNotEmpty()) {
            localVars.clear()
            currentStruct.Functions += currentFunction
        }

        currentFunction = CodeFunction()
    }

    override fun enterMethod_declaration(ctx: CSharpParser.Method_declarationContext?) {
        buildForMethodDecl(ctx)
    }

    private fun buildForMethodDecl(ctx: ParserRuleContext?) {
        if (ctx == null) return

        var commonMemberForClass: CSharpParser.Common_member_declarationContext? = null
        when (val parent = ctx.parent) {
            is CSharpParser.Common_member_declarationContext -> {
                commonMemberForClass = parent
            }

            is CSharpParser.Class_member_declarationContext -> {
                commonMemberForClass = parent.parent as CSharpParser.Common_member_declarationContext
            }
        }

        if (commonMemberForClass == null) return

        if (commonMemberForClass.parent is CSharpParser.Class_member_declarationContext) {
            handleClassMember(commonMemberForClass.parent as CSharpParser.Class_member_declarationContext?)
        }
    }

    override fun enterProperty_declaration(ctx: CSharpParser.Property_declarationContext?) {
        when (val parent = ctx?.parent) {
            is CSharpParser.Typed_member_declarationContext -> {
                val typeValue = ctx.member_name().text
                val typeContext = parent.type_()

                val field = createField(typeValue, typeContext)

                currentStruct.Fields += field
            }
        }
    }

    private fun createField(typeValue: String, typeContext: CSharpParser.Type_Context): CodeField {
        val field = CodeField(TypeValue = typeValue)
        field.TypeType = typeContext.text
        field.TypeRef = CSharpTypeRefBuilder.build(typeContext)

        when (val child = typeContext.base_type().getChild(0)) {
            is CSharpParser.Class_typeContext -> {
                val nsOrType = child.namespace_or_type_name()
                if (nsOrType != null) {
                    field.Modifiers = listOf(nsOrType.identifier()[0].text)
                    if (nsOrType.type_argument_list() != null) {
                        nsOrType.type_argument_list()
                            .asSequence()
                            .flatMap { it.type_().asSequence() }
                            .forEach { field.TypeType = it.text }
                    }
                }
            }
        }

        return field
    }

    override fun enterField_declaration(ctx: CSharpParser.Field_declarationContext?) {
        val variableDeclarators = ctx?.variable_declarators() ?: return
        
        // Get the type from the parent typed_member_declaration
        val parent = ctx.parent
        if (parent is CSharpParser.Typed_member_declarationContext) {
            val typeCtx = parent.type_()
            val typeText = typeCtx?.text ?: ""
            
            variableDeclarators.variable_declarator().forEach { varDecl ->
                val fieldName = varDecl.identifier()?.text ?: ""
                val fieldValue = varDecl.variable_initializer()?.text ?: ""
                
                if (fieldName.isNotEmpty()) {
                    fieldsMap[fieldName] = typeText
                    
                    val field = CodeField(
                        TypeType = typeText,
                        TypeValue = fieldValue,
                        TypeKey = fieldName,
                        TypeRef = CSharpTypeRefBuilder.build(typeCtx)
                    )
                    currentStruct.Fields += field
                }
            }
        }
    }

    // call from method invocation parent will be easy to search for method call
    override fun enterMethod_invocation(ctx: CSharpParser.Method_invocationContext?) {
        val parent = ctx?.parent ?: return
        val primaryExpr = when (parent) {
            is CSharpParser.Primary_expressionContext -> {
                ctx.parent as CSharpParser.Primary_expressionContext
            }

            else -> {
                null
            }
        } ?: return

        var ident = ""
        var member = ""
        var params: List<CodeProperty> = listOf()
        val memberAccessChain = mutableListOf<String>()
        var isThisAccess = false
        
        primaryExpr.children.forEach {
            when (it) {
                is CSharpParser.SimpleNameExpressionContext -> {
                    ident = it.identifier().text
                }

                is CSharpParser.ThisReferenceExpressionContext -> {
                    isThisAccess = true
                    ident = "this"
                }

                is CSharpParser.Member_accessContext -> {
                    val memberName = it.identifier()?.text ?: ""
                    if (memberName.isNotEmpty()) {
                        memberAccessChain.add(memberName)
                    }
                }

                is CSharpParser.Method_invocationContext -> {
                    it.argument_list()?.let { argumentList ->
                        params = parseParameters(argumentList)
                    }
                }
            }
        }

        // Determine function name and node name from the chain
        if (memberAccessChain.isNotEmpty()) {
            member = memberAccessChain.last()
            
            // Build node name from identifier and intermediate members
            val nodeNameParts = mutableListOf<String>()
            if (isThisAccess) {
                // For this.field.Method(), resolve field type if possible
                if (memberAccessChain.size > 1) {
                    val fieldName = memberAccessChain.first()
                    val resolvedType = fieldsMap[fieldName] ?: fieldName
                    nodeNameParts.add(resolvedType)
                    nodeNameParts.addAll(memberAccessChain.subList(1, memberAccessChain.size - 1))
                }
            } else if (ident.isNotEmpty()) {
                nodeNameParts.add(ident)
                if (memberAccessChain.size > 1) {
                    nodeNameParts.addAll(memberAccessChain.subList(0, memberAccessChain.size - 1))
                }
            }
            ident = nodeNameParts.joinToString(".")
        } else if (ident.isNotEmpty() && member.isEmpty()) {
            // Simple function call without member access
            member = ident
            ident = ""
        }

        // Resolve package from imports if available
        val resolvedPackage = resolvePackage(ident)
        
        val codeCall = CodeCall(
            Package = resolvedPackage,
            NodeName = ident,
            FunctionName = member,
            Parameters = params,
            // New structured fields
            ReceiverExpr = ident,
            Callee = member
        )
        currentFunction.FunctionCalls += codeCall
    }
    
    private fun resolvePackage(nodeName: String): String {
        if (nodeName.isEmpty()) return ""
        
        // Check if nodeName matches any import
        codeContainer.Imports.forEach { import ->
            if (import.Source.endsWith(".$nodeName") || import.Source == nodeName) {
                return import.Source
            }
        }
        
        // Check if it's in the same namespace
        if (currentNamespace.isNotEmpty()) {
            return "$currentNamespace.$nodeName"
        }
        
        return ""
    }

    private fun parseParameters(argumentList: CSharpParser.Argument_listContext): List<CodeProperty> {
        return argumentList.argument().map {
            CodeProperty(
                TypeType = it.refout?.text ?: "",
                TypeValue = it.expression().text
            )
        }
    }
    
    // Parse local variable declarations
    override fun enterLocal_variable_declaration(ctx: CSharpParser.Local_variable_declarationContext?) {
        val localVarType = ctx?.local_variable_type() ?: return
        val typeText = localVarType.text
        
        ctx.local_variable_declarator().forEach { varDecl ->
            val varName = varDecl.identifier()?.text ?: ""
            if (varName.isNotEmpty()) {
                // For 'var' keyword, try to infer type from initializer
                val actualType = if (typeText == "var") {
                    inferTypeFromInitializer(varDecl.local_variable_initializer()) ?: "var"
                } else {
                    typeText
                }
                localVars[varName] = actualType
                
                // Add to function's local variables
                currentFunction.LocalVariables += CodeProperty(
                    TypeValue = varName,
                    TypeType = actualType
                )
            }
        }
    }
    
    private fun inferTypeFromInitializer(initCtx: CSharpParser.Local_variable_initializerContext?): String? {
        initCtx ?: return null
        
        // Try to infer from 'new Type()' expressions
        val expression = initCtx.expression()
        if (expression != null) {
            val text = expression.text
            if (text.startsWith("new")) {
                // Extract type name from 'new TypeName(...)'
                val match = Regex("new\\s*([A-Za-z_][A-Za-z0-9_<>]*)").find(text)
                return match?.groupValues?.get(1)
            }
        }
        
        return null
    }
    
    // Clear local variables when exiting a function
    override fun exitMethod_declaration(ctx: CSharpParser.Method_declarationContext?) {
        localVars.clear()
        currentStruct.Functions += currentFunction
        currentFunction = CodeFunction()
    }
    
    override fun exitConstructor_declaration(ctx: CSharpParser.Constructor_declarationContext?) {
        localVars.clear()
        currentStruct.Functions += currentFunction
        currentFunction = CodeFunction()
    }
    
    // Parse object creation expressions for type tracking
    // ObjectCreationExpressionContext contains NEW type_ and object_creation_expression
    override fun enterObjectCreationExpression(ctx: CSharpParser.ObjectCreationExpressionContext?) {
        if (ctx == null) return
        
        val typeText = ctx.type_()?.text ?: return
        
        // Get the object_creation_expression which contains arguments
        val objCreation = ctx.object_creation_expression()
        val params = objCreation?.argument_list()?.let { parseParameters(it) } ?: listOf()
        
        val codeCall = CodeCall(
            Package = resolvePackage(typeText),
            NodeName = typeText,
            FunctionName = "<init>",
            Type = CallType.CREATOR,
            Parameters = params,
            // New structured fields
            ReceiverExpr = typeText,
            Callee = "<init>"
        )
        
        currentFunction.FunctionCalls += codeCall
    }
}
