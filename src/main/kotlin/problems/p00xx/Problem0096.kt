package problems.p00xx

import problems.Problem
import problems.runProblem

fun main() = runProblem()

class Problem0096 : Problem(24702) {
    override fun calc(): Any {
        val lines = getDataLines { it.map { c -> c.code - '0'.code } }
        val memCache = Array(80) { IntArray(81) to BooleanArray(9 * 9 * 9) }
        return lines.chunked(10).map { it.drop(1) }.sumOf { fieldList ->
            val field = fieldList.flatten().toIntArray()
            val possible = BooleanArray(9 * 9 * 9) { field[it / 9] == 0 }
            for (i in 0..80) if (field[i] != 0) removePossible(possible, i, field[i])
            val solved = solve(field, possible, memCache, 0) ?: error("Not solved!")
            solved[0] * 100 + solved[1] * 10 + solved[2]
        }
    }

    private fun solve(
        field: IntArray,
        possible: BooleanArray,
        memCache: Array<Pair<IntArray, BooleanArray>>,
        cachePos: Int = 0
    ): IntArray? {
        while (field.contains(0)) {
            var progress = false
            for (i in 0..80) {
                var single = -1
                for (j in 0..8) if (possible[i * 9 + j]) {
                    if (single != -1) {
                        single = -1
                        break
                    }
                    single = j + 1
                }
                if (single != -1) {
                    progress = true
                    field[i] = single
                    removePossible(possible, i, single)
                }
            }
            if (!progress) { // Start experimenting
                var min = 10
                var minField = -1
                for (i in 0..80) {
                    if (field[i] == 0) {
                        var count = 0
                        for (j in 0..8) if (possible[i * 9 + j]) count++
                        if (count == 0) return null
                        if (count < min) {
                            min = count
                            minField = i
                        }
                    }
                }
                val newField = memCache[cachePos].first
                val newPossible = memCache[cachePos].second
                for (j in 0..8) {
                    if (possible[minField * 9 + j]) {
                        System.arraycopy(field, 0, newField, 0, field.size)
                        System.arraycopy(possible, 0, newPossible, 0, newPossible.size)
                        newField[minField] = j + 1
                        removePossible(newPossible, minField, j + 1)
                        for (k in 0..8) newPossible[minField * 9 + k] = false
                        solve(newField, newPossible, memCache, cachePos + 1)?.let { return it }
                    }
                }
                return null
            }
        }
        return field
    }

    private fun removePossible(possible: BooleanArray, pos: Int, value: Int) {
        val colum = pos % 9
        val row = pos / 9 * 9
        val sector = pos / 27 * 27 + (pos / 3 * 3) % 9
        for (i in 0..8) {
            possible[(colum + i * 9) * 9 + value - 1] = false
            possible[(row + i) * 9 + value - 1] = false
            possible[(sector + i % 3 + (i / 3) * 9) * 9 + value - 1] = false
        }
    }
}
