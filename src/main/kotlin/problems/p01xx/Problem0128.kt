package problems.p01xx

import problems.Problem
import problems.runProblem
import util.primesSieve

fun main() = runProblem()

class Problem0128 : Problem(14516824220) {
    override fun calc(): Any {
        val sieve = primesSieve(1000000)
        var top = 2L
        var circle = 6
        var count = 0
        while (true) {
            if (sieve[circle - 1]) {
                if (sieve[circle + 1] && sieve[circle + circle + 5] && ++count == 2000) return top
                if (sieve[circle + 5] && sieve[circle + circle - 7] && ++count == 2000) return top + circle - 1
            }
            top += circle
            circle += 6
        }
    }
}