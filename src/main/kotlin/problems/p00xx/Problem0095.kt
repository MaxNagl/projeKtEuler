package problems.p00xx

import problems.Problem
import problems.runProblem

fun main() = runProblem()

class Problem0095 : Problem(14316) {
    override fun calc(): Any {
        val max = 1_000_000
        val sums = IntArray(max)
        for (i in 1 until max) {
            var j = i + i
            while (j < max) {
                sums[j] += i
                j += i
            }
        }

        var bestLen = 0
        var best = 0
        val visited = IntArray(max)
        for (i in 1 until max) {
            var current = i
            var len = 0
            while (current in i until max && visited[current] != i) {
                len++
                visited[current] = i
                current = sums[current]
            }
            if (current == i && len > bestLen) {
                bestLen = len
                best = i
            }
        }
        return best
    }
}
