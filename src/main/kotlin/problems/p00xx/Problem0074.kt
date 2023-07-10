package problems.p00xx

import problems.Problem
import problems.runProblem
import util.faculty

fun main() = runProblem()

class Problem0074 : Problem(402) {
    override fun calc(): Any {
        val faculties = IntArray(10) { it.faculty().toInt() }

        val chains = IntArray(9.faculty().toInt() * 6 + 1)

        fun Int.digitFaculty(): Int {
            var i = this
            var f = 0
            while (i > 0) {
                f += faculties[i % faculties.size]
                i /= 10
            }
            return f
        }

        var count = 0
        val list = IntArray(1000)
        (1..1000000).forEach i@{ i ->
            list[0] = i
            var listPos = 1
            var f = i
            while (true) {
                f = f.digitFaculty()
                val chain = chains[f]
                if (chain != 0) {
                    (0 until listPos).forEach { index ->
                        chains[list[index]] = listPos - index + chain
                    }
                    if (listPos + chain == 60) count++
                    return@i
                }
                if ((0..listPos).any { list[it] == f }) break
                list[listPos++] = f
            }
            val chainStart = list.indexOf(f)
            (0 until listPos).forEach { index ->
                chains[list[index]] = listPos - index.coerceAtMost(chainStart)
            }
            if (listPos == 60) count++
        }
        return count
    }
}
