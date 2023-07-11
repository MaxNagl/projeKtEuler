package problems.p00xx

import problems.Problem
import problems.runProblem
import util.inc
import util.toLastPermutation
import java.util.LinkedList
import kotlin.math.sqrt

fun main() = runProblem()

class Problem0098 : Problem(18769) {
    override fun calc(): Any {
        var max = 0
        val lines = LinkedList(getData { it.replace("\"", "").split(",").map { w -> w.trim() } })
        val words = lines.groupBy { word ->
            HashMap<Char, Int>().apply { word.forEach { inc(it) } }
        }.filter { it.value.size > 1 }
        val numbers = List(sqrt(1000000000f).toInt()) { it * it }.groupBy { word ->
            word.toLastPermutation()
        }
            .filter { it.value.size > 1 }.map { it.value.map { i -> i.toString() } }.groupBy { list ->
            HashMap<Char, Int>().apply {
                list.first().forEach { inc(it) }
            }.values.sorted()
        }
        words.forEach {
            val ws = it.value
            numbers[it.key.values.sorted()]?.forEach { nums ->
                val permutations = HashMap<Map<Char, Char>, ArrayList<String>>()
                nums.forEach { n ->
                    ws.forEach { w ->
                        val map = HashMap<Char, Char>()
                        n.indices.forEach { ni -> map[n[ni]] = w[ni] }
                        if (map.keys.size == map.values.toSet().size)
                            permutations.getOrPut(map) { ArrayList() }.add(n)
                    }
                    if (permutations.any { p -> p.value.size > 1 }) {
                        max = max.coerceAtLeast(
                                permutations.filter { p -> p.value.size > 1 }
                                        .maxOf { p -> p.value.maxOf { i -> i.toInt() } }
                        )
                    }
                }
            }
        }
        return max
    }
}
