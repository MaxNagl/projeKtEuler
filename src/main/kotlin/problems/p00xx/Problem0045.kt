package problems.p00xx

import problems.Problem
import problems.runProblem
import util.isHexagonal
import util.isPentagonal

fun main() = runProblem()

class Problem0045 : Problem(1533776805) {
    override fun calc(): Any {
        (286L..1000000L).forEach { n ->
            val i = (n * n + n) / 2
            if (i.isHexagonal() && i.isPentagonal()) return i
        }
        return 0
    }
}
