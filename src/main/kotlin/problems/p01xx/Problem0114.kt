package problems.p01xx

import problems.Problem
import problems.runProblem

fun main() = runProblem()

class Problem0114 : Problem(16475640049) {
    override fun calc(): Any {
        val possibilities = ArrayList<Long>()
        val l = 3
        for (i in 1..50) {
            var p = (possibilities.lastOrNull() ?: 1L) + (i + 1 - l).coerceAtLeast(0)
            for (j in 1..(i - l - 2)) p += possibilities[j] - 1
            possibilities.add(p)
        }
        return possibilities.last()
    }
}