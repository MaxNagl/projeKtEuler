package problems.p00xx

import problems.Problem
import problems.runProblem
import util.plusDec
import util.toDecByteArray

fun main() = runProblem()

class Problem0057 : Problem(153) {
    override fun calc(): Any {
        var result = 0
        var a = 3.toDecByteArray()
        var b = 2.toDecByteArray()
        repeat(1000) {
            val c = a.plusDec(b)
            a = c.plusDec(b)
            b = c
            if (a.size > b.size) result++
        }
        return result
    }
}
