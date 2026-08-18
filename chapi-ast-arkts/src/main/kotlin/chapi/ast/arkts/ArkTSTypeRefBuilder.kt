package chapi.ast.arkts

import chapi.ast.antlr.ArkTSParser
import chapi.domain.core.CodeTypeRef
import chapi.domain.core.TypeRefKind

/**
 * Builds CodeTypeRef from ArkTS type annotations.
 * Handles: union, intersection, generic, array, tuple, function types, etc.
 */
object ArkTSTypeRefBuilder {

    /**
     * Build CodeTypeRef from type annotation context.
     */
    fun build(ctx: ArkTSParser.TypeAnnotationContext?): CodeTypeRef? {
        if (ctx == null) return null
        return buildFromType(ctx.type_())
    }

    /**
     * Build CodeTypeRef from typeRef context (compatibility wrapper).
     */
    fun buildFromTypeRef(ctx: ArkTSParser.TypeRefContext?): CodeTypeRef? {
        if (ctx == null) return null
        return buildFromType(ctx.type_())
    }

    /**
     * Build CodeTypeRef from type_ context.
     */
    fun buildFromType(ctx: ArkTSParser.Type_Context?): CodeTypeRef? {
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
        ctx: ArkTSParser.UnionOrIntersectionOrPrimaryTypeContext,
        rawOverride: String? = null
    ): CodeTypeRef {
        val raw = rawOverride ?: ctx.text

        return when (ctx) {
            is ArkTSParser.UnionContext -> {
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
            is ArkTSParser.IntersectionContext -> {
                val types = flattenIntersection(ctx)
                CodeTypeRef(
                    raw = raw,
                    kind = TypeRefKind.INTERSECTION,
                    intersection = types
                )
            }
            is ArkTSParser.PrimaryContext -> {
                buildPrimaryType(ctx.primaryType(), raw)
            }
            else -> CodeTypeRef(raw = raw, name = raw, kind = TypeRefKind.UNKNOWN)
        }
    }

    private fun flattenUnion(ctx: ArkTSParser.UnionContext): List<CodeTypeRef> {
        val result = mutableListOf<CodeTypeRef>()
        for (child in ctx.unionOrIntersectionOrPrimaryType()) {
            when (child) {
                is ArkTSParser.UnionContext -> result.addAll(flattenUnion(child))
                else -> buildUnionOrIntersection(child).let { result.add(it) }
            }
        }
        return result
    }

    private fun flattenIntersection(ctx: ArkTSParser.IntersectionContext): List<CodeTypeRef> {
        val result = mutableListOf<CodeTypeRef>()
        for (child in ctx.unionOrIntersectionOrPrimaryType()) {
            when (child) {
                is ArkTSParser.IntersectionContext -> result.addAll(flattenIntersection(child))
                else -> buildUnionOrIntersection(child).let { result.add(it) }
            }
        }
        return result
    }

    private fun buildPrimaryType(ctx: ArkTSParser.PrimaryTypeContext?, rawOverride: String? = null): CodeTypeRef {
        if (ctx == null) return CodeTypeRef(raw = rawOverride ?: "", kind = TypeRefKind.UNKNOWN)

        val raw = rawOverride ?: ctx.text

        return when (ctx) {
            is ArkTSParser.ParenthesizedPrimTypeContext -> {
                buildFromType(ctx.type_()) ?: CodeTypeRef(raw = raw, kind = TypeRefKind.UNKNOWN)
            }
            is ArkTSParser.PredefinedPrimTypeContext -> {
                val name = ctx.predefinedType().text
                val kind = if (name == "void" || name == "never") TypeRefKind.SIMPLE
                           else if (name == "any" || name == "unknown") TypeRefKind.INTERFACE
                           else TypeRefKind.SIMPLE
                CodeTypeRef(raw = raw, name = name, kind = kind)
            }
            is ArkTSParser.ReferencePrimTypeContext -> {
                buildTypeReference(ctx.typeReference(), raw)
            }
            is ArkTSParser.ArrayPrimTypeContext -> {
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
            is ArkTSParser.TuplePrimTypeContext -> {
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
            is ArkTSParser.ObjectPrimTypeContext -> {
                // Object type like { x: number, y: string }
                CodeTypeRef(raw = raw, name = "object", kind = TypeRefKind.SIMPLE)
            }
            is ArkTSParser.QueryPrimTypeContext -> {
                // typeof queries
                CodeTypeRef(raw = raw, name = ctx.text, kind = TypeRefKind.SIMPLE)
            }
            is ArkTSParser.ThisPrimTypeContext -> {
                CodeTypeRef(raw = raw, name = "this", kind = TypeRefKind.SIMPLE)
            }
            is ArkTSParser.RedefinitionOfTypeContext -> {
                // typeReference Is primaryType
                CodeTypeRef(raw = raw, name = ctx.text, kind = TypeRefKind.SIMPLE)
            }
            is ArkTSParser.KeyOfTypeContext -> {
                // KeyOf primaryType
                CodeTypeRef(raw = raw, name = ctx.text, kind = TypeRefKind.SIMPLE)
            }
            else -> CodeTypeRef(raw = raw, name = raw, kind = TypeRefKind.UNKNOWN)
        }
    }

    private fun countArrayDimensions(ctx: ArkTSParser.ArrayPrimTypeContext): Int {
        var count = 1
        val primaryTypes = ctx.primaryType()
        if (primaryTypes.isNotEmpty()) {
            var inner = primaryTypes[0]
            while (inner is ArkTSParser.ArrayPrimTypeContext && inner.primaryType().isNotEmpty()) {
                count++
                inner = inner.primaryType()[0]
            }
        }
        return count
    }

    private fun buildTypeReference(ctx: ArkTSParser.TypeReferenceContext?, rawOverride: String?): CodeTypeRef {
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

    private fun buildGenericType(ctx: ArkTSParser.TypeGenericContext?, rawOverride: String?): CodeTypeRef {
        if (ctx == null) return CodeTypeRef(raw = rawOverride ?: "", kind = TypeRefKind.UNKNOWN)

        val raw = rawOverride ?: ctx.text
        val args = ctx.typeArgumentList()?.typeArgument()?.mapNotNull {
            buildFromType(it.type_())
        } ?: listOf()

        return CodeTypeRef(raw = raw, kind = TypeRefKind.GENERIC, args = args)
    }

    private fun buildFunctionType(ctx: ArkTSParser.FunctionTypeContext?, rawOverride: String?): CodeTypeRef {
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
