package chapi.ast.rustast

import chapi.ast.antlr.RustParser
import chapi.domain.core.*
import java.io.File

open class RustFullIdentListener(val fileName: String) : RustAstBaseListener(fileName) {

}
