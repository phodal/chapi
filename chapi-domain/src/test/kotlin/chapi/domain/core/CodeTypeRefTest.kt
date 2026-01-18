package chapi.domain.core

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class CodeTypeRefTest {

    private val json = Json {
        prettyPrint = true
        encodeDefaults = true
    }

    @Nested
    inner class SimpleTypeCreation {
        @Test
        fun `should create simple type`() {
            val typeRef = CodeTypeRef.simple("String")

            assertEquals("String", typeRef.name)
            assertEquals("String", typeRef.raw)
            assertEquals(TypeRefKind.SIMPLE, typeRef.kind)
            assertFalse(typeRef.nullable)
        }

        @Test
        fun `should create simple type with all defaults`() {
            val typeRef = CodeTypeRef(raw = "int", name = "int")

            assertEquals("int", typeRef.name)
            assertEquals("int", typeRef.raw)
            assertEquals("", typeRef.qualified)
            assertEquals(TypeRefKind.SIMPLE, typeRef.kind)
            assertTrue(typeRef.args.isEmpty())
            assertFalse(typeRef.nullable)
        }
    }

    @Nested
    inner class GenericTypeCreation {
        @Test
        fun `should create generic type with single argument`() {
            val typeRef = CodeTypeRef.generic("List", CodeTypeRef.simple("String"))

            assertEquals("List", typeRef.name)
            assertEquals("List<String>", typeRef.raw)
            assertEquals(TypeRefKind.GENERIC, typeRef.kind)
            assertEquals(1, typeRef.args.size)
            assertEquals("String", typeRef.args[0].name)
        }

        @Test
        fun `should create generic type with multiple arguments`() {
            val typeRef = CodeTypeRef.generic(
                "Map",
                CodeTypeRef.simple("String"),
                CodeTypeRef.simple("Integer")
            )

            assertEquals("Map", typeRef.name)
            assertEquals("Map<String, Integer>", typeRef.raw)
            assertEquals(TypeRefKind.GENERIC, typeRef.kind)
            assertEquals(2, typeRef.args.size)
            assertEquals("String", typeRef.args[0].name)
            assertEquals("Integer", typeRef.args[1].name)
        }

        @Test
        fun `should create nested generic type for Rust Result`() {
            // Result<User, Box<dyn Error>>
            val errorType = CodeTypeRef.generic("Box", CodeTypeRef.simple("dyn Error"))
            val resultType = CodeTypeRef.generic("Result", CodeTypeRef.simple("User"), errorType)

            assertEquals("Result", resultType.name)
            assertEquals(TypeRefKind.GENERIC, resultType.kind)
            assertEquals(2, resultType.args.size)
            assertEquals("User", resultType.args[0].name)
            assertEquals("Box", resultType.args[1].name)
            assertEquals("dyn Error", resultType.args[1].args[0].name)
        }

        @Test
        fun `should identify generic types`() {
            val genericType = CodeTypeRef.generic("List", CodeTypeRef.simple("String"))
            val simpleType = CodeTypeRef.simple("String")

            assertTrue(genericType.isGeneric())
            assertFalse(simpleType.isGeneric())
        }
    }

    @Nested
    inner class UnionAndIntersectionTypes {
        @Test
        fun `should create union type for TypeScript`() {
            // string | number | null
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
        fun `should create intersection type for TypeScript`() {
            // A & B & C
            val intersectionType = CodeTypeRef.intersection(
                CodeTypeRef.simple("A"),
                CodeTypeRef.simple("B"),
                CodeTypeRef.simple("C")
            )

            assertEquals(TypeRefKind.INTERSECTION, intersectionType.kind)
            assertEquals("A & B & C", intersectionType.raw)
            assertEquals(3, intersectionType.intersection.size)
        }

        @Test
        fun `should flatten nested union types`() {
            // (string | number) | (boolean | null)
            val nestedUnion = CodeTypeRef(
                kind = TypeRefKind.UNION,
                union = listOf(
                    CodeTypeRef.union(CodeTypeRef.simple("string"), CodeTypeRef.simple("number")),
                    CodeTypeRef.union(CodeTypeRef.simple("boolean"), CodeTypeRef.simple("null"))
                )
            )

            val flattened = nestedUnion.flattenUnion()
            assertEquals(4, flattened.size)
            assertEquals(listOf("string", "number", "boolean", "null"), flattened.map { it.name })
        }
    }

    @Nested
    inner class PointerAndReferenceTypes {
        @Test
        fun `should create pointer type for C`() {
            // char*
            val pointerType = CodeTypeRef.pointer(CodeTypeRef.simple("char"))

            assertEquals(TypeRefKind.POINTER, pointerType.kind)
            assertEquals("char", pointerType.name)
            assertEquals("*char", pointerType.raw)
            assertEquals(1, pointerType.pointerDepth)
            assertNotNull(pointerType.valueType)
            assertEquals("char", pointerType.valueType?.name)
        }

        @Test
        fun `should create double pointer type for C`() {
            // char**
            val doublePointer = CodeTypeRef.pointer(CodeTypeRef.simple("char"), 2)

            assertEquals(TypeRefKind.POINTER, doublePointer.kind)
            assertEquals("**char", doublePointer.raw)
            assertEquals(2, doublePointer.pointerDepth)
        }

        @Test
        fun `should create reference type for Cpp`() {
            // int&
            val refType = CodeTypeRef.reference(CodeTypeRef.simple("int"))

            assertEquals(TypeRefKind.REFERENCE, refType.kind)
            assertEquals("int", refType.name)
            assertEquals("int&", refType.raw)
            assertTrue(refType.isReference)
            assertFalse(refType.isConst)
        }

        @Test
        fun `should create const reference type for Cpp`() {
            // const string&
            val constRefType = CodeTypeRef.reference(CodeTypeRef.simple("string"), isConst = true)

            assertEquals(TypeRefKind.REFERENCE, constRefType.kind)
            assertEquals("const string&", constRefType.raw)
            assertTrue(constRefType.isReference)
            assertTrue(constRefType.isConst)
        }

        @Test
        fun `should get base type for pointer`() {
            val pointerType = CodeTypeRef.pointer(CodeTypeRef.simple("User"))
            val baseType = pointerType.baseType()

            assertNotNull(baseType)
            assertEquals("User", baseType?.name)
        }
    }

    @Nested
    inner class GoSpecificTypes {
        @Test
        fun `should create slice type`() {
            // []int
            val sliceType = CodeTypeRef.slice(CodeTypeRef.simple("int"))

            assertEquals(TypeRefKind.SLICE, sliceType.kind)
            assertEquals("[]int", sliceType.raw)
            assertTrue(sliceType.isSlice)
            assertEquals("int", sliceType.valueType?.name)
        }

        @Test
        fun `should create map type`() {
            // map[string]int
            val mapType = CodeTypeRef.map(
                CodeTypeRef.simple("string"),
                CodeTypeRef.simple("int")
            )

            assertEquals(TypeRefKind.MAP, mapType.kind)
            assertEquals("map[string]int", mapType.raw)
            assertEquals("string", mapType.keyType?.name)
            assertEquals("int", mapType.valueType?.name)
        }

        @Test
        fun `should create bidirectional channel type`() {
            // chan int
            val chanType = CodeTypeRef.channel(CodeTypeRef.simple("int"))

            assertEquals(TypeRefKind.CHANNEL, chanType.kind)
            assertEquals("chan int", chanType.raw)
            assertNull(chanType.channelDirection)
            assertEquals("int", chanType.valueType?.name)
        }

        @Test
        fun `should create send-only channel type`() {
            // chan<- string
            val sendChan = CodeTypeRef.channel(CodeTypeRef.simple("string"), "send")

            assertEquals("chan<- string", sendChan.raw)
            assertEquals("send", sendChan.channelDirection)
        }

        @Test
        fun `should create receive-only channel type`() {
            // <-chan string
            val recvChan = CodeTypeRef.channel(CodeTypeRef.simple("string"), "receive")

            assertEquals("<-chan string", recvChan.raw)
            assertEquals("receive", recvChan.channelDirection)
        }
    }

    @Nested
    inner class ArrayTypes {
        @Test
        fun `should create single dimension array`() {
            // int[]
            val arrayType = CodeTypeRef.array(CodeTypeRef.simple("int"))

            assertEquals(TypeRefKind.ARRAY, arrayType.kind)
            assertEquals("int[]", arrayType.raw)
            assertEquals(1, arrayType.arrayDimensions)
            assertEquals("int", arrayType.name)
        }

        @Test
        fun `should create multi-dimensional array`() {
            // String[][]
            val array2D = CodeTypeRef.array(CodeTypeRef.simple("String"), 2)

            assertEquals("String[][]", array2D.raw)
            assertEquals(2, array2D.arrayDimensions)
        }
    }

    @Nested
    inner class FunctionTypes {
        @Test
        fun `should create function type for TypeScript`() {
            // (x: number, y: string) => boolean
            val funcType = CodeTypeRef.function(
                listOf(CodeTypeRef.simple("number"), CodeTypeRef.simple("string")),
                CodeTypeRef.simple("boolean")
            )

            assertEquals(TypeRefKind.FUNCTION, funcType.kind)
            assertEquals("(number, string) => boolean", funcType.raw)
            assertEquals(2, funcType.parameterTypes.size)
            assertEquals("boolean", funcType.returnType?.name)
        }

        @Test
        fun `should create function type with no parameters`() {
            // () => void
            val funcType = CodeTypeRef.function(emptyList(), CodeTypeRef.simple("void"))

            assertEquals("() => void", funcType.raw)
            assertTrue(funcType.parameterTypes.isEmpty())
            assertEquals("void", funcType.returnType?.name)
        }
    }

    @Nested
    inner class TupleTypes {
        @Test
        fun `should create tuple type for TypeScript`() {
            // [string, number, boolean]
            val tupleType = CodeTypeRef.tuple(
                CodeTypeRef.simple("string"),
                CodeTypeRef.simple("number"),
                CodeTypeRef.simple("boolean")
            )

            assertEquals(TypeRefKind.TUPLE, tupleType.kind)
            assertEquals("[string, number, boolean]", tupleType.raw)
            assertEquals(3, tupleType.tupleElements.size)
        }

        @Test
        fun `should create Rust tuple`() {
            // (i32, String, bool)
            val rustTuple = CodeTypeRef(
                raw = "(i32, String, bool)",
                kind = TypeRefKind.TUPLE,
                tupleElements = listOf(
                    CodeTypeRef.simple("i32"),
                    CodeTypeRef.simple("String"),
                    CodeTypeRef.simple("bool")
                )
            )

            assertEquals(TypeRefKind.TUPLE, rustTuple.kind)
            assertEquals(3, rustTuple.tupleElements.size)
        }
    }

    @Nested
    inner class NullableTypes {
        @Test
        fun `should create nullable type`() {
            val nullableString = CodeTypeRef.nullable(CodeTypeRef.simple("String"))

            assertTrue(nullableString.nullable)
            assertEquals("String?", nullableString.raw)
        }

        @Test
        fun `should preserve kind when making generic nullable`() {
            val genericType = CodeTypeRef.generic("List", CodeTypeRef.simple("String"))
            val nullableGeneric = CodeTypeRef.nullable(genericType)

            assertTrue(nullableGeneric.nullable)
            assertEquals(TypeRefKind.GENERIC, nullableGeneric.kind)
        }
    }

    @Nested
    inner class RustSpecificTypes {
        @Test
        fun `should represent trait bounds`() {
            // T: Clone + Debug + 'static
            val boundedType = CodeTypeRef(
                raw = "T: Clone + Debug + 'static",
                name = "T",
                kind = TypeRefKind.SIMPLE,
                traitBounds = listOf("Clone", "Debug", "'static")
            )

            assertEquals("T", boundedType.name)
            assertEquals(3, boundedType.traitBounds.size)
            assertTrue(boundedType.traitBounds.contains("Clone"))
            assertTrue(boundedType.traitBounds.contains("Debug"))
        }

        @Test
        fun `should represent lifetime annotations`() {
            // &'a str
            val lifetimeRef = CodeTypeRef(
                raw = "&'a str",
                name = "str",
                kind = TypeRefKind.REFERENCE,
                lifetime = "'a",
                isReference = true
            )

            assertEquals("'a", lifetimeRef.lifetime)
            assertTrue(lifetimeRef.isReference)
        }

        @Test
        fun `should represent Option type`() {
            // Option<User>
            val optionType = CodeTypeRef.generic("Option", CodeTypeRef.simple("User"))
            val withNullable = optionType.copy(nullable = true)

            assertEquals("Option", withNullable.name)
            assertTrue(withNullable.nullable)
            assertEquals("User", withNullable.args[0].name)
        }
    }

    @Nested
    inner class TypeScriptSpecificTypes {
        @Test
        fun `should represent readonly type`() {
            val readonlyArray = CodeTypeRef(
                raw = "readonly string[]",
                name = "string",
                kind = TypeRefKind.ARRAY,
                arrayDimensions = 1,
                isReadonly = true
            )

            assertTrue(readonlyArray.isReadonly)
        }

        @Test
        fun `should represent mapped type with index signature`() {
            // { [key: string]: number }
            val mappedType = CodeTypeRef(
                raw = "{ [key: string]: number }",
                kind = TypeRefKind.MAPPED,
                indexKeyType = CodeTypeRef.simple("string"),
                valueType = CodeTypeRef.simple("number")
            )

            assertEquals(TypeRefKind.MAPPED, mappedType.kind)
            assertEquals("string", mappedType.indexKeyType?.name)
            assertEquals("number", mappedType.valueType?.name)
        }

        @Test
        fun `should represent type-only import`() {
            val typeOnlyRef = CodeTypeRef(
                raw = "User",
                name = "User",
                kind = TypeRefKind.SIMPLE,
                isTypeOnly = true
            )

            assertTrue(typeOnlyRef.isTypeOnly)
        }
    }

    @Nested
    inner class HelperMethods {
        @Test
        fun `should identify primitive types`() {
            assertTrue(CodeTypeRef.simple("int").isPrimitive())
            assertTrue(CodeTypeRef.simple("String").isPrimitive())
            assertTrue(CodeTypeRef.simple("boolean").isPrimitive())
            assertTrue(CodeTypeRef.simple("i32").isPrimitive())
            assertTrue(CodeTypeRef.simple("f64").isPrimitive())
            assertTrue(CodeTypeRef.simple("number").isPrimitive())
            assertFalse(CodeTypeRef.simple("User").isPrimitive())
            assertFalse(CodeTypeRef.simple("MyClass").isPrimitive())
        }

        @Test
        fun `should identify collection types`() {
            assertTrue(CodeTypeRef.simple("List").isCollection())
            assertTrue(CodeTypeRef.simple("ArrayList").isCollection())
            assertTrue(CodeTypeRef.simple("HashMap").isCollection())
            assertTrue(CodeTypeRef.simple("Vec").isCollection())
            assertTrue(CodeTypeRef.slice(CodeTypeRef.simple("int")).isCollection())
            assertTrue(CodeTypeRef.map(CodeTypeRef.simple("string"), CodeTypeRef.simple("int")).isCollection())
            assertFalse(CodeTypeRef.simple("User").isCollection())
        }

        @Test
        fun `should return correct toString`() {
            val typeWithRaw = CodeTypeRef(raw = "List<String>", name = "List")
            val typeWithoutRaw = CodeTypeRef(name = "User")

            assertEquals("List<String>", typeWithRaw.toString())
            assertEquals("User", typeWithoutRaw.toString())
        }
    }

    @Nested
    inner class Serialization {
        @Test
        fun `should serialize simple type to JSON`() {
            val typeRef = CodeTypeRef.simple("String")
            val jsonString = json.encodeToString(typeRef)

            // Check for content without exact spacing (JSON format may vary)
            assertTrue(jsonString.contains("\"name\"") && jsonString.contains("\"String\""))
            assertTrue(jsonString.contains("\"raw\"") && jsonString.contains("\"String\""))
            assertTrue(jsonString.contains("\"kind\"") && jsonString.contains("\"SIMPLE\""))
        }

        @Test
        fun `should serialize generic type to JSON`() {
            val typeRef = CodeTypeRef.generic("Map", CodeTypeRef.simple("String"), CodeTypeRef.simple("User"))
            val jsonString = json.encodeToString(typeRef)

            assertTrue(jsonString.contains("\"name\"") && jsonString.contains("\"Map\""))
            assertTrue(jsonString.contains("\"kind\"") && jsonString.contains("\"GENERIC\""))
            assertTrue(jsonString.contains("\"args\""))
        }

        @Test
        fun `should deserialize from JSON`() {
            val original = CodeTypeRef.generic("List", CodeTypeRef.simple("String"))
            val jsonString = json.encodeToString(original)
            val deserialized = json.decodeFromString<CodeTypeRef>(jsonString)

            assertEquals(original.name, deserialized.name)
            assertEquals(original.kind, deserialized.kind)
            assertEquals(original.args.size, deserialized.args.size)
            assertEquals(original.args[0].name, deserialized.args[0].name)
        }
    }

    @Nested
    inner class IntegrationWithExistingTypes {
        @Test
        fun `should work with CodeField`() {
            val field = CodeField(
                TypeType = "List<String>",
                TypeKey = "names",
                TypeRef = CodeTypeRef.generic("List", CodeTypeRef.simple("String"))
            )

            assertEquals("List<String>", field.TypeType)
            assertNotNull(field.TypeRef)
            assertEquals("List", field.TypeRef?.name)
            assertEquals(TypeRefKind.GENERIC, field.TypeRef?.kind)
        }

        @Test
        fun `should work with CodeProperty`() {
            val property = CodeProperty(
                TypeType = "Map<String, User>",
                TypeValue = "userMap",
                TypeRef = CodeTypeRef.generic(
                    "Map",
                    CodeTypeRef.simple("String"),
                    CodeTypeRef.simple("User")
                )
            )

            assertEquals("Map<String, User>", property.TypeType)
            assertNotNull(property.TypeRef)
            assertEquals("Map", property.TypeRef?.name)
            assertEquals(2, property.TypeRef?.args?.size)
        }

        @Test
        fun `should work with CodeFunction`() {
            val function = CodeFunction(
                Name = "getUser",
                ReturnType = "Result<User, Error>",
                ReturnTypeRef = CodeTypeRef.generic(
                    "Result",
                    CodeTypeRef.simple("User"),
                    CodeTypeRef.simple("Error")
                )
            )

            assertEquals("Result<User, Error>", function.ReturnType)
            assertNotNull(function.ReturnTypeRef)
            assertEquals("Result", function.ReturnTypeRef?.name)
            assertEquals(2, function.ReturnTypeRef?.args?.size)
        }

        @Test
        fun `should serialize CodeField with TypeRef`() {
            val field = CodeField(
                TypeType = "string",
                TypeKey = "name",
                TypeRef = CodeTypeRef.simple("string")
            )

            val jsonString = json.encodeToString(field)
            assertTrue(jsonString.contains("\"TypeType\"") && jsonString.contains("\"string\""))
            assertTrue(jsonString.contains("\"TypeRef\""))
        }
    }

    @Nested
    inner class ComplexTypeScenarios {
        @Test
        fun `should represent complex TypeScript type`() {
            // Promise<Result<User[], Error> | null>
            val arrayOfUsers = CodeTypeRef.array(CodeTypeRef.simple("User"))
            val resultType = CodeTypeRef.generic("Result", arrayOfUsers, CodeTypeRef.simple("Error"))
            val unionWithNull = CodeTypeRef.union(resultType, CodeTypeRef.simple("null"))
            val promiseType = CodeTypeRef.generic("Promise", unionWithNull)

            assertEquals("Promise", promiseType.name)
            assertEquals(TypeRefKind.GENERIC, promiseType.kind)

            val innerUnion = promiseType.args[0]
            assertEquals(TypeRefKind.UNION, innerUnion.kind)
            assertEquals(2, innerUnion.union.size)
        }

        @Test
        fun `should represent complex Go type`() {
            // map[string][]chan *User
            val userPointer = CodeTypeRef.pointer(CodeTypeRef.simple("User"))
            val userChannel = CodeTypeRef.channel(userPointer)
            val channelSlice = CodeTypeRef.slice(userChannel)
            val mapType = CodeTypeRef.map(CodeTypeRef.simple("string"), channelSlice)

            assertEquals(TypeRefKind.MAP, mapType.kind)
            assertEquals("string", mapType.keyType?.name)

            val sliceValue = mapType.valueType
            assertEquals(TypeRefKind.SLICE, sliceValue?.kind)

            val channelValue = sliceValue?.valueType
            assertEquals(TypeRefKind.CHANNEL, channelValue?.kind)

            val pointerValue = channelValue?.valueType
            assertEquals(TypeRefKind.POINTER, pointerValue?.kind)
            assertEquals("User", pointerValue?.valueType?.name)
        }

        @Test
        fun `should represent complex Rust type`() {
            // Box<dyn Fn(&'a str) -> Result<T, E> + Send + 'static>
            val strRef = CodeTypeRef(
                name = "str",
                kind = TypeRefKind.REFERENCE,
                lifetime = "'a",
                isReference = true
            )

            val resultType = CodeTypeRef.generic(
                "Result",
                CodeTypeRef.simple("T"),
                CodeTypeRef.simple("E")
            )

            val fnType = CodeTypeRef(
                raw = "Fn(&'a str) -> Result<T, E>",
                name = "Fn",
                kind = TypeRefKind.FUNCTION,
                parameterTypes = listOf(strRef),
                returnType = resultType,
                traitBounds = listOf("Send", "'static")
            )

            val boxType = CodeTypeRef.generic("Box", fnType)

            assertEquals("Box", boxType.name)
            assertEquals(TypeRefKind.GENERIC, boxType.kind)

            val inner = boxType.args[0]
            assertEquals(TypeRefKind.FUNCTION, inner.kind)
            assertEquals(2, inner.traitBounds.size)
        }

        @Test
        fun `should represent C++ template with function pointer`() {
            // std::function<int(*)(const char*, size_t)>
            val funcPtrType = CodeTypeRef(
                raw = "int(*)(const char*, size_t)",
                name = "function_pointer",
                kind = TypeRefKind.FUNCTION,
                parameterTypes = listOf(
                    CodeTypeRef.pointer(CodeTypeRef(name = "char", isConst = true)),
                    CodeTypeRef.simple("size_t")
                ),
                returnType = CodeTypeRef.simple("int")
            )

            val stdFunction = CodeTypeRef(
                raw = "std::function<int(*)(const char*, size_t)>",
                name = "function",
                qualified = "std::function",
                kind = TypeRefKind.GENERIC,
                args = listOf(funcPtrType)
            )

            assertEquals("function", stdFunction.name)
            assertEquals("std::function", stdFunction.qualified)
            assertEquals(TypeRefKind.GENERIC, stdFunction.kind)
        }
    }
}
