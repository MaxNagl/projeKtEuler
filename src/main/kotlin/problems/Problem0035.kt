package problems

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import util.isPrime
import util.primes
import util.rotate
import java.util.concurrent.atomic.AtomicInteger

fun main() = runProblem()

class Problem0035 : Problem(55) {
    val plusFirst1379 = arrayOf(1, 2, 1, 4, 3, 2, 1, 2, 1, 2)
    val plus1379 = arrayOf(1, 0, 1, 0, 3, 2, 1, 0, 1, 0)

    fun Int.nextNumWith1379(): Int {
        var i = this + plusFirst1379[this % 10]
        var pos = 10
        while (i > pos) {
            val j = (i / pos) % 10
            i += plus1379[j] * pos
            pos *= 10
        }
        return i
    }

    override fun calc(): Any {
        return calcOnly1379()
    }

    fun calcOnly1379(): Int {
        var result = 4 // include 2, 3, 5, 7
        var i = 11
        var max = 1
        val primes = primes(1000)
        while (i < 1000000) {
            while (max * max > i) max++
            if (i.isPrime(primes)) {
                var j = i.rotate()
                var isCircular = true
                while (j != i) {
                    if (!j.isPrime(primes)) {
                        isCircular = false
                        break
                    }
                    j = j.rotate()
                }
                if (isCircular) result++
            }
            i = i.nextNumWith1379()
        }
        return result
    }

    fun calcAll(): Int {
        var result = AtomicInteger()
        val primes = primes(1000000)
        runBlocking(Dispatchers.Default) {
            (0..primes.size / 1000).map { chunk ->
                async {
                    val start = chunk * 1000
                    val range = start..(start + 999).coerceAtMost(primes.size - 1)
                    range.forEach { index ->
                        val p = primes[index]
                        var i = p.rotate()
                        while (i != p) {
                            if (primes.binarySearch(i) < 0) return@forEach
                            i = i.rotate()
                        }
                        result.incrementAndGet()
                    }
                }
            }.awaitAll()
        }
        return result.get()
    }

}
