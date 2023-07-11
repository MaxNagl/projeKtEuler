package problems.p01xx

import problems.Problem
import problems.runProblem

fun main() = runProblem()

class Problem0116 : Problem(20492570929) {
    override fun calc(): Any {
        val size = 50
        val reds = mutableListOf(0L, 0L, 1L)
        for (i in 3..size) reds.add(reds[reds.size - 1] + reds[reds.size - 2] + 1L)
        val greens = mutableListOf(0L, 0L, 0L, 1L)
        for (i in 4..size) greens.add(greens[greens.size - 1] + greens[greens.size - 3] + 1L)
        val blues = mutableListOf(0L, 0L, 0L, 0L, 1L)
        for (i in 5..size) blues.add(blues[blues.size - 1] + blues[blues.size - 4] + 1L)
        return reds.last() + greens.last() + blues.last()
    }
}