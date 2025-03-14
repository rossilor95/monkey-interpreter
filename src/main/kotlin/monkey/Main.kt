package monkey

private const val PROMPT = ">> "

fun main() {
    println("Welcome to the Monkey programming language!")
    println("Feel free to type in commands")

    while (true) {
        print(PROMPT)
        val line = readlnOrNull() ?: continue
        val lexer = Lexer(line)
        lexer.forEachRemaining { println(it) }
    }
}
