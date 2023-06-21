package problems

fun main() = runProblem()

class Problem0040 : Problem(210) {
    override fun calc(): Any {
        var pos = 1
        var result = 1
        for (i in 1..6) {
            val v = digitAt(pos).code - '0'.code
            result *= v
            pos *= 10
        }
        return result
    }

    fun digitAt(n: Int): Char {
        var pos = n - 1
        var digitLen = 1
        var digitCount = 9
        var start = 1
        while (pos > digitCount * digitLen) {
            pos -= digitCount * digitLen
            digitCount *= 10
            start *= 10
            digitLen++
        }
        val num = pos / digitLen + start
        val numPos = pos % digitLen
        return num.toString()[numPos]
    }
}
