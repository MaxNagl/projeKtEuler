package problems.p00xx

import problems.Problem
import problems.runProblem

fun main() = runProblem()

class Problem0031 : Problem(73682) {
    override fun calc(): Any {
        return coinCount(200, intArrayOf(200, 100, 50, 20, 10, 5, 2, 1), 0)
    }

    private fun coinCount(amount: Int, coins: IntArray, pos: Int): Int {
        if (pos == coins.size - 1) return 1
        val coin = coins[pos]
        return (0..amount / coin).sumOf {
            coinCount(amount - it * coin, coins, pos + 1)
        }
    }
}

