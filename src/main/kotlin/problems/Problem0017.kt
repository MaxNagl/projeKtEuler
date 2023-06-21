package problems

import util.numberToWord
import util.sumOfDigits
import java.math.BigInteger

fun main() = runProblem()

class Problem0017 : Problem(21124) {
    override fun calc(): Any {
        return (1..1000).sumOf { it.toString().numberToWord().length }
    }
}