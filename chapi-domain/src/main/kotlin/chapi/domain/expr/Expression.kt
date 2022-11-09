package chapi.domain.expr

// todo: mapping to pratt parser ?
// mini sample <https://github.com/segeljakt/pratt>
sealed class Expression {
    class BinOp(val lhs: ExpressionNode, val op: BinOpKind, val rhs: ExpressionNode) : ExpressionNode {
        override fun toString() = "$lhs $op $rhs"
    }

    class UnaryOp(val lhs: ExpressionNode, val op: UnaryOpKind) : ExpressionNode {
        override fun toString(): String = "$op$lhs"
    }

    class ComparisonOp(val lhs: ExpressionNode, val op: ComparisonOpKind, val rhs: ExpressionNode) : ExpressionNode {
        override fun toString(): String = "$lhs $op $rhs"
    }

    class IntValue(val value: Int) : ExpressionNode {
        override fun toString() = value.toString()
    }

    class FloatValue(val value: Float) : ExpressionNode {
        override fun toString() = value.toString()
    }

    class StringValue(val value: String) : ExpressionNode {
        override fun toString() = value
    }

    class Variable(val name: String) : ExpressionNode {
        override fun toString() = name
    }

    class TryCatch(val tryBlock: ExpressionNode, val catchBlock: ExpressionNode) : ExpressionNode {
        override fun toString() = "try { $tryBlock } catch { $catchBlock }"
    }

    class IfElse(val condition: ExpressionNode, val thenBlock: ExpressionNode, val elseBlock: ExpressionNode) :
        ExpressionNode {
        override fun toString() = "if ($condition) { $thenBlock } else { $elseBlock }"
    }

    class Identifier(val name: String) : ExpressionNode {
        override fun toString() = name
    }

    // lhs: identifier, rhs: expression
    class MethodCall(val functionName: String, val args: Array<ExpressionNode>, val className: String = "") :
        ExpressionNode {
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

    class ArrayLiteral(val args: kotlin.Array<ExpressionNode>) : ExpressionNode {
        override fun toString() = "[${args.joinToString(", ")}]"
    }

    class Value(val value: Any) : ExpressionNode {
        override fun toString() = value.toString()
    }
}


sealed class ComparisonOpKind {
    object Equal : ComparisonOpKind() {
        override fun toString() = "=="
    }
    object NotEqual : ComparisonOpKind() {
        override fun toString() = "!="
    }

    object GreaterThan : ComparisonOpKind() {
        override fun toString() = ">"
    }

    object GreaterThanOrEqual : ComparisonOpKind() {
        override fun toString() = ">="
    }

    object LessThan : ComparisonOpKind() {
        override fun toString() = "<"
    }

    object LessThanOrEqual : ComparisonOpKind() {
        override fun toString() = "<="
    }

    object In : ComparisonOpKind() {
        override fun toString() = "in"
    }

    object NotIn : ComparisonOpKind() {
        override fun toString() = "!in"
    }

    object Is : ComparisonOpKind() {
        override fun toString() = "is"
    }
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

sealed class Typed {
    class IntType(val value: Int) : Typed()
    class FloatType(val value: Float) : Typed()
    class StringType(val value: String) : Typed()
    class BoolType(val value: Boolean) : Typed()
    class ArrayType(val value: Array<Typed>) : Typed()
    class ObjectType(val value: Any) : Typed()
    class NullType(val value: Any) : Typed()
}

interface Operator : ExpressionNode
