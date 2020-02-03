package domain.core

class CodePackage(
    var Name: String = "",
    var ID: String = "",
    var CodeFiles: Array<CodeFile> = arrayOf(),
    var Extension: Any = ""
) {

}
