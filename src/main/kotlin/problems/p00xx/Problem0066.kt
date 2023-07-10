package problems.p00xx

import problems.Problem
import problems.runProblem
import java.math.BigInteger
import kotlin.math.sqrt

fun main() = runProblem()

class Problem0066 : Problem(661) {
    override fun calc(): Any {
        var max = BigInteger.ZERO
        var best = 0
        (2..1000).forEach i@{ i ->
            val dBig = BigInteger.valueOf(i.toLong())
            val sqrt = sqrt(i.toFloat()).toInt()
            if (sqrt * sqrt == i) return@i
            var b = sqrt
            var d = 1
            val nums = ArrayList<BigInteger>()
            nums.add(BigInteger.valueOf(b.toLong()))
            repeat(1000) {
                val c = i - b * b
                if (c == 0) return@i
                val o = (sqrt + b)
                nums.add(BigInteger.valueOf((o * d / c).toLong()))
                b = sqrt - o % (c / d)
                d = c / d

                var y = BigInteger.ONE
                var x = nums.last()
                ((nums.size - 2) downTo 0).forEach { i ->
                    val t = y + x * nums[i]
                    y = x
                    x = t
                }

                if (x * x - y * y * dBig == BigInteger.ONE) {
                    if (x > max) {
                        max = x
                        best = i
                    }
                    return@i
                }
            }
        }
        return best
    }
}

