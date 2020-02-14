package chapi.domain.core

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CodePackageInfoTest {

    @Test
    fun getterSetter() {
        val pkgInfo = CodePackageInfo("project")
        assertEquals(pkgInfo.ProjectName, "project")
    }
}
