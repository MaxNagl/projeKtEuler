package problems.p00xx

import problems.Problem
import problems.runProblem
import java.util.*

fun main() = runProblem()

class Problem0083 : Problem(425185) {
    override fun calc(): Any {
        val data = getDataLines("Problem0081") { it.split(",").map { i -> i.toInt() } }
        val path = data.map { MutableList(it.size) { Int.MAX_VALUE } }
        val stack = Stack<Pair<Int, Int>>()
        stack.add(0 to 0)
        path[0][0] = data[0][0]
        val directions = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)
        while (!stack.isEmpty()) {
            val pos = stack.removeFirst()
            directions.forEach { dir ->
                val dx = pos.first + dir.first
                val dy = pos.second + dir.second
                val p = path[pos.first][pos.second]
                if (dx in path.indices && dy in path.indices) {
                    val w = p + data[dx][dy]
                    if (path[dx][dy] > w) {
                        path[dx][dy] = w
                        stack.add(dx to dy)
                    }
                }
            }
        }
        return path.last().last()
    }
}