package problems.p00xx

import problems.Problem
import problems.runProblem
import java.math.BigInteger

fun main() = runProblem()

class Problem0053 : Problem(4075) {
    override fun calc(): Any {
        var count = 0
        var f = BigInteger.ONE
        val million = BigInteger.valueOf(1000000L)
        val facs = List(101) { i ->
            f.also { f *= BigInteger.valueOf(i + 1L) }
        }
        (1..100).forEach { n ->
            (0..n).forEach { r ->
                val c = facs[n] / facs[r] / facs[n - r]
                if (c > million) count++
            }
        }
        return count
    }
}
