package monkey

class Lexer(private val input: String) : Iterator<Token> {

    private var position = -1
    private val allowedChars = 'a'..'z' union 'A'..'Z' union setOf('_')

    override fun next(): Token {
        val ch = nextChar()
        return when (ch) {
            in setOf('\n', '\t', ' ', '\r') -> next()
            in allowedChars -> readIdentifier()
            in '0'..'9' -> readNumber()
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

    private fun nextChar(): Char {
        position += 1
        return if (hasNext()) input[position] else Char.MIN_VALUE
    }

    private fun peekChar(n: Int = 1) = if (position + n < input.length) input[position + n] else Char.MIN_VALUE

    private fun readIdentifier(): Token {
        val startIndex = position
        while (peekChar() in allowedChars && hasNext()) {
            position += 1
        }
        val endIndex = position + 1
        val value = input.substring(startIndex, endIndex)
        return keywordTable[value] ?: Token.Identifier(value)
    }

    private fun readNumber(): Token.NumberLiteral {
        val startIndex = position
        while (peekChar() in '0'..'9' && hasNext()) {
            position += 1
        }
        val endIndex = position + 1
        val value = input.substring(startIndex, endIndex)
        return Token.NumberLiteral(value)
    }
}