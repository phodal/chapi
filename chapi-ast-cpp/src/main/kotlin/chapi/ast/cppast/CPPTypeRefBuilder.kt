package chapi.ast.cppast

import chapi.ast.antlr.CPP14Parser
import chapi.domain.core.CodeTypeRef
import chapi.domain.core.TypeRefKind

/**
 * Builds CodeTypeRef from C++ type contexts.
 * Handles: pointers, references, templates, const/volatile.
 */
object CPPTypeRefBuilder {

    /**
     * Build CodeTypeRef from typeSpecifier context.
     */
    fun build(ctx: CPP14Parser.TypeSpecifierContext?): CodeTypeRef? {
        if (ctx == null) return null
        val raw = ctx.text

        // TrailingTypeSpecifier - most common case
        ctx.trailingTypeSpecifier()?.let { return buildTrailingTypeSpecifier(it, raw) }

        // ClassSpecifier - class/struct definition
        ctx.classSpecifier()?.let { 
            val name = it.classHead()?.classHeadName()?.className()?.text ?: raw
            return CodeTypeRef(raw = raw, name = name, kind = TypeRefKind.SIMPLE)
        }

        // EnumSpecifier
        ctx.enumSpecifier()?.let {
            val name = it.enumHead()?.Identifier()?.text ?: raw
            return CodeTypeRef(raw = raw, name = name, kind = TypeRefKind.SIMPLE)
        }

        return CodeTypeRef(raw = raw, name = raw, kind = TypeRefKind.UNKNOWN)
    }

    private fun buildTrailingTypeSpecifier(ctx: CPP14Parser.TrailingTypeSpecifierContext, raw: String): CodeTypeRef {
        // SimpleTypeSpecifier
        ctx.simpleTypeSpecifier()?.let { return buildSimpleTypeSpecifier(it, raw) }

        // ElaboratedTypeSpecifier (class X, struct Y, enum Z)
        ctx.elaboratedTypeSpecifier()?.let {
            val name = it.Identifier()?.text 
                ?: it.simpleTemplateId()?.templateName()?.text 
                ?: raw
            return CodeTypeRef(raw = raw, name = name, kind = TypeRefKind.SIMPLE)
        }

        // TypeNameSpecifier (typename T)
        ctx.typeNameSpecifier()?.let {
            val name = it.Identifier()?.text 
                ?: it.simpleTemplateId()?.templateName()?.text 
                ?: raw
            return CodeTypeRef(raw = raw, name = name, kind = TypeRefKind.SIMPLE)
        }

        // CvQualifier (const, volatile)
        ctx.cvQualifier()?.let {
            return CodeTypeRef(
                raw = raw,
                name = it.text,
                kind = TypeRefKind.SIMPLE,
                isConst = it.Const() != null
            )
        }

        return CodeTypeRef(raw = raw, name = raw, kind = TypeRefKind.UNKNOWN)
    }

    private fun buildSimpleTypeSpecifier(ctx: CPP14Parser.SimpleTypeSpecifierContext, raw: String): CodeTypeRef {
        // Check for built-in types
        ctx.Char()?.let { return CodeTypeRef(raw = raw, name = "char", kind = TypeRefKind.SIMPLE) }
        ctx.Char16()?.let { return CodeTypeRef(raw = raw, name = "char16_t", kind = TypeRefKind.SIMPLE) }
        ctx.Char32()?.let { return CodeTypeRef(raw = raw, name = "char32_t", kind = TypeRefKind.SIMPLE) }
        ctx.Wchar()?.let { return CodeTypeRef(raw = raw, name = "wchar_t", kind = TypeRefKind.SIMPLE) }
        ctx.Bool()?.let { return CodeTypeRef(raw = raw, name = "bool", kind = TypeRefKind.SIMPLE) }
        ctx.Short()?.let { return CodeTypeRef(raw = raw, name = "short", kind = TypeRefKind.SIMPLE) }
        ctx.Int()?.let { return CodeTypeRef(raw = raw, name = "int", kind = TypeRefKind.SIMPLE) }
        ctx.Long()?.let { return CodeTypeRef(raw = raw, name = "long", kind = TypeRefKind.SIMPLE) }
        ctx.Signed()?.let { return CodeTypeRef(raw = raw, name = "signed", kind = TypeRefKind.SIMPLE) }
        ctx.Unsigned()?.let { return CodeTypeRef(raw = raw, name = "unsigned", kind = TypeRefKind.SIMPLE) }
        ctx.Float()?.let { return CodeTypeRef(raw = raw, name = "float", kind = TypeRefKind.SIMPLE) }
        ctx.Double()?.let { return CodeTypeRef(raw = raw, name = "double", kind = TypeRefKind.SIMPLE) }
        ctx.Void()?.let { return CodeTypeRef(raw = raw, name = "void", kind = TypeRefKind.SIMPLE) }
        ctx.Auto()?.let { return CodeTypeRef(raw = raw, name = "auto", kind = TypeRefKind.SIMPLE) }

        // TypeName (user-defined type)
        ctx.theTypeName()?.let { typeName ->
            val name = typeName.className()?.text ?: typeName.text
            return CodeTypeRef(raw = raw, name = name, kind = TypeRefKind.SIMPLE)
        }

        // SimpleTemplateId (template instantiation like vector<int>)
        ctx.simpleTemplateId()?.let { return buildSimpleTemplateId(it, raw) }

        // DecltypeSpecifier (decltype(expr))
        ctx.decltypeSpecifier()?.let {
            return CodeTypeRef(raw = raw, name = "decltype", kind = TypeRefKind.SIMPLE)
        }

        return CodeTypeRef(raw = raw, name = raw, kind = TypeRefKind.UNKNOWN)
    }

    private fun buildSimpleTemplateId(ctx: CPP14Parser.SimpleTemplateIdContext, raw: String): CodeTypeRef {
        val name = ctx.templateName()?.text ?: ""
        val args = ctx.templateArgumentList()?.templateArgument()?.mapNotNull { arg ->
            // Try to get type from theTypeId
            arg.theTypeId()?.let { typeId ->
                val typeRaw = typeId.text
                buildFromString(typeRaw)
            }
        } ?: listOf()

        // Special handling for common C++ types
        return when (name.lowercase()) {
            "vector", "list", "deque", "array", "forward_list" -> CodeTypeRef(
                raw = raw,
                name = name,
                kind = TypeRefKind.GENERIC,
                args = args,
                valueType = args.firstOrNull()
            )
            "map", "unordered_map", "multimap", "unordered_multimap" -> CodeTypeRef(
                raw = raw,
                name = name,
                kind = TypeRefKind.GENERIC,
                args = args,
                keyType = args.getOrNull(0),
                valueType = args.getOrNull(1)
            )
            "set", "unordered_set", "multiset", "unordered_multiset" -> CodeTypeRef(
                raw = raw,
                name = name,
                kind = TypeRefKind.GENERIC,
                args = args,
                valueType = args.firstOrNull()
            )
            "optional" -> CodeTypeRef(
                raw = raw,
                name = name,
                kind = TypeRefKind.NULLABLE,
                nullable = true,
                args = args,
                valueType = args.firstOrNull()
            )
            "unique_ptr", "shared_ptr", "weak_ptr" -> CodeTypeRef(
                raw = raw,
                name = name,
                kind = TypeRefKind.POINTER,
                args = args,
                valueType = args.firstOrNull()
            )
            "pair" -> CodeTypeRef(
                raw = raw,
                name = name,
                kind = TypeRefKind.TUPLE,
                args = args,
                tupleElements = args
            )
            "tuple" -> CodeTypeRef(
                raw = raw,
                name = name,
                kind = TypeRefKind.TUPLE,
                args = args,
                tupleElements = args
            )
            "function" -> CodeTypeRef(
                raw = raw,
                name = name,
                kind = TypeRefKind.FUNCTION,
                args = args
            )
            else -> CodeTypeRef(
                raw = raw,
                name = name,
                kind = TypeRefKind.GENERIC,
                args = args
            )
        }
    }

    /**
     * Build CodeTypeRef from a type string.
     */
    fun buildFromString(typeStr: String): CodeTypeRef {
        val raw = typeStr.trim()

        return when {
            // Pointer type
            raw.endsWith("*") -> {
                val baseRaw = raw.dropLast(1).trim()
                val pointerDepth = raw.count { it == '*' }
                val base = buildFromString(baseRaw.replace("*", "").trim())
                CodeTypeRef(
                    raw = raw,
                    name = base.name,
                    kind = TypeRefKind.POINTER,
                    valueType = base,
                    pointerDepth = pointerDepth
                )
            }
            // Reference type
            raw.endsWith("&") -> {
                val baseRaw = raw.dropLast(1).trim()
                val base = buildFromString(baseRaw)
                CodeTypeRef(
                    raw = raw,
                    name = base.name,
                    kind = TypeRefKind.REFERENCE,
                    isReference = true,
                    valueType = base
                )
            }
            // Rvalue reference
            raw.endsWith("&&") -> {
                val baseRaw = raw.dropLast(2).trim()
                val base = buildFromString(baseRaw)
                CodeTypeRef(
                    raw = raw,
                    name = base.name,
                    kind = TypeRefKind.REFERENCE,
                    isReference = true,
                    valueType = base
                )
            }
            // Const type
            raw.startsWith("const ") -> {
                val baseRaw = raw.removePrefix("const ").trim()
                val base = buildFromString(baseRaw)
                CodeTypeRef(
                    raw = raw,
                    name = base.name,
                    kind = base.kind,
                    isConst = true,
                    valueType = base.valueType,
                    args = base.args
                )
            }
            // Array type
            raw.endsWith("]") && raw.contains("[") -> {
                val bracketStart = raw.lastIndexOf('[')
                val baseRaw = raw.substring(0, bracketStart).trim()
                val base = buildFromString(baseRaw)
                val sizeStr = raw.substring(bracketStart + 1, raw.length - 1)
                val size = sizeStr.toIntOrNull()
                CodeTypeRef(
                    raw = raw,
                    name = base.name,
                    kind = TypeRefKind.ARRAY,
                    valueType = base,
                    arrayDimensions = 1,
                    arraySize = size
                )
            }
            // Template type
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
            // Qualified name (namespace::Type)
            raw.contains("::") && !raw.contains("<") -> {
                CodeTypeRef(
                    raw = raw,
                    name = raw.substringAfterLast("::"),
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
