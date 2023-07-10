package problems.p00xx

import problems.Problem
import problems.runProblem
import util.divisorsProper

fun main() = runProblem()

class Problem0088 : Problem(7587457) {
    override fun calc(): Any {
        val bests = IntArray(12000) { Int.MAX_VALUE }
        var missing = bests.size - 1
        for (n in 1..100000) {
            n.allProducts { sum, l ->
                val len = 1 + l + n - sum
                if (len < bests.size && bests[len] > n) {
                    if (bests[len] == Int.MAX_VALUE) missing--
                    bests[len] = n
                }
            }
            if (missing == 0) return bests.drop(2).toSet().sum()

        }
        return 0
    }

    private fun Int.allProducts(l: Int = 0, p: Int = 0, s: Int = 0, block: (Int, Int) -> Unit) {
        divisorsProper(this) {
            if (it == 1) {
                if (l <= this) block(s + this, p)
            } else if (l <= it) {
                (this / it).allProducts(it, p + 1, s + it, block)
            }
        }
    }
}