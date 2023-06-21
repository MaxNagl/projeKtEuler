package problems

fun main() = runProblem()

class Problem0001 : Problem(233168) {
    override fun calc(): Any {
        return (1 until 1000).filter { it % 3 == 0 || it % 5 == 0 }.sum()
    }
}