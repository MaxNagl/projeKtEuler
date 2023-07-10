package problems.p00xx

import problems.Problem
import problems.runProblem
import util.toLastPermutation

fun main() = runProblem()

class Problem0052 : Problem(142857) {
    override fun calc(): Any {
        (1..1000000).forEach { i ->
            val a = i.toLastPermutation()
            if ((i * 2).toLastPermutation() != a) return@forEach
            if ((i * 3).toLastPermutation() != a) return@forEach
            if ((i * 4).toLastPermutation() != a) return@forEach
            if ((i * 5).toLastPermutation() != a) return@forEach
            if ((i * 6).toLastPermutation() != a) return@forEach
            return i
        }
        return 0
    }
}
