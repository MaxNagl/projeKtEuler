package problems.p01xx

import problems.Problem
import problems.runProblem

fun main() = runProblem()

class Problem0112 : Problem(1587000) {
    override fun calc(): Any {
        var count = 0
        repeat(Int.MAX_VALUE) {
            if (it.isBouncy()) if (it <= ++count * 100 / 99) return it
        }
        return 0
    }

    private fun Int.isBouncy(): Boolean {
        var inc = 0
        var dec = 10
        var i = this
        while (i != 0) {
            val d = i % 10
            inc = if (inc <= d) d else if (dec == -1) return true else 11
            dec = if (dec >= d) d else if (inc == 11) return true else -1
            i /= 10
        }
        return false
    }
}
