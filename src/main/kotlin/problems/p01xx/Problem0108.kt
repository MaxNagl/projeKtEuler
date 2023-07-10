package problems.p01xx

import problems.Problem
import problems.runProblem
import util.big2
import util.big3
import util.primes
import java.math.BigInteger

fun main() = runProblem()

class Problem0108 : Problem(180180) {

    override fun calc(): Any {
        val primes = primes(100).map { BigInteger.valueOf(it.toLong()) }.toTypedArray()
        val best = arrayOf<BigInteger?>(null)

        optimize(target = BigInteger.valueOf(2001), primes = primes, best = best)

        return best[0] ?: ""
    }

    fun optimize(
        count: BigInteger = BigInteger.ONE,
        target: BigInteger,
        n: BigInteger = BigInteger.ONE,
        primes: Array<BigInteger>,
        primesPos: Int = 0,
        best: Array<BigInteger?>
    ) {
        if (count >= target) {
            best[0] = n
            return
        }
        var p = primes[primesPos]
        var i = big3
        while (best[0] == null || n * p < best[0]!!) {
            optimize(count * i, target, n * p, primes, primesPos + 1, best)
            i += big2
            p *= primes[primesPos]
        }
    }
}
