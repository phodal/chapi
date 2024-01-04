package chapi.parser.toml

import chapi.domain.core.CodeContainer

class TomlListener(val filePath: String) : TomlParserBaseListener() {
    fun getNodeInfo(): CodeContainer {
        return CodeContainer()
    }

}
