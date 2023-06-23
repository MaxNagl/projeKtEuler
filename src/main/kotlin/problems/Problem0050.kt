package problems

import util.isPrime
import util.primes

fun main() = runProblem()

class Problem0050 : Problem(997651) {
    override fun calc(): Any {
        val primes = primes(10000)
        var bestLen = 0
        var best = 0
        primes.forEachIndexed { startI, start ->
            var sum = start
            var len = 1
            ((startI + 1) until primes.size).forEach { i ->
                sum += primes[i]
                if (sum > 1000000) return@forEachIndexed
                len++
                if (len > bestLen) {
                    if (sum.isPrime(primes)) {
                        bestLen = len
                        best = sum
                    }
                }
            }
        }
        return best
    }
}
