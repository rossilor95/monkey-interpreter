package monkey.parse

import monkey.parse.Token.Type
import kotlin.test.Test
import kotlin.test.assertContentEquals

class LexerTest {

    @Test
    fun `should read operators`() {
        // Given
        val input = "= + - ! * / < > == !="
        val expected = listOf(
            Token(Type.ASSIGN),
            Token(Type.PLUS),
            Token(Type.MINUS),
            Token(Type.BANG),
            Token(Type.STAR),
            Token(Type.SLASH),
            Token(Type.LESS_THAN),
            Token(Type.GREATER_THAN),
            Token(Type.EQUAL),
            Token(Type.NOT_EQUAL),
            Token(Type.EOF),
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
            Token(Type.COMMA),
            Token(Type.SEMICOLON),
            Token(Type.LEFT_PAREN),
            Token(Type.RIGHT_PAREN),
            Token(Type.LEFT_BRACE),
            Token(Type.RIGHT_BRACE),
            Token(Type.EOF),
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
            Token(Type.LET),
            Token(Type.FUNCTION),
            Token(Type.TRUE),
            Token(Type.FALSE),
            Token(Type.IF),
            Token(Type.ELSE),
            Token(Type.RETURN),
            Token(Type.EOF),
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
            Token(Type.IDENTIFIER, "foo"),
            Token(Type.IDENTIFIER, "Bar"),
            Token(Type.IDENTIFIER, "fooBar"),
            Token(Type.IDENTIFIER, "foo_baz"),
            Token(Type.IDENTIFIER, "_quux"),
            Token(Type.EOF),
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
            Token(Type.INTEGER, "1"),
            Token(Type.INTEGER, "234"),
            Token(Type.INTEGER, "567890"),
            Token(Type.EOF),
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
            Token(Type.EOF),
        )

        // When
        val actual = Lexer(input).asSequence().toList()

        // Then
        assertContentEquals(expected, actual)
    }
}
