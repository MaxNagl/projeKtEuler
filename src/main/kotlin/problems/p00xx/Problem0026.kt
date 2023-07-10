package problems.p00xx

import problems.Problem
import problems.runProblem

fun main() = runProblem()

class Problem0026 : Problem(983) {
    override fun calc(): Any {
        var best = 0 to 0
        (999 downTo 1).forEach { i ->
            if (best.second >= i) return best.first
            var rest = 10 % i
            val rests = ArrayList<Int>()
            while (!rests.contains(rest)) {
                rests.add(rest)
                rest = (rest * 10) % i
            }
            val len = rests.size - rests.indexOf(rest)
            if (len > best.second) best = i to len
        }
        return best.first
    }
}

