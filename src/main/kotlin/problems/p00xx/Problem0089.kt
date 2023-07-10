package problems.p00xx

import problems.Problem
import problems.runProblem
import util.parseRoman
import util.toRomanString

fun main() = runProblem()

class Problem0089 : Problem(743) {
    override fun calc(): Any {
        return getDataLines { it }.sumOf { it.length - it.parseRoman()!!.toRomanString().length }
    }

}