package monkey

class Lexer(val input: String) : Iterator<Token> {
    private var position = -1

    override fun next(): Token {
        val ch = let {
            position += 1
            if (hasNext()) input[position] else Char.MIN_VALUE
        }
        return when (ch) {
            in setOf('\n', '\t', ' ', '\r') -> next()
            ',' -> Token.Delimiter.COMMA
            ';' -> Token.Delimiter.SEMICOLON
            '(' -> Token.Delimiter.LEFT_PAREN
            ')' -> Token.Delimiter.RIGHT_PAREN
            '{' -> Token.Delimiter.LEFT_BRACE
            '}' -> Token.Delimiter.RIGHT_BRACE
            Char.MIN_VALUE -> Token.EndOfFile
            else -> Token.Illegal
        }
    }

    override fun hasNext() = position < input.length
}