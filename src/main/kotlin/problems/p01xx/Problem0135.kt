package problems.p01xx

import problems.Problem
import problems.runProblem
import kotlin.math.min

fun main() = runProblem()

class Problem0135 : Problem(4989) {
    override fun calc(): Any {
        val i = ByteArray(1000001)
        for (c in 2..i.size)
            for (r in (4 - c % 4)..min(c * 3 - 1, i.size / c) step 4) i[c * r]++
        return i.count { it == 10.toByte() }
    }
}
