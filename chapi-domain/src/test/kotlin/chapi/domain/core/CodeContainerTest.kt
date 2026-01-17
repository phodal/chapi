package chapi.domain.core

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class CodeContainerTest {
    @Test
    internal fun usageForContainersField() {
        val csharpFile = CodeContainer(
            PackageName = "hello.cs"
        )
        val firstNameSpace = CodeContainer(
            PackageName = "worldSpace"
        )
        csharpFile.Containers += firstNameSpace

        assertEquals(csharpFile.Containers.size, 1)
    }

    @Test
    internal fun testEquals() {
        assertEquals(
            CodeContainer(
                PackageName = "hello.cs"
            ),
            CodeContainer(
                PackageName = "hello.cs"
            )
        )
    }

    @Test
    fun `should support structured container semantics for Java`() {
        val javaContainer = CodeContainer(
            FullName = "UserService.java",
            PackageName = "com.example.service",
            Language = "java",
            Kind = ContainerKind.SOURCE_FILE,
            DeclaredPackage = "com.example.service"
        )

        assertEquals("java", javaContainer.Language)
        assertEquals(ContainerKind.SOURCE_FILE, javaContainer.Kind)
        assertEquals("com.example.service", javaContainer.DeclaredPackage)
        // Legacy field should still work
        assertEquals("com.example.service", javaContainer.PackageName)
    }

    @Test
    fun `should support structured container semantics for Rust`() {
        val rustContainer = CodeContainer(
            FullName = "parser.rs",
            PackageName = "crate::infrastructure::parser",
            Language = "rust",
            Kind = ContainerKind.MODULE,
            ResolvedModulePath = "crate::infrastructure::parser"
        )

        assertEquals("rust", rustContainer.Language)
        assertEquals(ContainerKind.MODULE, rustContainer.Kind)
        assertEquals("crate::infrastructure::parser", rustContainer.ResolvedModulePath)
        // DeclaredPackage should be empty for Rust (no package declaration)
        assertEquals("", rustContainer.DeclaredPackage)
    }

    @Test
    fun `should support namespace path for CSharp`() {
        val csharpContainer = CodeContainer(
            FullName = "UserService.cs",
            PackageName = "MyApp.Services.Users",
            Language = "csharp",
            Kind = ContainerKind.NAMESPACE,
            DeclaredPackage = "MyApp.Services.Users",
            NamespacePath = listOf("MyApp", "Services", "Users")
        )

        assertEquals("csharp", csharpContainer.Language)
        assertEquals(ContainerKind.NAMESPACE, csharpContainer.Kind)
        assertEquals(listOf("MyApp", "Services", "Users"), csharpContainer.NamespacePath)
    }

    @Test
    fun `should support config container kind for Toml`() {
        val tomlContainer = CodeContainer(
            FullName = "Cargo.toml",
            Language = "toml",
            Kind = ContainerKind.CONFIG
        )

        assertEquals("toml", tomlContainer.Language)
        assertEquals(ContainerKind.CONFIG, tomlContainer.Kind)
    }

    @Test
    fun `should support build script container kind for CMake`() {
        val cmakeContainer = CodeContainer(
            FullName = "CMakeLists.txt",
            Language = "cmake",
            Kind = ContainerKind.BUILD_SCRIPT
        )

        assertEquals("cmake", cmakeContainer.Language)
        assertEquals(ContainerKind.BUILD_SCRIPT, cmakeContainer.Kind)
    }

    @Test
    fun `should support IDL container kind for Protobuf`() {
        val protobufContainer = CodeContainer(
            FullName = "user.proto",
            PackageName = "com.example.api",
            Language = "protobuf",
            Kind = ContainerKind.IDL,
            DeclaredPackage = "com.example.api"
        )

        assertEquals("protobuf", protobufContainer.Language)
        assertEquals(ContainerKind.IDL, protobufContainer.Kind)
        assertEquals("com.example.api", protobufContainer.DeclaredPackage)
    }

    @Test
    fun `should maintain backward compatibility with legacy PackageName field`() {
        // Old code that only uses PackageName should still work
        val legacyContainer = CodeContainer(
            FullName = "Test.java",
            PackageName = "com.example"
        )

        assertEquals("com.example", legacyContainer.PackageName)
        // New fields should have sensible defaults
        assertEquals("", legacyContainer.Language)
        assertEquals(ContainerKind.UNKNOWN, legacyContainer.Kind)
        assertEquals("", legacyContainer.DeclaredPackage)
        assertEquals("", legacyContainer.ResolvedModulePath)
        assertEquals(emptyList<String>(), legacyContainer.NamespacePath)
    }
}
