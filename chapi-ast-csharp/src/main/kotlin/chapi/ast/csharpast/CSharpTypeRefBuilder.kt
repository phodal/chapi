package chapi.ast.csharpast

import chapi.ast.antlr.CSharpParser
import chapi.domain.core.CodeTypeRef
import chapi.domain.core.TypeRefKind

/**
 * Builds CodeTypeRef from C# type contexts.
 * Handles: nullable reference types (?), generics, arrays, tuples.
 */
object CSharpTypeRefBuilder {

    /**
     * Build CodeTypeRef from type_ context.
     */
    fun build(ctx: CSharpParser.Type_Context?): CodeTypeRef? {
        if (ctx == null) return null
        val raw = ctx.text

        // Base type
        ctx.base_type()?.let { baseType ->
            return buildBaseType(baseType, raw, ctx)
        }

        return CodeTypeRef(raw = raw, name = raw, kind = TypeRefKind.UNKNOWN)
    }

    private fun buildBaseType(
        ctx: CSharpParser.Base_typeContext, 
        rawOverride: String,
        parentCtx: CSharpParser.Type_Context
    ): CodeTypeRef {
        val raw = rawOverride

        // Check for nullable suffix (?)
        val isNullable = parentCtx.INTERR()?.isNotEmpty() == true
        
        // Check for array rank specifiers
        val arrayRanks = parentCtx.rank_specifier()
        val isArray = arrayRanks?.isNotEmpty() == true
        val arrayDims = arrayRanks?.size ?: 0
        
        // Check for pointer types (*)
        val pointerDepth = parentCtx.STAR()?.size ?: 0

        // Simple type (predefined types like int, string, etc.)
        ctx.simple_type()?.let { simpleType ->
            val name = simpleType.text
            val baseRef = CodeTypeRef(raw = simpleType.text, name = name, kind = TypeRefKind.SIMPLE)
            
            return wrapWithModifiers(raw, baseRef, isNullable, isArray, arrayDims, pointerDepth)
        }

        // Class type (user-defined types, generics)
        ctx.class_type()?.let { classType ->
            val baseRef = buildClassType(classType, classType.text)
            return wrapWithModifiers(raw, baseRef, isNullable, isArray, arrayDims, pointerDepth)
        }

        // Tuple type
        ctx.tuple_type()?.let { tupleType ->
            val elements = tupleType.tuple_element()?.mapNotNull { elem ->
                build(elem.type_())
            } ?: listOf()
            
            val baseRef = CodeTypeRef(
                raw = tupleType.text,
                kind = TypeRefKind.TUPLE,
                tupleElements = elements
            )
            return wrapWithModifiers(raw, baseRef, isNullable, isArray, arrayDims, pointerDepth)
        }

        return CodeTypeRef(raw = raw, name = raw, kind = TypeRefKind.UNKNOWN)
    }

    private fun wrapWithModifiers(
        raw: String,
        baseRef: CodeTypeRef,
        isNullable: Boolean,
        isArray: Boolean,
        arrayDims: Int,
        pointerDepth: Int = 0
    ): CodeTypeRef {
        var result = baseRef.copy(raw = raw)
        
        if (pointerDepth > 0) {
            result = CodeTypeRef(
                raw = raw,
                name = baseRef.name,
                kind = TypeRefKind.POINTER,
                valueType = baseRef,
                pointerDepth = pointerDepth
            )
        }
        
        if (isArray) {
            result = CodeTypeRef(
                raw = raw,
                name = baseRef.name,
                kind = TypeRefKind.ARRAY,
                valueType = if (pointerDepth > 0) result else baseRef,
                arrayDimensions = arrayDims,
                args = baseRef.args
            )
        }
        
        if (isNullable) {
            result = CodeTypeRef(
                raw = raw,
                name = result.name,
                kind = TypeRefKind.NULLABLE,
                nullable = true,
                valueType = result,
                args = result.args
            )
        }
        
        return result
    }

    private fun buildClassType(ctx: CSharpParser.Class_typeContext, raw: String): CodeTypeRef {
        // Namespace or type name
        val nsOrType = ctx.namespace_or_type_name()
        if (nsOrType != null) {
            return buildNamespaceOrTypeName(nsOrType, raw)
        }

        // Object, dynamic, string
        ctx.OBJECT()?.let { 
            return CodeTypeRef(raw = raw, name = "object", kind = TypeRefKind.SIMPLE)
        }
        ctx.DYNAMIC()?.let {
            return CodeTypeRef(raw = raw, name = "dynamic", kind = TypeRefKind.SIMPLE)
        }
        ctx.STRING()?.let {
            return CodeTypeRef(raw = raw, name = "string", kind = TypeRefKind.SIMPLE)
        }

        return CodeTypeRef(raw = raw, name = raw, kind = TypeRefKind.UNKNOWN)
    }

    private fun buildNamespaceOrTypeName(ctx: CSharpParser.Namespace_or_type_nameContext, raw: String): CodeTypeRef {
        val identifiers = ctx.identifier()
        val typeArgLists = ctx.type_argument_list()

        if (identifiers.isNullOrEmpty()) {
            return CodeTypeRef(raw = raw, name = raw, kind = TypeRefKind.UNKNOWN)
        }

        // Get the simple name (last identifier)
        val name = identifiers.last().text
        
        // Build qualified name
        val qualified = identifiers.map { it.text }.joinToString(".")

        // Check for generic arguments
        val lastTypeArgs = typeArgLists?.lastOrNull()
        if (lastTypeArgs != null) {
            val args = buildTypeArguments(lastTypeArgs)
            
            // Special handling for common C# generic types
            return when (name.lowercase()) {
                "list", "ilist", "ienumerable", "icollection" -> CodeTypeRef(
                    raw = raw,
                    name = name,
                    qualified = if (identifiers.size > 1) qualified else "",
                    kind = TypeRefKind.GENERIC,
                    args = args,
                    valueType = args.firstOrNull()
                )
                "dictionary", "idictionary", "concurrentdictionary" -> CodeTypeRef(
                    raw = raw,
                    name = name,
                    qualified = if (identifiers.size > 1) qualified else "",
                    kind = TypeRefKind.GENERIC,
                    args = args,
                    keyType = args.getOrNull(0),
                    valueType = args.getOrNull(1)
                )
                "task" -> CodeTypeRef(
                    raw = raw,
                    name = name,
                    qualified = if (identifiers.size > 1) qualified else "",
                    kind = TypeRefKind.GENERIC,
                    args = args,
                    valueType = args.firstOrNull()
                )
                "nullable" -> CodeTypeRef(
                    raw = raw,
                    name = name,
                    qualified = if (identifiers.size > 1) qualified else "",
                    kind = TypeRefKind.NULLABLE,
                    nullable = true,
                    args = args,
                    valueType = args.firstOrNull()
                )
                else -> CodeTypeRef(
                    raw = raw,
                    name = name,
                    qualified = if (identifiers.size > 1) qualified else "",
                    kind = TypeRefKind.GENERIC,
                    args = args
                )
            }
        }

        // Simple type
        return CodeTypeRef(
            raw = raw,
            name = name,
            qualified = if (identifiers.size > 1) qualified else "",
            kind = TypeRefKind.SIMPLE
        )
    }

    private fun buildTypeArguments(ctx: CSharpParser.Type_argument_listContext): List<CodeTypeRef> {
        return ctx.type_()?.mapNotNull { build(it) } ?: listOf()
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
            // Array type
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
            // Tuple type
            raw.startsWith("(") && raw.endsWith(")") -> {
                val content = raw.drop(1).dropLast(1)
                val elements = splitTypeList(content).map { buildFromString(it.trim()) }
                CodeTypeRef(
                    raw = raw,
                    kind = TypeRefKind.TUPLE,
                    tupleElements = elements
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
