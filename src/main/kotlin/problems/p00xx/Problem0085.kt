package problems.p00xx

import problems.Problem
import problems.runProblem
import kotlin.math.absoluteValue

fun main() = runProblem()

class Problem0085 : Problem(2772) {
    override fun calc(): Any {
        var best = 0 to Int.MAX_VALUE
        var (x, sx) = 1 to 1
        while (sx < 2000000) {
            var (y, sy) = x to sx
            while (sx * sy < 2000000) {
                sy += ++y
                val dst = (2000000 - sx * sy).absoluteValue
                if (dst < best.second) best = x * y to dst
            }
            sx += ++x
        }
        return best.first
    }
}