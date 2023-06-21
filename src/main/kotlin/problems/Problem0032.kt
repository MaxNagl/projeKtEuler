package problems

import kotlin.math.sqrt

fun main() = runProblem()

class Problem0032 : Problem(45228) {
    override fun calc(): Any {
        var count = 0L
        val digits = BooleanArray(10)
        val digits2 = BooleanArray(10)
        digits[0] = true
        (1..99999).forEach p@{ p ->
            digits.fill(false, 1, 10)
            var t = p
            while (t > 0) {
                val d = t % 10
                if (digits[d]) return@p
                digits[d] = true
                t /= 10
            }
            val limit = sqrt(p.toFloat()).toInt()
            (2..limit).forEach a@{ a ->
                if (p % a == 0) {
                    System.arraycopy(digits, 0, digits2, 0, 10)
                    t = a
                    while (t > 0) {
                        val d = t % 10
                        if (digits2[d]) return@a
                        digits2[d] = true
                        t /= 10
                    }
                    var b = p / a
                    while (b > 0) {
                        val d = b % 10
                        if (digits2[d]) return@a
                        digits2[d] = true
                        b /= 10
                    }
                    digits2.forEach { if (!it) return@a }
                    count += p
                    return@p
                }
            }
        }
        return count
    }
}

