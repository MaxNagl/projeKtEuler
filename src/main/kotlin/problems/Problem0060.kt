package problems

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import util.isPrime
import util.primes
import kotlin.math.pow

fun main() = runProblem()

class Problem0060 : Problem(26033, 10) {
    override fun calc(): Any {
        val check = primes(10000)
        val primes = check.map { it to 10f.pow(it.toString().length.toFloat()).toInt() }
        var candidates = primes.map { arrayOf(it) }
        runBlocking(Dispatchers.Default) {
            repeat(4) {
                candidates = candidates.chunked(10).map { part ->
                    async {
                        val cache = HashMap<Int, Boolean>()
                        val newCandidates = ArrayList<Array<Pair<Int, Int>>>()
                        part.forEach { cs ->
                            primes.forEach p@{ p ->
                                if (cs.any { it.first >= p.first }) return@p
                                cs.forEach { c ->
                                    val key = p.first * c.first
                                    if (cache.getOrPut(key) {
                                            !(p.first * c.second + c.first).isPrime(check) ||
                                                    !(c.first * p.second + p.first).isPrime(check)
                                        }) return@p
                                }
                                newCandidates.add(cs + p)
                            }
                        }
                        newCandidates
                    }
                }.awaitAll().flatten()
            }
        }
        candidates.forEach { cs -> println(cs.map { it.first }) }
        return candidates.minOf { cs -> cs.sumOf { it.first } }
    }
}

