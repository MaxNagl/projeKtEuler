package problems

import util.ggt
import kotlin.math.roundToInt
import kotlin.math.sqrt

fun main() = runProblem()

class Problem0075 : Problem(161667) {
    override fun calc(): Any {
        val max = sqrt(1500000f / 2).toInt()
        val unique = IntArray(1500001)
        (1..max).forEach { n ->
            val nn = n * n
            (n + 1..max).forEach { m ->
                val a = m * m - nn
                if (a % 2 != 0) {
                    val b = 2 * m * n
                    if (ggt(a, b) == 1) {
                        val c = sqrt(a.toDouble() * a + b.toDouble() * b).roundToInt()
                        val len = a + b + c
                        (len..1500000 step len).forEach { l -> unique[l]++ }
                    }
                }
            }
        }
        return unique.count { it == 1 }
    }
}
