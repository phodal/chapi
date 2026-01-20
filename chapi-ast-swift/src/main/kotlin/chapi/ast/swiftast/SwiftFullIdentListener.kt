package chapi.ast.swiftast

import chapi.ast.antlr.Swift5Parser
import chapi.ast.antlr.Swift5ParserBaseListener
import chapi.domain.core.*
import org.antlr.v4.runtime.ParserRuleContext

class SwiftFullIdentListener(private val filePath: String) : Swift5ParserBaseListener() {
    private val imports = mutableListOf<CodeImport>()
    private val dataStructures = mutableListOf<CodeDataStruct>()
    private val stack = ArrayDeque<CodeDataStruct>()
    private val topLevelFunctions = mutableListOf<CodeFunction>()
    private val topLevelFields = mutableListOf<CodeField>()
    
    // Current context tracking
    private var currentFunction: CodeFunction? = null

    // ==================== Import Declaration ====================
    
    override fun enterImport_declaration(ctx: Swift5Parser.Import_declarationContext) {
        val source = ctx.import_path()?.text ?: return
        val kind = ctx.import_kind()?.text
        
        val codeImport = CodeImport(
            Source = source,
            UsageName = listOf(source.split(".").last()),
            PathSegments = source.split(".")
        )
        
        // If import kind is specified (class, struct, enum, etc.)
        if (kind != null) {
            codeImport.Scope = kind
        }
        
        imports += codeImport
    }

    // ==================== Attribute and Modifier Helpers ====================
    
    private fun buildAnnotationsFromAttributes(ctx: Swift5Parser.AttributesContext?): List<CodeAnnotation> {
        return ctx?.attribute()?.map { buildAnnotation(it) } ?: listOf()
    }
    
    private fun buildAnnotation(ctx: Swift5Parser.AttributeContext): CodeAnnotation {
        val name = ctx.attribute_name()?.text ?: ""
        val keyValues = mutableListOf<AnnotationKeyValue>()
        
        // Parse attribute arguments if present
        ctx.attribute_argument_clause()?.balanced_tokens()?.balanced_token()?.forEach { token ->
            keyValues.add(AnnotationKeyValue(Key = token.text, Value = ""))
        }
        
        return CodeAnnotation(
            Name = name,
            KeyValues = keyValues,
            Position = ctx.toPosition()
        )
    }
    
    private fun buildAccessLevelModifier(ctx: Swift5Parser.Access_level_modifierContext?): String {
        if (ctx == null) return ""
        
        val base = when {
            ctx.PRIVATE() != null -> "private"
            ctx.FILE_PRIVATE() != null -> "fileprivate"
            ctx.INTERNAL() != null -> "internal"
            ctx.PUBLIC() != null -> "public"
            ctx.OPEN() != null -> "open"
            else -> ""
        }
        
        // Check for (set) suffix
        return if (ctx.SET() != null) {
            "$base(set)"
        } else {
            base
        }
    }
    
    private fun buildModifiersFromDeclarationModifiers(ctx: Swift5Parser.Declaration_modifiersContext?): List<String> {
        return ctx?.declaration_modifier()?.mapNotNull { modCtx ->
            // Check for access level modifier
            modCtx.access_level_modifier()?.let { return@mapNotNull buildAccessLevelModifier(it) }
            
            // Check for mutation modifier
            modCtx.mutation_modifier()?.let { return@mapNotNull it.text }
            
            // Other modifiers (static, final, lazy, etc.)
            modCtx.text.takeIf { it.isNotEmpty() }
        } ?: listOf()
    }

    // ==================== Struct Declaration ====================

    override fun enterStruct_declaration(ctx: Swift5Parser.Struct_declarationContext) {
        val name = ctx.struct_name()?.text ?: return
        
        // Extract attributes and access modifier directly from context
        val annotations = buildAnnotationsFromAttributes(ctx.attributes())
        val accessModifier = buildAccessLevelModifier(ctx.access_level_modifier())
        val modifierAnnotations = if (accessModifier.isNotEmpty()) {
            listOf(CodeAnnotation(Name = accessModifier))
        } else {
            listOf()
        }
        
        val struct = CodeDataStruct(
            NodeName = name,
            Type = DataStructType.STRUCT,
            FilePath = filePath,
            Position = ctx.toPosition(),
            Annotations = annotations + modifierAnnotations
        )
        
        // Parse inheritance
        ctx.type_inheritance_clause()?.let { inheritanceCtx ->
            struct.Implements = buildInheritanceList(inheritanceCtx)
        }
        
        stack.addLast(struct)
    }

    override fun exitStruct_declaration(ctx: Swift5Parser.Struct_declarationContext) {
        finishDataStruct()
    }

    // ==================== Class Declaration ====================

    override fun enterClass_declaration(ctx: Swift5Parser.Class_declarationContext) {
        val name = ctx.class_name()?.text ?: return
        
        // Extract attributes and modifiers directly from context
        val annotations = buildAnnotationsFromAttributes(ctx.attributes())
        val modifiers = extractClassModifiers(ctx)
        
        val modifierAnnotations = modifiers.filter { it.isNotEmpty() }.map { CodeAnnotation(Name = it) }
        
        val classStruct = CodeDataStruct(
            NodeName = name,
            Type = DataStructType.CLASS,
            FilePath = filePath,
            Position = ctx.toPosition(),
            Annotations = annotations + modifierAnnotations
        )
        
        // Parse inheritance
        ctx.type_inheritance_clause()?.let { inheritanceCtx ->
            val inheritanceList = buildInheritanceList(inheritanceCtx)
            if (inheritanceList.isNotEmpty()) {
                // First item is typically the superclass for classes
                classStruct.Extend = inheritanceList.first()
                if (inheritanceList.size > 1) {
                    classStruct.Implements = inheritanceList.drop(1)
                }
            }
        }
        
        stack.addLast(classStruct)
    }
    
    private fun extractClassModifiers(ctx: Swift5Parser.Class_declarationContext): List<String> {
        val modifiers = mutableListOf<String>()
        
        // Parse from context text to extract modifiers
        // The grammar is: attributes? (access_level_modifier? FINAL? | FINAL access_level_modifier?) CLASS
        val text = ctx.text
        val classKeywordIndex = text.indexOf("class")
        if (classKeywordIndex > 0) {
            val prefix = text.substring(0, classKeywordIndex).lowercase()
            if (prefix.contains("public")) modifiers.add("public")
            if (prefix.contains("private")) modifiers.add("private")
            if (prefix.contains("internal")) modifiers.add("internal")
            if (prefix.contains("fileprivate")) modifiers.add("fileprivate")
            if (prefix.contains("open")) modifiers.add("open")
            if (prefix.contains("final")) modifiers.add("final")
        }
        
        return modifiers
    }

    override fun exitClass_declaration(ctx: Swift5Parser.Class_declarationContext) {
        finishDataStruct()
    }

    // ==================== Actor Declaration (Swift 5.5+) ====================

    override fun enterActor_declaration(ctx: Swift5Parser.Actor_declarationContext) {
        val name = ctx.actor_name()?.text ?: return
        
        // Extract attributes and modifiers
        val annotations = buildAnnotationsFromAttributes(ctx.attributes())
        val accessModifier = buildAccessLevelModifier(ctx.access_level_modifier())
        val modifiers = mutableListOf<String>()
        
        if (accessModifier.isNotEmpty()) {
            modifiers.add(accessModifier)
        }
        
        // Check for distributed modifier
        if (ctx.DISTRIBUTED() != null) {
            modifiers.add("distributed")
        }
        
        val modifierAnnotations = modifiers.map { CodeAnnotation(Name = it) }
        
        val actorStruct = CodeDataStruct(
            NodeName = name,
            Type = DataStructType.CLASS, // Actor is similar to class
            FilePath = filePath,
            Position = ctx.toPosition(),
            Annotations = annotations + modifierAnnotations
        )
        
        // Parse inheritance
        ctx.type_inheritance_clause()?.let { inheritanceCtx ->
            actorStruct.Implements = buildInheritanceList(inheritanceCtx)
        }
        
        stack.addLast(actorStruct)
    }
    
    override fun exitActor_declaration(ctx: Swift5Parser.Actor_declarationContext) {
        finishDataStruct()
    }

    // ==================== Enum Declaration ====================

    override fun enterEnum_declaration(ctx: Swift5Parser.Enum_declarationContext) {
        val name = ctx.union_style_enum()?.enum_name()?.text
            ?: ctx.raw_value_style_enum()?.enum_name()?.text
            ?: return
        
        // Extract attributes and access modifier directly from context
        val annotations = buildAnnotationsFromAttributes(ctx.attributes())
        val accessModifier = buildAccessLevelModifier(ctx.access_level_modifier())
        val modifierAnnotations = if (accessModifier.isNotEmpty()) {
            listOf(CodeAnnotation(Name = accessModifier))
        } else {
            listOf()
        }
            
        val enumStruct = CodeDataStruct(
            NodeName = name,
            Type = DataStructType.ENUM,
            FilePath = filePath,
            Position = ctx.toPosition(),
            Annotations = annotations + modifierAnnotations
        )
        
        // Parse inheritance/raw value type
        ctx.union_style_enum()?.type_inheritance_clause()?.let { inheritanceCtx ->
            enumStruct.Implements = buildInheritanceList(inheritanceCtx)
        }
        ctx.raw_value_style_enum()?.type_inheritance_clause()?.let { inheritanceCtx ->
            val inheritanceList = buildInheritanceList(inheritanceCtx)
            if (inheritanceList.isNotEmpty()) {
                // First is typically the raw value type
                enumStruct.Extend = inheritanceList.first()
                if (inheritanceList.size > 1) {
                    enumStruct.Implements = inheritanceList.drop(1)
                }
            }
        }
        
        // Parse enum cases
        ctx.union_style_enum()?.union_style_enum_members()?.union_style_enum_member()?.forEach { member ->
            member.union_style_enum_case_clause()?.union_style_enum_case_list()?.union_style_enum_case()?.forEach { caseCtx ->
                val caseName = caseCtx.enum_case_name()?.text ?: return@forEach
                enumStruct.Fields += CodeField(
                    TypeKey = caseName,
                    TypeType = caseCtx.tuple_type()?.text ?: ""
                )
            }
        }
        
        ctx.raw_value_style_enum()?.raw_value_style_enum_members()?.raw_value_style_enum_member()?.forEach { member ->
            member.raw_value_style_enum_case_clause()?.raw_value_style_enum_case_list()?.raw_value_style_enum_case()?.forEach { caseCtx ->
                val caseName = caseCtx.enum_case_name()?.text ?: return@forEach
                val rawValue = caseCtx.raw_value_assignment()?.raw_value_literal()?.text ?: ""
                enumStruct.Fields += CodeField(
                    TypeKey = caseName,
                    TypeValue = rawValue
                )
            }
        }
        
        stack.addLast(enumStruct)
    }

    override fun exitEnum_declaration(ctx: Swift5Parser.Enum_declarationContext) {
        finishDataStruct()
    }

    // ==================== Protocol Declaration ====================

    override fun enterProtocol_declaration(ctx: Swift5Parser.Protocol_declarationContext) {
        val name = ctx.protocol_name()?.text ?: return
        
        // Extract attributes and access modifier directly from context
        val annotations = buildAnnotationsFromAttributes(ctx.attributes())
        val accessModifier = buildAccessLevelModifier(ctx.access_level_modifier())
        val modifierAnnotations = if (accessModifier.isNotEmpty()) {
            listOf(CodeAnnotation(Name = accessModifier))
        } else {
            listOf()
        }
        
        val protocol = CodeDataStruct(
            NodeName = name,
            Type = DataStructType.INTERFACE, // Protocol is similar to Interface
            FilePath = filePath,
            Position = ctx.toPosition(),
            Annotations = annotations + modifierAnnotations
        )
        
        // Parse inheritance (protocol can inherit from other protocols)
        ctx.type_inheritance_clause()?.let { inheritanceCtx ->
            protocol.Implements = buildInheritanceList(inheritanceCtx)
        }
        
        stack.addLast(protocol)
    }
    
    override fun exitProtocol_declaration(ctx: Swift5Parser.Protocol_declarationContext) {
        finishDataStruct()
    }

    // ==================== Extension Declaration ====================

    override fun enterExtension_declaration(ctx: Swift5Parser.Extension_declarationContext) {
        val typeName = ctx.type_identifier()?.text ?: return
        
        // Extract attributes and access modifier directly from context
        val annotations = buildAnnotationsFromAttributes(ctx.attributes())
        val accessModifier = buildAccessLevelModifier(ctx.access_level_modifier())
        val modifierAnnotations = if (accessModifier.isNotEmpty()) {
            listOf(CodeAnnotation(Name = accessModifier))
        } else {
            listOf()
        }
        
        val extension = CodeDataStruct(
            NodeName = typeName,
            Type = DataStructType.CLASS, // Extension extends existing type
            FilePath = filePath,
            Position = ctx.toPosition(),
            Annotations = annotations + modifierAnnotations
        )
        
        // Parse protocol conformance
        ctx.type_inheritance_clause()?.let { inheritanceCtx ->
            extension.Implements = buildInheritanceList(inheritanceCtx)
        }
        
        stack.addLast(extension)
    }
    
    override fun exitExtension_declaration(ctx: Swift5Parser.Extension_declarationContext) {
        finishDataStruct()
    }

    // ==================== Function Declaration ====================

    override fun enterFunction_declaration(ctx: Swift5Parser.Function_declarationContext) {
        val name = ctx.function_name()?.text ?: return

        val returnTypeText = ctx.function_signature()
            ?.function_result()
            ?.type()
            ?.text
            ?.takeIf { it.isNotBlank() }

        // Extract attributes and modifiers from function_head
        val functionHead = ctx.function_head()
        val annotations = buildAnnotationsFromAttributes(functionHead?.attributes())
        val modifiers = buildModifiersFromDeclarationModifiers(functionHead?.declaration_modifiers()).toMutableList()

        val function = CodeFunction(
            Name = name,
            ReturnType = returnTypeText ?: "",
            ReturnTypeRef = returnTypeText?.let { SwiftTypeRefBuilder.build(it) },
            Position = ctx.toPosition(),
            Annotations = annotations,
            Modifiers = modifiers
        )
        
        // Parse parameters
        ctx.function_signature()?.parameter_clause()?.parameter_list()?.parameter()?.forEach { paramCtx ->
            function.Parameters += buildParameter(paramCtx)
        }
        
        // Check for async (Swift 5.5+)
        ctx.function_signature()?.async_clause()?.let {
            function.Modifiers = function.Modifiers + "async"
        }
        
        // Check for throws/rethrows with typed throws support (Swift 6)
        ctx.function_signature()?.throw_clause()?.let { throwClause ->
            if (throwClause.RETHROWS() != null) {
                function.Modifiers = function.Modifiers + "rethrows"
            } else if (throwClause.THROWS() != null) {
                // Check for typed throws: throws(ErrorType)
                val errorType = throwClause.type()?.text
                if (errorType != null) {
                    function.Modifiers = function.Modifiers + "throws($errorType)"
                } else {
                    function.Modifiers = function.Modifiers + "throws"
                }
            }
        }
        
        currentFunction = function

        if (stack.isEmpty()) {
            topLevelFunctions += function
        } else {
            val current = stack.last()
            current.Functions = current.Functions + function
        }
    }
    
    override fun exitFunction_declaration(ctx: Swift5Parser.Function_declarationContext) {
        currentFunction = null
    }

    // ==================== Initializer Declaration ====================

    override fun enterInitializer_declaration(ctx: Swift5Parser.Initializer_declarationContext) {
        // Extract attributes and modifiers from initializer_head
        val initHead = ctx.initializer_head()
        val annotations = buildAnnotationsFromAttributes(initHead?.attributes())
        val modifiers = buildModifiersFromDeclarationModifiers(initHead?.declaration_modifiers())
        
        val function = CodeFunction(
            Name = "init",
            IsConstructor = true,
            Position = ctx.toPosition(),
            Annotations = annotations,
            Modifiers = modifiers
        )
        
        // Check for failable initializer
        initHead?.let { head ->
            if (head.QUESTION() != null) {
                function.Name = "init?"
            }
            if (head.BANG() != null) {
                function.Name = "init!"
            }
        }
        
        // Parse parameters
        ctx.parameter_clause()?.parameter_list()?.parameter()?.forEach { paramCtx ->
            function.Parameters += buildParameter(paramCtx)
        }
        
        // Check for throws/rethrows
        if (ctx.THROWS() != null) {
            function.Modifiers = function.Modifiers + "throws"
        }
        if (ctx.RETHROWS() != null) {
            function.Modifiers = function.Modifiers + "rethrows"
        }
        
        currentFunction = function

        if (stack.isNotEmpty()) {
            val current = stack.last()
            current.Functions = current.Functions + function
        }
    }
    
    override fun exitInitializer_declaration(ctx: Swift5Parser.Initializer_declarationContext) {
        currentFunction = null
    }

    // ==================== Deinitializer Declaration ====================

    override fun enterDeinitializer_declaration(ctx: Swift5Parser.Deinitializer_declarationContext) {
        // Extract attributes
        val annotations = buildAnnotationsFromAttributes(ctx.attributes())
        
        val function = CodeFunction(
            Name = "deinit",
            Position = ctx.toPosition(),
            Annotations = annotations
        )

        if (stack.isNotEmpty()) {
            val current = stack.last()
            current.Functions = current.Functions + function
        }
    }

    // ==================== Variable Declaration ====================

    override fun enterVariable_declaration(ctx: Swift5Parser.Variable_declarationContext) {
        // Extract attributes and modifiers from variable_declaration_head
        val varHead = ctx.variable_declaration_head()
        val annotations = buildAnnotationsFromAttributes(varHead?.attributes())
        val modifiers = buildModifiersFromDeclarationModifiers(varHead?.declaration_modifiers())
        
        val varName = ctx.variable_name()?.text
        
        if (varName != null) {
            // Single variable with type annotation
            val typeAnnotation = ctx.type_annotation()?.type()?.text ?: ""
            val initialValue = ctx.initializer()?.expression()?.text ?: ""
            
            val field = CodeField(
                TypeKey = varName,
                TypeType = typeAnnotation,
                TypeValue = initialValue,
                Annotations = annotations,
                Modifiers = modifiers + "var",
                TypeRef = if (typeAnnotation.isNotEmpty()) SwiftTypeRefBuilder.build(typeAnnotation) else null
            )
            
            addFieldToCurrentScope(field)
        } else {
            // Pattern initializer list (e.g., var a = 1, b = 2)
            ctx.pattern_initializer_list()?.pattern_initializer()?.forEach { patternInit ->
                val pattern = patternInit.pattern()
                val name = pattern?.identifier_pattern()?.text ?: pattern?.text ?: return@forEach
                val typeAnnotation = pattern?.type_annotation()?.type()?.text ?: ""
                val initialValue = patternInit.initializer()?.expression()?.text ?: ""
                
                val field = CodeField(
                    TypeKey = name,
                    TypeType = typeAnnotation,
                    TypeValue = initialValue,
                    Annotations = annotations,
                    Modifiers = modifiers + "var",
                    TypeRef = if (typeAnnotation.isNotEmpty()) SwiftTypeRefBuilder.build(typeAnnotation) else null
                )
                
                addFieldToCurrentScope(field)
            }
        }
    }

    // ==================== Constant Declaration ====================

    override fun enterConstant_declaration(ctx: Swift5Parser.Constant_declarationContext) {
        // Extract attributes and modifiers
        val annotations = buildAnnotationsFromAttributes(ctx.attributes())
        val modifiers = buildModifiersFromDeclarationModifiers(ctx.declaration_modifiers())
        
        ctx.pattern_initializer_list()?.pattern_initializer()?.forEach { patternInit ->
            val pattern = patternInit.pattern()
            val name = pattern?.identifier_pattern()?.text ?: pattern?.text ?: return@forEach
            val typeAnnotation = pattern?.type_annotation()?.type()?.text ?: ""
            val initialValue = patternInit.initializer()?.expression()?.text ?: ""
            
            val field = CodeField(
                TypeKey = name,
                TypeType = typeAnnotation,
                TypeValue = initialValue,
                Annotations = annotations,
                Modifiers = modifiers + "let",
                TypeRef = if (typeAnnotation.isNotEmpty()) SwiftTypeRefBuilder.build(typeAnnotation) else null
            )
            
            addFieldToCurrentScope(field)
        }
    }

    // ==================== Protocol Member Declarations ====================

    override fun enterProtocol_method_declaration(ctx: Swift5Parser.Protocol_method_declarationContext) {
        val name = ctx.function_name()?.text ?: return
        
        // Extract attributes and modifiers from function_head
        val functionHead = ctx.function_head()
        val annotations = buildAnnotationsFromAttributes(functionHead?.attributes())
        val modifiers = buildModifiersFromDeclarationModifiers(functionHead?.declaration_modifiers())
        
        val function = CodeFunction(
            Name = name,
            Position = ctx.toPosition(),
            Annotations = annotations,
            Modifiers = modifiers
        )
        
        // Parse return type
        ctx.function_signature()?.function_result()?.type()?.text?.let { returnType ->
            function.ReturnType = returnType
            function.ReturnTypeRef = SwiftTypeRefBuilder.build(returnType)
        }
        
        // Parse parameters
        ctx.function_signature()?.parameter_clause()?.parameter_list()?.parameter()?.forEach { paramCtx ->
            function.Parameters += buildParameter(paramCtx)
        }

        if (stack.isNotEmpty()) {
            val current = stack.last()
            current.Functions = current.Functions + function
        }
    }

    override fun enterProtocol_property_declaration(ctx: Swift5Parser.Protocol_property_declarationContext) {
        val varName = ctx.variable_name()?.text ?: return
        val typeAnnotation = ctx.type_annotation()?.type()?.text ?: ""
        
        // Extract attributes and modifiers from variable_declaration_head
        val varHead = ctx.variable_declaration_head()
        val annotations = buildAnnotationsFromAttributes(varHead?.attributes())
        val modifiers = buildModifiersFromDeclarationModifiers(varHead?.declaration_modifiers())
        
        val field = CodeField(
            TypeKey = varName,
            TypeType = typeAnnotation,
            Annotations = annotations,
            Modifiers = modifiers,
            TypeRef = if (typeAnnotation.isNotEmpty()) SwiftTypeRefBuilder.build(typeAnnotation) else null
        )
        
        // Check getter/setter requirements
        ctx.getter_setter_keyword_block()?.let { block ->
            if (block.getter_keyword_clause() != null) {
                field.Modifiers = field.Modifiers + "get"
            }
            if (block.setter_keyword_clause() != null) {
                field.Modifiers = field.Modifiers + "set"
            }
        }
        
        addFieldToCurrentScope(field)
    }

    // ==================== Function Call Expression ====================

    override fun enterFunction_call_suffix(ctx: Swift5Parser.Function_call_suffixContext) {
        if (currentFunction == null) return
        
        // Get the function call context
        val postfixExpr = ctx.parent as? Swift5Parser.Postfix_expressionContext ?: return
        val primaryExpr = postfixExpr.primary_expression()
        
        val functionName = primaryExpr?.unqualified_name()?.identifier()?.text ?: primaryExpr?.text ?: return
        
        val call = CodeCall(
            FunctionName = functionName,
            Position = ctx.toPosition()
        )
        
        // Parse arguments
        ctx.function_call_argument_clause()?.function_call_argument_list()?.function_call_argument()?.forEach { argCtx ->
            val argName = argCtx.argument_name()?.identifier()?.text ?: ""
            val argValue = argCtx.expression()?.text ?: argCtx.identifier()?.text ?: ""
            
            call.Parameters += CodeProperty(
                TypeValue = argValue,
                TypeType = argName
            )
        }
        
        currentFunction?.let { func ->
            func.FunctionCalls = func.FunctionCalls + call
        }
    }

    // ==================== Helper Methods ====================

    private fun buildParameter(ctx: Swift5Parser.ParameterContext): CodeProperty {
        val externalName = ctx.external_parameter_name()?.text ?: ""
        val localName = ctx.local_parameter_name()?.text ?: ""
        val typeAnnotation = ctx.type_annotation()?.type()?.text ?: ""
        val defaultValue = ctx.default_argument_clause()?.expression()?.text ?: ""
        
        // Build annotations from parameter attributes
        val annotations = ctx.attributes()?.attribute()?.map { buildAnnotation(it) } ?: listOf()
        
        // Check for ownership modifier (Swift 6: consuming, borrowing, inout)
        val modifiers = mutableListOf<String>()
        ctx.ownership_modifier()?.let { ownershipCtx ->
            when {
                ownershipCtx.CONSUMING() != null -> modifiers.add("consuming")
                ownershipCtx.BORROWING() != null -> modifiers.add("borrowing")
                ownershipCtx.INOUT() != null -> modifiers.add("inout")
                else -> { /* no modifier */ }
            }
        }
        
        return CodeProperty(
            TypeValue = if (externalName.isNotEmpty() && externalName != localName) {
                "$externalName $localName"
            } else {
                localName
            },
            TypeType = typeAnnotation,
            DefaultValue = defaultValue,
            Annotations = annotations,
            Modifiers = modifiers,
            TypeRef = if (typeAnnotation.isNotEmpty()) SwiftTypeRefBuilder.build(typeAnnotation) else null
        )
    }
    
    private fun buildInheritanceList(ctx: Swift5Parser.Type_inheritance_clauseContext): List<String> {
        // Swift 6: support ~Copyable syntax in inheritance
        return ctx.type_inheritance_list()?.type_inheritance_item()?.mapNotNull { item ->
            val typeText = item.type_identifier()?.text ?: return@mapNotNull null
            // Check if it's a noncopyable type (~Copyable)
            if (item.TILDE() != null) {
                "~$typeText"
            } else {
                typeText
            }
        } ?: listOf()
    }
    
    private fun addFieldToCurrentScope(field: CodeField) {
        if (stack.isEmpty()) {
            topLevelFields += field
        } else {
            val current = stack.last()
            current.Fields = current.Fields + field
        }
    }

    fun getNodeInfo(): CodeContainer {
        val topLevel = if (topLevelFunctions.isEmpty() && topLevelFields.isEmpty()) {
            null
        } else {
            TopLevelScope(
                Functions = topLevelFunctions.toList(),
                Fields = topLevelFields.toList()
            )
        }

        return CodeContainer(
            FullName = filePath,
            PackageName = "",
            Imports = imports.toList(),
            DataStructures = dataStructures.toList(),
            Language = "swift",
            Kind = ContainerKind.SOURCE_FILE,
            DeclaredPackage = "",
            TopLevel = topLevel,
        )
    }

    private fun finishDataStruct() {
        if (stack.isEmpty()) return
        val finished = stack.removeLast()
        if (stack.isEmpty()) {
            dataStructures += finished
        } else {
            val parent = stack.last()
            parent.InnerStructures = parent.InnerStructures + finished
        }
    }
}

private fun ParserRuleContext.toPosition(): CodePosition {
    val startToken = this.start
    val stopToken = this.stop
    return CodePosition(
        StartLine = startToken?.line ?: 0,
        StartLinePosition = startToken?.charPositionInLine ?: 0,
        StopLine = stopToken?.line ?: 0,
        StopLinePosition = stopToken?.charPositionInLine ?: 0,
    )
}
