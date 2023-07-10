package problems.p00xx

import problems.Problem
import problems.runProblem

fun main() = runProblem()

class Problem0009 : Problem(31875000) {
    override fun calc(): Any {
        for (a in 1..998) {
            for (b in a..999 - a) {
                val c = 1000 - a - b
                if (a * a + b * b == c * c) return a * b * c
            }
        }
        return 0
    }
}