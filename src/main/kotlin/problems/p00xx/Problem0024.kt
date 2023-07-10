package problems.p00xx

import problems.Problem
import problems.runProblem
import util.faculty

fun main() = runProblem()

class Problem0024 : Problem(2783915460) {
    override fun calc(): Any {
        val pos = 1000000 - 1
        val digits = MutableList(10) { it }
        var result = 0L
        (9 downTo 0).forEach {
            val f = it.faculty().toInt()
            val v = (pos / f) % digits.size
            result = result * 10 + digits[v]
            digits.removeAt(v)
        }
        return result
    }
}

