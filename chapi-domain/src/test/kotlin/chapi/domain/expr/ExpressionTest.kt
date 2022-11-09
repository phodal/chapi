package chapi.domain.expr

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ExpressionTest {
    @Test
    fun shouldPresentationAdd() {
        // addOp(1, 2)
        val binOp = Expression.BinOp(
            lhs = Expression.IntValue(1),
            op = BinOpKind.Add,
            rhs = Expression.IntValue(2)
        )

        assertEquals("1 + 2", binOp.toString())
    }

    @Test
    fun shouldAddWithSub() {
        val add = Expression.BinOp(lhs = Expression.IntValue(1), op = BinOpKind.Add, rhs = Expression.IntValue(2))
        val binOp = Expression.BinOp(
            lhs = add,
            op = BinOpKind.Sub,
            rhs = Expression.IntValue(2)
        )

        assertEquals("1 + 2 - 2", binOp.toString())
    }

    @Test
    fun shouldUnaryOp() {
        val binOp = Expression.UnaryOp(
            lhs = Expression.IntValue(1),
            op = UnaryOpKind.Neg
        )

        assertEquals("-1", binOp.toString())
    }

    @Test
    fun shouldPresentationIdentSub() {
        val binOp = Expression.BinOp(
            lhs = Expression.Variable("a"),
            op = BinOpKind.Add,
            rhs = Expression.Variable("b")
        )

        assertEquals("a + b", binOp.toString())
    }

    @Test
    fun functionCall() {
        assertEquals(Expression.MethodCall(
                functionName = "add",
                className = "math",
                args = arrayOf(
                    Expression.IntValue(1),
                    Expression.IntValue(2)
                )
            ).toString(), "math.add(1, 2)"
        )

        assertEquals(Expression.MethodCall(
                functionName = "add",
                args = arrayOf(
                    Expression.IntValue(1),
                    Expression.IntValue(2)
                )
            ).toString(), "add(1, 2)"
        )
    }

    @Test
    fun arrayLiteral() {
        assertEquals(Expression.ArrayLiteral(
                args = arrayOf(
                    Expression.IntValue(1),
                    Expression.IntValue(2)
                )
            ).toString(), "[1, 2]"
        )
    }

    @Test
    fun tryCatch() {
        assertEquals(Expression.TryCatch(
                tryBlock = Expression.IntValue(1),
                catchBlock = Expression.IntValue(2)
            ).toString(), "try { 1 } catch { 2 }"
        )
    }

    @Test
    fun ifElse() {
        assertEquals(Expression.IfElse(
                condition = Expression.IntValue(1),
                thenBlock = Expression.IntValue(2),
                elseBlock = Expression.IntValue(3)
            ).toString(), "if (1) { 2 } else { 3 }"
        )
    }
}
