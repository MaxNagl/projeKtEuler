package problems

import util.ggt

fun main() = runProblem()

class Problem0071 : Problem(428570) {
    override fun calc(): Any {
        var best = 1L to 8L
        (1000000L downTo 8L).forEach { d ->
            val n = 3L * d / 7L
            if (best.first * d < best.second * n) {
                if (ggt(n.toInt(), d.toInt()) == 1) {
                    best = n to d
                }
            }
        }
        return best.first
    }
}