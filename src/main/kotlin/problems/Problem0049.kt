package problems

import util.primes
import util.primesCheckArray

fun main() = runProblem()

class Problem0049 : Problem(null) {
    override fun calc(): Any {
        val primes = primes(10000)
        val primesCheck = primesCheckArray(10000)
        val primesChars = primes.map { it.toLastPermutation() }
        primes.forEachIndexed { index, p1 ->
            val p1s = primesChars[index]
            ((index + 1) until primes.size).forEach { p2i ->
                val p2 = primes[p2i]
                val p3 = 2 * p2 - p1
                if (p3 < 10000 && primesCheck[p3] && p1 != 1487) {
                    val p2s = primesChars[p2i]
                    if (p1s == p2s) {
                        val p3s = p3.toLastPermutation()
                        if (p2s == p3s) return "$p1$p2$p3".toLong()
                    }
                }
            }
        }
        return 0
    }

    val digits = IntArray(10)
    private fun Int.toLastPermutation(): Int {
        digits.fill(0)
        var i = this
        while (i != 0) {
            digits[i % 10]++
            i /= 10
        }
        (9 downTo 0).forEach { d -> (1..digits[d]).forEach { i = i * 10 + d } }
        return i
    }
}
