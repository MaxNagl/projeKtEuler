package problems.p01xx

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import problems.Problem
import problems.runProblem
import util.FastSquare
import util.ggt

fun main() = runProblem()

class Problem0141 : Problem(878454337159, hasMulticore = true) {
    override fun calc(): Any {
        val fs = FastSquare()
        var sum = 0L
        val partition = if (multicore) 1000 else 1000000
        runBlocking(Dispatchers.Default) {
            for (start in 0 until 1000000 step partition) launch {
                for (div in 1..1000) {
                    val dd = div * div
                    for (a in dd + start - (start % dd)..start + partition step dd) {
                        val g = a.toLong() * a / div / div / div
                        var x = div
                        var c = 0L
                        a@ while (c < 1000000000000) {
                            c = g * ++x * x * x + a
                            if (fs.isSquare(c) && ggt(div, x) == 1) sum += c
                        }
                    }
                }
            }
        }
        return sum
    }
}
