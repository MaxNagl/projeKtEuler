package problems

import kotlin.math.roundToInt
import kotlin.math.sqrt

fun main() = runProblem()

class Problem0094 : Problem(518408346) {
    override fun calc(): Any {
        val max = 1_000_000_000
        var total = 0
        val sqrt3 = sqrt(3f)
        for (n in 1..1_000_000) {
            val l = (n * sqrt3).roundToInt()
            listOf(l, 2 * n + l).forEach { m ->
                val a = m * m - n * n
                val b = 2 * m * n
                val c = m * m + n * n
                if (m * m > max) return total
                if ((a * 2 - c) == 1 && (a * 2 + c * 2) <= max) total += a * 2 + c * 2
                if ((c - b * 2) == 1 && (b * 2 + c * 2) <= max) total += b * 2 + c * 2
            }
        }
        return total
    }
}