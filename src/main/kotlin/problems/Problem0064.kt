package problems

import kotlin.math.sqrt

fun main() = runProblem()

class Problem0064 : Problem(1322) {
    override fun calc(): Any {
        var count = 0
        (1..10000).forEach x@{ x ->
            val sqrt = sqrt(x.toFloat()).toInt()
            var a = sqrt
            var b = 1
            var c = sqrt

            val r = HashMap<Long, Int>()

            for (i in 1..10000) {
                val d = x - c * c
                if (d == 0) return@x
                val f = d / b
                val g = b * (sqrt + c) * f / d
                val e = sqrt - (g % f)
                val key = a.toLong() * x * x + e * x + f
                val last = r[key]
                if (last != null) {
                    if ((i - last) % 2 == 1) count++
                    return@x
                }
                r[key] = i
                a = g / f
                b = f
                c = e
            }
        }

        return count
    }
}

