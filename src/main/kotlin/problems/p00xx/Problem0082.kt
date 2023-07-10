package problems.p00xx

import problems.Problem
import problems.runProblem
import kotlin.math.min

fun main() = runProblem()

class Problem0082 : Problem(260324) {
    override fun calc(): Any {
        val data = getDataLines("Problem0081") { it.split(",").map { i -> i.toInt() } }
        val path = data.map { it.toMutableList() }
        for (x in 1 until data.size) {
            for (y in 0 until data.size) path[y][x] += path[y][x - 1]
            for (y in 1 until path.size) path[y][x] = min(path[y][x], path[y - 1][x] + data[y][x])
            for (y in (path.size - 2) downTo 0) path[y][x] =
                min(path[y][x], path[y + 1][x] + data[y][x])
        }
        return path.minBy { it.last() }.last()
    }
}