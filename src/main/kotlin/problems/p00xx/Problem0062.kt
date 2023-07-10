package problems.p00xx

import problems.Problem
import problems.runProblem
import java.math.BigInteger

fun main() = runProblem()

class Problem0062 : Problem(127035954683) {
    override fun calc(): Any {
        val map = HashMap<String, Pair<Int, Long>>()
        (0L..10000L).forEach { i ->
            val cube = i * i * i
            val digits = String(cube.toString().toCharArray().sortedArray())
            val count = map[digits] ?: (0 to i)
            if (count.first == 4) return BigInteger.valueOf(count.second).pow(3)
            map[digits] = (count.first + 1) to count.second
        }
        return 0
    }
}

