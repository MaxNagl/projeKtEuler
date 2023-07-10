package problems.p00xx

import problems.Problem
import problems.runProblem
import util.isPalindrome

fun main() = runProblem()

class Problem0004 : Problem(906609) {
    override fun calc(): Any {
        var max = 0
        for (a in 100..999)
            for (b in 100..999)
                if (a * b > max && (a * b).toString().isPalindrome()) max = a * b
        return max
    }
}