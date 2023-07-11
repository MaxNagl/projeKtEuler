package problems.p01xx

import problems.Problem
import problems.runProblem
import util.isPandigital1to9

fun main() = runProblem()

class Problem0104 : Problem(329468) {
    override fun calc(): Any {
        var lastA = 1
        var lastB = 1
        var firstA = 1L
        var firstB = 1L

        repeat(1000000) { i ->
            val lastC = lastA
            lastA = (lastB + lastA) % 1000000000
            lastB = lastC

            val firstC = firstA
            firstA += firstB
            firstB = firstC
            while (firstA > 100000000000000000L) {
                firstA /= 10
                firstB /= 10
            }

            if (lastC.isPandigital1to9() && (firstC / 100000000).toInt().isPandigital1to9())
                return i + 2
        }
        return 0
    }

}

