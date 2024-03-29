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
        assertEquals(
            Expression.MethodCall(
                lhs = Expression.Identifier("math"),
                caller = Expression.Identifier("add"),
                rhs = arrayOf(
                    Expression.IntValue(1),
                    Expression.IntValue(2)
                )
            ).toString(), "math.add(1, 2)"
        )

        assertEquals(
            Expression.MethodCall(
                lhs = Expression.Identifier("add"),
                rhs = arrayOf(
                    Expression.IntValue(1),
                    Expression.IntValue(2)
                )
            ).toString(), "add(1, 2)"
        )
    }

    @Test
    fun arrayLiteral() {
        assertEquals(
            Expression.ArrayLiteral(
                args = arrayOf(
                    Expression.IntValue(1),
                    Expression.IntValue(2)
                )
            ).toString(), "[1, 2]"
        )
    }

    @Test
    fun tryCatch() {
        assertEquals(
            Expression.TryCatch(
                tryBlock = Expression.Value(1),
                catchBlock = Expression.IntValue(2)
            ).toString(), "try { 1 } catch { 2 }"
        )
    }

    @Test
    fun ifElse() {
        assertEquals(
            Expression.IfElse(
                condition = Expression.IntValue(1),
                thenBlock = Expression.IntValue(2),
                elseBlock = Expression.IntValue(3)
            ).toString(), "if (1) { 2 } else { 3 }"
        )
    }

    @Test
    fun compareItem() {
        assertEquals(
            Expression.ComparisonOp(
                lhs = Expression.IntValue(1),
                op = ComparisonOpKind.GreaterThan,
                rhs = Expression.IntValue(2)
            ).toString(), "1 > 2"
        )
    }

    @Test
    fun shouldPresentationIdentSubWithParen() {
        val binOp = Expression.BinOp(
            lhs = Expression.Variable("a"),
            op = BinOpKind.Add,
            rhs = Expression.BinOp(
                lhs = Expression.Variable("b"),
                op = BinOpKind.Sub,
                rhs = Expression.Variable("c")
            )
        )

        assertEquals("a + b - c", binOp.toString())
    }

    @Test
    fun assignment() {
        assertEquals(
            Expression.AssignOp(
                lhs = Expression.Variable("a"),
                op = AssignOpKind.Assign,
                rhs = Expression.IntValue(1)
            ).toString(), "a = 1"
        )
    }

    @Test
    fun switchCase() {
        assertEquals(
            Expression.SwitchCases(
                lhs = Expression.Variable("a"),
                cases = arrayOf(
                    Expression.CaseOp(
                        condition = Expression.IntValue(1),
                        thenBlock = Expression.IntValue(2)
                    ),
                    Expression.CaseOp(
                        condition = Expression.IntValue(2),
                        thenBlock = Expression.IntValue(3)
                    )
                ),
                defaultBlock = Expression.IntValue(4)
            ).toString(), "switch (a) {\n" +
                "case 1: 2\n" +
                "case 2: 3\n" +
                "default: 4\n" +
                "}"
        )
    }

    @Test
    fun range() {
        assertEquals(
            Expression.RangeOp(
                lhs = Expression.IntValue(1),
                rhs = Expression.IntValue(2)
            ).toString(), "1..2"
        )
    }

    @Test
    fun chainedMethodCall() {
        assertEquals(
            Expression.MethodCall(
                lhs = Expression.MethodCall(
                    lhs = Expression.StringValue("add"),
                    rhs = arrayOf(
                        Expression.IntValue(1),
                        Expression.IntValue(2)
                    ),
                ),
                caller = Expression.Identifier("sub"),
                rhs = arrayOf(
                    Expression.IntValue(3)
                )
            ).toString(), "add(1, 2).sub(3)"
        )
    }

    @Test
    fun returnExprOp() {
        assertEquals(
            Expression.JumpOp(
                op = JumpOpKind.Return(Expression.IntValue(1))
            ).toString(), "return 1"
        )
    }
}
