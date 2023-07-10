package problems.p00xx

import problems.Problem
import problems.runProblem
import util.sumOfDigits
import java.math.BigInteger

fun main() = runProblem()

class Problem0020 : Problem(648) {
    override fun calc(): Any {
        var num = BigInteger.ONE
        for (i in 2L..100L) num *= BigInteger.valueOf(i)
        return num.toString().sumOfDigits()
    }
}