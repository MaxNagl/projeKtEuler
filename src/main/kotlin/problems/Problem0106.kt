package problems

import util.subsets

fun main() = runProblem()

class Problem0106 : Problem(21384) {
    @Suppress("ConvertArgumentToSet")
    override fun calc(): Any {
        var count = 0
        val set = IntArray(12) { it + 1 }
        set.subsets s1@{ s1, s1s ->
            if (s1s == 0) return@s1
            val left = (set.toList() - s1.take(s1s)).toIntArray()
            left.subsets s2@{ s2, s2s ->
                if (s2s != s1s) return@s2
                if (s2[0] > s1[0]) {
                    (0 until s1s).forEach {
                        if (s2[it] < s1[it]) {
                            count++
                            return@s2
                        }
                    }
                }
            }
        }
        return count
    }
}
