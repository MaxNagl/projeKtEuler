package problems.p00xx

import problems.Problem
import problems.runProblem
import util.primes
import kotlin.math.pow

fun main() = runProblem()

class Problem0087 : Problem(1097343) {
    override fun calc(): Any {
        val limit = 50_000_000
        val sqrt2 = limit.toFloat().pow(1 / 2f).toInt()
        val sqrt3 = limit.toFloat().pow(1 / 3f).toInt()
        val primes = primes(sqrt2)
        val p2 = primes.map { it * it }
        val p3 = primes.filter { it < sqrt3 }.map { it * it * it }
        val p4 = p2.filter { it < sqrt2 }.map { it * it }
        val sums = BooleanArray(limit)
        var count = 0
        p2.forEach { i2 ->
            p3.forEach { i3 ->
                p4.forEach { i4 ->
                    val i = i2 + i3 + i4
                    if (i < limit && !sums[i]) {
                        sums[i] = true
                        count++
                    }
                }
            }
        }
        return count
    }
}