package problems.p01xx

import problems.Problem
import problems.runProblem
import util.ggt
import util.primes
import kotlin.math.sqrt

fun main() = runProblem()

class Problem0127 : Problem(18407904) {
    override fun calc(): Any {
        val primes = primes(1000)
        val rads = IntArray(120_000)
        val rrads = arrayOfNulls<ArrayList<Int>>(rads.size)
        rads.fill(1)
        (2 until rads.size).forEach { p ->
            if (rads[p] == 1) (p until rads.size step p).forEach { rads[it] *= p }
        }
        val maxRad = sqrt(rads.size.toFloat()).toInt()
        rads.forEachIndexed { index, rad ->
            if (rad < maxRad) (rrads[rad]
                    ?: ArrayList<Int>().apply { rrads[rad] = this }).add(index)
        }
        var t = 0L
        for (c in 2 until rads.size) {
            val radC = rads[c]
            val maxM = (c - 1) / radC
            if (rads[c - 1] <= maxM) t += c
            if (maxM >= 6) {
                val minP = primes.first { radC % it != 0 }
                var validPrimes = 0
                for (p in primes) {
                    if (p > maxM / minP) break
                    if (radC % p != 0) if (++validPrimes > 1) break
                }
                if (validPrimes == 2) {
                    val radAMax = sqrt(maxM.toFloat()).toInt()
                    for (radA in 2..radAMax) {
                        val rradA = rrads[radA] ?: continue
                        if (ggt(radA, radC) == 1) for (a in rradA) {
                            if (a > c) break
                            val b = c - a
                            val radB = rads[b]
                            if (radB > radA && radA * radB <= maxM && ggt(radB, radC) == 1 && ggt(radA, radB) == 1) t += c
                        }
                    }
                }
            }
        }
        return t
    }
}