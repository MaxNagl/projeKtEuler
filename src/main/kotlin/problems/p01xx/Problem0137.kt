package problems.p01xx

import problems.Problem
import problems.runProblem
import util.big2
import util.big5
import java.math.BigInteger
import kotlin.math.sqrt

fun main() = runProblem()

class Problem0137 : Problem(1120149658760) {
    override fun calc(): Any {
        var last = big2
        var i = BigInteger.valueOf(100)
        var count = 1
        while (true) {
            val x = big5 * i * i + big2 * i + BigInteger.ONE
            if (x.sqrtAndRemainder()[1] == BigInteger.ZERO) {
                if (++count == 15) return i
                val next = i * i / last
                last = i
                i = next
            }
            i -= BigInteger.ONE
        }
    }
}
