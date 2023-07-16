package problems.p01xx

import problems.Problem
import problems.runProblem
import util.isPalindrome
import util.isPermutation

fun main() = runProblem()

class Problem0125 : Problem(2906969179) {
    override fun calc(): Any {
        val result = HashSet<Int>()
        (1..10000).forEach { s ->
            var sum = s * s
            for (e in (s + 1)..10000) {
                sum += e * e
                if (sum > 100000000) break
                if (sum.isPalindrome()) result.add(sum)
            }
        }
        return result.sumOf { it.toLong() }
    }
}