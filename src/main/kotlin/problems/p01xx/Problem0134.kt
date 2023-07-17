package problems.p01xx

import problems.Problem
import problems.runProblem
import util.primes

fun main() = runProblem()

class Problem0134 : Problem(18613426663617118) {
    override fun calc(): Any {
        var sum = 0L
        val mappings = Array(10) { a ->
            Array(10) { b ->
                IntArray(10) { c ->
                    var d = b
                    var i = 0
                    while (i < 10 && d % 10 != c) {
                        d += a
                        i++
                    }
                    i
                }
            }
        }
        var prev = -1
        primes(1000010).forEach { a ->
            if (prev in 5..1000000) {
                var b = prev
                var c = 0
                var p = 1
                val map = mappings[a % 10]
                while (b != 0) {
                    c += a * map[c % 10][b % 10]
                    b /= 10
                    c /= 10
                    p *= 10
                }
                sum += c.toLong() * p + prev
            }
            prev = a
        }
        return sum
    }
}