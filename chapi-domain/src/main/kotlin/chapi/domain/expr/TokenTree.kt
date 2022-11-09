package chapi.domain.expr

import java.util.function.BinaryOperator
import java.util.function.IntBinaryOperator

// todo: mapping to pratt parser ?
// mini sample <https://github.com/segeljakt/pratt>
//
// enum class TokenTree {
//     Primary,
//     Prefix,
//     Infix,
//     Postfix;
// }
sealed class Expression {
    class BinOp(lhs: Expression, op: BinOpKind, rhs: Expression) : ExpressionNode
    class UnOp(lhs: Expression, op: UnOpKind) : ExpressionNode
    class Int(val value: Int?) : ExpressionNode
}

sealed class BinOpKind {
    object Add : BinOpKind() // +
    object Sub : BinOpKind() // -
    object Mul : BinOpKind() // *
    object Div : BinOpKind() // /
    object Pow : BinOpKind() // ^
    object Eq : BinOpKind() // =
}

sealed class UnOpKind {
    object Not : UnOpKind() // !
    object Neg : UnOpKind() // -
    object Try : UnOpKind() // ?
}

interface ExpressionType {
    val name: String
}

interface ExpressionNode {
    val childrens: Array<ExpressionNode> get() = emptyArray()
    val expressionTypes: Array<ExpressionType> get() = emptyArray()

    fun getPriority(): Int = 0

    fun addChildren(child: Operator) {
        childrens.plus(child)
    }

    fun addExpressionType(type: ExpressionType) {
        expressionTypes.plus(type)
    }
}


interface Operator : ExpressionNode

enum class Arithmetics : BinaryOperator<Int>, IntBinaryOperator {
    PLUS {
        override fun apply(t: Int, u: Int): Int {
            return t + u
        }
    }
    ;

    override fun applyAsInt(t: Int, u: Int) = apply(t, u)
}
