package chapi.parser.cmake

import chapi.domain.core.CodeContainer
import chapi.domain.core.ContainerKind

class CMakeBasicListener(val filePath: String) : CMakeBaseListener() {
    private val container: CodeContainer = CodeContainer(
        FullName = filePath,
        Language = "cmake",
        Kind = ContainerKind.BUILD_SCRIPT
    )

    override fun enterCommand_invocation(ctx: CMakeParser.Command_invocationContext?) {
        val id = ctx?.Identifier()?.text ?: ""
        val arg = ctx?.single_argument()?.map { it.text } ?: emptyList()

        container.Fields += chapi.domain.core.CodeField(
            TypeKey = id,
            TypeValue = arg.joinToString(" "),
            TypeType = "String"
        )
    }

    fun getNodeInfo(): CodeContainer {
        return container
    }
}
