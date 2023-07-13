package problems.p01xx

import problems.Problem
import problems.runProblem
import java.util.*
import kotlin.math.absoluteValue
import kotlin.math.sign

fun main() = runProblem()

class Problem0119 : Problem(248155780267521) {
    override fun calc(): Any {
        val numbers = LinkedList<Pair<Long, Long>>()
        (2L..100L).forEach { numbers.add(it to it) }
        var count = 0
        while(true) {
            val number = numbers.removeFirst()
            var j = number.first
            if (j > 10) {
                var sum = 0L
                while (j != 0L) {
                    sum += j % 10
                    j /= 10
                }
                if (sum == number.second && ++count == 30) return number.first
            }
            val next = number.first * number.second to number.second
            val pos = Collections.binarySearch(numbers, next) { a, b -> (a.first - b.first).sign }
            if (pos > 0) numbers.add(pos, next) else numbers.add(-1 - pos, next)
        }
    }
}