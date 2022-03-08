package chapi.app.path

import java.io.File

// filePath: point to current file
// sourcePath: like `../../`
//
// output: to normalize path
fun ecmaPathConvert(basedPath: String, sourcePath: String): String {
    // import "@/src/component/Hello.js"
    val isResolvePath = sourcePath.startsWith("@/")
    if(isResolvePath) {
        return sourcePath.removeRange(0, 2)
    }

    var file = File(basedPath)

    // use parent to convert
    if(file.isFile) {
        file = file.parentFile
    }

    val resolve = file.resolve(File(sourcePath))
    return resolve.normalize().toString()
}

fun relativeRoot(basedPath: String, sourcePath: String): String {
    var pathname = sourcePath
    val isResolvePath = pathname.startsWith("@/")
    if(isResolvePath) {
        pathname = pathname.removeRange(0, 2)
    }

    return File(pathname).relativeTo(File(basedPath)).toString()
}
