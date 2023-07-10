package problems.p00xx

import problems.Problem
import problems.runProblem
import util.simplifyFraction

fun main() = runProblem()

class Problem0033 : Problem(100) {
    override fun calc(): Any {
        var result = 1 to 1
        (1..9).forEach { a ->
            (0..9).forEach { b ->
                val e = a * 10 + b
                (1..9).forEach { c ->
                    (0..9).forEach { d ->
                        val f = e to c * 10 + d
                        if (f.first < f.second) {
                            val simple = f.simplifyFraction()
                            if (a == d && (b to c).simplifyFraction() == simple) {
                                result = result.first * f.first to result.second * f.second
                            }
                            if (b == c && (a to d).simplifyFraction() == simple) {
                                result = result.first * f.first to result.second * f.second
                            }
                        }
                    }
                }
            }
        }
        return result.simplifyFraction().second
    }

}
