package problems

fun main() = runProblem()

class Problem0013 : Problem(5537376230) {
    override fun calc(): Any {
        return getDataLines { it.take(11).toLong() }.sumOf { it }.toString().take(10)
    }
}