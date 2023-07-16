package problems.p01xx

import problems.Problem
import problems.runProblem
import util.primes
import java.math.BigInteger
import kotlin.math.log

fun main() = runProblem()

class Problem0133 : Problem(453647705) {
    override fun calc(): Any {
        var sum = 10L // 2 + 3 + 5
        val test = BigInteger.TEN.pow(log(100000f, 2f).toInt())
        primes(100000).forEach { p ->
            if (p <= 5) return@forEach
            if (BigInteger.TEN.modPow(test, BigInteger.valueOf(p.toLong())) != BigInteger.ONE)
                sum += p
        }
        return sum
    }
}