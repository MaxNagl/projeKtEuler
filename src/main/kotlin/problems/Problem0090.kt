package problems

import util.subsets

fun main() = runProblem()

class Problem0090 : Problem(null) {
    override fun calc(): Any {
        var count = 0
        val subsets = ArrayList<Set<Int>>()
        arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 6).subsets(6) { ints -> subsets.add(ints.toSet()) }
        subsets.forEachIndexed { s1i, s1 ->
            (s1i until subsets.size).forEach s2i@{ s2i ->
                val s2 = subsets[s2i]
                if ((!s1.contains(0) || !s2.contains(1)) && (!s1.contains(1) || !s2.contains(0))) return@s2i
                if ((!s1.contains(0) || !s2.contains(4)) && (!s1.contains(4) || !s2.contains(0))) return@s2i
                if ((!s1.contains(0) || !s2.contains(6)) && (!s1.contains(6) || !s2.contains(0))) return@s2i
                if ((!s1.contains(1) || !s2.contains(6)) && (!s1.contains(6) || !s2.contains(1))) return@s2i
                if ((!s1.contains(2) || !s2.contains(5)) && (!s1.contains(5) || !s2.contains(2))) return@s2i
                if ((!s1.contains(3) || !s2.contains(6)) && (!s1.contains(6) || !s2.contains(3))) return@s2i
                if ((!s1.contains(4) || !s2.contains(6)) && (!s1.contains(6) || !s2.contains(4))) return@s2i
                if ((!s1.contains(8) || !s2.contains(1)) && (!s1.contains(1) || !s2.contains(8))) return@s2i
                count++
            }
        }
        return count
    }

}