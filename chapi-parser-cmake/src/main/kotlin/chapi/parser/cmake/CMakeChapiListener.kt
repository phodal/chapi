package chapi.parser.cmake

import chapi.domain.core.CodeContainer

class CMakeChapiListener(val filePath: String) : CMakeBaseListener() {
    fun getNodeInfo(): CodeContainer {
        return CodeContainer(FullName = filePath)
    }
}
