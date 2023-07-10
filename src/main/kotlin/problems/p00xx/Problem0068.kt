package problems.p00xx

import problems.Problem
import problems.runProblem

fun main() = runProblem()

class Problem0068 : Problem(6357528249411013) {
    override fun calc(): Any {
        val best = arrayOf("")
        addNum(IntArray(10), 0, 0, best)
        return best[0]
    }

    fun addNum(set: IntArray, pos: Int, sum: Int, best: Array<String>) {
        if (pos == 5 && (set[2] + set[3] + set[4]) != sum) return
        if (pos == 7 && (set[4] + set[5] + set[6]) != sum) return
        if (pos == 9 && (set[6] + set[7] + set[8]) != sum) return
        if (pos == 10) {
            if ((set[8] + set[9] + set[1]) == sum) {
                val s1 = set[0].toString() + set[1].toString() + set[2].toString()
                val s2 = set[3].toString() + set[2].toString() + set[4].toString()
                val s3 = set[5].toString() + set[4].toString() + set[6].toString()
                val s4 = set[7].toString() + set[6].toString() + set[8].toString()
                val s5 = set[9].toString() + set[8].toString() + set[1].toString()
                if (s1 < s2 && s1 < s3 && s1 < s4 && s1 < s5) {
                    val s = s1 + s2 + s3 + s4 + s5
                    best[0] = s.map { (it.code - '0'.code) + 1 }.joinToString(separator = "")
                }
            }
            return
        }
        val s = if (pos == 3) set[0] + set[1] + set[2] else sum
        (0..9).forEach it@{ a ->
            (0..pos).forEach { if (set[it] == a) return@it }
            set[pos] = a
            addNum(set, pos + 1, s, best)
        }
    }
}

