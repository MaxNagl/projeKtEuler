package problems.p00xx

import problems.Problem
import problems.runProblem
import kotlin.math.sqrt

fun main() = runProblem()

class Problem0044 : Problem(5482660) {
    fun isPentagonal(num: Int): Boolean {
        val p = (sqrt(num * 24 + 1f) + 1).toInt() / 6
        return p * (3 * p - 1) / 2 == num
    }

    override fun calc(): Any {
        (1..1000000).forEach { i ->
            val a = i * (3 * i - 1) / 2
            (1 until i).forEach { j ->
                val b = j * (3 * j - 1) / 2
                if (isPentagonal(a + b) && isPentagonal(a - b)) return a - b
            }
        }
        return 0
    }
}
