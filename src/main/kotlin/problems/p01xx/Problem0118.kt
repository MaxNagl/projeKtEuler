package problems.p01xx

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import problems.Problem
import problems.runProblem
import util.*

fun main() = runProblem()

class Problem0118 : Problem(44680, hasMulticore = true) {
    override fun calc(): Any {
        val primes = primesSieve(if (multicore) 10000 else 1000000)
        val primeCache = primes(10000)
        if (multicore) {
            return runBlocking(Dispatchers.Default) {
                (0 until 9).map { a ->
                    (0 until 8).map { b ->
                        async {
                            var count = 0
                            val array = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
                            array.swap(0, a)
                            array.swap(1, 1 + b)
                            array.permutations(2) {
                                val l = it.last()
                                if (l % 2 != 0 && l != 5)
                                    count += countPrimes(it, 0, 0, primeCache, primes)
                            }
                            count
                        }
                    }
                }.flatten().awaitAll().sum()
            }
        } else {
            var count = 0
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9).permutations(0) {
                val l = it.last()
                if (l % 2 != 0 && l != 5) count += countPrimes(it, 0, 0, primeCache, primes)
            }
            return count
        }
    }

    private fun countPrimes(
        array: IntArray,
        start: Int,
        last: Int,
        primeCache: IntArray,
        primes: BooleanArray
    ): Int {
        if (start == array.size) return 1
        var count = 0
        var n = 0
        for (i in start until array.size) {
            n = n * 10 + array[i]
            if (n > last && (last != 0 && i == array.size - 1 || n < 9999)) {
                val isPrime = if (n < primes.size) primes[n] else n.isPrime(primeCache)
                if (isPrime) count += countPrimes(array, i + 1, n, primeCache, primes)
            }
        }
        return count
    }
}