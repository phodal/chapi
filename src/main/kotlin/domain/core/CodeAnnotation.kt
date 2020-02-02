package domain.core

data class AnnotationKeyValue(var Key: String = "", var Value: String = "") {}

open class CodeAnnotation(
    var Name: String = "",
    var KeyValues: Array<AnnotationKeyValue> = arrayOf()
) {
    open fun isComponentOrRepository(): Boolean {
        return this.Name == "Component" || this.Name == "Repository"
    }

    open fun isTest(): Boolean {
        return this.Name == "Test"
    }

    open fun isIgnore(): Boolean {
        return this.Name == "Ignore"
    }

    open fun isIgnoreOrTest(): Boolean {
        return this.isIgnore() || this.isTest()
    }
}
