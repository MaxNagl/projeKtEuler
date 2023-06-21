package problems

import util.faculty

fun main() = runProblem()

class Problem0034 : Problem(40730) {
    override fun calc(): Any {
        val faculties = List(10) { it.faculty().toInt() }
        val max = faculties[9]
        var result = 0
        (3..max).forEach i@ { i ->
            var t = i
            var sum = 0
            while (t != 0) {
                sum += faculties[t % 10]
                t /= 10
                if (sum > i) return@i
            }
            if (sum == i) result += i
        }
        return result
    }

}
