package chapi.ast.kotlinast

import chapi.ast.antlr.KotlinParser
import chapi.domain.core.CodeTypeRef
import chapi.domain.core.TypeRefKind

/**
 * Builds CodeTypeRef from Kotlin type contexts.
 * Handles: nullable types (?), generics, function types, collections.
 */
object KotlinTypeRefBuilder {

    /**
     * Build CodeTypeRef from type context.
     */
    fun build(ctx: KotlinParser.TypeContext?): CodeTypeRef? {
        if (ctx == null) return null
        val raw = ctx.text

        // NullableType - T?
        ctx.nullableType()?.let { return buildNullableType(it, raw) }

        // TypeReference - simple or user type
        ctx.typeReference()?.let { return buildTypeReference(it, raw) }

        // ParenthesizedType - (T)
        ctx.parenthesizedType()?.let { 
            return build(it.type()) ?: CodeTypeRef(raw = raw, kind = TypeRefKind.UNKNOWN)
        }

        // FunctionType - (A, B) -> C
        ctx.functionType()?.let { return buildFunctionType(it, raw) }

        return CodeTypeRef(raw = raw, name = raw, kind = TypeRefKind.UNKNOWN)
    }

    private fun buildNullableType(ctx: KotlinParser.NullableTypeContext, raw: String): CodeTypeRef {
        // Get the inner type (typeReference or parenthesizedType)
        val innerType = ctx.typeReference()?.let { buildTypeReference(it, it.text) }
            ?: ctx.parenthesizedType()?.type()?.let { build(it) }

        return CodeTypeRef(
            raw = raw,
            name = innerType?.name ?: "",
            qualified = innerType?.qualified ?: "",
            kind = TypeRefKind.NULLABLE,
            nullable = true,
            valueType = innerType,
            args = innerType?.args ?: listOf()
        )
    }

    private fun buildTypeReference(ctx: KotlinParser.TypeReferenceContext, raw: String): CodeTypeRef {
        // DYNAMIC keyword
        if (ctx.DYNAMIC() != null) {
            return CodeTypeRef(raw = raw, name = "dynamic", kind = TypeRefKind.SIMPLE)
        }

        // UserType - most common case
        ctx.userType()?.let { return buildUserType(it, raw) }

        return CodeTypeRef(raw = raw, name = raw, kind = TypeRefKind.UNKNOWN)
    }

    private fun buildUserType(ctx: KotlinParser.UserTypeContext, raw: String): CodeTypeRef {
        val simpleTypes = ctx.simpleUserType()
        if (simpleTypes.isNullOrEmpty()) {
            return CodeTypeRef(raw = raw, name = raw, kind = TypeRefKind.UNKNOWN)
        }

        // Build qualified name from all parts
        val parts = simpleTypes.map { it.simpleIdentifier().text }
        val name = parts.last()
        val qualified = if (parts.size > 1) parts.joinToString(".") else ""

        // Check for generic arguments on the last segment
        val lastSimpleType = simpleTypes.last()
        val typeArgs = lastSimpleType.typeArguments()

        if (typeArgs != null) {
            val args = buildTypeArguments(typeArgs)
            
            // Special handling for common Kotlin types
            return when (name.lowercase()) {
                "list", "mutablelist", "arraylist" -> CodeTypeRef(
                    raw = raw,
                    name = name,
                    qualified = qualified,
                    kind = TypeRefKind.GENERIC,
                    args = args,
                    valueType = args.firstOrNull()
                )
                "set", "mutableset", "hashset", "linkedhashset" -> CodeTypeRef(
                    raw = raw,
                    name = name,
                    qualified = qualified,
                    kind = TypeRefKind.GENERIC,
                    args = args,
                    valueType = args.firstOrNull()
                )
                "map", "mutablemap", "hashmap", "linkedhashmap" -> CodeTypeRef(
                    raw = raw,
                    name = name,
                    qualified = qualified,
                    kind = TypeRefKind.GENERIC,
                    args = args,
                    keyType = args.getOrNull(0),
                    valueType = args.getOrNull(1)
                )
                "array" -> CodeTypeRef(
                    raw = raw,
                    name = name,
                    qualified = qualified,
                    kind = TypeRefKind.ARRAY,
                    args = args,
                    valueType = args.firstOrNull()
                )
                "sequence", "flow", "channel" -> CodeTypeRef(
                    raw = raw,
                    name = name,
                    qualified = qualified,
                    kind = TypeRefKind.GENERIC,
                    args = args,
                    valueType = args.firstOrNull()
                )
                "deferred", "job", "completabledeferred" -> CodeTypeRef(
                    raw = raw,
                    name = name,
                    qualified = qualified,
                    kind = TypeRefKind.GENERIC,
                    args = args
                )
                "pair" -> CodeTypeRef(
                    raw = raw,
                    name = name,
                    qualified = qualified,
                    kind = TypeRefKind.TUPLE,
                    args = args,
                    tupleElements = args
                )
                "triple" -> CodeTypeRef(
                    raw = raw,
                    name = name,
                    qualified = qualified,
                    kind = TypeRefKind.TUPLE,
                    args = args,
                    tupleElements = args
                )
                else -> CodeTypeRef(
                    raw = raw,
                    name = name,
                    qualified = qualified,
                    kind = TypeRefKind.GENERIC,
                    args = args
                )
            }
        }

        // Simple type without generics
        return CodeTypeRef(
            raw = raw,
            name = name,
            qualified = qualified,
            kind = TypeRefKind.SIMPLE
        )
    }

    private fun buildTypeArguments(ctx: KotlinParser.TypeArgumentsContext): List<CodeTypeRef> {
        return ctx.typeProjection()?.mapNotNull { proj ->
            // TypeProjection can be type or MULT (*)
            if (proj.MULT() != null) {
                CodeTypeRef(raw = "*", name = "*", kind = TypeRefKind.SIMPLE)
            } else {
                proj.type()?.let { build(it) }
            }
        } ?: listOf()
    }

    private fun buildFunctionType(ctx: KotlinParser.FunctionTypeContext, raw: String): CodeTypeRef {
        // (ReceiverType.)? (params) -> ReturnType
        val params = ctx.functionTypeParameters()?.type()?.mapNotNull { build(it) }
            ?: ctx.functionTypeParameters()?.parameter()?.mapNotNull { 
                build(it.type())
            }
            ?: listOf()

        val returnType = build(ctx.type())

        return CodeTypeRef(
            raw = raw,
            kind = TypeRefKind.FUNCTION,
            parameterTypes = params,
            returnType = returnType
        )
    }

    /**
     * Build CodeTypeRef from a type string.
     */
    fun buildFromString(typeStr: String): CodeTypeRef {
        val raw = typeStr.trim()

        return when {
            // Nullable type
            raw.endsWith("?") -> {
                val innerRaw = raw.dropLast(1)
                val inner = buildFromString(innerRaw)
                CodeTypeRef(
                    raw = raw,
                    name = inner.name,
                    kind = TypeRefKind.NULLABLE,
                    nullable = true,
                    valueType = inner,
                    args = inner.args
                )
            }
            // Function type
            raw.contains("->") && raw.contains("(") -> {
                CodeTypeRef(raw = raw, kind = TypeRefKind.FUNCTION)
            }
            // Generic type
            raw.contains("<") && raw.endsWith(">") -> {
                val bracketStart = raw.indexOf('<')
                val name = raw.substring(0, bracketStart)
                val argsRaw = raw.substring(bracketStart + 1, raw.length - 1)
                val args = splitTypeList(argsRaw).map { buildFromString(it.trim()) }
                
                CodeTypeRef(
                    raw = raw,
                    name = name,
                    kind = TypeRefKind.GENERIC,
                    args = args
                )
            }
            // Qualified type name
            raw.contains(".") && !raw.contains("<") -> {
                CodeTypeRef(
                    raw = raw,
                    name = raw.substringAfterLast("."),
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

    private fun splitTypeList(str: String): List<String> {
        val result = mutableListOf<String>()
        var depth = 0
        var current = StringBuilder()
        
        for (char in str) {
            when (char) {
                '<', '(' -> {
                    depth++
                    current.append(char)
                }
                '>', ')' -> {
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
