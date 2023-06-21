package problems

import util.primes

fun main() = runProblem()

class Problem0047 : Problem(134043) {
    override fun calc(): Any {
        val max = 200000
        val primes = primes(1000)
        var consecutive = 0
        val factors = IntArray(max + 1)
        primes.forEach { p ->
            var i = p
            while (i < factors.size) {
                factors[i]++
                i += p
            }
        }
        (2..max).forEach { i ->
            if (factors[i] == 4) {
                if (++consecutive == 4) return i - 3
            } else consecutive = 0
        }
        return 0
    }
}
