package domain.core

data class AnnotationKeyValue(val Key: String = "", val Value: String = "") {}

open class CodeAnnotation(val Name: String = "", var KeyValues: Array<AnnotationKeyValue>) {
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
