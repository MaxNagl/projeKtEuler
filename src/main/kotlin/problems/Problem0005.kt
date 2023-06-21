package problems

import util.primeFactors
import util.primes

fun main() = runProblem()

class Problem0005 : Problem(232792560) {
    override fun calc(): Any {
        val primes = primes(10)
        val allFactors = HashMap<Int, Int>()
        for (i in 2L..20L) {
            val factors = primeFactors(i, primes)
            factors.toSet().forEach { f ->
                val count = factors.count { it == f }
                if (allFactors.getOrDefault(f, 0) < count) allFactors[f] = count
            }
        }
        var multi = 1L
        allFactors.forEach { f -> repeat(f.value) { multi *= f.key } }
        return multi
    }
}