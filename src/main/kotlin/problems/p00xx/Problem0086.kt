@file:JvmName("Problem0086Kt")

package problems.p00xx

import problems.Problem
import problems.runProblem
import util.ggt
import kotlin.math.min

fun main() = runProblem()

class Problem0086 : Problem(1818) {
    override fun calc(): Any {
        val maxN = 2000
        val pairs = HashSet<Pair<Int, Int>>()
        (1..100).forEach { n ->
            ((n + 1)..100).forEach { m ->
                val a = m * m - n * n
                val b = 2 * m * n
                if (a < maxN * 2 && b < maxN * 2) {
                    val c = m * m + n * n
                    val ggt = ggt(a, ggt(b, c))
                    if (ggt == 1) {
                        pairs.add(a / ggt to b / ggt)
                        pairs.add(b / ggt to a / ggt)
                    }
                }
            }
        }
        val ps = pairs.filter { it.first < maxN }.sortedBy { it.first }
        var count = 0
        (1..maxN).forEach n@{ n ->
            if (count > 1000000) return n - 1
            ps.forEach { p ->
                if (n % p.first == 0) {
                    val b = p.second * n / p.first
                    val c = (b + 1) / 2
                    val max = min(b, n + 1)
                    if (max > c) count += max - c
                } else if (p.first > n) return@n
            }
        }
        return count
    }
}