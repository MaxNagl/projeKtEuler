package problems

fun main() = runProblem()

class Problem0002 : Problem(4613732) {
    override fun calc(): Any {
        val limit = 4_000_000
        var a = 1
        var b = 1
        var sum = 0
        while (a < limit && b < limit) {
            a += b
            b += a
            if (a % 2 == 0) sum += a
            if (b % 2 == 0) sum += b
        }
        return sum
    }
}