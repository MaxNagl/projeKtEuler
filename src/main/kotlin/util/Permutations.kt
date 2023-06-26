package util

fun <T> Array<T>.permutations(index: Int = 0, block: (Array<T>) -> Unit) {
    if (index == size - 1) block(this) else {
        val a = get(index)
        val last = index == size - 2
        for (i in index until size) {
            val b = get(i)
            set(index, b)
            set(i, a)
            if (last) block(this) else permutations(index + 1, block)
            set(i, b)
        }
        set(index, a)
    }
}

fun <T> Array<T>.permutations(
    index: Int = 0,
    stopper: (Array<T>, Int) -> Boolean,
    block: (Array<T>) -> Unit
) {
    if (stopper(this, index)) {
        block(this)
        return
    }
    if (index == size - 1) block(this) else {
        val a = get(index)
        val last = index == size - 2
        for (i in index until size) {
            val b = get(i)
            set(index, b)
            set(i, a)
            if (last) block(this) else permutations(index + 1, stopper, block)
            set(i, b)
        }
        set(index, a)
    }
}

fun Int.toLastPermutation(): Int {
    val digits = IntArray(10)
    var i = this
    while (i != 0) {
        digits[i % digits.size]++
        i /= 10
    }
    (9 downTo 0).forEach { d -> repeat(digits[d]) { i = i * 10 + d } }
    return i
}

fun Int.isPermutation(other: Int): Boolean {
    val digits = IntArray(10)
    var i = this
    while (i > 0) {
        digits[i % digits.size]++
        i /= 10
    }
    var j = other
    while (j > 0) {
        digits[j % digits.size]--
        j /= 10
    }
    return digits.all { it == 0 }
}