package problems

import util.primes

fun main() = runProblem()

class Problem0077 : Problem(71) {
    override fun calc(): Any {
        val primes = primes(100).reversed().toIntArray()
        for (i in 1..100) if (coinCount(i, primes, 0, HashMap()) > 5000) return i
        return 0
    }

    private fun coinCount(
        amount: Int,
        coins: IntArray,
        pos: Int,
        cache: MutableMap<Pair<Int, Int>, Int>
    ): Int {
        if (pos == coins.size) return if (amount == 0) 1 else 0
        val cacheKey = amount to pos
        cache[cacheKey]?.let { return it }
        val coin = coins[pos]
        return (0..amount / coin).sumOf {
            coinCount(amount - it * coin, coins, pos + 1, cache)
        }.also { cache[cacheKey] = it }
    }
}
