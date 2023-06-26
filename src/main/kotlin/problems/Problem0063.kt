package problems

import kotlin.math.sqrt

fun main() = runProblem()

class Problem0063 : Problem(1322) {
    override fun calc(): Any {
        var result = 0
        val vals = HashMap<Long, Int>()
        (1..10000).forEach i@{ i ->
            vals.clear()
            val sqrt = sqrt(i.toFloat()).toInt()
            var b = sqrt
            var d = 1
            repeat(1000) {
                val c = i - b * b
                if (c == 0) return@i
                val o = (sqrt + b)
                val a = o * d / c
                b = sqrt - o % (c / d)
                d = c / d
                val key = a.toLong() * i * i + b * i + d
                val other = vals[key]
                if (other != null) {
                    if ((it - other) % 2 == 1) result++
                    return@i
                }
                vals[key] = it
            }
        }
        return result
    }
}

