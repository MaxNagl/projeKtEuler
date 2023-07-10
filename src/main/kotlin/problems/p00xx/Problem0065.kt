package problems.p00xx

import problems.Problem
import problems.runProblem
import java.math.BigInteger

fun main() = runProblem()

class Problem0065 : Problem(272) {
    override fun calc(): Any {
        fun x(i: Int) = when {
            i == 1 -> BigInteger.valueOf(2L)
            i % 3 == 0 -> BigInteger.valueOf(2 * i / 3L)
            else -> BigInteger.ONE
        }

        val n = 100
        var b = x(n)
        var a = BigInteger.ONE
        ((n - 1) downTo 1).forEach { i ->
            val c = x(i) * a + b
            b = a
            a = c
        }

        return a.toString().toCharArray().sumOf { it.code - '0'.code }
    }
}

