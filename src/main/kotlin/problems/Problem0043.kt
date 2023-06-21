package problems

import util.permutations

fun main() = runProblem()

class Problem0043 : Problem(16695334890) {
    fun Array<Int>.calc(index: Int = 0, block: (Array<Int>) -> Unit) {
        if (index == size - 1) block(this) else {
            if (index == 4 && (this[1] * 100 + this[2] * 10 + this[3]) % 2 != 0) return
            if (index == 5 && (this[2] * 100 + this[3] * 10 + this[4]) % 3 != 0) return
            if (index == 6 && (this[3] * 100 + this[4] * 10 + this[5]) % 5 != 0) return
            if (index == 7 && (this[4] * 100 + this[5] * 10 + this[6]) % 7 != 0) return
            if (index == 8 && (this[5] * 100 + this[6] * 10 + this[7]) % 11 != 0) return
            val a = get(index)
            val last = index == size - 2
            for (i in index until size) {
                val b = get(i)
                set(index, b)
                set(i, a)
                if (last) block(this) else calc(index + 1, block)
                set(i, b)
            }
            set(index, a)
        }
    }

    override fun calc(): Any {
        var sum = 0L
        Array(10) { it }.calc { digits ->
            if (
                (digits[6] * 100 + digits[7] * 10 + digits[8]) % 13 == 0 &&
                (digits[7] * 100 + digits[8] * 10 + digits[9]) % 17 == 0
            )  {
                var num = 0L
                digits.forEach { num = num * 10 + it }
                sum += num
            }
        }
        return sum
    }
}
