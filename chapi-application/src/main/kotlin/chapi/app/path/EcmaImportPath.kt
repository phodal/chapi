package chapi.app.path

import java.io.File

// filePath: point to current file
// sourcePath: like `../../`
//
// output: to normalize path
fun ecmaPathConvert(basedPath: String, sourcePath: String): String {
    var file = File(basedPath)

    // use parent to convert
    if(file.isFile) {
        file = file.parentFile
    }

    val resolve = file.resolve(File(sourcePath))
    return resolve.normalize().toString()
}
