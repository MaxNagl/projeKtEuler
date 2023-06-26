package problems

import util.isPermutation
import util.primes

fun main() = runProblem()

class Problem0070 : Problem(8319823) {
    override fun calc(): Any {
        val primes = primes(5000)
        var bestRatio = 2f
        var best = 0
        ((primes.size - 1) downTo 0).forEach p1@{ p1i ->
            val p1 = primes[p1i]
            ((p1i + 1) until primes.size).forEach { p2i ->
                val p2 = primes[p2i]
                val n = p1 * p2
                if (n > 10000000) return@p1
                val count = 1 + n - (n / p1) - (n / p2)
                val ratio = n.toFloat() / count
                if (ratio < bestRatio && n.isPermutation(count)) {
                    bestRatio = ratio
                    best = n
                }
            }
        }
        return best
    }
}