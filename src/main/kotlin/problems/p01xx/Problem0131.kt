package problems.p01xx

import problems.Problem
import problems.runProblem
import util.isPrime
import util.primeFactors
import util.primes
import util.primesSieve
import java.math.BigInteger

fun main() = runProblem()

class Problem0131 : Problem(173) {
    override fun calc(): Any {
        val primes = primes(1000)
        var count = 0
        repeat(1000000) {
            val b = BigInteger.valueOf(it.toLong() + 1)
            val a = b * b * b
            val r = b * b * (b + BigInteger.ONE)
            val p = ((r * r * r - a * a * a) / (a * a)).toInt()
            if (p > 1000000) return count
            if (p.isPrime(primes)) count++
        }
        return 0
    }
}