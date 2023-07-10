package problems.p00xx

import problems.Problem
import problems.runProblem
import util.divisorsProperSum

fun main() = runProblem()

class Problem0021 : Problem(31626) {
    override fun calc(): Any {
        var result = 0
        for (i in 1..10000) {
            var sum = divisorsProperSum(i)
            if (sum > i) {
                var other = divisorsProperSum(sum)
                if (other == i) result += i + sum
            }
        }
        return result
    }
}