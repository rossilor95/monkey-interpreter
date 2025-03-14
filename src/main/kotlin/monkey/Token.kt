package monkey

sealed interface Token {
    data object Illegal : Token
    data object EndOfFile : Token

    data class Identifier(val value: String) : Token
    data class NumberLiteral(val value: String) : Token
    data class StringLiteral(val value: String) : Token

    enum class Operator : Token {
        ASSIGN, PLUS, MINUS, BANG, STAR, SLASH, LESS_THAN, GREATER_THAN, EQUAL, NOT_EQUAL,
    }

    enum class Delimiter : Token {
        COMMA, LEFT_PAREN, RIGHT_PAREN, LEFT_BRACE, RIGHT_BRACE, SEMICOLON,
    }

    enum class Keyword : Token {
        FUNCTION, LET,
    }
}

val keywordTable = mapOf(
    "fn" to Token.Keyword.FUNCTION,
    "let" to Token.Keyword.LET,
)
