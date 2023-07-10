package problems.p00xx

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import problems.Problem
import problems.runProblem
import util.divisorsProperSum

fun main() = runProblem()

class Problem0023 : Problem(4179871) {
    override fun calc(): Any {
        val candidates = BooleanArray(28123)
        val abundants = ArrayList<Int>()
        (1..28123).forEach i@{ i -> if (divisorsProperSum(i) > i) abundants.add(i) }
        runBlocking(Dispatchers.Default) {
            abundants.chunked(100).mapIndexed { index, list ->
                async {
                    list.forEachIndexed i@{ index2, i ->
                        (0..index * 100 + index2).forEach {
                            val c = i + abundants[it]
                            if (c < candidates.size) candidates[c] = true else return@i
                        }
                    }
                }
            }.awaitAll()
        }
        var sum = 0
        candidates.forEachIndexed { index, b -> if (!b) sum += index }
        return sum
    }
}