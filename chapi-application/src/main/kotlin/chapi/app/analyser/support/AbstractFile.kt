package chapi.app.analyser.support

import java.io.File

open class AbstractFile(
    var fileName: String,
    var absolutePath: String
) {
    companion object {
        fun toAbstractFile(file: File): AbstractFile {
            return AbstractFile(
                fileName = file.name,
                absolutePath = file.absolutePath
            )
        }
    }
}
