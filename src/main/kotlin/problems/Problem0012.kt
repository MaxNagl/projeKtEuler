package problems

import util.primeFactorForeach
import util.primes

fun main() = runProblem()

class Problem0012 : Problem(76576500) {
    override fun calc(): Any {
        var t = 0
        val primes = primes(100000)
        for (i in 1..17000) {
            t += i
            var x = 1
            primeFactorForeach(t.toLong(), primes) { _, v ->
                x *= v + 1
            }
            if (x >= 500) return t
        }
        return 0
    }
}