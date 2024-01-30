package chapi.parser.cmake

import chapi.domain.core.CodeContainer

class CMakeBasicListener(val filePath: String) : CMakeBaseListener() {
    val container: CodeContainer = CodeContainer(FullName = filePath)

    override fun enterCommand_invocation(ctx: CMakeParser.Command_invocationContext?) {
        val id = ctx?.Identifier()?.text ?: ""
        val arg = ctx?.single_argument()?.map { it.text } ?: emptyList()

        container.Fields += chapi.domain.core.CodeField(
            TypeKey = id,
            TypeValue = arg.joinToString(" "),
            TypeType = "CMake"
        )
    }

    fun getNodeInfo(): CodeContainer {
        return container
    }
}
