package problems.p00xx

import problems.Problem
import problems.runProblem

fun main() = runProblem()

class Problem0015 : Problem(137846528820) {
    override fun calc(): Any {
        val paths = Array(21) { LongArray(21) }
        for (y in 0..20) paths[0][y] = 1
        for (x in 0..20) paths[x][0] = 1

        for (x in 1..20) for (y in 1..20) {
            paths[x][y] = paths[x - 1][y] + paths[x][y - 1]
        }

        return paths[20][20]
    }
}