package problems.p00xx

import problems.Problem
import problems.runProblem

fun main() = runProblem()

class Problem0076 : Problem(190569291) {
    override fun calc(): Any {
        return coinCount(100, IntArray(99) { 99 - it }, 0, HashMap())
    }

    private fun coinCount(
        amount: Int,
        coins: IntArray,
        pos: Int,
        cache: MutableMap<Pair<Int, Int>, Int>
    ): Int {
        if (pos == coins.size - 1) return 1
        val cacheKey = amount to pos
        cache[cacheKey]?.let { return it }
        val coin = coins[pos]
        return (0..amount / coin).sumOf {
            coinCount(amount - it * coin, coins, pos + 1, cache)
        }.also { cache[cacheKey] = it }
    }
}
