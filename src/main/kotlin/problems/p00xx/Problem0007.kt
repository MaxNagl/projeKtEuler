package problems.p00xx

import problems.Problem
import problems.runProblem
import util.primes

fun main() = runProblem()

class Problem0007 : Problem(104743) {
    override fun calc(): Any {
        return primes(1000000)[10000]
    }
}