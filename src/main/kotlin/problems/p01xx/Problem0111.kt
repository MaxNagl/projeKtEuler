package problems.p01xx

import problems.Problem
import problems.runProblem
import util.isPrime
import util.primes
import util.subsets
import java.math.BigInteger

fun main() = runProblem()

class Problem0111 : Problem(612407567715) {
    override fun calc(): Any {
        val primes = primes(100000)
        val tens = List(10) { BigInteger.TEN.pow(it).toLong() }
        return (0..9).sumOf { fixedDigit ->
            for (otherDigits in 1..10) {
                primeSum(10, fixedDigit, otherDigits, tens, primes).let {
                    if (it > 0) return@sumOf it
                }
            }
            0L
        }
    }

    @Suppress("SameParameterValue", "ConvertArgumentToSet")
    private fun primeSum(
        maxDigits: Int,
        fixedDigit: Int,
        otherDigits: Int,
        tens: List<Long>,
        primes: IntArray
    ): Long {
        var sum = 0L
        val min = tens[maxDigits - 1]
        tens.take(maxDigits).toTypedArray().subsets(maxDigits - otherDigits) { fixed ->
            val missing = tens - fixed
            val fixedSum = fixed.sum() * fixedDigit
            (0 until tens[otherDigits]).forEach { fill ->
                var f = fill
                var i = fixedSum
                missing.forEach {
                    i += f % 10 * it
                    f /= 10
                }
                if (i > min && i.isPrime(primes)) sum += i
            }
        }
        return sum
    }
}
