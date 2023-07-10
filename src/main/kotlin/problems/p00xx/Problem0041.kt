package problems.p00xx

import problems.Problem
import problems.runProblem
import util.isPrime
import util.permutations
import util.primes
import kotlin.math.sqrt

fun main() = runProblem()

class Problem0041 : Problem(7652413) {
    override fun calc(): Any {
        val primes = primes(sqrt(987654321.0).toInt())
        var best = 0
        (9 downTo 2).forEach { maxDigits ->
            Array(maxDigits) { it + 1 }.permutations { digits ->
                val last = digits.last()
                if (last % 2 == 0 || last == 5) return@permutations
                var num = 0
                digits.forEach { num = num * 10 + it }
                if (num.isPrime(primes) && num > best) best = num
            }
            if (best != 0) return best
        }
        return best
    }
}
