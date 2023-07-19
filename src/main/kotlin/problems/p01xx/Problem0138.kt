package problems.p01xx

import problems.Problem
import problems.runProblem
import util.big2
import java.math.BigInteger
import kotlin.system.exitProcess

fun main() = runProblem()

class Problem0138 : Problem(1118049290473932) {
    override fun calc(): Any {
        var a = BigInteger.valueOf(2)
        var last = BigInteger.ONE
        var sum = BigInteger.ZERO
        var count = 0
        val plusMinusOne = listOf(BigInteger.ONE, -BigInteger.ONE)
        var misses = 0
        while (true) {
            misses++
            plusMinusOne.forEach {
                val b = a * big2 + it
                val sqrt = (a * a + b * b).sqrtAndRemainder()
                if (sqrt[1] == BigInteger.ZERO) {
                    sum += sqrt[0]
                    if (++count == 12) return sum
                    val next = a * a / (last + BigInteger.ONE)
                    last = a
                    a = next
                    misses = 0
                }
            }
            a += BigInteger.ONE
        }
    }
}