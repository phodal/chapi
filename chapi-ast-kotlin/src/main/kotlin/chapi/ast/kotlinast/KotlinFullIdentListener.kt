package chapi.ast.kotlinast

/**
 * listen to full identifier with complex type and sceneries, such as:
 * - lambda expression
 * - call relationship
 * - coroutine
 */
class KotlinFullIdentListener(fileName: String) : KotlinBasicIdentListener(fileName)
