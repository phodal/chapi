package chapi.domain.core

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class CodeMemberTest {
    @Test
    internal fun shouldBuildMemberId_WhenDefaultNode() {
        var nodes: Array<CodeFunction> = arrayOf()
        val codeFunction = CodeFunction(Name = "Builder", IsConstructor = false)
        nodes += codeFunction
        val codeMember = CodeMember(
            DataStructID = "default",
            FunctionNodes = nodes,
            AliasPackage = "alias"
        )

        codeMember.buildMemberId()

        assertEquals(codeMember.ID, "alias:Builder")
    }

    @Test
    internal fun shouldBuildMemberId_WhenNormalNode() {
        var nodes: Array<CodeFunction> = arrayOf()
        val codeFunction = CodeFunction(Name = "Builder", IsConstructor = false)
        nodes += codeFunction
        val codeMember = CodeMember(
            FileID = "fileId",
            DataStructID = "builder",
            FunctionNodes = nodes,
            AliasPackage = "alias"
        )

        codeMember.buildMemberId()

        assertEquals(codeMember.ID, "fileId|alias::builder")
    }

    @Test
    internal fun shouldBuildMemberId_WhenSameFileIdPackageName() {
        var nodes: Array<CodeFunction> = arrayOf()
        val codeFunction = CodeFunction(Name = "Builder", IsConstructor = false)
        nodes += codeFunction
        val codeMember = CodeMember(
            FileID = "Builder",
            DataStructID = "builder",
            FunctionNodes = nodes,
            AliasPackage = "alias"
        )

        codeMember.buildMemberId()

        assertEquals(codeMember.ID, "Builder|alias::builder")
    }
}
