package chapi.domain.expr

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

    class UnaryOp(lhs: ExpressionNode, op: UnaryOpKind) : ExpressionNode
    class IntValue(val value: kotlin.Int) : ExpressionNode {
        override fun toString() = value.toString()
    }

    class Variable(val name: kotlin.String) : ExpressionNode {
        override fun toString() = name
    }

    class Identifier(val name: kotlin.String) : ExpressionNode {
        override fun toString() = name
    }

    // lhs: identifier, rhs: expression
    class MethodCall(val functionName: String, val args: Array<ExpressionNode>, val className: String = "") : ExpressionNode {
        override fun toString(): String {
            return if (className == "") {
                "$functionName(${args.joinToString(", ")})"
            } else {
                "$className.$functionName(${args.joinToString(", ")})"
            }
        }
    }

    class Arguments(val args: kotlin.Array<ExpressionNode>) : ExpressionNode {
        override fun toString() = args.joinToString(", ")
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

    object Mod : BinOpKind() {
        override fun toString() = "%"
    }
}

sealed class UnaryOpKind {
    object Not : UnaryOpKind() {
        override fun toString() = "!"
    }

    object Neg : UnaryOpKind() {
        override fun toString() = "-"
    }

    object Try : UnaryOpKind() {
        override fun toString() = "?"
    }

    class Custom(val symbol: String = "") : UnaryOpKind() {
        override fun toString() = symbol
    }
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
