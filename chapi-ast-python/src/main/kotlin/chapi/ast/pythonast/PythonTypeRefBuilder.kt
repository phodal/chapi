package chapi.ast.pythonast

import chapi.ast.antlr.PythonParser
import chapi.domain.core.CodeTypeRef
import chapi.domain.core.TypeRefKind

/**
 * Builds CodeTypeRef from Python type annotations.
 * Handles: Optional, Union, List, Dict, Tuple, Callable, etc.
 */
object PythonTypeRefBuilder {

    /**
     * Build CodeTypeRef from test context (type annotation).
     */
    fun build(ctx: PythonParser.TestContext?): CodeTypeRef? {
        if (ctx == null) return null
        return buildFromString(ctx.text)
    }

    /**
     * Build CodeTypeRef from a type annotation string.
     */
    fun buildFromString(typeStr: String): CodeTypeRef {
        val raw = typeStr.trim()
        
        if (raw.isEmpty()) {
            return CodeTypeRef(raw = raw, kind = TypeRefKind.UNKNOWN)
        }

        return when {
            // None type
            raw == "None" -> CodeTypeRef(raw = raw, name = "None", kind = TypeRefKind.SIMPLE)
            
            // Optional[T] - nullable type
            raw.startsWith("Optional[") && raw.endsWith("]") -> {
                val innerRaw = raw.substring(9, raw.length - 1)
                val inner = buildFromString(innerRaw)
                CodeTypeRef(
                    raw = raw,
                    name = "Optional",
                    kind = TypeRefKind.NULLABLE,
                    nullable = true,
                    valueType = inner,
                    args = listOf(inner)
                )
            }
            
            // Union[T1, T2, ...] - union type
            raw.startsWith("Union[") && raw.endsWith("]") -> {
                val content = raw.substring(6, raw.length - 1)
                val elements = splitTypeList(content).map { buildFromString(it.trim()) }
                
                // Check if it's Optional (Union with None)
                val hasNone = elements.any { it.name == "None" }
                val nonNoneElements = elements.filter { it.name != "None" }
                
                if (hasNone && nonNoneElements.size == 1) {
                    // Union[T, None] is equivalent to Optional[T]
                    CodeTypeRef(
                        raw = raw,
                        name = "Optional",
                        kind = TypeRefKind.NULLABLE,
                        nullable = true,
                        valueType = nonNoneElements.first(),
                        union = elements
                    )
                } else {
                    CodeTypeRef(
                        raw = raw,
                        name = "Union",
                        kind = TypeRefKind.UNION,
                        union = elements
                    )
                }
            }
            
            // T | U - Python 3.10+ union syntax
            raw.contains("|") && !raw.contains("[") -> {
                val elements = raw.split("|").map { buildFromString(it.trim()) }
                val hasNone = elements.any { it.name == "None" }
                val nonNoneElements = elements.filter { it.name != "None" }
                
                if (hasNone && nonNoneElements.size == 1) {
                    CodeTypeRef(
                        raw = raw,
                        name = "Optional",
                        kind = TypeRefKind.NULLABLE,
                        nullable = true,
                        valueType = nonNoneElements.first(),
                        union = elements
                    )
                } else {
                    CodeTypeRef(
                        raw = raw,
                        kind = TypeRefKind.UNION,
                        union = elements
                    )
                }
            }
            
            // List[T], list[T]
            (raw.startsWith("List[") || raw.startsWith("list[")) && raw.endsWith("]") -> {
                val bracketStart = raw.indexOf('[')
                val name = raw.substring(0, bracketStart)
                val innerRaw = raw.substring(bracketStart + 1, raw.length - 1)
                val inner = buildFromString(innerRaw)
                CodeTypeRef(
                    raw = raw,
                    name = name,
                    kind = TypeRefKind.GENERIC,
                    args = listOf(inner),
                    valueType = inner
                )
            }
            
            // Set[T], set[T], FrozenSet[T]
            (raw.startsWith("Set[") || raw.startsWith("set[") || raw.startsWith("FrozenSet[") || raw.startsWith("frozenset[")) && raw.endsWith("]") -> {
                val bracketStart = raw.indexOf('[')
                val name = raw.substring(0, bracketStart)
                val innerRaw = raw.substring(bracketStart + 1, raw.length - 1)
                val inner = buildFromString(innerRaw)
                CodeTypeRef(
                    raw = raw,
                    name = name,
                    kind = TypeRefKind.GENERIC,
                    args = listOf(inner),
                    valueType = inner
                )
            }
            
            // Dict[K, V], dict[K, V]
            (raw.startsWith("Dict[") || raw.startsWith("dict[")) && raw.endsWith("]") -> {
                val bracketStart = raw.indexOf('[')
                val name = raw.substring(0, bracketStart)
                val content = raw.substring(bracketStart + 1, raw.length - 1)
                val parts = splitTypeList(content)
                val keyType = if (parts.isNotEmpty()) buildFromString(parts[0].trim()) else null
                val valueType = if (parts.size > 1) buildFromString(parts[1].trim()) else null
                CodeTypeRef(
                    raw = raw,
                    name = name,
                    kind = TypeRefKind.GENERIC,
                    args = listOfNotNull(keyType, valueType),
                    keyType = keyType,
                    valueType = valueType
                )
            }
            
            // Tuple[T1, T2, ...], tuple[T1, T2, ...]
            (raw.startsWith("Tuple[") || raw.startsWith("tuple[")) && raw.endsWith("]") -> {
                val bracketStart = raw.indexOf('[')
                val name = raw.substring(0, bracketStart)
                val content = raw.substring(bracketStart + 1, raw.length - 1)
                val elements = splitTypeList(content).map { buildFromString(it.trim()) }
                CodeTypeRef(
                    raw = raw,
                    name = name,
                    kind = TypeRefKind.TUPLE,
                    args = elements,
                    tupleElements = elements
                )
            }
            
            // Callable[[Args], Return]
            raw.startsWith("Callable[") && raw.endsWith("]") -> {
                val content = raw.substring(9, raw.length - 1)
                // Find the matching bracket for parameter list
                val paramEnd = findMatchingBracket(content, 0)
                if (paramEnd > 0 && content.startsWith("[")) {
                    val paramsRaw = content.substring(1, paramEnd)
                    val params = if (paramsRaw.isEmpty()) listOf() 
                                 else splitTypeList(paramsRaw).map { buildFromString(it.trim()) }
                    val returnRaw = content.substring(paramEnd + 2).trim() // Skip "], "
                    val returnType = buildFromString(returnRaw)
                    CodeTypeRef(
                        raw = raw,
                        name = "Callable",
                        kind = TypeRefKind.FUNCTION,
                        parameterTypes = params,
                        returnType = returnType
                    )
                } else {
                    // Ellipsis or other format
                    CodeTypeRef(raw = raw, name = "Callable", kind = TypeRefKind.FUNCTION)
                }
            }
            
            // Sequence[T], Iterable[T], Iterator[T]
            (raw.startsWith("Sequence[") || raw.startsWith("Iterable[") || raw.startsWith("Iterator[")) && raw.endsWith("]") -> {
                val bracketStart = raw.indexOf('[')
                val name = raw.substring(0, bracketStart)
                val innerRaw = raw.substring(bracketStart + 1, raw.length - 1)
                val inner = buildFromString(innerRaw)
                CodeTypeRef(
                    raw = raw,
                    name = name,
                    kind = TypeRefKind.GENERIC,
                    args = listOf(inner),
                    valueType = inner
                )
            }
            
            // Type[T] - class type
            raw.startsWith("Type[") && raw.endsWith("]") -> {
                val innerRaw = raw.substring(5, raw.length - 1)
                val inner = buildFromString(innerRaw)
                CodeTypeRef(
                    raw = raw,
                    name = "Type",
                    kind = TypeRefKind.GENERIC,
                    args = listOf(inner),
                    valueType = inner
                )
            }
            
            // Any generic type: Name[Args]
            raw.contains("[") && raw.endsWith("]") -> {
                val bracketStart = raw.indexOf('[')
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
            
            // Qualified type name (module.Type)
            raw.contains(".") && !raw.contains("[") -> {
                CodeTypeRef(
                    raw = raw,
                    name = raw.substringAfterLast("."),
                    qualified = raw,
                    kind = TypeRefKind.SIMPLE
                )
            }
            
            // Simple type (str, int, float, bool, etc.)
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
                '[', '(' -> {
                    depth++
                    current.append(char)
                }
                ']', ')' -> {
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

    private fun findMatchingBracket(str: String, startIndex: Int): Int {
        if (startIndex >= str.length || str[startIndex] != '[') return -1
        
        var depth = 0
        for (i in startIndex until str.length) {
            when (str[i]) {
                '[' -> depth++
                ']' -> {
                    depth--
                    if (depth == 0) return i
                }
            }
        }
        return -1
    }
}
