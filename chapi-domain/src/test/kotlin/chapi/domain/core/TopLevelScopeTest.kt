package chapi.domain.core

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class TopLevelScopeTest {

    // ==================== TopLevelScope Basic Tests ====================

    @Test
    fun `empty TopLevelScope should report isEmpty`() {
        val scope = TopLevelScope()
        assertTrue(scope.isEmpty())
        assertFalse(scope.isNotEmpty())
        assertEquals(0, scope.declarationCount())
    }

    @Test
    fun `TopLevelScope with only Extension should not be empty`() {
        val scope = TopLevelScope(
            Extension = mapOf("__all__" to "[\"foo\", \"bar\"]")
        )
        assertFalse(scope.isEmpty())
        assertTrue(scope.isNotEmpty())
        // declarationCount doesn't include Extension
        assertEquals(0, scope.declarationCount())
    }

    @Test
    fun `TopLevelScope with functions should not be empty`() {
        val scope = TopLevelScope(
            Functions = listOf(
                CodeFunction(Name = "main"),
                CodeFunction(Name = "helper")
            )
        )
        assertFalse(scope.isEmpty())
        assertTrue(scope.isNotEmpty())
        assertEquals(2, scope.declarationCount())
    }

    @Test
    fun `should find function by name`() {
        val mainFn = CodeFunction(Name = "main", ReturnType = "void")
        val helperFn = CodeFunction(Name = "helper", ReturnType = "string")
        val scope = TopLevelScope(Functions = listOf(mainFn, helperFn))

        assertEquals(mainFn, scope.findFunction("main"))
        assertEquals(helperFn, scope.findFunction("helper"))
        assertNull(scope.findFunction("nonexistent"))
    }

    @Test
    fun `should find field by name`() {
        val configField = CodeField(TypeKey = "CONFIG", TypeType = "Config")
        val counterField = CodeField(TypeKey = "counter", TypeType = "int")
        val scope = TopLevelScope(Fields = listOf(configField, counterField))

        assertEquals(configField, scope.findField("CONFIG"))
        assertEquals(counterField, scope.findField("counter"))
        assertNull(scope.findField("nonexistent"))
    }

    @Test
    fun `should return exported names from specifiers`() {
        val scope = TopLevelScope(
            Exports = listOf(
                CodeExport(
                    Kind = ExportKind.NAMED,
                    Specifiers = listOf(
                        ExportSpecifier(LocalName = "foo", ExportedName = "foo"),
                        ExportSpecifier(LocalName = "bar", ExportedName = "renamedBar")
                    )
                )
            )
        )

        assertEquals(listOf("foo", "renamedBar"), scope.exportedNames())
    }

    @Test
    fun `should return exported names from Name field for default exports`() {
        val scope = TopLevelScope(
            Exports = listOf(
                CodeExport(Kind = ExportKind.DEFAULT, Name = "MyComponent")
            )
        )

        assertEquals(listOf("MyComponent"), scope.exportedNames())
    }

    // ==================== TypeScript Top-Level Example ====================

    @Test
    fun `should represent TypeScript top-level declarations`() {
        // Simulating:
        // const API_URL = "https://api.example.com";
        // export function fetchData() { ... }
        // export const handler = async () => { ... };

        val scope = TopLevelScope(
            Fields = listOf(
                CodeField(TypeKey = "API_URL", TypeType = "string", TypeValue = "\"https://api.example.com\"")
            ),
            Functions = listOf(
                CodeFunction(Name = "fetchData", Modifiers = listOf("export")),
                CodeFunction(Name = "handler", Modifiers = listOf("export", "const"))
            ),
            Exports = listOf(
                CodeExport(Kind = ExportKind.NAMED, Specifiers = listOf(
                    ExportSpecifier(LocalName = "fetchData", ExportedName = "fetchData"),
                    ExportSpecifier(LocalName = "handler", ExportedName = "handler")
                ))
            )
        )

        assertEquals(1, scope.Fields.size)
        assertEquals(2, scope.Functions.size)
        assertEquals(1, scope.Exports.size)
        assertEquals(listOf("fetchData", "handler"), scope.exportedNames())
    }

    // ==================== Python Module-Level Example ====================

    @Test
    fun `should represent Python module-level declarations`() {
        // Simulating:
        // MAX_RETRIES = 3
        // def process_data(data): ...

        val scope = TopLevelScope(
            Fields = listOf(
                CodeField(TypeKey = "MAX_RETRIES", TypeType = "int", TypeValue = "3")
            ),
            Functions = listOf(
                CodeFunction(Name = "process_data", Parameters = listOf(
                    CodeProperty(TypeValue = "data", TypeType = "")
                ))
            )
        )

        assertEquals(1, scope.Fields.size)
        assertEquals(1, scope.Functions.size)
        assertEquals("MAX_RETRIES", scope.findField("MAX_RETRIES")?.TypeKey)
        assertEquals("process_data", scope.findFunction("process_data")?.Name)
    }

    // ==================== Go Package-Level Example ====================

    @Test
    fun `should represent Go package-level declarations`() {
        // Simulating:
        // var globalConfig Config
        // func main() { ... }
        // func helper() { ... }

        val scope = TopLevelScope(
            Fields = listOf(
                CodeField(TypeKey = "globalConfig", TypeType = "Config")
            ),
            Functions = listOf(
                CodeFunction(Name = "main"),
                CodeFunction(Name = "helper")
            )
        )

        assertEquals(1, scope.Fields.size)
        assertEquals(2, scope.Functions.size)
        assertNotNull(scope.findFunction("main"))
        assertNotNull(scope.findFunction("helper"))
    }

    // ==================== Rust Free Functions Example ====================

    @Test
    fun `should represent Rust free functions and static items`() {
        // Simulating:
        // static CONFIG: Config = Config::default();
        // fn main() { ... }
        // fn helper() -> Result<(), Error> { ... }

        val scope = TopLevelScope(
            Fields = listOf(
                CodeField(
                    TypeKey = "CONFIG",
                    TypeType = "Config",
                    Modifiers = listOf("static")
                )
            ),
            Functions = listOf(
                CodeFunction(Name = "main"),
                CodeFunction(
                    Name = "helper",
                    ReturnType = "Result<(), Error>",
                    ReturnTypeRef = CodeTypeRef.generic(
                        "Result",
                        CodeTypeRef.tuple(),
                        CodeTypeRef.simple("Error")
                    )
                )
            )
        )

        assertEquals(1, scope.Fields.size)
        assertEquals(2, scope.Functions.size)
        assertEquals(listOf("static"), scope.Fields[0].Modifiers)
        assertEquals(TypeRefKind.GENERIC, scope.Functions[1].ReturnTypeRef?.kind)
    }

    // ==================== Type Alias Tests ====================

    @Test
    fun `should represent TypeScript type aliases`() {
        // type UserId = string;
        // type Handler = (req: Request) => Response;

        val scope = TopLevelScope(
            TypeAliases = listOf(
                CodeTypeAlias(
                    Name = "UserId",
                    OriginalType = "string",
                    TypeRef = CodeTypeRef.simple("string")
                ),
                CodeTypeAlias(
                    Name = "Handler",
                    OriginalType = "(req: Request) => Response",
                    TypeRef = CodeTypeRef.function(
                        listOf(CodeTypeRef.simple("Request")),
                        CodeTypeRef.simple("Response")
                    )
                )
            )
        )

        assertEquals(2, scope.TypeAliases.size)
        assertEquals("UserId", scope.TypeAliases[0].Name)
        assertEquals("Handler", scope.TypeAliases[1].Name)
        assertEquals(TypeRefKind.FUNCTION, scope.TypeAliases[1].TypeRef?.kind)
    }

    @Test
    fun `should represent Rust type aliases with generics`() {
        // type Result<T> = std::result::Result<T, MyError>;

        val scope = TopLevelScope(
            TypeAliases = listOf(
                CodeTypeAlias(
                    Name = "Result",
                    OriginalType = "std::result::Result<T, MyError>",
                    TypeParameters = listOf("T"),
                    TypeRef = CodeTypeRef.generic(
                        "std::result::Result",
                        CodeTypeRef.simple("T"),
                        CodeTypeRef.simple("MyError")
                    )
                )
            )
        )

        assertEquals(1, scope.TypeAliases.size)
        assertEquals(listOf("T"), scope.TypeAliases[0].TypeParameters)
    }

    // ==================== CodeContainer Integration Tests ====================

    @Test
    fun `CodeContainer should support TopLevel field`() {
        val container = CodeContainer(
            FullName = "utils.ts",
            Language = "typescript",
            Kind = ContainerKind.MODULE,
            TopLevel = TopLevelScope(
                Functions = listOf(CodeFunction(Name = "utilFunction")),
                Fields = listOf(CodeField(TypeKey = "CONSTANT", TypeType = "string"))
            )
        )

        assertNotNull(container.TopLevel)
        assertEquals(1, container.TopLevel?.Functions?.size)
        assertEquals(1, container.TopLevel?.Fields?.size)
    }

    @Test
    fun `getTopLevelFunctions should combine TopLevel and legacy default nodes`() {
        val container = CodeContainer(
            TopLevel = TopLevelScope(
                Functions = listOf(CodeFunction(Name = "newStyleFn"))
            ),
            DataStructures = listOf(
                CodeDataStruct(
                    NodeName = "default",
                    Functions = listOf(CodeFunction(Name = "legacyFn"))
                ),
                CodeDataStruct(
                    NodeName = "ActualClass",
                    Functions = listOf(CodeFunction(Name = "classMethod"))
                )
            )
        )

        val topLevelFns = container.getTopLevelFunctions()
        assertEquals(2, topLevelFns.size)
        assertTrue(topLevelFns.any { it.Name == "newStyleFn" })
        assertTrue(topLevelFns.any { it.Name == "legacyFn" })
        assertFalse(topLevelFns.any { it.Name == "classMethod" })
    }

    @Test
    fun `getTopLevelFields should combine multiple sources`() {
        val container = CodeContainer(
            Fields = listOf(CodeField(TypeKey = "containerField", TypeType = "string")),
            TopLevel = TopLevelScope(
                Fields = listOf(CodeField(TypeKey = "topLevelField", TypeType = "int"))
            ),
            DataStructures = listOf(
                CodeDataStruct(
                    NodeName = "default",
                    Fields = listOf(CodeField(TypeKey = "defaultField", TypeType = "bool"))
                )
            )
        )

        val topLevelFields = container.getTopLevelFields()
        assertEquals(3, topLevelFields.size)
        assertTrue(topLevelFields.any { it.TypeKey == "containerField" })
        assertTrue(topLevelFields.any { it.TypeKey == "topLevelField" })
        assertTrue(topLevelFields.any { it.TypeKey == "defaultField" })
    }

    @Test
    fun `getActualDataStructures should exclude default nodes`() {
        val container = CodeContainer(
            DataStructures = listOf(
                CodeDataStruct(NodeName = "default"),
                CodeDataStruct(NodeName = ""),
                CodeDataStruct(NodeName = "UserService"),
                CodeDataStruct(NodeName = "OrderRepository")
            )
        )

        val actual = container.getActualDataStructures()
        assertEquals(2, actual.size)
        assertTrue(actual.any { it.NodeName == "UserService" })
        assertTrue(actual.any { it.NodeName == "OrderRepository" })
    }

    @Test
    fun `hasTopLevelDeclarations should detect TopLevel content`() {
        val containerWithTopLevel = CodeContainer(
            TopLevel = TopLevelScope(Functions = listOf(CodeFunction(Name = "fn")))
        )
        assertTrue(containerWithTopLevel.hasTopLevelDeclarations())

        val containerWithDefault = CodeContainer(
            DataStructures = listOf(CodeDataStruct(NodeName = "default"))
        )
        assertTrue(containerWithDefault.hasTopLevelDeclarations())

        val emptyContainer = CodeContainer()
        assertFalse(emptyContainer.hasTopLevelDeclarations())
    }

    @Test
    fun `migrateDefaultNodesToTopLevel should move content from default nodes`() {
        val container = CodeContainer(
            DataStructures = listOf(
                CodeDataStruct(
                    NodeName = "default",
                    Functions = listOf(
                        CodeFunction(Name = "fn1"),
                        CodeFunction(Name = "fn2")
                    ),
                    Fields = listOf(
                        CodeField(TypeKey = "field1", TypeType = "string")
                    )
                ),
                CodeDataStruct(
                    NodeName = "ActualClass",
                    Functions = listOf(CodeFunction(Name = "classMethod"))
                )
            )
        )

        container.migrateDefaultNodesToTopLevel()

        // TopLevel should now have the migrated content
        assertNotNull(container.TopLevel)
        assertEquals(2, container.TopLevel?.Functions?.size)
        assertEquals(1, container.TopLevel?.Fields?.size)

        // DataStructures should only have actual classes
        assertEquals(1, container.DataStructures.size)
        assertEquals("ActualClass", container.DataStructures[0].NodeName)
    }

    @Test
    fun `migrateDefaultNodesToTopLevel should merge with existing TopLevel`() {
        val container = CodeContainer(
            TopLevel = TopLevelScope(
                Functions = listOf(CodeFunction(Name = "existingFn"))
            ),
            DataStructures = listOf(
                CodeDataStruct(
                    NodeName = "default",
                    Functions = listOf(CodeFunction(Name = "migratedFn"))
                )
            )
        )

        container.migrateDefaultNodesToTopLevel()

        assertEquals(2, container.TopLevel?.Functions?.size)
        assertTrue(container.TopLevel?.Functions?.any { it.Name == "existingFn" } == true)
        assertTrue(container.TopLevel?.Functions?.any { it.Name == "migratedFn" } == true)
        assertEquals(0, container.DataStructures.size)
    }

    @Test
    fun `migrateDefaultNodesToTopLevel should handle empty default nodes`() {
        val container = CodeContainer(
            DataStructures = listOf(
                CodeDataStruct(NodeName = ""), // empty name = like default
                CodeDataStruct(NodeName = "RealClass")
            )
        )

        container.migrateDefaultNodesToTopLevel()

        assertNotNull(container.TopLevel)
        assertEquals(1, container.DataStructures.size)
        assertEquals("RealClass", container.DataStructures[0].NodeName)
    }

    // ==================== CodeDataStruct.isLegacyTopLevelContainer Tests ====================

    @Test
    fun `isLegacyTopLevelContainer should detect default node`() {
        val defaultNode = CodeDataStruct(NodeName = "default")
        assertTrue(defaultNode.isLegacyTopLevelContainer())
    }

    @Test
    fun `isLegacyTopLevelContainer should detect empty node name`() {
        val emptyNode = CodeDataStruct(NodeName = "")
        assertTrue(emptyNode.isLegacyTopLevelContainer())
    }

    @Test
    fun `isLegacyTopLevelContainer should detect DEFAULT type`() {
        val defaultTypeNode = CodeDataStruct(
            NodeName = "something",
            Type = DataStructType.DEFAULT
        )
        assertTrue(defaultTypeNode.isLegacyTopLevelContainer())
    }

    @Test
    fun `isLegacyTopLevelContainer should detect lowercase OBJECT nodes`() {
        // Rust module files like "main.rs" create OBJECT nodes with lowercase names
        val rustMainNode = CodeDataStruct(
            NodeName = "main",
            Type = DataStructType.OBJECT
        )
        assertTrue(rustMainNode.isLegacyTopLevelContainer())
    }

    @Test
    fun `isLegacyTopLevelContainer should not flag actual classes`() {
        val actualClass = CodeDataStruct(
            NodeName = "UserService",
            Type = DataStructType.CLASS
        )
        assertFalse(actualClass.isLegacyTopLevelContainer())

        val actualInterface = CodeDataStruct(
            NodeName = "Repository",
            Type = DataStructType.INTERFACE
        )
        assertFalse(actualInterface.isLegacyTopLevelContainer())

        val actualStruct = CodeDataStruct(
            NodeName = "Config",
            Type = DataStructType.STRUCT
        )
        assertFalse(actualStruct.isLegacyTopLevelContainer())
    }

    // ==================== Backward Compatibility Tests ====================

    @Test
    fun `should maintain backward compatibility when TopLevel is null`() {
        val legacyContainer = CodeContainer(
            PackageName = "com.example",
            DataStructures = listOf(
                CodeDataStruct(
                    NodeName = "default",
                    Functions = listOf(CodeFunction(Name = "legacyFn"))
                )
            )
        )

        // TopLevel is null (legacy behavior)
        assertNull(legacyContainer.TopLevel)

        // But getTopLevelFunctions should still work via fallback
        assertEquals(1, legacyContainer.getTopLevelFunctions().size)
        assertEquals("legacyFn", legacyContainer.getTopLevelFunctions()[0].Name)
    }
}
