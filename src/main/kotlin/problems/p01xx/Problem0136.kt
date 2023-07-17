package problems.p01xx

import problems.Problem
import problems.runProblem
import util.primesSieveMC

fun main() = runProblem()

class Problem0136 : Problem(2544559, hasMulticore = true) {
    override fun calc(): Any {
        val max = 50000000
        var count = 0
        primesSieveMC(max + 1, multicore) { primes, range ->
            var c = 0
            for (i in range) {
                if (primes[i]) {
                    if (i < max / 4) c++
                    if (i < max / 16) c++
                    if (i % 4 == 3) c++
                }
            }
            synchronized(this) { count += c }
        }
        return count
    }
}
