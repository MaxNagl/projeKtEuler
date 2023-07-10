package problems.p00xx

import problems.Problem
import problems.runProblem

fun main() = runProblem()

class Problem0006 : Problem(25164150) {
    override fun calc(): Any {
        val max = 100L
        val a = (1L..max).sumOf { it * it }
        val b = (max + 1) * max / 2
        return b * b - a
    }
}