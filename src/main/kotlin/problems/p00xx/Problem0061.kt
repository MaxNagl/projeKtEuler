package problems.p00xx

import problems.Problem
import problems.runProblem
import util.*

fun main() = runProblem()

class Problem0061 : Problem(28684) {
    data class Num(
        val value: Int,
        val set: Int,
        val first: Int = value / 100,
        val second: Int = value % 100,
    ) {
        var nexts: Array<Num> = emptyArray()
    }

    fun collect4digits(set: Int, calc: (Long) -> Long) = ArrayList<Num>().apply {
        (1L..100000L).forEach {
            val v = calc(it).toInt()
            if (v > 9999) return this
            if (v > 999) add(Num(v, set))
        }

    }

    override fun calc(): Any {
        val sets = listOf(
            collect4digits(0) { it.triangular() },
            collect4digits(1) { it.square() },
            collect4digits(2) { it.pentagonal() },
            collect4digits(3) { it.hexagonal() },
            collect4digits(4) { it.heptagonal() },
            collect4digits(5) { it.octagonal() },
        )
        val all = sets.flatten()

        all.forEach { v ->
            val nexts = ArrayList<Num>()
            all.forEach { n ->
                if (n.first == v.second && n.set != v.set) nexts.add(n)
            }
            v.nexts = nexts.toTypedArray()
        }

        var combis = sets[0].map { arrayOf(it) }
        repeat(5) {
            val newCombis = ArrayList<Array<Num>>()
            combis.forEach { c ->
                c.last().nexts.forEach { n ->
                    if (c.none { it.set == n.set }) {
                        newCombis.add(c + n)
                    }
                }
            }
            combis = newCombis
        }
        combis.forEach { c ->
            if (c.first().first == c.last().second) return c.sumOf { it.value }
        }
        return 0
    }
}

