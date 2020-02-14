package chapi.domain.core

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CodePackageManagerInfoTest {

    @Test
    fun getterSetter() {
        val pkgInfo = CodePackageManagerInfo("project")
        assertEquals(pkgInfo.ProjectName, "project")
    }
}
