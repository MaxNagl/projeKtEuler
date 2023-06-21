package problems

fun main() = runProblem()

class Problem0019 : Problem(171) {
    override fun calc(): Any {
        var year = 1900
        var month = 0
        var day = 0
        var count = 0
        while (year <= 2000) {
            day = (day + when (month) {
                1 -> if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) 29 else 28
                3, 5, 8, 10 -> 30
                else -> 31
            }) % 7
            month = (month + 1) % 12
            if (month == 0) year++
            if (day == 6 && year in 1901..2000) count++
        }
        return count
    }
}