package problems.p01xx

import problems.Problem
import problems.runProblem
import util.primeFactors
import util.primesSieve

fun main() = runProblem()

class Problem0130 : Problem() {
    override fun calc(): Any {
        val primes = primesSieve(100000)
        var n = 2
        var count = 0
        var sum = 0
        while (true) {
            n++
            if (n % 2 == 0 || n % 5 == 0) continue
            if (!primes[n]) {
                var a = 1
                var t = 1 % n
                while (t != 0) {
                    t = (t * 10 + 1) % n
                    a++
                }
                if ((n - 1) % a == 0) {
                    sum += n
                    if (++count == 25) return sum
                }
            }
        }
    }
}