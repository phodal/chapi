package chapi.ast.typescriptast

open class TSIdentify(var filePath: String = "") {
    fun resolvePackage(): String {
        val split = filePath.split("/").toMutableList()
        if(split.size == 1) {
            return split[0]
        }

        if(split[0] == "src" || split[0] == "") {
            split[0] = "@"
        } else {
            split.add(0, "@")
        }

        val names = split.dropLast(1)
        return names.joinToString(".")
    }
}
