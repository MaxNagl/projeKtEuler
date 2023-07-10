package problems.p00xx

import problems.Problem
import problems.runProblem

fun main() = runProblem()

class Problem0038 : Problem(932718654) {
    override fun calc(): Any {
        val digits = Array(9) { '1' + it }
        val sb = StringBuilder()
        var best = 0
        (1..9999).forEach { i ->
            sb.clear()
            var m = 1
            while (sb.length < 9) sb.append(i * m++)
            val s = sb.toString()
            if (s.length == 9 && digits.all { s.contains(it) }) {
                val num = s.toInt()
                if (num > best) best = num
            }
        }
        return best
    }
}
