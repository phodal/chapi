rootProject.name = "chapi"

include(
    ":chapi-domain",
    ":chapi-helper",
// tier 1 languages
    ":chapi-ast-java",
    ":chapi-ast-typescript",

// tier 2 languages
    ":chapi-ast-kotlin",
    ":chapi-ast-go",
    ":chapi-ast-python",
    ":chapi-ast-scala",

// tier 3 languages
    ":chapi-ast-rust",
    ":chapi-ast-csharp",
    ":chapi-ast-c",
    ":chapi-ast-cpp",
)

