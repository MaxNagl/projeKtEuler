package problems

import util.primes

fun main() = runProblem()

class Problem0069 : Problem(510510) {
    override fun calc(): Any {
        var r = 1
        primes(100).forEach {
            if (r * it > 1000000) return r
            r *= it
        }
        return 0
    }
}

