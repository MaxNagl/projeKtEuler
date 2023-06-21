package problems

import util.isPrime
import util.primes

fun main() = runProblem()

class Problem0037 : Problem(748317) {
    override fun calc(): Any {
        val digits = listOf(1, 2, 3, 5, 7, 9)
        val primes = primes(10000)
        val result = HashSet<Int>()
        digits.forEach { start ->
            checkPrime(start, digits, primes, result)
        }
        result.removeIf {
            var t = it
            var r = 100000000
            while (r > 1) {
                t %= r
                if (!t.isPrime(primes)) return@removeIf true
                r /= 10
            }
            false
        }
        return result.sum()
    }

    private fun checkPrime(start: Int, digits: List<Int>, primes: IntArray, result: HashSet<Int>) {
        if (!start.isPrime(primes)) return
        if (start > 10) result.add(start)
        digits.forEach { d ->
            val r = start * 10 + d
            checkPrime(r, digits, primes, result)
        }
    }
}
