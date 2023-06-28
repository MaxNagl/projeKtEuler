package problems

import util.sumOfDigits
import java.math.BigDecimal
import java.math.MathContext

fun main() = runProblem()

class Problem0080 : Problem(40886) {
    override fun calc(): Any {
        var sum = 0
        for (i in 1..100) {
            val s = BigDecimal(i).sqrt(MathContext(102)).toString().replace(".", "").take(100)
            if (s.length == 100) sum += s.sumOfDigits()
        }
        return sum
    }
}