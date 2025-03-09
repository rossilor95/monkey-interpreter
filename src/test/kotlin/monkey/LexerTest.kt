package monkey

import kotlin.test.Test
import kotlin.test.assertContentEquals

class LexerTest {

    @Test
    fun `should read delimiters`() {
        // Given
        val input = ", ; () {}"
        val expected = listOf(
            Token.Delimiter.COMMA,
            Token.Delimiter.SEMICOLON,
            Token.Delimiter.LEFT_PAREN,
            Token.Delimiter.RIGHT_PAREN,
            Token.Delimiter.LEFT_BRACE,
            Token.Delimiter.RIGHT_BRACE,
            Token.EndOfFile
        )

        // When
        val tokens = Lexer(input).asSequence().toList()

        // Then
        assertContentEquals(expected, tokens)
    }

    @Test
    fun `should ignore whitespace`() {
        // Given
        val input = "  \n \r \t "
        val expected = listOf(Token.EndOfFile)

        // When
        val tokens = Lexer(input).asSequence().toList()

        // Then
        assertContentEquals(expected, tokens)
    }
}
