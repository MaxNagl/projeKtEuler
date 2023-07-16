package problems.p01xx

import problems.Problem
import problems.runProblem

fun main() = runProblem()

class Problem0124 : Problem(21417) {
    override fun calc(): Any {
        val rads = IntArray(100001)
        rads.fill(1)
        (2 until rads.size).forEach { p ->
            if (rads[p] == 1) (p until rads.size step p).forEach { rads[it] *= p }
        }
        val radCounts = IntArray(rads.size)
        rads.forEach { radCounts[it]++ }
        var pos = 10000
        radCounts.forEachIndexed { rad, c ->
            if (c > pos) rads.forEachIndexed { i, r -> if (r == rad) if (pos-- == 0) return i }
            pos -= c
        }
        return 0
    }
}