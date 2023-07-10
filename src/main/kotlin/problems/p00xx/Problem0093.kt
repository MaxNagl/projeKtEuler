package problems.p00xx

import problems.Problem
import problems.runProblem
import util.permutations

fun main() = runProblem()

class Problem0093 : Problem(1258) {
    override fun calc(): Any {
        var best = 0
        var result = 0
        for (a in 1..9) for (b in a + 1..9) for (c in b + 1..9) for (d in c + 1..9) {
            val s = arrayOf<Float?>(a.toFloat(), b.toFloat(), c.toFloat(), d.toFloat())
            val set = HashSet<Int>()
            s.permutations { ops(s, set) }
            var last = Int.MIN_VALUE
            var consecs = 0
            var bestConsecs = 0
            set.sorted().forEach {
                if (last + 1 == it) {
                    consecs++
                    if (consecs > bestConsecs) bestConsecs = consecs
                } else {
                    consecs = 1
                }
                last = it
            }
            if (bestConsecs > best) {
                best = bestConsecs
                result = a * 1000 + b * 100 + c * 10 + d
            }
        }
        return result
    }

    fun ops(values: Array<Float?>, results: HashSet<Int>) {
        if (values.size == 1) {
            val first = values.first()!!
            if (first.toInt().toFloat() == first && first > 0) results.add(first.toInt())
        } else {
            val vals = values.copyOf(values.size - 1)
            for (i in vals.indices) {
                for (j in 0 until i) vals[j] = values[j]
                for (j in i + 1 until vals.size) vals[j] = values[j + 1]
                val a = values[i]!!
                val b = values[i + 1]!!
                vals[i] = a + b
                ops(vals, results)
                vals[i] = a - b
                ops(vals, results)
                vals[i] = a / b
                ops(vals, results)
                vals[i] = a * b
                ops(vals, results)
            }
        }
    }
}