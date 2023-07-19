package problems.p01xx

import problems.Problem
import problems.runProblem
import util.pythagoreanTriple
import kotlin.math.absoluteValue

fun main() = runProblem()

class Problem0139 : Problem(10057761) {
    override fun calc(): Any {
        var count = 0
        pythagoreanTriple(50000000) { a, b, c ->
            (a - b).absoluteValue <= 2
        }.sortedBy { it.first }.forEach {
            count += 100000000 / (it.first + it.second + it.third)
        }
        return count
    }
}