package chapi.ast.kotlinast

/**
 * listen to full identifier with complex type and sceneries, such as:
 * - call relationship
 * |    TODO call chain
 * - lambda expression
 * - coroutine
 */
class KotlinFullIdentListener(fileName: String) : KotlinBasicIdentListener(fileName)
