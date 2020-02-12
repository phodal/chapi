package domain.core

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CodeProjectTest {
    @Test
    internal fun shouldCreateProject() {
        val pkg = CodePackage(
            ID = "com.phodal",
            Name = "domain"
        )
        val codeModule = CodeModule()
        codeModule.Packages += pkg
        val project = CodeProject()
        project.Modules += codeModule

        assertEquals(project.Modules.size, 1)
    }
}
