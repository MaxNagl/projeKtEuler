package problems.p00xx

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import problems.Problem
import problems.runProblem
import util.indexesOfTrue
import util.primesSieve

fun main() = runProblem()

class Problem0051 : Problem(121313) {
    override fun calc(): Any {
        val primesSieve = primesSieve(1000000)
        val primes = primesSieve.indexesOfTrue()
        return runBlocking(Dispatchers.Default) {
            (1..62).map { mutation ->
                async { checkMutations(mutation, primes, primesSieve) }
            }.awaitAll().min()
        }
    }

    private fun checkMutations(
        mutation: Int,
        primes: IntArray,
        primesCheck: BooleanArray
    ): Int {
        val removes = ArrayList<Int>()
        var pot = 1
        var mut = mutation
        var filter = 0
        repeat(10) {
            if (mut and 1 == 1) removes.add(pot)
            mut /= 2
            pot *= 10
            filter += pot
        }
        val removeSum = removes.sum()
        val removeFirst = removes.removeFirst()
        primes.forEach p@{ p ->
            if (p > 200000) return Int.MAX_VALUE
            if (p < removeSum) return@p
            val digit = (p / removeFirst) % 10
            removes.forEach { r -> if ((p / r) % 10 != digit) return@p }
            var other = p - removeSum * digit
            var others = 0
            repeat(10) { d ->
                if (other >= removeSum && primesCheck[other]) others++
                other += removeSum
            }
            if (others == 8) return p
        }
        return Int.MAX_VALUE
    }
}
