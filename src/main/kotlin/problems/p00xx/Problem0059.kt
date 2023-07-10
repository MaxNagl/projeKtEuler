package problems.p00xx

import problems.Problem
import problems.runProblem

fun main() = runProblem()

class Problem0059 : Problem(129448) {
    override fun calc(): Any {
        val enc = getData { it.split(",").map { it.toInt() } }
        val dec = ByteArray(enc.size)
        val possibleChars = (' '.code)..('z'.code)
        fun possibilities(offset: Int) = (0..255).filter { a ->
            (offset until enc.size step 3).all { enc[it] xor a in possibleChars }
        }
        possibilities(0).forEach { a ->
            possibilities(1).forEach { b ->
                possibilities(2).forEach { c ->
                    repeat(enc.size) {
                        dec[it] = (enc[it] xor when (it % 3) {
                            0 -> a
                            1 -> b
                            else -> c
                        }).toByte()
                    }
                    if (String(dec).contains("the")) return dec.sum()
                }
            }
        }
        return 0
    }
}
