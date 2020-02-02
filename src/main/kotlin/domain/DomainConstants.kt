package domain

public class DomainConstants {
    companion object {
        val ASSERTION_LIST = arrayOf(
            "assert",
            "should",
            "check",    // ArchUnit,
            "maynotbe", // ArchUnit,
            "is",       // RestAssured,
            "spec",     // RestAssured,
            "verify"   // Mockito,
        )
    }
}
