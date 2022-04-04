package chapi.app.analyser.support

import java.io.File

open class AbstractFile(
    var fileName: String,
    var absolutePath: String,
    var isFile: Boolean,
    var relativePath: String,
    var content: String
) {
    companion object {
        fun toAbstractFile(file: File, workspace: File): AbstractFile {
            return AbstractFile(
                fileName = file.name,
                absolutePath = file.absolutePath,
                relativePath = file.relativeTo(workspace).toString(),
                isFile = file.isFile,
                content = ""
            )
        }
    }
}
