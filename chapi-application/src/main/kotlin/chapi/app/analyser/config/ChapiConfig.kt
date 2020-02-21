package chapi.app.analyser.config

data class ChapiConfig(
    var language: String = "",
    var withoutTest: Boolean = false,
    var filterRule: String = ""
) {

}
