package problems.p01xx

import problems.Problem
import problems.runProblem
import util.primes
import java.math.BigInteger
import java.util.Collections
import kotlin.math.absoluteValue

fun main() = runProblem()

class Problem0123 : Problem(21035) {
    override fun calc(): Any {
        val primes = primes(500000).map { BigInteger.valueOf(it.toLong()) }
        val max = BigInteger.valueOf(10000000000L)
        return Collections.binarySearch((0 until primes.size step 2).toMutableList(), 0) { n, m ->
            val p = primes[n]
            val mod = p * p
            val a = (p - BigInteger.ONE).modPow(BigInteger.valueOf(n + 1L), mod)
            val b = (p + BigInteger.ONE).modPow(BigInteger.valueOf(n + 1L), mod)
            ((a + b) % mod - max).signum()
        }.absoluteValue * 2 - 1
    }
}