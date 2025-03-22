package monkey.parse

data class Token(val type: Type, val lexeme: String? = null) {

    enum class Type {
        ILLEGAL,
        EOF,

        // Identifiers + literals
        IDENTIFIER,
        INTEGER,

        // Operators
        ASSIGN,
        PLUS,
        MINUS,
        BANG,
        STAR,
        SLASH,
        LESS_THAN,
        GREATER_THAN,
        EQUAL,
        NOT_EQUAL,

        // Delimiters
        COMMA,
        LEFT_PAREN,
        RIGHT_PAREN,
        LEFT_BRACE,
        RIGHT_BRACE,
        SEMICOLON,

        // Keywords
        FUNCTION,
        LET,
        TRUE,
        FALSE,
        IF,
        ELSE,
        RETURN,
    }
}

val keywordTable = mapOf(
    "fn" to Token.Type.FUNCTION,
    "let" to Token.Type.LET,
    "true" to Token.Type.TRUE,
    "false" to Token.Type.FALSE,
    "if" to Token.Type.IF,
    "else" to Token.Type.ELSE,
    "return" to Token.Type.RETURN,
)
