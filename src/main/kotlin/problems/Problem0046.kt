package problems

import util.primesCheckArray

fun main() = runProblem()

class Problem0046 : Problem(5777) {
    override fun calc(): Any {
        val primes = primesCheckArray(10000)
        (9..10000 step 2).forEach { i ->
            if (!primes[i]) {
                var a = 1
                while (a * a < i) {
                    val p = i - 2 * a * a
                    if (p < 1) return i else if (primes[p]) return@forEach
                    a++
                }
            }
        }
        return 0
    }
}
