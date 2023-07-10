package problems.p00xx

import problems.Problem
import problems.runProblem
import util.numberToWord

fun main() = runProblem()

class Problem0017 : Problem(21124) {
    override fun calc(): Any {
        return (1..1000).sumOf { it.toString().numberToWord().length }
    }
}