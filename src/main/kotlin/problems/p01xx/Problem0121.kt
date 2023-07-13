package problems.p01xx

import problems.Problem
import problems.runProblem
import kotlin.random.Random

fun main() = runProblem()

class Problem0121 : Problem(2269) {
    override fun calc(): Any {
        var chances = DoubleArray(15)
        chances[0] = 1.0
        repeat(chances.size) {
            val f = 1.0 / (it + 2)
            chances = DoubleArray(chances.size) {i ->
                chances[i] * (1 - f) + (chances.getOrNull(i - 1) ?: 0.0) * f
            }
        }
        return (1 / ((chances.size + 1) / 2 until chances.size).sumOf { chances[it] }).toInt()
    }
}