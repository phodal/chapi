package chapi.ast.todo

import chapi.ast.antlr.CommentLexer
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.Token

private class TodoConstants {
    companion object {
        val TodoIdentifiers = arrayOf(
            "TODO",
            "FIXME"
        )
    }
}

open class TodoIdentApp {
    open fun Analysis(Str: String): Array<Todo> {
        var todos = arrayOf<Todo>()
        val lexer = this.lexerText(Str)

        for (token in lexer.allTokens) {
            val ONE_LINE_COMMENT = 1
            val LINE_COMMENT = 2
            val PYTHON_COMMENT = 3

            if (
                token.type == ONE_LINE_COMMENT ||
                token.type == LINE_COMMENT ||
                token.type == PYTHON_COMMENT
            ) {
                val parsedComment = this.parseComment(token)

                if (parsedComment != null) {
                    todos += parsedComment
                }
            }
        }

        return todos
    }

    private fun parseComment(token: Token): Todo? {
        val comment = token.text
        var trimText = comment.trim { it <= ' ' }
        if (trimText.startsWith("# ")) {
            trimText = trimText.substring(2)
        }

        if (isTodoIdentifier(trimText)) {
            val todo = Todo(Line = token.line)
            return todo
        }

        return null
    }

    private fun isTodoIdentifier(trimText: String): Boolean {
        for (todoIdentifier in TodoConstants.TodoIdentifiers) {
            if (trimText.toUpperCase().startsWith(todoIdentifier)) {
                return true
            }
        }

        return false
    }

    open fun lexerText(Str: String): CommentLexer {
        return CommentLexer(CharStreams.fromString(Str))
    }
}
