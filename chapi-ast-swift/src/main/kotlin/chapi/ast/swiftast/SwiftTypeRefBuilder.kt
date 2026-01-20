package chapi.ast.swiftast

import chapi.domain.core.CodeTypeRef
import chapi.domain.core.TypeRefKind

/**
 * Builder for creating CodeTypeRef from Swift type strings.
 * 
 * Handles Swift-specific type syntax including:
 * - Optional types: String?, Int?
 * - Implicitly unwrapped optionals: String!, Int!
 * - Array types: [String], [[Int]]
 * - Dictionary types: [String: Int]
 * - Tuple types: (Int, String)
 * - Function types: (Int, String) -> Bool
 * - Generic types: Array<String>, Dictionary<String, Int>
 * - Protocol composition: Protocol1 & Protocol2
 * - Opaque types: some Protocol
 * - Metatypes: Type.Type, Type.Protocol
 */
object SwiftTypeRefBuilder {
    
    fun build(typeText: String): CodeTypeRef {
        val trimmed = typeText.trim()
        if (trimmed.isEmpty()) return CodeTypeRef()
        
        return parseType(trimmed)
    }
    
    private fun parseType(typeText: String): CodeTypeRef {
        val trimmed = typeText.trim()
        
        // Handle optional types (suffix ? or !)
        if (trimmed.endsWith("?") && !trimmed.startsWith("(")) {
            val baseType = parseType(trimmed.dropLast(1))
            return CodeTypeRef.nullable(baseType)
        }
        
        if (trimmed.endsWith("!") && !trimmed.startsWith("(")) {
            val baseType = parseType(trimmed.dropLast(1))
            return baseType.copy(
                raw = trimmed,
                nullable = true // Implicitly unwrapped optional is also nullable
            )
        }
        
        // Handle array literal syntax [Type]
        if (trimmed.startsWith("[") && trimmed.endsWith("]") && !trimmed.contains(":")) {
            val innerType = trimmed.drop(1).dropLast(1).trim()
            val elementType = parseType(innerType)
            return CodeTypeRef.array(elementType)
        }
        
        // Handle dictionary literal syntax [Key: Value]
        if (trimmed.startsWith("[") && trimmed.endsWith("]") && trimmed.contains(":")) {
            val inner = trimmed.drop(1).dropLast(1).trim()
            val colonIndex = findTopLevelColon(inner)
            if (colonIndex > 0) {
                val keyType = parseType(inner.substring(0, colonIndex).trim())
                val valueType = parseType(inner.substring(colonIndex + 1).trim())
                return CodeTypeRef.map(keyType, valueType)
            }
        }
        
        // Handle tuple types (Type1, Type2, ...)
        if (trimmed.startsWith("(") && trimmed.endsWith(")") && !trimmed.contains("->")) {
            val inner = trimmed.drop(1).dropLast(1).trim()
            if (inner.isEmpty()) {
                return CodeTypeRef(raw = trimmed, name = "Void", kind = TypeRefKind.TUPLE)
            }
            val elements = splitByTopLevelComma(inner).map { parseType(it.trim()) }
            if (elements.size == 1) {
                // Single element in parentheses is just the element type
                return elements.first()
            }
            return CodeTypeRef.tuple(*elements.toTypedArray())
        }
        
        // Handle function types (Params) -> ReturnType
        val arrowIndex = findTopLevelArrow(trimmed)
        if (arrowIndex > 0) {
            val paramPart = trimmed.substring(0, arrowIndex).trim()
            val returnPart = trimmed.substring(arrowIndex + 2).trim()
            
            val paramTypes = if (paramPart.startsWith("(") && paramPart.endsWith(")")) {
                val inner = paramPart.drop(1).dropLast(1).trim()
                if (inner.isEmpty()) {
                    listOf()
                } else {
                    splitByTopLevelComma(inner).map { parseType(it.trim()) }
                }
            } else {
                listOf(parseType(paramPart))
            }
            
            val returnType = parseType(returnPart)
            return CodeTypeRef.function(paramTypes, returnType)
        }
        
        // Handle protocol composition Type1 & Type2
        if (trimmed.contains("&")) {
            val parts = trimmed.split("&").map { it.trim() }
            if (parts.size > 1) {
                val types = parts.map { parseType(it) }
                return CodeTypeRef.intersection(*types.toTypedArray())
            }
        }
        
        // Handle opaque types: some Protocol
        if (trimmed.startsWith("some ")) {
            val protocolType = parseType(trimmed.removePrefix("some ").trim())
            return protocolType.copy(raw = trimmed)
        }
        
        // Handle metatypes: Type.Type or Type.Protocol
        if (trimmed.endsWith(".Type") || trimmed.endsWith(".Protocol")) {
            val baseName = when {
                trimmed.endsWith(".Type") -> trimmed.dropLast(5)
                trimmed.endsWith(".Protocol") -> trimmed.dropLast(9)
                else -> trimmed
            }
            return CodeTypeRef(
                raw = trimmed,
                name = baseName,
                kind = TypeRefKind.SIMPLE
            )
        }
        
        // Handle generic types: Type<Arg1, Arg2>
        val genericStart = trimmed.indexOf('<')
        if (genericStart > 0 && trimmed.endsWith(">")) {
            val baseName = trimmed.substring(0, genericStart)
            val argsText = trimmed.substring(genericStart + 1, trimmed.length - 1)
            val args = splitByTopLevelComma(argsText).map { parseType(it.trim()) }
            return CodeTypeRef.generic(baseName, *args.toTypedArray())
        }
        
        // Handle inout parameter type
        if (trimmed.startsWith("inout ")) {
            val baseType = parseType(trimmed.removePrefix("inout ").trim())
            return baseType.copy(
                raw = trimmed,
                isReference = true
            )
        }
        
        // Simple type
        return CodeTypeRef.simple(trimmed)
    }
    
    /**
     * Find the index of a top-level colon (not inside brackets or generics)
     */
    private fun findTopLevelColon(text: String): Int {
        var depth = 0
        for (i in text.indices) {
            when (text[i]) {
                '[', '(', '<' -> depth++
                ']', ')', '>' -> depth--
                ':' -> if (depth == 0) return i
            }
        }
        return -1
    }
    
    /**
     * Find the index of a top-level arrow (->) not inside brackets or generics
     */
    private fun findTopLevelArrow(text: String): Int {
        var depth = 0
        var i = 0
        while (i < text.length - 1) {
            when (text[i]) {
                '[', '(', '<' -> depth++
                ']', ')', '>' -> depth--
                '-' -> if (depth == 0 && text[i + 1] == '>') return i
            }
            i++
        }
        return -1
    }
    
    /**
     * Split by comma at the top level (not inside brackets or generics)
     */
    private fun splitByTopLevelComma(text: String): List<String> {
        val result = mutableListOf<String>()
        var depth = 0
        var start = 0
        
        for (i in text.indices) {
            when (text[i]) {
                '[', '(', '<' -> depth++
                ']', ')', '>' -> depth--
                ',' -> {
                    if (depth == 0) {
                        result.add(text.substring(start, i).trim())
                        start = i + 1
                    }
                }
            }
        }
        
        if (start < text.length) {
            result.add(text.substring(start).trim())
        }
        
        return result
    }
}
