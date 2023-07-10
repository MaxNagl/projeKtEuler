package problems.p00xx

import problems.Problem
import problems.runProblem

fun main() = runProblem()

class Problem0092 : Problem(8581146) {
    override fun calc(): Any {
        var count = 0
        val cache = IntArray(9 * 9 * 7 + 1)
        for (i in 1..cache.size) {
            var j = i
            while (j != 1 && j != 89) {
                var next = 0
                while (j != 0) {
                    next += (j % 10) * (j % 10)
                    j /= 10
                }
                j = next
            }
            if (i < cache.size) cache[i] = if (j == 89) 1 else 0
        }
        for (d1 in 0..9) {
            val s1 = d1 * d1
            for (d2 in 0..9) {
                val s2 = s1 + d2 * d2
                for (d3 in 0..9) {
                    val s3 = s2 + d3 * d3
                    for (d4 in 0..9) {
                        val s4 = s3 + d4 * d4
                        for (d5 in 0..9) {
                            val s5 = s4 + d5 * d5
                            for (d6 in 0..9) {
                                val s6 = s5 + d6 * d6
                                for (d7 in 0..9) {
                                    count += cache[s6 + d7 * d7]
                                }
                            }
                        }
                    }
                }
            }
        }
        return count
    }
}