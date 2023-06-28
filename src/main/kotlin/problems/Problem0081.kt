package problems

import kotlin.math.min

fun main() = runProblem()

class Problem0081 : Problem(427337) {
    override fun calc(): Any {
        val data = getDataLines { it.split(",").map { i -> i.toInt() }.toMutableList() }
        for (x in 1 until data.size) data[x][0] += data[x - 1][0]
        for (x in 1 until data.size) data[0][x] += data[0][x - 1]
        for (x in 1 until data.size)
            for (y in 1 until data.size) data[x][y] += min(data[x - 1][y], data[x][y - 1])
        return data.last().last()
    }
}