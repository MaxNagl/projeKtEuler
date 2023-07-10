package problems.p00xx

import problems.Problem
import problems.runProblem
import util.primes
import kotlin.math.max

fun main() = runProblem()

class Problem0027 : Problem(-59231) {
    override fun calc(): Any {
        val maxPrime = 100000
        val quickCheck = BooleanArray(maxPrime)
        primes(maxPrime).forEach { quickCheck[it] = true }
        var best = Triple(1, 0, 0)
        for (a in -999..999) for (b in max(-1000, -a)..1000) {
            val first = 1 + a + b
            if (quickCheck[first]) {
                for (i in 1..1000) {
                    val c = i * i + i * a + b
                    if (c > maxPrime) throw IllegalStateException()
                    if (c <= 0 || !quickCheck[c]) {
                        if (i > best.first) best = Triple(i, a, b)
                        break
                    }
                }
            }
        }
        return best.second * best.third
    }
}

