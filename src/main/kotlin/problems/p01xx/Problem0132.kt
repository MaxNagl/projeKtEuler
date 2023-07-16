package problems.p01xx

import problems.Problem
import problems.runProblem
import util.primes
import java.math.BigInteger

fun main() = runProblem()

class Problem0132 : Problem(843296) {
    override fun calc(): Any {
        var count = 0
        var sum = 0
        val bil = BigInteger.TEN.pow(9)
        primes(200000).forEach { p ->
            if (p <= 5) return@forEach
            if (BigInteger.TEN.modPow(bil, BigInteger.valueOf(p.toLong())) == BigInteger.ONE) {
                sum += p
                if (++count == 40) return sum
            }
        }
        return 0
    }

    private fun alternative(): Any {
        var count = 0
        var sum = 0
        primes(200000).forEach { p ->
            if (p == 2 || p == 5) return@forEach
            var a = 2
            var t = 11 % p
            while (t != 0) {
                t = (t * 100 + 11) % p
                a += 2
            }
            if (1000000000 % a == 0) {
                sum += p
                if (++count == 40) return sum
            }
        }
        return 0
    }
}