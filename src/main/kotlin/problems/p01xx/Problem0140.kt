package problems.p01xx

import problems.Problem
import problems.runProblem
import util.big2
import util.big5
import java.math.BigInteger

fun main() = runProblem()

class Problem0140 : Problem(5673835352990) {
    override fun calc(): Any {
        val big14 = BigInteger.valueOf(14L)
        var lastLast = big2
        var last = big5
        var i = BigInteger.valueOf(25)
        var count = 2
        var sum = big2 + big5
        while (true) {
            val x = big5 * i * i + big14 * i + BigInteger.ONE
            if (x.sqrtAndRemainder()[1] == BigInteger.ZERO) {
                sum += i
                if (++count == 30) return sum
                val next = i * last / lastLast
                lastLast = last
                last = i
                i = next
            }
            i -= BigInteger.ONE
        }
    }
}