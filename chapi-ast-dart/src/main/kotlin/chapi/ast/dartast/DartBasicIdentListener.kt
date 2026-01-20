package chapi.ast.dartast

import chapi.ast.antlr.Dart2Parser
import chapi.domain.core.*

/**
 * Basic identifier listener for Dart code.
 * Extracts basic structural information: imports, classes, mixins, extensions, enums, and function signatures.
 */
open class DartBasicIdentListener(private val filePath: String) : DartAstListener() {

    private var codeContainer: CodeContainer = CodeContainer(
        FullName = filePath,
        Language = "dart",
        Kind = ContainerKind.SOURCE_FILE
    )

    private var classNodes: MutableList<CodeDataStruct> = mutableListOf()
    private var currentNode: CodeDataStruct = CodeDataStruct()
    private var currentFunction: CodeFunction = CodeFunction()
    private var hasEnterClass: Boolean = false

    // Track library name if specified
    override fun enterLibraryName(ctx: Dart2Parser.LibraryNameContext?) {
        ctx?.dottedIdentifierList()?.let {
            codeContainer.PackageName = it.text
            codeContainer.DeclaredPackage = it.text
        }
    }

    // Handle library imports
    override fun enterLibraryImport(ctx: Dart2Parser.LibraryImportContext?) {
        val importSpec = ctx?.importSpecification()
        val uri = importSpec?.configurableUri()?.uri()?.stringLiteral()?.text?.trim('"', '\'') ?: return

        val codeImport = CodeImport(Source = uri)
        codeImport.PathSegments = uri.split("/")

        // Handle 'as' prefix
        val asId = importSpec.identifier()
        if (asId != null) {
            codeImport.AsName = asId.text
        }

        // Handle show/hide combinators
        val combinators = importSpec.combinator()
        for (combinator in combinators) {
            val identifiers = combinator.identifierList()?.identifier()?.map { it.text } ?: continue
            if (combinator.SHOW_() != null) {
                codeImport.UsageName = identifiers
                codeImport.Kind = ImportKind.NAMED
            } else if (combinator.HIDE_() != null) {
                // Store hidden identifiers in metadata
                codeImport.Kind = ImportKind.NAMED
            }
        }

        if (codeImport.Kind == ImportKind.UNKNOWN) {
            codeImport.Kind = ImportKind.WILDCARD
        }

        codeContainer.Imports += codeImport
    }

    // Handle library exports
    override fun enterLibraryExport(ctx: Dart2Parser.LibraryExportContext?) {
        val uri = ctx?.configurableUri()?.uri()?.stringLiteral()?.text?.trim('"', '\'') ?: return

        // Create an export entry
        val codeExport = CodeExport(Name = uri)
        codeContainer.DataStructures.find { it.NodeName == "default" }?.Exports?.let {
            // Add to default struct if exists
        }
    }

    // Handle class declaration
    override fun enterClassDeclaration(ctx: Dart2Parser.ClassDeclarationContext?) {
        hasEnterClass = true

        val typeId = ctx?.typeIdentifier() ?: return
        
        // Determine class type based on modifiers (Dart 3 support)
        val modifiers = ctx.classModifier()
        val isAbstract = modifiers?.any { it.ABSTRACT_() != null } == true
        val isSealed = modifiers?.any { it.SEALED_() != null } == true
        val isBase = modifiers?.any { it.BASE_() != null } == true
        val isInterface = modifiers?.any { it.INTERFACE_() != null } == true
        val isFinal = modifiers?.any { it.FINAL_() != null } == true
        val isMixinClass = modifiers?.any { it.MIXIN_() != null } == true

        val classType = when {
            isSealed -> DataStructType.CLASS  // sealed classes are still classes
            isAbstract -> DataStructType.ABSTRACT_CLASS
            isInterface -> DataStructType.INTERFACE
            isMixinClass -> DataStructType.TRAIT  // mixin class is similar to trait
            else -> DataStructType.CLASS
        }

        currentNode = CodeDataStruct(
            NodeName = getTypeIdentifierText(typeId),
            Type = classType,
            Package = codeContainer.PackageName,
            FilePath = filePath
        )
        
        // Store class modifiers as annotations for additional context
        val modifierList = mutableListOf<String>()
        if (isSealed) modifierList.add("sealed")
        if (isBase) modifierList.add("base")
        if (isInterface) modifierList.add("interface")
        if (isFinal) modifierList.add("final")
        if (isAbstract) modifierList.add("abstract")
        if (isMixinClass) modifierList.add("mixin")
        
        if (modifierList.isNotEmpty()) {
            currentNode.Annotations = modifierList.map { 
                CodeAnnotation(Name = it) 
            }
        }
        
        currentNode.Position = buildPosition(ctx)

        // Handle extends
        ctx.superclass()?.let { superclass ->
            superclass.typeNotVoid()?.let {
                currentNode.Extend = it.text
            }
            // Handle mixins in superclass (e.g., extends Foo with Bar, Baz)
            superclass.mixins()?.typeNotVoidList()?.typeNotVoid()?.let { mixins ->
                currentNode.MultipleExtend = mixins.map { it.text }
            }
        }

        // Handle implements
        ctx.interfaces()?.typeNotVoidList()?.typeNotVoid()?.let { interfaces ->
            currentNode.Implements = interfaces.map { it.text }
        }
    }

    override fun exitClassDeclaration(ctx: Dart2Parser.ClassDeclarationContext?) {
        hasEnterClass = false
        if (currentNode.NodeName.isNotEmpty()) {
            classNodes.add(currentNode)
        }
        currentNode = CodeDataStruct()
    }

    // Handle mixin declaration
    override fun enterMixinDeclaration(ctx: Dart2Parser.MixinDeclarationContext?) {
        hasEnterClass = true

        val typeId = ctx?.typeIdentifier() ?: return
        
        // Check for Dart 3 mixin modifiers (base, sealed)
        val isBase = ctx.BASE_() != null
        val isSealed = ctx.SEALED_() != null
        
        currentNode = CodeDataStruct(
            NodeName = getTypeIdentifierText(typeId),
            Type = DataStructType.TRAIT, // Mixin is similar to trait
            Package = codeContainer.PackageName,
            FilePath = filePath
        )
        
        // Store mixin modifiers
        val modifierList = mutableListOf<String>("mixin")
        if (isBase) modifierList.add("base")
        if (isSealed) modifierList.add("sealed")
        currentNode.Annotations = modifierList.map { CodeAnnotation(Name = it) }
        
        currentNode.Position = buildPosition(ctx)

        // Handle 'on' constraints (superclass constraints)
        // In ANTLR, the grammar has: (ON_ typeNotVoidList)?
        // typeNotVoidList returns a single context containing multiple typeNotVoid elements
        ctx.typeNotVoidList()?.let { typeList ->
            val constraints = typeList.typeNotVoid()
            if (constraints.isNotEmpty()) {
                currentNode.MultipleExtend = constraints.map { it.text }
            }
        }

        // Handle implements
        ctx.interfaces()?.typeNotVoidList()?.typeNotVoid()?.let { interfaces ->
            currentNode.Implements = interfaces.map { it.text }
        }
    }

    override fun exitMixinDeclaration(ctx: Dart2Parser.MixinDeclarationContext?) {
        hasEnterClass = false
        if (currentNode.NodeName.isNotEmpty()) {
            classNodes.add(currentNode)
        }
        currentNode = CodeDataStruct()
    }

    // Handle extension declaration
    override fun enterExtensionDeclaration(ctx: Dart2Parser.ExtensionDeclarationContext?) {
        hasEnterClass = true

        ctx ?: return

        val name = ctx.identifier()?.text ?: "anonymous"
        currentNode = CodeDataStruct(
            NodeName = name,
            Type = DataStructType.OBJECT, // Extension is like an object extension
            Package = codeContainer.PackageName,
            FilePath = filePath
        )
        currentNode.Position = buildPosition(ctx)

        // Extension target type
        ctx.type()?.let {
            currentNode.Extend = it.text
        }
    }

    override fun exitExtensionDeclaration(ctx: Dart2Parser.ExtensionDeclarationContext?) {
        hasEnterClass = false
        if (currentNode.NodeName.isNotEmpty()) {
            classNodes.add(currentNode)
        }
        currentNode = CodeDataStruct()
    }

    // Handle enum declaration
    override fun enterEnumType(ctx: Dart2Parser.EnumTypeContext?) {
        hasEnterClass = true

        val name = ctx?.identifier()?.text ?: return
        currentNode = CodeDataStruct(
            NodeName = name,
            Type = DataStructType.ENUM,
            Package = codeContainer.PackageName,
            FilePath = filePath
        )
        currentNode.Position = buildPosition(ctx)

        // Extract enum entries as fields
        ctx.enumEntry()?.let { entries ->
            currentNode.Fields = entries.map { entry ->
                CodeField(
                    TypeKey = entry.identifier()?.text ?: "",
                    TypeType = name,
                    TypeValue = entry.identifier()?.text ?: ""
                )
            }
        }
    }

    override fun exitEnumType(ctx: Dart2Parser.EnumTypeContext?) {
        hasEnterClass = false
        if (currentNode.NodeName.isNotEmpty()) {
            classNodes.add(currentNode)
        }
        currentNode = CodeDataStruct()
    }

    // Handle function signatures in class members
    override fun enterMethodSignature(ctx: Dart2Parser.MethodSignatureContext?) {
        val funcSig = ctx?.functionSignature()
        val getterSig = ctx?.getterSignature()
        val setterSig = ctx?.setterSignature()
        val constructorSig = ctx?.constructorSignature()
        val factorySig = ctx?.factoryConstructorSignature()
        val operatorSig = ctx?.operatorSignature()

        currentFunction = when {
            constructorSig != null -> buildConstructorFunction(constructorSig, ctx)
            factorySig != null -> buildFactoryConstructorFunction(factorySig, ctx)
            funcSig != null -> buildFunction(funcSig, ctx)
            getterSig != null -> buildGetterFunction(getterSig, ctx)
            setterSig != null -> buildSetterFunction(setterSig, ctx)
            operatorSig != null -> buildOperatorFunction(operatorSig, ctx)
            else -> CodeFunction()
        }
    }

    override fun exitMethodSignature(ctx: Dart2Parser.MethodSignatureContext?) {
        if (hasEnterClass && currentFunction.Name.isNotEmpty()) {
            currentNode.Functions += currentFunction
        }
        currentFunction = CodeFunction()
    }

    private fun buildFunction(ctx: Dart2Parser.FunctionSignatureContext, parent: Dart2Parser.MethodSignatureContext): CodeFunction {
        val func = CodeFunction(
            Name = ctx.identifier()?.text ?: "",
            ReturnType = ctx.type()?.text ?: "void",
            Position = buildPosition(parent),
            IsConstructor = false
        )
        
        // Parse parameters
        ctx.formalParameterPart()?.formalParameterList()?.let { params ->
            func.Parameters = parseParameters(params)
        }

        // Check for static modifier
        if (parent.STATIC_() != null) {
            func.Modifiers += "static"
        }

        return func
    }

    private fun buildConstructorFunction(ctx: Dart2Parser.ConstructorSignatureContext, parent: Dart2Parser.MethodSignatureContext): CodeFunction {
        val name = ctx.constructorName()?.typeIdentifier()?.text ?: ""
        val namedPart = ctx.constructorName()?.identifier()?.text
        
        val func = CodeFunction(
            Name = if (namedPart != null) "$name.$namedPart" else name,
            ReturnType = name,
            Position = buildPosition(parent),
            IsConstructor = true
        )

        ctx.formalParameterList()?.let { params ->
            func.Parameters = parseParameters(params)
        }

        return func
    }

    private fun buildFactoryConstructorFunction(ctx: Dart2Parser.FactoryConstructorSignatureContext, parent: Dart2Parser.MethodSignatureContext): CodeFunction {
        val name = ctx.constructorName()?.typeIdentifier()?.text ?: ""
        val namedPart = ctx.constructorName()?.identifier()?.text

        val func = CodeFunction(
            Name = if (namedPart != null) "$name.$namedPart" else name,
            ReturnType = name,
            Position = buildPosition(parent),
            IsConstructor = true
        )
        func.Modifiers += "factory"

        if (ctx.CONST_() != null) {
            func.Modifiers += "const"
        }

        ctx.formalParameterList()?.let { params ->
            func.Parameters = parseParameters(params)
        }

        return func
    }

    private fun buildGetterFunction(ctx: Dart2Parser.GetterSignatureContext, parent: Dart2Parser.MethodSignatureContext): CodeFunction {
        return CodeFunction(
            Name = "get ${ctx.identifier()?.text ?: ""}",
            ReturnType = ctx.type()?.text ?: "dynamic",
            Position = buildPosition(parent),
            IsConstructor = false
        ).also {
            it.Modifiers += "getter"
            if (parent.STATIC_() != null) {
                it.Modifiers += "static"
            }
        }
    }

    private fun buildSetterFunction(ctx: Dart2Parser.SetterSignatureContext, parent: Dart2Parser.MethodSignatureContext): CodeFunction {
        val func = CodeFunction(
            Name = "set ${ctx.identifier()?.text ?: ""}",
            ReturnType = "void",
            Position = buildPosition(parent),
            IsConstructor = false
        )
        func.Modifiers += "setter"
        if (parent.STATIC_() != null) {
            func.Modifiers += "static"
        }

        ctx.formalParameterList()?.let { params ->
            func.Parameters = parseParameters(params)
        }

        return func
    }

    private fun buildOperatorFunction(ctx: Dart2Parser.OperatorSignatureContext, parent: Dart2Parser.MethodSignatureContext): CodeFunction {
        val operatorSymbol = ctx.operator()?.text ?: ""
        val func = CodeFunction(
            Name = "operator $operatorSymbol",
            ReturnType = ctx.type()?.text ?: "dynamic",
            Position = buildPosition(parent),
            IsConstructor = false
        )
        func.Modifiers += "operator"

        ctx.formalParameterList()?.let { params ->
            func.Parameters = parseParameters(params)
        }

        return func
    }

    private fun parseParameters(ctx: Dart2Parser.FormalParameterListContext): List<CodeProperty> {
        val params = mutableListOf<CodeProperty>()

        // Normal parameters
        ctx.normalFormalParameters()?.normalFormalParameter()?.forEach { param ->
            parseNormalParameter(param)?.let { params.add(it) }
        }

        // Optional positional parameters
        ctx.optionalOrNamedFormalParameters()?.optionalPositionalFormalParameters()
            ?.defaultFormalParameter()?.forEach { param ->
                param.normalFormalParameter()?.let { 
                    parseNormalParameter(it)?.let { p -> params.add(p) }
                }
            }

        // Named parameters
        ctx.optionalOrNamedFormalParameters()?.namedFormalParameters()
            ?.defaultNamedParameter()?.forEach { param ->
                param.normalFormalParameterNoMetadata()?.let {
                    parseNormalParameterNoMetadata(it)?.let { p -> params.add(p) }
                }
            }

        return params
    }

    private fun parseNormalParameter(ctx: Dart2Parser.NormalFormalParameterContext): CodeProperty? {
        return parseNormalParameterNoMetadata(ctx.normalFormalParameterNoMetadata())
    }

    private fun parseNormalParameterNoMetadata(ctx: Dart2Parser.NormalFormalParameterNoMetadataContext?): CodeProperty? {
        if (ctx == null) return null

        // Simple formal parameter (type identifier)
        ctx.simpleFormalParameter()?.let { simple ->
            simple.declaredIdentifier()?.let { declared ->
                return CodeProperty(
                    TypeType = declared.finalConstVarOrType()?.text ?: "dynamic",
                    TypeValue = declared.identifier()?.text ?: ""
                )
            }
            simple.identifier()?.let { id ->
                return CodeProperty(
                    TypeType = "dynamic",
                    TypeValue = id.text
                )
            }
        }

        // Function formal parameter
        ctx.functionFormalParameter()?.let { funcParam ->
            return CodeProperty(
                TypeType = funcParam.type()?.text ?: "Function",
                TypeValue = funcParam.identifier()?.text ?: ""
            )
        }

        // Field formal parameter (this.xxx)
        ctx.fieldFormalParameter()?.let { fieldParam ->
            return CodeProperty(
                TypeType = fieldParam.finalConstVarOrType()?.text ?: "dynamic",
                TypeValue = fieldParam.identifier()?.text ?: ""
            )
        }

        return null
    }

    // Handle top-level function declaration
    override fun enterTopLevelDeclaration(ctx: Dart2Parser.TopLevelDeclarationContext?) {
        if (hasEnterClass) return

        // Handle top-level function
        ctx?.functionSignature()?.let { funcSig ->
            val func = CodeFunction(
                Name = funcSig.identifier()?.text ?: "",
                ReturnType = funcSig.type()?.text ?: "void",
                Position = buildPosition(ctx),
                IsConstructor = false
            )

            funcSig.formalParameterPart()?.formalParameterList()?.let { params ->
                func.Parameters = parseParameters(params)
            }

            // Store in top-level scope
            ensureTopLevelScope()
            codeContainer.TopLevel = codeContainer.TopLevel?.copy(
                Functions = (codeContainer.TopLevel?.Functions ?: emptyList()) + func
            )
        }

        // Handle top-level getter
        ctx?.getterSignature()?.let { getterSig ->
            val func = CodeFunction(
                Name = "get ${getterSig.identifier()?.text ?: ""}",
                ReturnType = getterSig.type()?.text ?: "dynamic",
                Position = buildPosition(ctx),
                IsConstructor = false
            )
            func.Modifiers += "getter"

            ensureTopLevelScope()
            codeContainer.TopLevel = codeContainer.TopLevel?.copy(
                Functions = (codeContainer.TopLevel?.Functions ?: emptyList()) + func
            )
        }

        // Handle top-level setter
        ctx?.setterSignature()?.let { setterSig ->
            val func = CodeFunction(
                Name = "set ${setterSig.identifier()?.text ?: ""}",
                ReturnType = "void",
                Position = buildPosition(ctx),
                IsConstructor = false
            )
            func.Modifiers += "setter"

            setterSig.formalParameterList()?.let { params ->
                func.Parameters = parseParameters(params)
            }

            ensureTopLevelScope()
            codeContainer.TopLevel = codeContainer.TopLevel?.copy(
                Functions = (codeContainer.TopLevel?.Functions ?: emptyList()) + func
            )
        }

        // Handle top-level variable declarations
        ctx?.initializedIdentifierList()?.let { idList ->
            idList.initializedIdentifier()?.forEach { initId ->
                val field = CodeField(
                    TypeKey = initId.identifier()?.text ?: "",
                    TypeType = ctx.varOrType()?.type()?.text ?: ctx.type()?.text ?: "var",
                    TypeValue = ""
                )

                ensureTopLevelScope()
                codeContainer.TopLevel = codeContainer.TopLevel?.copy(
                    Fields = (codeContainer.TopLevel?.Fields ?: emptyList()) + field
                )
            }
        }
    }

    private fun ensureTopLevelScope() {
        if (codeContainer.TopLevel == null) {
            codeContainer.TopLevel = TopLevelScope()
        }
    }

    // Handle typedef
    override fun enterTypeAlias(ctx: Dart2Parser.TypeAliasContext?) {
        ctx?.typeIdentifier()?.let { typeId ->
            val typedef = CodeDataStruct(
                NodeName = getTypeIdentifierText(typeId),
                Type = DataStructType.OBJECT, // typedef is like a type alias
                Package = codeContainer.PackageName,
                FilePath = filePath
            )
            typedef.Position = buildPosition(ctx)

            // Store the aliased type
            ctx.type()?.let {
                typedef.Extend = it.text
            }

            classNodes.add(typedef)
        }
    }

    fun getNodeInfo(): CodeContainer {
        codeContainer.DataStructures = classNodes
        return codeContainer
    }
}
