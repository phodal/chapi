package chapi.ast.rustast

import chapi.ast.antlr.RustParser
import chapi.domain.core.CodeTypeRef
import chapi.domain.core.TypeRefKind

/**
 * Builds CodeTypeRef from Rust type contexts.
 * Handles: references, pointers, generics, Result, Option, tuples, arrays, slices, function pointers.
 */
object RustTypeRefBuilder {

    /**
     * Build CodeTypeRef from Type_ context.
     */
    fun build(ctx: RustParser.Type_Context?): CodeTypeRef? {
        if (ctx == null) return null
        val raw = ctx.text

        // TypeNoBounds or other type
        ctx.typeNoBounds()?.let { return buildTypeNoBounds(it, raw) }

        // ImplTraitType - impl Trait
        ctx.implTraitType()?.let { return buildImplTraitType(it, raw) }

        // TraitObjectType - dyn Trait
        ctx.traitObjectType()?.let { return buildTraitObjectType(it, raw) }

        return CodeTypeRef(raw = raw, name = raw, kind = TypeRefKind.UNKNOWN)
    }

    private fun buildTypeNoBounds(ctx: RustParser.TypeNoBoundsContext, rawOverride: String): CodeTypeRef {
        val raw = rawOverride

        // ParenthesizedType
        ctx.parenthesizedType()?.let { 
            return build(it.type_()) ?: CodeTypeRef(raw = raw, kind = TypeRefKind.UNKNOWN)
        }

        // TypePath - most common case
        ctx.typePath()?.let { return buildTypePath(it, raw) }

        // ImplTraitTypeOneBound - only matches impl Trait
        ctx.implTraitTypeOneBound()?.let { return buildImplTraitOneBound(it, raw) }

        // TraitObjectTypeOneBound - this is often how simple types are parsed!
        // 'dyn'? traitBound, where traitBound eventually contains typePath
        ctx.traitObjectTypeOneBound()?.let { traitObj ->
            // If it has explicit 'dyn' keyword, treat as trait object
            if (raw.startsWith("dyn ")) {
                return buildTraitObjectOneBound(traitObj, raw)
            }
            // Otherwise, extract the typePath from traitBound
            val typePath = traitObj.traitBound()?.typePath()
            if (typePath != null) {
                return buildTypePath(typePath, raw)
            }
        }

        // TupleType
        ctx.tupleType()?.let { return buildTupleType(it, raw) }

        // NeverType (!)
        ctx.neverType()?.let { return CodeTypeRef(raw = raw, name = "!", kind = TypeRefKind.SIMPLE) }

        // RawPointerType
        ctx.rawPointerType()?.let { return buildRawPointerType(it, raw) }

        // ReferenceType
        ctx.referenceType()?.let { return buildReferenceType(it, raw) }

        // ArrayType
        ctx.arrayType()?.let { return buildArrayType(it, raw) }

        // SliceType
        ctx.sliceType()?.let { return buildSliceType(it, raw) }

        // InferredType (_)
        ctx.inferredType()?.let { return CodeTypeRef(raw = raw, name = "_", kind = TypeRefKind.UNKNOWN) }

        // QualifiedPathInType
        ctx.qualifiedPathInType()?.let { 
            return CodeTypeRef(raw = raw, name = ctx.text, kind = TypeRefKind.SIMPLE)
        }

        // BareFunctionType
        ctx.bareFunctionType()?.let { return buildBareFunctionType(it, raw) }

        // MacroInvocation
        ctx.macroInvocation()?.let {
            return CodeTypeRef(raw = raw, name = ctx.text, kind = TypeRefKind.SIMPLE)
        }

        return CodeTypeRef(raw = raw, name = raw, kind = TypeRefKind.UNKNOWN)
    }

    private fun buildTypePath(ctx: RustParser.TypePathContext, raw: String): CodeTypeRef {
        val segments = ctx.typePathSegment()
        if (segments.isNullOrEmpty()) {
            return CodeTypeRef(raw = raw, name = raw, kind = TypeRefKind.SIMPLE)
        }

        // Build the full path name
        val pathParts = segments.mapNotNull { seg ->
            seg.pathIdentSegment()?.identifier()?.text
        }
        val name = pathParts.lastOrNull() ?: ctx.text
        val qualified = pathParts.joinToString("::")

        // Check for generic arguments in the last segment
        val lastSegment = segments.lastOrNull()
        val genericArgs = lastSegment?.genericArgs()

        if (genericArgs != null) {
            val args = buildGenericArgs(genericArgs)
            
            // Special handling for common Rust types
            return when (name.lowercase()) {
                "option" -> {
                    val innerType = args.firstOrNull()
                    CodeTypeRef(
                        raw = raw,
                        name = name,
                        qualified = qualified,
                        kind = TypeRefKind.NULLABLE,
                        args = args,
                        nullable = true,
                        valueType = innerType
                    )
                }
                "result" -> {
                    CodeTypeRef(
                        raw = raw,
                        name = name,
                        qualified = qualified,
                        kind = TypeRefKind.GENERIC,
                        args = args
                    )
                }
                "vec", "hashmap", "btreemap", "hashset", "btreeset" -> {
                    CodeTypeRef(
                        raw = raw,
                        name = name,
                        qualified = qualified,
                        kind = TypeRefKind.GENERIC,
                        args = args,
                        valueType = args.firstOrNull()
                    )
                }
                "box", "rc", "arc", "refcell", "cell", "mutex", "rwlock" -> {
                    CodeTypeRef(
                        raw = raw,
                        name = name,
                        qualified = qualified,
                        kind = TypeRefKind.GENERIC,
                        args = args,
                        valueType = args.firstOrNull()
                    )
                }
                else -> {
                    CodeTypeRef(
                        raw = raw,
                        name = name,
                        qualified = qualified,
                        kind = TypeRefKind.GENERIC,
                        args = args
                    )
                }
            }
        }

        // Simple type path without generics
        return CodeTypeRef(
            raw = raw,
            name = name,
            qualified = if (pathParts.size > 1) qualified else "",
            kind = TypeRefKind.SIMPLE
        )
    }

    private fun buildGenericArgs(ctx: RustParser.GenericArgsContext): List<CodeTypeRef> {
        val args = mutableListOf<CodeTypeRef>()
        
        // GenericArgsTypes
        ctx.genericArgsTypes()?.type_()?.forEach { typeCtx ->
            build(typeCtx)?.let { args.add(it) }
        }

        // GenericArgsBindings
        ctx.genericArgsBindings()?.genericArgsBinding()?.forEach { binding ->
            build(binding.type_())?.let { args.add(it) }
        }

        return args
    }

    private fun buildReferenceType(ctx: RustParser.ReferenceTypeContext, raw: String): CodeTypeRef {
        val innerType = ctx.typeNoBounds()?.let { buildTypeNoBounds(it, it.text) }
        val lifetime = ctx.lifetime()?.text
        val isMut = ctx.KW_MUT() != null

        return CodeTypeRef(
            raw = raw,
            name = innerType?.name ?: "",
            kind = TypeRefKind.REFERENCE,
            valueType = innerType,
            lifetime = lifetime,
            isConst = !isMut,
            isReference = true
        )
    }

    private fun buildRawPointerType(ctx: RustParser.RawPointerTypeContext, raw: String): CodeTypeRef {
        val innerType = ctx.typeNoBounds()?.let { buildTypeNoBounds(it, it.text) }
        val isConst = ctx.KW_CONST() != null
        
        return CodeTypeRef(
            raw = raw,
            name = innerType?.name ?: "",
            kind = TypeRefKind.POINTER,
            valueType = innerType,
            pointerDepth = 1,
            isConst = isConst
        )
    }

    private fun buildSliceType(ctx: RustParser.SliceTypeContext, raw: String): CodeTypeRef {
        val elementType = build(ctx.type_())
        
        return CodeTypeRef(
            raw = raw,
            name = "slice",
            kind = TypeRefKind.SLICE,
            valueType = elementType,
            isSlice = true
        )
    }

    private fun buildArrayType(ctx: RustParser.ArrayTypeContext, raw: String): CodeTypeRef {
        val elementType = build(ctx.type_())
        val sizeExpr = ctx.expression()?.text
        val size = sizeExpr?.toIntOrNull()

        return CodeTypeRef(
            raw = raw,
            name = elementType?.name ?: "array",
            kind = TypeRefKind.ARRAY,
            valueType = elementType,
            arrayDimensions = 1,
            arraySize = size
        )
    }

    private fun buildTupleType(ctx: RustParser.TupleTypeContext, raw: String): CodeTypeRef {
        // TupleType: '(' ((type_ ',')+ type_?)? ')'
        val elements = ctx.type_()?.mapNotNull { build(it) } ?: listOf()
        
        // Unit type ()
        if (elements.isEmpty()) {
            return CodeTypeRef(raw = raw, name = "()", kind = TypeRefKind.SIMPLE)
        }

        return CodeTypeRef(
            raw = raw,
            kind = TypeRefKind.TUPLE,
            tupleElements = elements
        )
    }

    private fun buildBareFunctionType(ctx: RustParser.BareFunctionTypeContext, raw: String): CodeTypeRef {
        val params = ctx.functionParametersMaybeNamedVariadic()
            ?.maybeNamedFunctionParameters()
            ?.maybeNamedParam()
            ?.mapNotNull { build(it.type_()) } ?: listOf()

        val returnType = ctx.bareFunctionReturnType()?.typeNoBounds()?.let { buildTypeNoBounds(it, it.text) }

        return CodeTypeRef(
            raw = raw,
            kind = TypeRefKind.FUNCTION,
            parameterTypes = params,
            returnType = returnType
        )
    }

    private fun buildImplTraitType(ctx: RustParser.ImplTraitTypeContext, raw: String): CodeTypeRef {
        val bounds = ctx.typeParamBounds()?.typeParamBound()?.mapNotNull { bound ->
            bound.traitBound()?.typePath()?.text
        } ?: listOf()

        return CodeTypeRef(
            raw = raw,
            name = "impl",
            kind = TypeRefKind.SIMPLE,
            traitBounds = bounds
        )
    }

    private fun buildImplTraitOneBound(ctx: RustParser.ImplTraitTypeOneBoundContext, raw: String): CodeTypeRef {
        val bound = ctx.traitBound()?.typePath()?.text ?: ""
        
        return CodeTypeRef(
            raw = raw,
            name = "impl",
            kind = TypeRefKind.SIMPLE,
            traitBounds = if (bound.isNotEmpty()) listOf(bound) else listOf()
        )
    }

    private fun buildTraitObjectType(ctx: RustParser.TraitObjectTypeContext, raw: String): CodeTypeRef {
        val bounds = ctx.typeParamBounds()?.typeParamBound()?.mapNotNull { bound ->
            bound.traitBound()?.typePath()?.text
        } ?: listOf()

        return CodeTypeRef(
            raw = raw,
            name = "dyn",
            kind = TypeRefKind.INTERFACE,
            traitBounds = bounds
        )
    }

    private fun buildTraitObjectOneBound(ctx: RustParser.TraitObjectTypeOneBoundContext, raw: String): CodeTypeRef {
        val bound = ctx.traitBound()?.typePath()?.text ?: ""
        
        return CodeTypeRef(
            raw = raw,
            name = "dyn",
            kind = TypeRefKind.INTERFACE,
            traitBounds = if (bound.isNotEmpty()) listOf(bound) else listOf()
        )
    }

    /**
     * Build CodeTypeRef from a type string (simple parsing for common patterns).
     */
    fun buildFromString(typeStr: String): CodeTypeRef {
        val raw = typeStr.trim()

        return when {
            // Reference
            raw.startsWith("&") -> {
                val rest = raw.drop(1).trim()
                val (lifetime, typeWithoutLifetime) = extractLifetime(rest)
                val isMut = typeWithoutLifetime.startsWith("mut ")
                val innerRaw = if (isMut) typeWithoutLifetime.drop(4).trim() else typeWithoutLifetime
                val inner = buildFromString(innerRaw)
                
                CodeTypeRef(
                    raw = raw,
                    name = inner.name,
                    kind = TypeRefKind.REFERENCE,
                    valueType = inner,
                    lifetime = lifetime,
                    isConst = !isMut,
                    isReference = true
                )
            }
            // Raw pointer
            raw.startsWith("*const ") || raw.startsWith("*mut ") -> {
                val isConst = raw.startsWith("*const ")
                val innerRaw = raw.drop(if (isConst) 7 else 5).trim()
                val inner = buildFromString(innerRaw)
                
                CodeTypeRef(
                    raw = raw,
                    name = inner.name,
                    kind = TypeRefKind.POINTER,
                    valueType = inner,
                    pointerDepth = 1,
                    isConst = isConst
                )
            }
            // Slice
            raw.startsWith("[") && raw.contains("]") && !raw.contains(";") -> {
                val elementRaw = raw.drop(1).dropLast(1).trim()
                val element = buildFromString(elementRaw)
                
                CodeTypeRef(
                    raw = raw,
                    name = "slice",
                    kind = TypeRefKind.SLICE,
                    valueType = element,
                    isSlice = true
                )
            }
            // Array
            raw.startsWith("[") && raw.contains(";") -> {
                val content = raw.drop(1).dropLast(1)
                val parts = content.split(";")
                if (parts.size == 2) {
                    val elementRaw = parts[0].trim()
                    val sizeRaw = parts[1].trim()
                    val element = buildFromString(elementRaw)
                    
                    CodeTypeRef(
                        raw = raw,
                        name = element.name,
                        kind = TypeRefKind.ARRAY,
                        valueType = element,
                        arrayDimensions = 1,
                        arraySize = sizeRaw.toIntOrNull()
                    )
                } else {
                    CodeTypeRef(raw = raw, name = raw, kind = TypeRefKind.ARRAY)
                }
            }
            // Tuple
            raw.startsWith("(") && raw.endsWith(")") -> {
                val content = raw.drop(1).dropLast(1).trim()
                if (content.isEmpty()) {
                    CodeTypeRef(raw = raw, name = "()", kind = TypeRefKind.SIMPLE)
                } else {
                    val elements = splitTypeList(content).map { buildFromString(it) }
                    CodeTypeRef(
                        raw = raw,
                        kind = TypeRefKind.TUPLE,
                        tupleElements = elements
                    )
                }
            }
            // Generic type with angle brackets
            raw.contains("<") && raw.endsWith(">") -> {
                val bracketStart = raw.indexOf('<')
                val name = raw.substring(0, bracketStart)
                val argsRaw = raw.substring(bracketStart + 1, raw.length - 1)
                val args = splitTypeList(argsRaw).map { buildFromString(it.trim()) }
                
                // Special cases
                when (name.lowercase()) {
                    "option" -> CodeTypeRef(
                        raw = raw,
                        name = name,
                        kind = TypeRefKind.NULLABLE,
                        args = args,
                        nullable = true,
                        valueType = args.firstOrNull()
                    )
                    else -> CodeTypeRef(
                        raw = raw,
                        name = name,
                        kind = TypeRefKind.GENERIC,
                        args = args
                    )
                }
            }
            // Qualified path (mod::Type)
            raw.contains("::") -> {
                val parts = raw.split("::")
                CodeTypeRef(
                    raw = raw,
                    name = parts.last(),
                    qualified = raw,
                    kind = TypeRefKind.SIMPLE
                )
            }
            // Simple type
            else -> {
                CodeTypeRef(raw = raw, name = raw, kind = TypeRefKind.SIMPLE)
            }
        }
    }

    private fun extractLifetime(str: String): Pair<String?, String> {
        if (str.startsWith("'")) {
            val spaceIndex = str.indexOf(' ')
            if (spaceIndex > 0) {
                return str.substring(0, spaceIndex) to str.substring(spaceIndex + 1).trim()
            }
        }
        return null to str
    }

    private fun splitTypeList(str: String): List<String> {
        val result = mutableListOf<String>()
        var depth = 0
        var current = StringBuilder()
        
        for (char in str) {
            when (char) {
                '<', '(', '[' -> {
                    depth++
                    current.append(char)
                }
                '>', ')', ']' -> {
                    depth--
                    current.append(char)
                }
                ',' -> {
                    if (depth == 0) {
                        result.add(current.toString().trim())
                        current = StringBuilder()
                    } else {
                        current.append(char)
                    }
                }
                else -> current.append(char)
            }
        }
        
        if (current.isNotEmpty()) {
            result.add(current.toString().trim())
        }
        
        return result
    }
}
