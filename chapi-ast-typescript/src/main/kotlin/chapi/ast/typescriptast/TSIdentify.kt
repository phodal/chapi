package chapi.ast.typescriptast

import java.io.File

open class TSIdentify(var filePath: String = "") {
    fun resolvePackage(): String {
        val split = filePath.split("/").toMutableList()
        if(split.size == 1) {
            return File(split[0]).nameWithoutExtension
        }

        if(split[0] == "src" || split[0] == "") {
            split[0] = "@"
        } else {
            split.add(0, "@")
        }

        split[split.size - 1] = File(split.last()).nameWithoutExtension
        return split.joinToString(".")
    }
}
