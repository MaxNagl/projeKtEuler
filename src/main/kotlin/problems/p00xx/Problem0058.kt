package problems.p00xx

import problems.Problem
import problems.runProblem
import util.isPrime
import util.primes

fun main() = runProblem()

class Problem0058 : Problem(26241) {
    override fun calc(): Any {
        val primes = primes(100000)
        var i = 1
        var plus = 2
        var count = 0
        (3..1000000 step 2).forEach { sideLength ->
            repeat(4) {
                i += plus
                if (i.isPrime(primes)) count++
            }
            val total = plus * 2 + 1
            val percent = count * 100 / total
            if (percent < 10) return sideLength
            plus += 2
        }
        return count
    }
}
