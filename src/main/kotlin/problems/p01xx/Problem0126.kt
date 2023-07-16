package problems.p01xx

import problems.Problem
import problems.runProblem

fun main() = runProblem()

class Problem0126 : Problem(18522) {
    override fun calc(): Any {
        var limit = 2000
        while (true) {
            calcWithLimit(limit).let { if (it as Int > 0) return it }
            limit *= 10
        }
    }

    fun calcWithLimit(limit: Int): Any {
        val blocks = IntArray(limit + 1)
        for (x in 1..(limit / 4)) {
            for (y in x..(limit / 2 - x) / (x + 1)) {
                var z = y
                var outerBlocks = 2 * (x * y + x * z + y * z)
                while (outerBlocks < limit) {
                    var layerBlocks = x * 2 + y * 2
                    while (outerBlocks < limit) {
                        blocks[outerBlocks]++
                        outerBlocks += 2 * layerBlocks + 4 * z
                        layerBlocks += 4
                    }
                    z++
                    outerBlocks = 2 * (x * y + x * z + y * z)
                }
            }
        }
        return blocks.indexOf(1000)
    }

    class Cubes(
            val base: Triple<Int, Int, Int>,
            val layerBlocks: Int = base.first * 2 + base.second * 2,
            val outerBlocks: Int = base.first * base.second * 2 + layerBlocks * base.third,
            val stage: Int = 1
    ) {
        fun addLayer(): Cubes {
            return Cubes(base, layerBlocks + 4, outerBlocks + 2 * layerBlocks + 4 * base.third, 0)
        }
    }

    fun calcOO(): Any {
        val nexts = Array(100000) { ArrayList<Cubes>() }
        nexts[6].add(Cubes(Triple(1, 1, 1)))
        var cubesCount = 0
        nexts.forEach { nextList ->
            if (nextList.size == 1000) println(cubesCount)
            if (nextList.size == 1000) return nextList.first().outerBlocks
            nextList.forEach { cubes ->
                cubesCount++
                if (cubes.stage == 1) {
                    Cubes(Triple(cubes.base.first + 1, cubes.base.second, cubes.base.third), stage = 1).apply { nexts[outerBlocks].add(this) }
                    if (cubes.base.first != 1)
                        Cubes(Triple(cubes.base.first, 2, 1), stage = 2).apply { nexts[outerBlocks].add(this) }
                }
                if (cubes.stage == 2) {
                    if (cubes.base.first > cubes.base.second)
                        Cubes(Triple(cubes.base.first, cubes.base.second + 1, cubes.base.third), stage = 2).apply { nexts[outerBlocks].add(this) }
                    if (cubes.base.second != 1)
                        Cubes(Triple(cubes.base.first, cubes.base.second, 2), stage = 3).apply { nexts[outerBlocks].add(this) }
                }
                if (cubes.stage == 3) {
                    if (cubes.base.second > cubes.base.third)
                        Cubes(Triple(cubes.base.first, cubes.base.second, cubes.base.third + 1), stage = 3).apply { nexts[outerBlocks].add(this) }
                }
                cubes.addLayer().apply { nexts[outerBlocks].add(this) }
            }
            nextList.clear()
        }
        return 0
    }
}