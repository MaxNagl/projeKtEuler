package problems.p00xx

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import problems.Problem
import problems.runProblem
import util.eulerPhi
import util.primes

fun main() = runProblem()

class Problem0072 : Problem(303963552391) {
    override fun calc(): Any {
        val primes = primes(1000)
        return runBlocking(Dispatchers.Default) {
            (2..1000000).chunked(1000).map { chunked ->
                async { chunked.sumOf { i -> i.eulerPhi(primes) }.toLong() }
            }.awaitAll()
        }.sum()
    }
}
