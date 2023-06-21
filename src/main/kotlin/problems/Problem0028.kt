package problems

fun main() = runProblem()

class Problem0028 : Problem(669171001) {
    override fun calc(): Any {
        var sum = 1L
        var num = 1L
        var plus = 2L
        for (j in 1..(1001 / 2)) {
            for (i in 1..4) {
                num += plus
                sum += num
            }
            plus += 2
        }
        return sum
    }
}

