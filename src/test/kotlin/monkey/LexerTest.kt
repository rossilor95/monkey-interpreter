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
            Token.EndOfFile,
        )

        // When
        val actual = Lexer(input).asSequence().toList()

        // Then
        assertContentEquals(expected, actual)
    }

    @Test
    fun `should read keywords`() {
        // Given
        val input = "let fn"
        val expected = listOf(
            Token.Keyword.LET,
            Token.Keyword.FUNCTION,
            Token.EndOfFile,
        )

        // When
        val actual = Lexer(input).asSequence().toList()

        // Then
        assertContentEquals(expected, actual)
    }

    @Test
    fun `should read identifiers`() {
        // Given
        val input = "foo Bar fooBar foo_baz _quux"
        val expected = listOf(
            Token.Identifier("foo"),
            Token.Identifier("Bar"),
            Token.Identifier("fooBar"),
            Token.Identifier("foo_baz"),
            Token.Identifier("_quux"),
            Token.EndOfFile,
        )

        // When
        val actual = Lexer(input).asSequence().toList()

        // Then
        assertContentEquals(expected, actual)
    }

    @Test
    fun `should read number literals`() {
        // Given
        val input = "1 234 567890"
        val expected = listOf(
            Token.NumberLiteral("1"),
            Token.NumberLiteral("234"),
            Token.NumberLiteral("567890"),
            Token.EndOfFile,
        )

        // When
        val actual = Lexer(input).asSequence().toList()

        // Then
        assertContentEquals(expected, actual)
    }

    @Test
    fun `should ignore whitespace`() {
        // Given
        val input = "  \n \r \t "
        val expected = listOf(Token.EndOfFile)

        // When
        val actual = Lexer(input).asSequence().toList()

        // Then
        assertContentEquals(expected, actual)
    }
}
