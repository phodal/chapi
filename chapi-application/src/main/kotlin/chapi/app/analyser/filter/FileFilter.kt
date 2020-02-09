package chapi.app.analyser.filter

open class FileFilter {
    companion object {
     fun javaFileFilter(path: String): Boolean {
         return path.endsWith(".java")
     }
    }
}
