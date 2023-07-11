package problems.p00xx

import problems.Problem
import problems.runProblem
import kotlin.math.ln

fun main() = runProblem()

class Problem0099 : Problem(709) {
    override fun calc(): Any {
        val lines = getDataLines { it.split(",").map { i -> i.toInt() } }
        return lines.indexOf(lines.maxBy { ln(it[0].toDouble()) * it[1] }) + 1
    }
}
