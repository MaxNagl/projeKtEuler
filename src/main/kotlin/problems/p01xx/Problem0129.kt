package problems.p01xx

import problems.Problem
import problems.runProblem
import util.primeFactors
import util.primes
import java.math.BigInteger

fun main() = runProblem()

class Problem0129 : Problem() {
    override fun calc(): Any {
        var n = 1000000
        while (true) {
            n++
            if (n % 2 == 0 || n % 5 == 0) continue
            var count = 1
            var t = 1 % n
            while (t != 0) {
                if (count++ > 1000000) return n
                t = (t * 10 + 1) % n
            }
            println("$n $count")
        }
    }
}