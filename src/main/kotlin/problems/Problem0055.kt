package problems

import util.plusDec
import util.toDecByteArray

fun main() = runProblem()

class Problem0055 : Problem(249) {
    override fun calc(): Any {
        var count = 0
        (1..9999).forEach {
            var a = it.toDecByteArray()
            for (j in 1..50) {
                val sr = a.reversedArray()
                if (j != 1 && a.contentEquals(sr)) {
                    count++
                    break
                }
                a = a.plusDec(sr)
            }
        }
        return 9999 - count
    }
}
