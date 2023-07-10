package problems.p00xx

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import problems.Problem
import problems.runProblem
import util.ggt

fun main() = runProblem()

class Problem0073 : Problem(7295372) {
    override fun calc(): Any {
        return runBlocking(Dispatchers.Default) {
            (3..12000).chunked(100).map { chunked ->
                async {
                    var count = 0
                    chunked.forEach { d ->
                        val min = d / 3 + 1
                        val max = d / 2
                        (min..max).forEach { n ->
                            if (ggt(d, n) == 1) count++
                        }
                    }
                    count
                }
            }.awaitAll().sumOf { it }
        }
    }
}
