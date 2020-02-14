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
}
