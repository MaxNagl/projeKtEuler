package problems.p01xx

import problems.Problem
import problems.runProblem
import kotlin.math.sign

fun main() = runProblem()

class Problem0102 : Problem(228) {
    override fun calc(): Any {
        return getDataLines {
            val a = it.split(",").map { s -> s.toInt() }
            listOf(a[0] to a[1], a[2] to a[3], a[4] to a[5])
        }.count { t ->
            val z1 = zero(t[0], t[1])
            val z2 = zero(t[1], t[2])
            val z3 = zero(t[2], t[0])
            z1.sign != z2.sign && z2.sign != z3.sign && z1.sign != z3.sign
        }
    }

    fun zero(a: Pair<Int, Int>, b: Pair<Int, Int>): Int {
        if (a.first.sign == b.first.sign) return 0
        return a.second + (b.second - a.second) * (a.first) / (a.first - b.first)
    }
}