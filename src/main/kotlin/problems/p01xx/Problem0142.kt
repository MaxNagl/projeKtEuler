package problems.p01xx

import problems.Problem
import problems.runProblem
import util.FastSquare

fun main() = runProblem()

class Problem0142 : Problem(1006193) {
    override fun calc(): Any {
        val fs = FastSquare()
        for (sy in 1..Int.MAX_VALUE) {
            val dy = sy * sy
            for (sz in 1 until sy) {
                val dz = sz * sz
                if (fs.isSquare(dy + dz)) {
                    for (sx in sy..(sy * 2)) {
                        val t = sx * sx - dy
                        if (t % 2 == 1) continue
                        val x = t / 2
                        val y = x + dy
                        val z = y + dz
                        if (fs.isSquare(y + z) && fs.isSquare(x + z)) return x + y + z
                    }
                }
            }
        }
        return 0
    }
}
