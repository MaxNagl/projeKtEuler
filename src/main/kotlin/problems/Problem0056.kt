package problems

import java.math.BigInteger

fun main() = runProblem()

class Problem0056 : Problem(null) {
    override fun calc(): Any {
        var best = 0
        (1..99).forEach { i ->
            val a = BigInteger.valueOf(i.toLong())
            var b = BigInteger.ONE
            (1..99).forEach { j ->
                b *= a
                val sum = b.toString().sumOf { it.code - '0'.code }
                if (sum > best) best = sum
            }
        }
        return best
    }
}
