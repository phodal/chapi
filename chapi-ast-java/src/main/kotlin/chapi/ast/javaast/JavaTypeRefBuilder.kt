package chapi.ast.javaast

import chapi.ast.antlr.JavaParser
import chapi.domain.core.CodeTypeRef
import chapi.domain.core.TypeRefKind

/**
 * Builds CodeTypeRef from Java type contexts.
 * Handles: primitives, arrays, generics, parameterized types.
 */
object JavaTypeRefBuilder {

    /**
     * Build CodeTypeRef from typeType context.
     */
    fun build(ctx: JavaParser.TypeTypeContext?): CodeTypeRef? {
        if (ctx == null) return null
        val raw = ctx.text

        // Check for array brackets
        val arrayDims = ctx.LBRACK()?.size ?: 0

        // Primitive type
        ctx.primitiveType()?.let { primitiveCtx ->
            val name = primitiveCtx.text
            return if (arrayDims > 0) {
                CodeTypeRef(
                    raw = raw,
                    name = name,
                    kind = TypeRefKind.ARRAY,
                    valueType = CodeTypeRef(raw = name, name = name, kind = TypeRefKind.SIMPLE),
                    arrayDimensions = arrayDims
                )
            } else {
                CodeTypeRef(raw = raw, name = name, kind = TypeRefKind.SIMPLE)
            }
        }

        // Class or interface type
        ctx.classOrInterfaceType()?.let { classType ->
            val baseType = buildClassOrInterfaceType(classType)
            return if (arrayDims > 0) {
                CodeTypeRef(
                    raw = raw,
                    name = baseType.name,
                    kind = TypeRefKind.ARRAY,
                    valueType = baseType,
                    arrayDimensions = arrayDims,
                    args = baseType.args
                )
            } else {
                baseType.copy(raw = raw)
            }
        }

        return CodeTypeRef(raw = raw, name = raw, kind = TypeRefKind.UNKNOWN)
    }

    private fun buildClassOrInterfaceType(ctx: JavaParser.ClassOrInterfaceTypeContext): CodeTypeRef {
        val raw = ctx.text

        // classOrInterfaceType wraps classType
        val classType = ctx.classType() ?: return CodeTypeRef(raw = raw, name = raw, kind = TypeRefKind.UNKNOWN)

        // Get type identifiers from classType
        val identifiers = classType.typeIdentifier()
        if (identifiers.isNullOrEmpty()) {
            return CodeTypeRef(raw = raw, name = raw, kind = TypeRefKind.UNKNOWN)
        }

        // Get the last identifier (the actual type name)
        val lastIdent = identifiers.last()
        val name = lastIdent.text

        // Build qualified name from all identifiers
        val allParts = identifiers.map { it.text }
        val qualified = if (allParts.size > 1) allParts.joinToString(".") else ""

        // Check for generic arguments (typeArguments comes after typeIdentifier in classType)
        val typeArgs = classType.typeArguments()?.lastOrNull()
        
        if (typeArgs != null) {
            val args = buildTypeArguments(typeArgs)
            
            // Special handling for common Java generic types
            return when (name.lowercase()) {
                "list", "arraylist", "linkedlist" -> CodeTypeRef(
                    raw = raw,
                    name = name,
                    qualified = qualified,
                    kind = TypeRefKind.GENERIC,
                    args = args,
                    valueType = args.firstOrNull()
                )
                "set", "hashset", "treeset", "linkedhashset" -> CodeTypeRef(
                    raw = raw,
                    name = name,
                    qualified = qualified,
                    kind = TypeRefKind.GENERIC,
                    args = args,
                    valueType = args.firstOrNull()
                )
                "map", "hashmap", "treemap", "linkedhashmap" -> CodeTypeRef(
                    raw = raw,
                    name = name,
                    qualified = qualified,
                    kind = TypeRefKind.GENERIC,
                    args = args,
                    keyType = args.getOrNull(0),
                    valueType = args.getOrNull(1)
                )
                "optional" -> CodeTypeRef(
                    raw = raw,
                    name = name,
                    qualified = qualified,
                    kind = TypeRefKind.NULLABLE,
                    args = args,
                    nullable = true,
                    valueType = args.firstOrNull()
                )
                "future", "completablefuture", "supplier", "consumer", "function", "predicate" -> CodeTypeRef(
                    raw = raw,
                    name = name,
                    qualified = qualified,
                    kind = TypeRefKind.GENERIC,
                    args = args
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

    private fun buildTypeArguments(ctx: JavaParser.TypeArgumentsContext): List<CodeTypeRef> {
        return ctx.typeArgument()?.mapNotNull { arg ->
            // TypeArgument can be a type or a wildcard
            arg.typeType()?.let { build(it) } 
                ?: arg.text?.let { 
                    // Wildcard: ?, ? extends T, ? super T
                    CodeTypeRef(raw = it, name = it, kind = TypeRefKind.SIMPLE)
                }
        } ?: listOf()
    }

    /**
     * Build CodeTypeRef from a type string.
     */
    fun buildFromString(typeStr: String): CodeTypeRef {
        val raw = typeStr.trim()

        return when {
            // Array with brackets
            raw.endsWith("[]") -> {
                val dims = raw.count { it == '[' }
                val baseRaw = raw.replace("[]", "")
                val base = buildFromString(baseRaw)
                CodeTypeRef(
                    raw = raw,
                    name = base.name,
                    kind = TypeRefKind.ARRAY,
                    valueType = base,
                    arrayDimensions = dims
                )
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
            // Primitive types
            raw in listOf("int", "long", "short", "byte", "float", "double", "boolean", "char", "void") -> {
                CodeTypeRef(raw = raw, name = raw, kind = TypeRefKind.SIMPLE)
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
                '<' -> {
                    depth++
                    current.append(char)
                }
                '>' -> {
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
