package problems.p00xx

import problems.Problem
import problems.runProblem

fun main() = runProblem()

class Problem0091 : Problem(14234) {
    override fun calc(): Any {
        val max = 50
        val maxp = max + 1
        val found = HashSet<Int>()
        (0..max).forEach { x1 ->
            (0..max).forEach y1@{ y1 ->
                if (x1 == 0 && y1 == 0) return@y1
                (0..max).forEach { x2 ->
                    (0..max).forEach y2@{ y2 ->
                        if (x1 * x2 == -y1 * y2 || (x1 - x2) * x2 == (y2 - y1) * y2) {
                            if (x2 == 0 && y2 == 0 || x2 == x1 && y2 == y1) return@y2
                            val p1 = x1 * maxp + y1
                            val p2 = x2 * maxp + y2
                            if (!found.contains(p2 * maxp * maxp + p1)) {
                                found.add(p1 * maxp * maxp + p2)
                            }
                        }
                    }
                }
            }
        }
        return found.size
    }
}