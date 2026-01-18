package chapi.domain.core

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class CodeTypeRefTest {

    private val json = Json { prettyPrint = true; encodeDefaults = true }

    @Test
    fun `simple type creation`() {
        val typeRef = CodeTypeRef.simple("String")
        assertEquals("String", typeRef.name)
        assertEquals("String", typeRef.raw)
        assertEquals(TypeRefKind.SIMPLE, typeRef.kind)
    }

    @Test
    fun `generic type with single argument`() {
        val typeRef = CodeTypeRef.generic("List", CodeTypeRef.simple("String"))
        assertEquals("List", typeRef.name)
        assertEquals("List<String>", typeRef.raw)
        assertEquals(TypeRefKind.GENERIC, typeRef.kind)
        assertEquals(1, typeRef.args.size)
    }

    @Test
    fun `generic type with multiple arguments - Map`() {
        val typeRef = CodeTypeRef.generic("Map", CodeTypeRef.simple("String"), CodeTypeRef.simple("User"))
        assertEquals("Map", typeRef.name)
        assertEquals("Map<String, User>", typeRef.raw)
        assertEquals(2, typeRef.args.size)
    }

    @Test
    fun `nested generic - Rust Result`() {
        val errorType = CodeTypeRef.generic("Box", CodeTypeRef.simple("Error"))
        val resultType = CodeTypeRef.generic("Result", CodeTypeRef.simple("User"), errorType)
        assertEquals("Result", resultType.name)
        assertEquals(2, resultType.args.size)
        assertEquals("Box", resultType.args[1].name)
    }

    @Test
    fun `union type for TypeScript`() {
        val unionType = CodeTypeRef.union(
            CodeTypeRef.simple("string"),
            CodeTypeRef.simple("number"),
            CodeTypeRef.simple("null")
        )
        assertEquals(TypeRefKind.UNION, unionType.kind)
        assertEquals("string | number | null", unionType.raw)
        assertEquals(3, unionType.union.size)
    }

    @Test
    fun `intersection type for TypeScript`() {
        val intersectionType = CodeTypeRef.intersection(
            CodeTypeRef.simple("A"),
            CodeTypeRef.simple("B")
        )
        assertEquals(TypeRefKind.INTERSECTION, intersectionType.kind)
        assertEquals("A & B", intersectionType.raw)
    }

    @Test
    fun `pointer type for C`() {
        val pointerType = CodeTypeRef.pointer(CodeTypeRef.simple("char"), 2)
        assertEquals(TypeRefKind.POINTER, pointerType.kind)
        assertEquals("**char", pointerType.raw)
        assertEquals(2, pointerType.pointerDepth)
    }

    @Test
    fun `reference type for Cpp`() {
        val refType = CodeTypeRef.reference(CodeTypeRef.simple("string"), isConst = true)
        assertEquals(TypeRefKind.REFERENCE, refType.kind)
        assertEquals("const string&", refType.raw)
        assertTrue(refType.isConst)
    }

    @Test
    fun `slice type for Go`() {
        val sliceType = CodeTypeRef.slice(CodeTypeRef.simple("int"))
        assertEquals(TypeRefKind.SLICE, sliceType.kind)
        assertEquals("[]int", sliceType.raw)
        assertTrue(sliceType.isSlice)
    }

    @Test
    fun `map type for Go`() {
        val mapType = CodeTypeRef.map(CodeTypeRef.simple("string"), CodeTypeRef.simple("int"))
        assertEquals(TypeRefKind.MAP, mapType.kind)
        assertEquals("map[string]int", mapType.raw)
        assertEquals("string", mapType.keyType?.name)
        assertEquals("int", mapType.valueType?.name)
    }

    @Test
    fun `channel type for Go`() {
        val chanType = CodeTypeRef.channel(CodeTypeRef.simple("int"))
        assertEquals(TypeRefKind.CHANNEL, chanType.kind)
        assertEquals("chan int", chanType.raw)

        val sendChan = CodeTypeRef.channel(CodeTypeRef.simple("string"), "send")
        assertEquals("chan<- string", sendChan.raw)

        val recvChan = CodeTypeRef.channel(CodeTypeRef.simple("string"), "receive")
        assertEquals("<-chan string", recvChan.raw)
    }

    @Test
    fun `array type`() {
        val arrayType = CodeTypeRef.array(CodeTypeRef.simple("int"), 2)
        assertEquals(TypeRefKind.ARRAY, arrayType.kind)
        assertEquals("int[][]", arrayType.raw)
        assertEquals(2, arrayType.arrayDimensions)
    }

    @Test
    fun `function type for TypeScript`() {
        val funcType = CodeTypeRef.function(
            listOf(CodeTypeRef.simple("number"), CodeTypeRef.simple("string")),
            CodeTypeRef.simple("boolean")
        )
        assertEquals(TypeRefKind.FUNCTION, funcType.kind)
        assertEquals("(number, string) => boolean", funcType.raw)
    }

    @Test
    fun `tuple type`() {
        val tupleType = CodeTypeRef.tuple(
            CodeTypeRef.simple("string"),
            CodeTypeRef.simple("number")
        )
        assertEquals(TypeRefKind.TUPLE, tupleType.kind)
        assertEquals("[string, number]", tupleType.raw)
    }

    @Test
    fun `nullable type`() {
        val nullableString = CodeTypeRef.nullable(CodeTypeRef.simple("String"))
        assertTrue(nullableString.nullable)
        assertEquals("String?", nullableString.raw)
    }

    @Test
    fun `isPrimitive returns true for primitives`() {
        assertTrue(CodeTypeRef.simple("int").isPrimitive())
        assertTrue(CodeTypeRef.simple("String").isPrimitive())
        assertTrue(CodeTypeRef.simple("number").isPrimitive())
        assertTrue(CodeTypeRef.simple("i32").isPrimitive())
        assertFalse(CodeTypeRef.simple("User").isPrimitive())
    }

    @Test
    fun `isCollection identifies collection types`() {
        assertTrue(CodeTypeRef.simple("List").isCollection())
        assertTrue(CodeTypeRef.simple("Vec").isCollection())
        assertTrue(CodeTypeRef.slice(CodeTypeRef.simple("int")).isCollection())
        assertFalse(CodeTypeRef.simple("User").isCollection())
    }

    @Test
    fun `flattenUnion flattens nested unions`() {
        val nested = CodeTypeRef(
            kind = TypeRefKind.UNION,
            union = listOf(
                CodeTypeRef.union(CodeTypeRef.simple("a"), CodeTypeRef.simple("b")),
                CodeTypeRef.simple("c")
            )
        )
        val flattened = nested.flattenUnion()
        assertEquals(3, flattened.size)
    }

    @Test
    fun `baseType returns inner type`() {
        val pointer = CodeTypeRef.pointer(CodeTypeRef.simple("User"))
        assertEquals("User", pointer.baseType()?.name)

        val slice = CodeTypeRef.slice(CodeTypeRef.simple("int"))
        assertEquals("int", slice.baseType()?.name)
    }

    @Test
    fun `serialization works correctly`() {
        val typeRef = CodeTypeRef.generic("Map", CodeTypeRef.simple("String"), CodeTypeRef.simple("User"))
        val jsonString = json.encodeToString(typeRef)
        assertTrue(jsonString.contains("\"name\""))
        assertTrue(jsonString.contains("\"Map\""))

        val decoded = json.decodeFromString<CodeTypeRef>(jsonString)
        assertEquals(typeRef.name, decoded.name)
        assertEquals(typeRef.kind, decoded.kind)
    }

    // Integration tests with existing domain classes

    @Test
    fun `CodeField with TypeRef - backward compatible`() {
        // Old usage still works
        val fieldOld = CodeField(TypeType = "String", TypeKey = "name")
        assertEquals("String", fieldOld.TypeType)
        assertNull(fieldOld.TypeRef)

        // New usage with TypeRef
        val fieldNew = CodeField(
            TypeType = "List<String>",
            TypeKey = "names",
            TypeRef = CodeTypeRef.generic("List", CodeTypeRef.simple("String"))
        )
        assertEquals("List<String>", fieldNew.TypeType)
        assertNotNull(fieldNew.TypeRef)
        assertEquals("List", fieldNew.TypeRef?.name)
    }

    @Test
    fun `CodeProperty with TypeRef - backward compatible`() {
        // Old usage still works
        val propOld = CodeProperty(TypeType = "int", TypeValue = "count")
        assertEquals("int", propOld.TypeType)
        assertNull(propOld.TypeRef)

        // New usage with TypeRef
        val propNew = CodeProperty(
            TypeType = "Map<String, User>",
            TypeValue = "userMap",
            TypeRef = CodeTypeRef.generic("Map", CodeTypeRef.simple("String"), CodeTypeRef.simple("User"))
        )
        assertEquals("Map", propNew.TypeRef?.name)
    }

    @Test
    fun `CodeFunction with ReturnTypeRef - backward compatible`() {
        // Old usage still works
        val funcOld = CodeFunction(Name = "getUser", ReturnType = "User")
        assertEquals("User", funcOld.ReturnType)
        assertNull(funcOld.ReturnTypeRef)

        // New usage with ReturnTypeRef
        val funcNew = CodeFunction(
            Name = "fetchUsers",
            ReturnType = "Promise<User[]>",
            ReturnTypeRef = CodeTypeRef.generic(
                "Promise",
                CodeTypeRef.array(CodeTypeRef.simple("User"))
            )
        )
        assertEquals("Promise", funcNew.ReturnTypeRef?.name)
    }
}
