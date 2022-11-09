package chapi.domain.expr

import java.util.function.BinaryOperator
import java.util.function.IntBinaryOperator

class Expression(
    var Type: ExpressionType,
    var Value: String = "",
    var Children: Array<Expression> = arrayOf()
)

enum class ExpressionType {
    Primary,
    Infix,
    Prefix,
    Postfix,
}

enum class Arithmetics : BinaryOperator<Int>, IntBinaryOperator {
    PLUS {
        override fun apply(t: Int, u: Int): Int {
            return t + u
        }
    }
    ;

    override fun applyAsInt(t: Int, u: Int) = apply(t, u)
}
