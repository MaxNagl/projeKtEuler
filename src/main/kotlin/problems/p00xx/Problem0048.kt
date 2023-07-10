package problems.p00xx

import problems.Problem
import problems.runProblem

fun main() = runProblem()

class Problem0048 : Problem(9110846700) {
    override fun calc(): Any {
        var result = 0L
        (1..1000).forEach { i ->
            var p = 1L
            (1..i).forEach { p = p * i % 10000000000L }
            result = (result + p) % 10000000000L
        }
        return result
    }
}
