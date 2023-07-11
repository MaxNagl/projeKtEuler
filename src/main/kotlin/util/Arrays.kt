package util

fun <T> Array<T>.swap(i: Int, j: Int) {
    val a = get(i)
    set(i, get(j))
    set(j, a)
}

fun IntArray.swap(i: Int, j: Int) {
    val a = get(i)
    set(i, get(j))
    set(j, a)
}
