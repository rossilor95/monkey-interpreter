package monkey

sealed interface Token {
    object Illegal : Token
    object EndOfFile : Token

    data class Identifier(val value: String) : Token
    data class NumberLiteral(val value: String) : Token
    data class StringLiteral(val value: String) : Token

    enum class Operator : Token {
        ASSIGN, PLUS
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
