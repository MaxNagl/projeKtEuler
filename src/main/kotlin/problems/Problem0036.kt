package problems

import util.filterMultithreaded
import util.isPalindrome

fun main() = runProblem()

class Problem0036 : Problem(872187) {
    override fun calc(): Any {
        return (1..999999).filterMultithreaded(1000) {
            it.toString().isPalindrome() && it.toString(2).isPalindrome()
        }.sumOf { it.sum() }
    }
}
