package chapi.ast.arkts

import java.io.File

enum class OS {
    WINDOWS, LINUX, MAC, SOLARIS
}
fun getOS(): OS? {
    val os = System.getProperty("os.name").lowercase()
    return when {
        os.contains("win") -> {
            OS.WINDOWS
        }

        os.contains("nix") || os.contains("nux") || os.contains("aix") -> {
            OS.LINUX
        }

        os.contains("mac") -> {
            OS.MAC
        }

        os.contains("sunos") -> {
            OS.SOLARIS
        }

        else -> null
    }
}

object EcmaImportHelper {
    /**
     * Converts a ArkTS import statement to the corresponding import statement in the target language.
     *
     * This method takes an import statement in ArkTS and converts it to the equivalent import statement in the target language. It handles the conversion of file paths and package names.
     *
     * @param importSource The import statement to be converted. It should be in the format of a ArkTS import statement.
     * @param sourceFile The file path of the current file. This is used to resolve relative file paths in the import statement.
     *
     * @return The converted import statement in the target language.
     *
     * Usage:
     * ```kotlin
     * // case 1
     * val output = EcmaImportHelper.convertArkTSImport("../component", "src/main.tsx")
     * println(output) // "component"
     * // case 2
     * val output = EcmaImportHelper.convertArkTSImport("src/component", "src/main.tsx")
     * println(output) // "@.component"
     * // case 3
     * val output = EcmaImportHelper.convertArkTSImport("@/page/component", "src/main.tsx")
     * println(output) // "@.page.component"
     * ```
     *
     */
    fun convertArkTSImport(importSource: String, sourceFile: String): String {
        var imp = importSource
        if (!imp.startsWith("@")) {
            imp = importConvert(sourceFile, imp)
            if (imp.startsWith("src/")) {
                imp = imp.replaceFirst("src/", "@/")
            }
        }

        imp = imp.replace("/", ".")
        return imp
    }


    /**
     * Converts the import path to the corresponding file path based on the given file path and import path.
     *
     * @param filepath The file path of the current file.
     * @param importPath The import path to be converted.
     * @return The converted file path.
     */
    fun importConvert(filepath: String, importPath: String): String {
        // import "@/src/component/Hello.js"
        val isResolvePath = importPath.startsWith("@/")
        if (isResolvePath) {
            var pathname = importPath.removeRange(0, 2)
            pathname = "src/$pathname"

            if (getOS() == OS.WINDOWS) pathname = pathname.replace("\\", "/")

            return pathname
        }

        if (importPath.startsWith("./") || importPath.startsWith("../")) {
            var file = File(filepath)

            // use parent to convert
            if (file.extension.isNotEmpty()) {
                // src/main.tsx don't have parent
                if (file.parentFile != null) {
                    file = file.parentFile
                }
            }

            val resolve = file.resolve(File(importPath))
            return resolve.normalize().toString()
        }

        var finalPath = importPath
        if (getOS() == OS.WINDOWS) finalPath = finalPath.replace("\\", "/")
        return finalPath
    }

}
