package problems.p00xx

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import problems.Problem
import problems.runProblem

fun main() = runProblem()

class Problem0014 : Problem(837799) {
    override fun calc(): Any {
        var longest = 0L to 0
        runBlocking(Dispatchers.Default) {
            (0L..1000L).map { async { calcLengthInRange(it * 1000 + 1, it * 1000 + 1000) } }
                .forEach {
                    if (it.await().second > longest.second) longest = it.await()
                }
        }
        return longest.first
    }

    fun calcLengthInRange(min: Long, max: Long): Pair<Long, Int> {
        var longest = 0
        var longestVal = 0L
        for (i in min..max) {
            val len = calcLengths(i)
            if (len > longest) {
                longest = len
                longestVal = i
            }
        }
        return longestVal to longest
    }

    fun calcLengths(v: Long): Int {
        var i = v
        var len = 1
        while (i != 1L) {
            i = if (i % 2L == 0L) i / 2 else 3 * i + 1
            len++
        }
        return len
    } // 152 ms (without async)

    fun calcLengthsRec(v: Long): Int {
        if (v == 1L) return 1
        return calcLengthsRec(if (v % 2L == 0L) v / 2 else 3 * v + 1) + 1
    } // 180 ms (without async)

    fun calcLengthsWithMap(v: Long, lengths: HashMap<Long, Int>): Int {
        lengths[v]?.let { return it }
        if (v == 1L) return 1
        val length = calcLengthsWithMap(if (v % 2L == 0L) v / 2 else 3 * v + 1, lengths) + 1
        lengths[v] = length
        return length
    } // 236 ms (without async)

}