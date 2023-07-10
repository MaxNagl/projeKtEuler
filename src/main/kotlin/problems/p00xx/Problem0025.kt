package problems.p00xx

import problems.Problem
import problems.runProblem
import java.math.BigInteger

fun main() = runProblem()

class Problem0025 : Problem(4782) {
    override fun calc(): Any {
        val max = BigInteger("9".repeat(999))
        var a = BigInteger.ONE
        var b = BigInteger.ONE
        var count = 3
        while (true) {
            val next = a + b
            if (next > max) return count
            if (a < b) a = next else b = next
            count++
        }
    }
}

