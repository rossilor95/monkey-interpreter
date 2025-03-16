package monkey

import kotlin.test.Test
import kotlin.test.assertContentEquals

class LexerTest {

    @Test
    fun `should read operators`() {
        // Given
        val input = "= + - ! * / < > == !="
        val expected = listOf(
            Token(Token.Type.ASSIGN),
            Token(Token.Type.PLUS),
            Token(Token.Type.MINUS),
            Token(Token.Type.BANG),
            Token(Token.Type.STAR),
            Token(Token.Type.SLASH),
            Token(Token.Type.LESS_THAN),
            Token(Token.Type.GREATER_THAN),
            Token(Token.Type.EQUAL),
            Token(Token.Type.NOT_EQUAL),
            Token(Token.Type.EOF),
        )

        // When
        val actual = Lexer(input).asSequence().toList()

        // Then
        assertContentEquals(expected, actual)
    }

    @Test
    fun `should read delimiters`() {
        // Given
        val input = ", ; () {}"
        val expected = listOf(
            Token(Token.Type.COMMA),
            Token(Token.Type.SEMICOLON),
            Token(Token.Type.LEFT_PAREN),
            Token(Token.Type.RIGHT_PAREN),
            Token(Token.Type.LEFT_BRACE),
            Token(Token.Type.RIGHT_BRACE),
            Token(Token.Type.EOF),
        )

        // When
        val actual = Lexer(input).asSequence().toList()

        // Then
        assertContentEquals(expected, actual)
    }

    @Test
    fun `should read keywords`() {
        // Given
        val input = "let fn true false if else return"
        val expected = listOf(
            Token(Token.Type.LET),
            Token(Token.Type.FUNCTION),
            Token(Token.Type.TRUE),
            Token(Token.Type.FALSE),
            Token(Token.Type.IF),
            Token(Token.Type.ELSE),
            Token(Token.Type.RETURN),
            Token(Token.Type.EOF),
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
            Token(Token.Type.IDENTIFIER, "foo"),
            Token(Token.Type.IDENTIFIER, "Bar"),
            Token(Token.Type.IDENTIFIER, "fooBar"),
            Token(Token.Type.IDENTIFIER, "foo_baz"),
            Token(Token.Type.IDENTIFIER, "_quux"),
            Token(Token.Type.EOF),
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
            Token(Token.Type.INTEGER, "1"),
            Token(Token.Type.INTEGER, "234"),
            Token(Token.Type.INTEGER, "567890"),
            Token(Token.Type.EOF),
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
        val expected = listOf(
            Token(Token.Type.EOF),
        )

        // When
        val actual = Lexer(input).asSequence().toList()

        // Then
        assertContentEquals(expected, actual)
    }
}
