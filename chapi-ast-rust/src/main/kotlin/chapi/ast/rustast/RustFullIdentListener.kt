package chapi.ast.rustast

import chapi.ast.antlr.RustParser
import chapi.domain.core.CodeProperty

open class RustFullIdentListener(val fileName: String) : RustAstBaseListener(fileName) {
}
