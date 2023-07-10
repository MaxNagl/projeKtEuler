package problems.p00xx

import problems.Problem
import problems.runProblem
import kotlin.math.sqrt

fun main() = runProblem()

class Problem0039 : Problem(840) {
    override fun calc(): Any {
        val count = IntArray(1000)
        (3..448).forEach { c ->
            val p = c * c
            (1..c).forEach { a ->
                val r = p - a * a
                val b = sqrt(r.toFloat()).toInt()
                if (b * b == r && a < b && a + b + c < 1000) {
                    count[a + b + c]++
                }
            }
        }
        return count.indexOf(count.max())
    }
}
