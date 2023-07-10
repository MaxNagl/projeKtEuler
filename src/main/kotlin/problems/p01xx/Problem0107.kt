package problems.p01xx

import problems.Problem
import problems.runProblem

fun main() = runProblem()

class Problem0107 : Problem(259679) {
    override fun calc(): Any {
        val data = getDataCsvLines { if (it == "-") null else it.toInt() }
        val added = mutableSetOf(0)
        var addedSum = 0
        while (added.size != data.size) {
            var best = Int.MAX_VALUE
            var bestTarget = 0
            data.indices.forEach { x ->
                if (added.contains(x)) {
                    data.indices.forEach { y ->
                        if (!added.contains(y)) {
                            val len = data[x][y]
                            if (len != null && len < best) {
                                best = len
                                bestTarget = y
                            }
                        }
                    }
                }
            }
            addedSum += best
            added.add(bestTarget)
        }
        return data.sumOf { it.filterNotNull().sum() } / 2 - addedSum
    }
}