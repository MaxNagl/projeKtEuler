package problems.p00xx

import problems.Problem
import problems.runProblem

fun main() = runProblem()

class Problem0011 : Problem(70600674) {
    override fun calc(): Any {
        val d = getData { txt ->
            txt.replace("\n", " ").split(" ").map { it.toInt() }.chunked(20)
        }
        val products = ArrayList<Int>()
        for (x in 0..20 - 4) {
            for (y in 0..20 - 4) {
                products.addAll(
                    listOf(
                        d[x][y] * d[x + 1][y] * d[x + 2][y] * d[x + 3][y],
                        d[x][y] * d[x][y + 1] * d[x][y + 2] * d[x][y + 3],
                        d[x][y] * d[x + 1][y + 1] * d[x + 2][y + 2] * d[x + 3][y + 3],
                        d[19 - x][y] * d[19 - x - 1][y + 1] * d[19 - x - 2][y + 2] * d[19 - x - 3][y + 3]
                    )
                )
            }
        }
        return products.max()
    }
}