package problems.p01xx

import problems.Problem
import problems.runProblem
import java.util.*

fun main() = runProblem()

class Problem0122 : Problem(1582) {
    override fun calc(): Any {
        val result = IntArray(201)
        Arrays.fill(result, 2, result.size, 100000)
        repeat(100) {
            val values = IntArray(it + 7).apply { set(0, 1) }
            calc(values, 1, result)
            if (!result.contains(100000)) return result.drop(2).sum()
        }
        return 0
    }

    fun calc(values: IntArray, size: Int, result: IntArray) {
        if (size == values.size) return
        val last = values[size - 1]
        for (x in (size - 1) downTo 0) {
            val vx = values[x]
            for (y in (size - 1) downTo x) {
                val v = vx + values[y]
                if (v > last && v < result.size && result[v] >= size) {
                    result[v] = size
                    values[size] = v
                    calc(values, size + 1, result)
                }
            }
        }
    }
}