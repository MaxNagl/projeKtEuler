package problems.p00xx

import problems.Problem
import problems.runProblem
import java.math.BigInteger

fun main() = runProblem()

class Problem0097 : Problem(8739992577) {
    override fun calc(): Any {
        var n = BigInteger.valueOf(28433L)
        var pot = BigInteger.valueOf(2L)
        val mod = BigInteger.valueOf(10000000000L)
        var exp = 7830457
        while (exp > 0) {
            if (exp % 2 == 1) n = (n * pot) % mod
            pot = (pot * pot) % mod
            exp /= 2
        }
        return n.toLong() + 1
    }
}
