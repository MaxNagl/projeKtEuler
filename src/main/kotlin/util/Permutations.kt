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