package util

fun Int.faculty(): Long {
    var f: Long = 1
    (2..this).forEach { f *= it }
    return f
}

fun ggt(a: Int, b: Int): Int {
    var c = a
    var d = b
    while (c != 0 && d != 0) {
        if (c >= d) c %= d else d %= c
    }
    return c + d
}

fun ggt(a: Long, b: Long): Long {
    var c = a
    var d = b
    while (c != 0L && d != 0L) {
        if (c >= d) c %= d else d %= c
    }
    return c + d
}

fun Pair<Int, Int>.simplifyFraction(): Pair<Int, Int> {
    val ggt = ggt(first, second)
    return Pair(first / ggt, second / ggt)
}

fun Long.rotate(): Long {
    var j = this
    var i = this % 10
    while (j > 10) {
        j /= 10
        i *= 10
    }
    return this / 10L + i
}

fun Int.rotate(): Int {
    var j = this
    var i = this % 10
    while (j > 10) {
        j /= 10
        i *= 10
    }
    return this / 10 + i
}

fun Long.pow(pow: Int): Long {
    if (pow == 0) return 1L
    var v = this
    repeat(pow - 1) { v *= this }
    return v
}
