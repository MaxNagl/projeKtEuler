package problems.p01xx

import problems.Problem
import problems.runProblem

fun main() = runProblem()

class Problem0105 : Problem(73702) {
    override fun calc(): Any {
        return getDataLines { it.split(",").map { i -> i.toInt() } }.sumOf { set ->
            var sums = IntArray(1)
            set.forEach { sums = addToSubsetSums(sums, it) ?: return@sumOf 0 }
            set.sum()
        }
    }

    private fun addToSubsetSums(lengths: IntArray, num: Int): IntArray? {
        (num until lengths.size).forEach { index ->
            if (lengths[index - num] != 0 && lengths[index] != 0) return null
        }
        var lastSum = 1
        return IntArray(lengths.size + num) { index ->
            if (index < num) lengths.getOrNull(index) ?: 0
            else if (index == num) 1
            else if (index < lengths.size) {
                val down = lengths[index - num]
                val last = lengths[index]
                if (down != 0) down + 1 else last
            } else {
                val down = lengths[index - num]
                if (down != 0) down + 1 else 0
            }.also {
                if (it != 0 && it != lastSum) {
                    if (it < lastSum) return null
                    lastSum = it
                }
            }
        }
    }
}

