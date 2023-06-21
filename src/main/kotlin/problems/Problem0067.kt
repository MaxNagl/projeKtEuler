package problems

import kotlin.math.max

fun main() = runProblem()

class Problem0067 : Problem(7273) {
    override fun calc(): Any {
        val data = getDataLines { it.split(" ").map { it.toInt() } }
        var sums = data.last()
        data.reversed().drop(1).forEach { line ->
            sums = line.mapIndexed { index, i ->
                max(i + sums[index], i + sums[index + 1])
            }
        }
        return sums.single()
    }
}