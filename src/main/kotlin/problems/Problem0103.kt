package problems

fun main() = runProblem()

class Problem0103 : Problem(20313839404245) {
    var count = 0

    @Suppress("KotlinConstantConditions")
    override fun calc(): Any {
        var size = 6
        var best = when (size) {
            1 -> intArrayOf(1)
            2 -> intArrayOf(1, 2)
            3 -> intArrayOf(2, 3, 4)
            4 -> intArrayOf(3, 5, 6, 7)
            5 -> intArrayOf(6, 9, 11, 12, 13)
            6 -> intArrayOf(11, 18, 19, 20, 22, 25)
            7 -> intArrayOf(20, 31, 38, 39, 40, 42, 45)
            else -> return 0
        }
        while (size < 7) {
            size++
            val min = best[(best.size) / 2]
            val sum = best.sum() + min * best.size + min
            var minSum = Int.MAX_VALUE
            testPossibleSets(IntArray(size), 0, min, sum, IntArray(1)) {
                synchronized(this@Problem0103) {
                    if (it.sum() < minSum) {
                        minSum = it.sum()
                        best = it.copyOf()
                    }
                }
            }
        }
        return best.joinToString(separator = "")
    }

    @Suppress("DeferredResultUnused")
    private fun testPossibleSets(
        current: IntArray,
        pos: Int,
        minValue: Int,
        restSum: Int,
        lengths: IntArray,
        action: (IntArray) -> Unit
    ) {
        val left = current.size - pos - 1
        (minValue..(restSum - left * minValue - left * left + 1)).forEach { i ->
            current[pos] = i
            val newLengths = addToSubsetSums(lengths, i)
            if (newLengths != null) {
                if (pos == current.size - 1) action(current)
                else testPossibleSets(
                    current.copyOf(),
                    pos + 1,
                    i + 1,
                    restSum - i,
                    newLengths,
                    action,
                )
            }
        }
    }

    private fun addToSubsetSums(lengths: IntArray, num: Int): IntArray? {
        (num until lengths.size).forEach { index ->
            if (lengths[index - num] != 0 && lengths[index] != 0) return null
        }
        var lastSum = 1
        return IntArray(lengths.size + num) { index ->
            if (index < num) lengths.getOrNull(index) ?: 0
            else if (index == num) 1
            else if (index < lengths.size) {
                val down = lengths[index - num]
                val last = lengths[index]
                if (down != 0) down + 1 else last
            } else {
                val down = lengths[index - num]
                if (down != 0) down + 1 else 0
            }.also {
                if (it != 0 && it != lastSum) {
                    if (it < lastSum) return null
                    lastSum = it
                }
            }
        }
    }
}

