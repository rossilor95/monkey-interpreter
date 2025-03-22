package monkey.parse

sealed interface Node

sealed interface Expr : Node {
    data class Identifier(val value: String) : Expr
}

sealed interface Stmt : Node {
    data class Let(val name: Expr.Identifier, val value: Expr?) : Stmt
}

data class Program(val statements: List<Stmt>) : Node
