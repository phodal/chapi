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
    class BinOp(val lhs: ExpressionNode, val op: BinOpKind, val rhs: ExpressionNode) : ExpressionNode {
        override fun toString(): String {
            return "$lhs $op $rhs"
        }
    }

    class UnOp(lhs: ExpressionNode, op: UnOpKind) : ExpressionNode
    class IntValue(val value: kotlin.Int) : ExpressionNode {
        override fun toString() = value.toString()
    }

    class CustomValueType(val value: ValueType) : ExpressionNode

}

sealed class BinOpKind {
    object Add : BinOpKind() {
        override fun toString() = "+"
    }

    object Sub : BinOpKind() {
        override fun toString() = "-"
    }

    object Mul : BinOpKind() {
        override fun toString() = "*"
    }

    object Div : BinOpKind() {
        override fun toString() = "/"
    }

    object Pow : BinOpKind() {
        override fun toString() = "^"
    }
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


interface ValueType {
    val name: String
    val value: Any
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
