package problems

fun main() = runProblem()

class Problem0079 : Problem(73162890) {
    override fun calc(): Any {
        val data = getDataLines { it.toCharArray().map { it.digitToInt() } }

        data class Digit(val digit: Int, val befores: MutableSet<Int>)

        val digits = data.flatten().toSet().map { c ->
            val befores = HashSet<Int>()
            data.forEach {
                (1..2).forEach { i -> if (it[i] == c) befores.addAll(it.take(i)) }
            }
            Digit(c, befores)
        }.toMutableSet()

        var start = ""
        while (digits.isNotEmpty()) {
            val s = digits.firstOrNull { it.befores.isEmpty() }
            if (s != null) {
                start += s.digit.toString()
                digits.forEach { it.befores.remove(s.digit) }
                digits.removeIf { it.digit == s.digit }
            }
        }
        return start
    }
}