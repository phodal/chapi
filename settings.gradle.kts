rootProject.name = "chapi"

include(
    ":chapi-domain",
    ":chapi-application",
    ":chapi-testing"
)

include(
    ":chapi-ast-java",
    ":chapi-ast-typescript",
    ":chapi-ast-go",
    ":chapi-ast-python",
    ":chapi-ast-c",
    ":chapi-ast-csharp",
    ":chapi-ast-kotlin",
    ":chapi-ast-scala"
)

