package problems.p01xx

import problems.Problem
import problems.runProblem

fun main() = runProblem()

class Problem0120 : Problem(333082500) {
    override fun calc(): Any {
        return (3..1000).sumOf { a ->
            val m = a * a
            val b = a - 1
            val c = a + 1
            var x = b * b
            var y = c * c
            var max = 0
            while(x != b || y != c) {
                x = (x * b) % m
                y = (y * c) % m
                max = ((x + y) % m).coerceAtLeast(max)
            }
            max
        }
    }
}