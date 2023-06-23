package problems

fun main() = runProblem()

class Problem0052 : Problem(142857) {
    override fun calc(): Any {
        (1..1000000).forEach { i ->
            val a = i.toLastPermutation()
            if ((i * 2).toLastPermutation() != a) return@forEach
            if ((i * 3).toLastPermutation() != a) return@forEach
            if ((i * 4).toLastPermutation() != a) return@forEach
            if ((i * 5).toLastPermutation() != a) return@forEach
            if ((i * 6).toLastPermutation() != a) return@forEach
            return i
        }
        return 0
    }

    private fun Int.toLastPermutation(): Int {
        val digits = IntArray(10)
        var i = this
        while (i != 0) {
            digits[i % 10]++
            i /= 10
        }
        (9 downTo 0).forEach { d -> repeat(digits[d]) { i = i * 10 + d } }
        return i
    }
}
