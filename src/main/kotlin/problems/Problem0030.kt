package problems

fun main() = runProblem()

class Problem0030 : Problem(443839) {
    override fun calc(): Any {
        var result = 0
        val pots = IntArray(10) { it * it * it * it * it}
        val max = 9 * 9 * 9 * 9 * 9 * 6
        (2..max).forEach { j ->
            var i = j
            var sum = 0
            while (i != 0) {
                sum += pots[i % 10]
                i /= 10
            }
            if (sum == j) result += j
        }
        return result
    }
}

