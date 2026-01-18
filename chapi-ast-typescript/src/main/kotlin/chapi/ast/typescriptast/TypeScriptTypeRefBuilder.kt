package chapi.ast.typescriptast

import chapi.ast.antlr.TypeScriptParser
import chapi.domain.core.CodeTypeRef
import chapi.domain.core.TypeRefKind

/**
 * Builds CodeTypeRef from TypeScript type annotations.
 * Handles: union, intersection, generic, array, tuple, function types, etc.
 */
object TypeScriptTypeRefBuilder {

    /**
     * Build CodeTypeRef from type annotation context.
     */
    fun build(ctx: TypeScriptParser.TypeAnnotationContext?): CodeTypeRef? {
        if (ctx == null) return null
        return buildFromType(ctx.type_())
    }

    /**
     * Build CodeTypeRef from typeRef context (compatibility wrapper).
     */
    fun buildFromTypeRef(ctx: TypeScriptParser.TypeRefContext?): CodeTypeRef? {
        if (ctx == null) return null
        return buildFromType(ctx.type_())
    }

    /**
     * Build CodeTypeRef from type_ context.
     */
    fun buildFromType(ctx: TypeScriptParser.Type_Context?): CodeTypeRef? {
        if (ctx == null) return null

        val raw = ctx.text

        // Check for function type
        if (ctx.functionType() != null) {
            return buildFunctionType(ctx.functionType(), raw)
        }

        // Check for constructor type
        if (ctx.constructorType() != null) {
            return CodeTypeRef(raw = raw, name = "new", kind = TypeRefKind.FUNCTION)
        }

        // Check for type generic (standalone generic like `<T>`)
        if (ctx.typeGeneric() != null) {
            return buildGenericType(ctx.typeGeneric(), raw)
        }

        // Handle union/intersection/primary
        val unionOrIntersection = ctx.unionOrIntersectionOrPrimaryType()
        if (unionOrIntersection != null) {
            return buildUnionOrIntersection(unionOrIntersection, raw)
        }

        return CodeTypeRef(raw = raw, name = raw, kind = TypeRefKind.UNKNOWN)
    }

    private fun buildUnionOrIntersection(
        ctx: TypeScriptParser.UnionOrIntersectionOrPrimaryTypeContext,
        rawOverride: String? = null
    ): CodeTypeRef {
        val raw = rawOverride ?: ctx.text

        return when (ctx) {
            is TypeScriptParser.UnionContext -> {
                val types = flattenUnion(ctx)
                // Check if nullable (contains null or undefined)
                val hasNull = types.any { it.name == "null" || it.name == "undefined" }
                CodeTypeRef(
                    raw = raw,
                    kind = TypeRefKind.UNION,
                    union = types,
                    nullable = hasNull
                )
            }
            is TypeScriptParser.IntersectionContext -> {
                val types = flattenIntersection(ctx)
                CodeTypeRef(
                    raw = raw,
                    kind = TypeRefKind.INTERSECTION,
                    intersection = types
                )
            }
            is TypeScriptParser.PrimaryContext -> {
                buildPrimaryType(ctx.primaryType(), raw)
            }
            else -> CodeTypeRef(raw = raw, name = raw, kind = TypeRefKind.UNKNOWN)
        }
    }

    private fun flattenUnion(ctx: TypeScriptParser.UnionContext): List<CodeTypeRef> {
        val result = mutableListOf<CodeTypeRef>()
        for (child in ctx.unionOrIntersectionOrPrimaryType()) {
            when (child) {
                is TypeScriptParser.UnionContext -> result.addAll(flattenUnion(child))
                else -> buildUnionOrIntersection(child).let { result.add(it) }
            }
        }
        return result
    }

    private fun flattenIntersection(ctx: TypeScriptParser.IntersectionContext): List<CodeTypeRef> {
        val result = mutableListOf<CodeTypeRef>()
        for (child in ctx.unionOrIntersectionOrPrimaryType()) {
            when (child) {
                is TypeScriptParser.IntersectionContext -> result.addAll(flattenIntersection(child))
                else -> buildUnionOrIntersection(child).let { result.add(it) }
            }
        }
        return result
    }

    private fun buildPrimaryType(ctx: TypeScriptParser.PrimaryTypeContext?, rawOverride: String? = null): CodeTypeRef {
        if (ctx == null) return CodeTypeRef(raw = rawOverride ?: "", kind = TypeRefKind.UNKNOWN)

        val raw = rawOverride ?: ctx.text

        return when (ctx) {
            is TypeScriptParser.ParenthesizedPrimTypeContext -> {
                buildFromType(ctx.type_()) ?: CodeTypeRef(raw = raw, kind = TypeRefKind.UNKNOWN)
            }
            is TypeScriptParser.PredefinedPrimTypeContext -> {
                val name = ctx.predefinedType().text
                val kind = if (name == "void" || name == "never") TypeRefKind.SIMPLE
                           else if (name == "any" || name == "unknown") TypeRefKind.INTERFACE
                           else TypeRefKind.SIMPLE
                CodeTypeRef(raw = raw, name = name, kind = kind)
            }
            is TypeScriptParser.ReferencePrimTypeContext -> {
                buildTypeReference(ctx.typeReference(), raw)
            }
            is TypeScriptParser.ArrayPrimTypeContext -> {
                // primaryType '[' primaryType? ']' - can be array or indexed access
                val primaryTypes = ctx.primaryType()
                if (primaryTypes.isNotEmpty()) {
                    val elementType = buildPrimaryType(primaryTypes[0])
                    val dims = countArrayDimensions(ctx)
                    CodeTypeRef(
                        raw = raw,
                        name = elementType.name,
                        kind = TypeRefKind.ARRAY,
                        valueType = elementType,
                        arrayDimensions = dims
                    )
                } else {
                    CodeTypeRef(raw = raw, kind = TypeRefKind.ARRAY, arrayDimensions = 1)
                }
            }
            is TypeScriptParser.TuplePrimTypeContext -> {
                // '[' tupleElementTypes ']'
                val elements = ctx.tupleElementTypes()?.type_()?.mapNotNull {
                    buildFromType(it)
                } ?: listOf()
                CodeTypeRef(
                    raw = raw,
                    kind = TypeRefKind.TUPLE,
                    tupleElements = elements
                )
            }
            is TypeScriptParser.ObjectPrimTypeContext -> {
                // Object type like { x: number, y: string }
                CodeTypeRef(raw = raw, name = "object", kind = TypeRefKind.SIMPLE)
            }
            is TypeScriptParser.QueryPrimTypeContext -> {
                // typeof queries
                CodeTypeRef(raw = raw, name = ctx.text, kind = TypeRefKind.SIMPLE)
            }
            is TypeScriptParser.ThisPrimTypeContext -> {
                CodeTypeRef(raw = raw, name = "this", kind = TypeRefKind.SIMPLE)
            }
            is TypeScriptParser.RedefinitionOfTypeContext -> {
                // typeReference Is primaryType
                CodeTypeRef(raw = raw, name = ctx.text, kind = TypeRefKind.SIMPLE)
            }
            is TypeScriptParser.KeyOfTypeContext -> {
                // KeyOf primaryType
                CodeTypeRef(raw = raw, name = ctx.text, kind = TypeRefKind.SIMPLE)
            }
            else -> CodeTypeRef(raw = raw, name = raw, kind = TypeRefKind.UNKNOWN)
        }
    }

    private fun countArrayDimensions(ctx: TypeScriptParser.ArrayPrimTypeContext): Int {
        var count = 1
        val primaryTypes = ctx.primaryType()
        if (primaryTypes.isNotEmpty()) {
            var inner = primaryTypes[0]
            while (inner is TypeScriptParser.ArrayPrimTypeContext && inner.primaryType().isNotEmpty()) {
                count++
                inner = inner.primaryType()[0]
            }
        }
        return count
    }

    private fun buildTypeReference(ctx: TypeScriptParser.TypeReferenceContext?, rawOverride: String?): CodeTypeRef {
        if (ctx == null) return CodeTypeRef(raw = rawOverride ?: "", kind = TypeRefKind.UNKNOWN)

        val raw = rawOverride ?: ctx.text
        val typeName = ctx.typeName()?.text ?: ""

        // Check for generic type arguments
        val typeGeneric = ctx.typeGeneric()
        if (typeGeneric != null) {
            val args = typeGeneric.typeArgumentList()?.typeArgument()?.mapNotNull {
                buildFromType(it.type_())
            } ?: listOf()

            // Special handling for common generic types
            val kind = when (typeName.lowercase()) {
                "array" -> TypeRefKind.ARRAY
                "promise", "observable" -> TypeRefKind.GENERIC
                "map", "set", "weakmap", "weakset" -> TypeRefKind.GENERIC
                "partial", "required", "readonly", "pick", "omit", "record" -> TypeRefKind.MAPPED
                else -> TypeRefKind.GENERIC
            }

            return CodeTypeRef(
                raw = raw,
                name = typeName,
                kind = kind,
                args = args,
                valueType = args.firstOrNull() // For Array<T>, store T as valueType
            )
        }

        // Simple type reference
        val isNullable = typeName == "null" || typeName == "undefined"
        return CodeTypeRef(
            raw = raw,
            name = typeName,
            kind = if (isNullable) TypeRefKind.NULLABLE else TypeRefKind.SIMPLE,
            nullable = isNullable
        )
    }

    private fun buildGenericType(ctx: TypeScriptParser.TypeGenericContext?, rawOverride: String?): CodeTypeRef {
        if (ctx == null) return CodeTypeRef(raw = rawOverride ?: "", kind = TypeRefKind.UNKNOWN)

        val raw = rawOverride ?: ctx.text
        val args = ctx.typeArgumentList()?.typeArgument()?.mapNotNull {
            buildFromType(it.type_())
        } ?: listOf()

        return CodeTypeRef(raw = raw, kind = TypeRefKind.GENERIC, args = args)
    }

    private fun buildFunctionType(ctx: TypeScriptParser.FunctionTypeContext?, rawOverride: String?): CodeTypeRef {
        if (ctx == null) return CodeTypeRef(raw = rawOverride ?: "", kind = TypeRefKind.FUNCTION)

        val raw = rawOverride ?: ctx.text

        // Parse parameter types
        val paramTypes = ctx.parameterList()?.parameter()?.mapNotNull { param ->
            param.requiredParameter()?.typeAnnotation()?.let { build(it) }
                ?: param.optionalParameter()?.typeAnnotation()?.let { build(it) }
        } ?: listOf()

        // Parse return type
        val returnType = buildFromType(ctx.type_())

        return CodeTypeRef(
            raw = raw,
            kind = TypeRefKind.FUNCTION,
            parameterTypes = paramTypes,
            returnType = returnType
        )
    }
}
