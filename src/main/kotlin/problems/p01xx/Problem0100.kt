package problems.p01xx

import problems.Problem
import problems.runProblem
import java.math.BigInteger

fun main() = runProblem()

class Problem0100 : Problem(756872327473) {
    override fun calc(): Any {
        var last = BigInteger.ONE
        var a = BigInteger.ONE
        val two = BigInteger.valueOf(2L)
        val max = (BigInteger.TEN.pow(12 + 12) / two).sqrt()
        while (true) {
            val b = (a * a * two).sqrt()
            val n = a * (a - BigInteger.ONE)
            val d = b * (b - BigInteger.ONE)
            if (two * n == d) {
                if (a > max) return a
                val next = a * a / last
                last = a
                a = next
            }
            a++
        }
    }
}