package monkey.parse

import monkey.parse.Token.Type

class Lexer(private val input: String) : Iterator<Token> {

    private var position = -1
    private val allowedChars = 'a'..'z' union 'A'..'Z' union setOf('_')

    override fun next(): Token {
        val ch = nextChar()
        return when (ch) {
            in setOf('\n', '\t', ' ', '\r') -> next()
            in allowedChars -> readIdentifier()
            in '0'..'9' -> readNumber()
            '=' -> if (peekChar() == '=') {
                position += 1
                Token(Type.EQUAL)
            } else {
                Token(Type.ASSIGN)
            }

            '!' -> if (peekChar() == '=') {
                position += 1
                Token(Type.NOT_EQUAL)
            } else {
                Token(Type.BANG)
            }

            '+' -> Token(Type.PLUS)
            '-' -> Token(Type.MINUS)
            '/' -> Token(Type.SLASH)
            '*' -> Token(Type.STAR)
            '<' -> Token(Type.LESS_THAN)
            '>' -> Token(Type.GREATER_THAN)
            ',' -> Token(Type.COMMA)
            ';' -> Token(Type.SEMICOLON)
            '(' -> Token(Type.LEFT_PAREN)
            ')' -> Token(Type.RIGHT_PAREN)
            '{' -> Token(Type.LEFT_BRACE)
            '}' -> Token(Type.RIGHT_BRACE)
            Char.MIN_VALUE -> Token(Type.EOF)
            else -> Token(Type.ILLEGAL)
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
        while (peekChar() in allowedChars) {
            position += 1
        }
        val endIndex = position + 1
        val lexeme = input.substring(startIndex, endIndex)
        return keywordTable[lexeme]?.let {
            Token(type = it)
        } ?: Token(Type.IDENTIFIER, lexeme)
    }

    private fun readNumber(): Token {
        val startIndex = position
        while (peekChar() in '0'..'9') {
            position += 1
        }
        val endIndex = position + 1
        val lexeme = input.substring(startIndex, endIndex)
        return Token(Type.INTEGER, lexeme)
    }
}