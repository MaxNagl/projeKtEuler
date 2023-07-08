package util

import java.util.*

@Suppress("ReplaceJavaStaticMethodWithKotlinAnalog")
fun <E> Array<E>.subsets(size: Int, function: (Array<E>) -> Unit) {
    subsets(Arrays.copyOf(this, size), 0, 0, function)
}

private fun <E> Array<E>.subsets(
    subSet: Array<E>,
    pos: Int = 0,
    start: Int = 0,
    function: (Array<E>) -> Unit
) {
    if (pos == subSet.size) return function(subSet)
    (start until size).forEach {
        subSet[pos] = this[it]
        subsets(subSet, pos + 1, it + 1, function)
    }
}

fun IntArray.subsets(function: (IntArray, Int) -> Unit) =
    subsets(IntArray(size), 0, 0, function)

fun IntArray.subsets(
    current: IntArray,
    pos: Int = 0,
    start: Int = 0,
    function: (IntArray, Int) -> Unit,
) {
    function(current, pos)
    if (pos == size) return
    (start until size).forEach {
        current[pos] = get(it)
        subsets(current, pos + 1, start = it + 1, function)
    }
}

