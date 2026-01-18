package chapi.ast.goast

import chapi.ast.antlr.GoParser
import chapi.domain.core.CodeTypeRef
import chapi.domain.core.TypeRefKind

/**
 * Builds CodeTypeRef from Go type contexts.
 * Handles: pointer, slice, map, channel, array, struct, interface, function types.
 */
object GoTypeRefBuilder {

    /**
     * Build CodeTypeRef from type_ context.
     */
    fun build(ctx: GoParser.Type_Context?): CodeTypeRef? {
        if (ctx == null) return null
        val raw = ctx.text

        // Check for type literal
        val typeLit = ctx.typeLit()
        if (typeLit != null) {
            return buildTypeLit(typeLit, raw)
        }

        // Type name (possibly with type arguments for generics)
        val typeName = ctx.typeName()
        if (typeName != null) {
            val name = typeName.text
            val typeArgs = ctx.typeArgs()
            
            if (typeArgs != null) {
                // Generic type: SomeType[T, U]
                val args = typeArgs.typeList()?.type_()?.mapNotNull { build(it) } ?: listOf()
                return CodeTypeRef(
                    raw = raw,
                    name = name,
                    kind = TypeRefKind.GENERIC,
                    args = args
                )
            }
            
            // Simple type name
            return CodeTypeRef(
                raw = raw,
                name = name,
                kind = TypeRefKind.SIMPLE
            )
        }

        // Parenthesized type
        val parenType = ctx.type_()
        if (parenType != null) {
            return build(parenType)
        }

        return CodeTypeRef(raw = raw, name = raw, kind = TypeRefKind.UNKNOWN)
    }

    private fun buildTypeLit(ctx: GoParser.TypeLitContext, rawOverride: String): CodeTypeRef {
        val raw = rawOverride

        // Check each type literal alternative
        ctx.pointerType()?.let { return buildPointerType(it, raw) }
        ctx.sliceType()?.let { return buildSliceType(it, raw) }
        ctx.mapType()?.let { return buildMapType(it, raw) }
        ctx.channelType()?.let { return buildChannelType(it, raw) }
        ctx.arrayType()?.let { return buildArrayType(it, raw) }
        ctx.structType()?.let { return buildStructType(it, raw) }
        ctx.interfaceType()?.let { return buildInterfaceType(it, raw) }
        ctx.functionType()?.let { return buildFunctionType(it, raw) }

        return CodeTypeRef(raw = raw, name = raw, kind = TypeRefKind.UNKNOWN)
    }

    private fun buildPointerType(ctx: GoParser.PointerTypeContext, raw: String): CodeTypeRef {
        val innerType = build(ctx.type_())
        val depth = countPointerDepth(ctx)
        
        return CodeTypeRef(
            raw = raw,
            name = innerType?.name ?: "",
            kind = TypeRefKind.POINTER,
            valueType = innerType,
            pointerDepth = depth
        )
    }

    private fun countPointerDepth(ctx: GoParser.PointerTypeContext): Int {
        var depth = 1
        var inner = ctx.type_()?.typeLit()?.pointerType()
        while (inner != null) {
            depth++
            inner = inner.type_()?.typeLit()?.pointerType()
        }
        return depth
    }

    private fun buildSliceType(ctx: GoParser.SliceTypeContext, raw: String): CodeTypeRef {
        val elementType = build(ctx.elementType()?.type_())
        
        return CodeTypeRef(
            raw = raw,
            name = "slice",
            kind = TypeRefKind.SLICE,
            valueType = elementType,
            isSlice = true
        )
    }

    private fun buildMapType(ctx: GoParser.MapTypeContext, raw: String): CodeTypeRef {
        val keyType = build(ctx.type_())
        val valueType = build(ctx.elementType()?.type_())
        
        return CodeTypeRef(
            raw = raw,
            name = "map",
            kind = TypeRefKind.MAP,
            keyType = keyType,
            valueType = valueType
        )
    }

    private fun buildChannelType(ctx: GoParser.ChannelTypeContext, raw: String): CodeTypeRef {
        val elementType = build(ctx.elementType()?.type_())
        
        // Determine channel direction
        val direction = when {
            ctx.RECEIVE() != null && ctx.CHAN() != null -> {
                // Check the order to determine direction
                val chanIndex = ctx.children.indexOfFirst { it.text == "chan" }
                val recvIndex = ctx.children.indexOfFirst { it.text == "<-" }
                when {
                    recvIndex < chanIndex -> "receive" // <-chan
                    else -> "send" // chan<-
                }
            }
            else -> null // bidirectional: chan
        }
        
        return CodeTypeRef(
            raw = raw,
            name = "chan",
            kind = TypeRefKind.CHANNEL,
            valueType = elementType,
            channelDirection = direction
        )
    }

    private fun buildArrayType(ctx: GoParser.ArrayTypeContext, raw: String): CodeTypeRef {
        val elementType = build(ctx.elementType()?.type_())
        val sizeExpr = ctx.arrayLength()?.expression()?.text
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

    private fun buildStructType(ctx: GoParser.StructTypeContext, raw: String): CodeTypeRef {
        return CodeTypeRef(
            raw = raw,
            name = "struct",
            kind = TypeRefKind.SIMPLE
        )
    }

    private fun buildInterfaceType(ctx: GoParser.InterfaceTypeContext, raw: String): CodeTypeRef {
        // Check if it's the empty interface (interface{} or any)
        val isEmpty = ctx.typeElement().isEmpty() && ctx.methodSpec().isEmpty()
        
        return CodeTypeRef(
            raw = raw,
            name = if (isEmpty) "interface{}" else "interface",
            kind = TypeRefKind.INTERFACE
        )
    }

    private fun buildFunctionType(ctx: GoParser.FunctionTypeContext, raw: String): CodeTypeRef {
        val signature = ctx.signature()
        
        // Parse parameter types
        val paramTypes = signature?.parameters()?.parameterDecl()?.mapNotNull { param ->
            build(param.type_())
        } ?: listOf()
        
        // Parse return type
        val result = signature?.result()
        val returnType = when {
            result?.parameters() != null -> {
                // Multiple return values - create tuple
                val returns = result.parameters().parameterDecl().mapNotNull { build(it.type_()) }
                if (returns.size == 1) returns.first()
                else CodeTypeRef(
                    raw = result.text,
                    kind = TypeRefKind.TUPLE,
                    tupleElements = returns
                )
            }
            result?.type_() != null -> build(result.type_())
            else -> null
        }
        
        return CodeTypeRef(
            raw = raw,
            kind = TypeRefKind.FUNCTION,
            parameterTypes = paramTypes,
            returnType = returnType
        )
    }

    /**
     * Build CodeTypeRef from a type string (simple parsing for common patterns).
     */
    fun buildFromString(typeStr: String): CodeTypeRef {
        val raw = typeStr.trim()
        
        return when {
            // Pointer
            raw.startsWith("*") -> {
                val depth = raw.takeWhile { it == '*' }.length
                val innerRaw = raw.drop(depth)
                val inner = buildFromString(innerRaw)
                CodeTypeRef(
                    raw = raw,
                    name = inner.name,
                    kind = TypeRefKind.POINTER,
                    valueType = inner,
                    pointerDepth = depth
                )
            }
            // Slice
            raw.startsWith("[]") -> {
                val elementRaw = raw.drop(2)
                val element = buildFromString(elementRaw)
                CodeTypeRef(
                    raw = raw,
                    name = "slice",
                    kind = TypeRefKind.SLICE,
                    valueType = element,
                    isSlice = true
                )
            }
            // Map
            raw.startsWith("map[") -> {
                val bracketEnd = findMatchingBracket(raw, 3)
                if (bracketEnd > 3) {
                    val keyRaw = raw.substring(4, bracketEnd)
                    val valueRaw = raw.substring(bracketEnd + 1)
                    CodeTypeRef(
                        raw = raw,
                        name = "map",
                        kind = TypeRefKind.MAP,
                        keyType = buildFromString(keyRaw),
                        valueType = buildFromString(valueRaw)
                    )
                } else {
                    CodeTypeRef(raw = raw, name = "map", kind = TypeRefKind.MAP)
                }
            }
            // Channel
            raw.startsWith("chan ") || raw.startsWith("chan<-") || raw.startsWith("<-chan ") -> {
                val (direction, elementRaw) = when {
                    raw.startsWith("<-chan ") -> "receive" to raw.drop(7)
                    raw.startsWith("chan<- ") -> "send" to raw.drop(7)
                    raw.startsWith("chan ") -> null to raw.drop(5)
                    else -> null to raw
                }
                CodeTypeRef(
                    raw = raw,
                    name = "chan",
                    kind = TypeRefKind.CHANNEL,
                    valueType = buildFromString(elementRaw),
                    channelDirection = direction
                )
            }
            // Array with size [n]T
            raw.startsWith("[") && !raw.startsWith("[]") -> {
                val bracketEnd = raw.indexOf(']')
                if (bracketEnd > 1) {
                    val sizeStr = raw.substring(1, bracketEnd)
                    val elementRaw = raw.substring(bracketEnd + 1)
                    CodeTypeRef(
                        raw = raw,
                        name = buildFromString(elementRaw).name,
                        kind = TypeRefKind.ARRAY,
                        valueType = buildFromString(elementRaw),
                        arrayDimensions = 1,
                        arraySize = sizeStr.toIntOrNull()
                    )
                } else {
                    CodeTypeRef(raw = raw, name = raw, kind = TypeRefKind.UNKNOWN)
                }
            }
            // Interface
            raw == "interface{}" || raw == "any" -> {
                CodeTypeRef(raw = raw, name = raw, kind = TypeRefKind.INTERFACE)
            }
            // Qualified identifier (pkg.Type)
            raw.contains(".") && !raw.contains("[") -> {
                CodeTypeRef(
                    raw = raw,
                    name = raw.substringAfterLast("."),
                    qualified = raw,
                    kind = TypeRefKind.SIMPLE
                )
            }
            // Generic type T[U]
            raw.contains("[") && raw.endsWith("]") -> {
                val bracketStart = raw.indexOf('[')
                val name = raw.substring(0, bracketStart)
                val argsRaw = raw.substring(bracketStart + 1, raw.length - 1)
                val args = argsRaw.split(",").map { buildFromString(it.trim()) }
                CodeTypeRef(
                    raw = raw,
                    name = name,
                    kind = TypeRefKind.GENERIC,
                    args = args
                )
            }
            // Simple type
            else -> {
                CodeTypeRef(raw = raw, name = raw, kind = TypeRefKind.SIMPLE)
            }
        }
    }

    private fun findMatchingBracket(str: String, startIndex: Int): Int {
        var depth = 1
        var i = startIndex + 1
        while (i < str.length && depth > 0) {
            when (str[i]) {
                '[' -> depth++
                ']' -> depth--
            }
            i++
        }
        return if (depth == 0) i - 1 else -1
    }
}
