package problems.p01xx

import problems.Problem
import problems.runProblem

fun main() = runProblem()

class Problem0117 : Problem(100808458960497) {
    override fun calc(): Any {
        val size = 50
        val reds = mutableListOf(0L, 0L, 1L, 3L, 7L)
        for (i in 5..size) {
            reds.add(reds[reds.size - 1] + reds[reds.size - 2] + reds[reds.size - 3] + reds[reds.size - 4] + 3L)
        }
        return reds.last() + 1
    }
}