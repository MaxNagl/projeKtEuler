package problems

fun main() = runProblem()

class Problem0115 : Problem(168) {
    override fun calc(): Any {
        val possibilities = ArrayList<Long>()
        val l = 50
        for (i in 1..100000) {
            var p = (possibilities.lastOrNull() ?: 1L) + (i + 1 - l).coerceAtLeast(0)
            for (j in 1..(i - l - 2)) p += possibilities[j] - 1
            if (p > 1000000) return i
            possibilities.add(p)
        }
        return 0
    }
}