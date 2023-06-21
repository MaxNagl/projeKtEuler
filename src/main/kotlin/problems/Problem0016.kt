package problems

import util.sumOfDigits
import java.math.BigInteger

fun main() = runProblem()

class Problem0016 : Problem(1366) {
    override fun calc(): Any {
        return BigInteger.TWO.pow(1000).toString().sumOfDigits()
    }
}