package problems

fun main() = runProblem()

class Problem0008 : Problem(23514624000) {
    override fun calc(): Any {
        val size = 13
        var result = 0L
        val data = getData { it.replace("\n", "") }
        for (start in 0..data.length - size) {
            var prod = 1L
            for (i in 0 until size) prod *= data[start + i].code - 48
            if (prod > result) result = prod
        }
        return result
    }
}