package domain.core

class CodeProperty(
    var Modifiers: Array<String>,
    var ParamName: String,
    var TypeValue: String,
    var TypeType: String,
    var ReturnTypes: Array<CodeProperty>,
    var Parameters: Array<CodeProperty>
) {

}
