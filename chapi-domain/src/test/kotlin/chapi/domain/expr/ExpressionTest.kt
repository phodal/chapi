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
}
