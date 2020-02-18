package chapi.app.analyser

import chapi.app.analyser.config.ChapiConfig
import chapi.app.analyser.filter.FileFilter
import chapi.app.analyser.support.AbstractFile
import chapi.ast.javaast.JavaAnalyser
import chapi.domain.core.CodeDataStruct
import java.io.BufferedReader
import java.io.File

open class ChapiAnalyser(
    var config: ChapiConfig = ChapiConfig()
) {

}
