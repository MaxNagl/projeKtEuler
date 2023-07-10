package problems.p01xx

import problems.Problem
import problems.runProblem
import util.*
import java.math.BigInteger

fun main() = runProblem()

class Problem0101 : Problem(37076114526) {
    override fun calc(): Any {

        val us = (1L..10L).map { ni ->
            (0..4).sumOf { ni.pow(it * 2) - ni.pow(it * 2 + 1) } + ni.pow(10)
        }

        var sum = 0L
        (1..us.size).forEach { s ->
            val a = (0 until s).map { ai ->
                LongArray(s + 1) { j ->
                    if (j < s) BigInteger.valueOf((ai + 1).toLong()).pow(j).toLong()
                    else us[ai]
                }
            }.toTypedArray()
            a.indices.forEach { i ->
                val a1 = a[i][i]
                a.indices.forEach { j ->
                    if (j != i) {
                        a[j].multiplyEach(a1)
                        a[j].subtractEach(a[i], a[j][i] / a1)
                        a[j].divideEach(a[j].ggt())
                    }
                }
            }
            (0 until s).map { ai ->
                sum += BigInteger.valueOf((s + 1).toLong()).pow(ai).toLong() * a[ai][s]
            }

        }

        return sum
    }
}