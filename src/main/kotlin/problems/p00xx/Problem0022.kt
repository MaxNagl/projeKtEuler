package problems.p00xx

import problems.Problem
import problems.runProblem

fun main() = runProblem()

class Problem0022 : Problem(871198282) {
    override fun calc(): Any {
        var sum = 0
        getData { it.replace("\"", "").split(",") }
            .sorted()
            .forEachIndexed { i, name ->
                sum += name.sumOf { it.code - 'A'.code + 1 } * (i + 1)
            }
        return sum
    }
}