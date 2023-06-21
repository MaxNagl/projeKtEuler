package problems

import util.primes

fun main() = runProblem()

class Problem0010 : Problem(142913828922) {
    override fun calc(): Any {
        return primes(2000000).sumOf { it.toLong() }
    }
}