package util

import kotlin.math.sqrt

fun Long.isTriangular(): Boolean {
    val p = (sqrt(this * 8 + 1f) - 1).toLong() / 2
    return p * (p + 1) / 2 == this
}

fun Long.isPentagonal(): Boolean {
    val p = (sqrt(this * 24 + 1f) + 1).toLong() / 6
    return p * (3 * p - 1) / 2 == this
}

fun Long.isHexagonal(): Boolean {
    val p = (sqrt(this * 8 + 1f) + 1).toLong() / 4
    return p * (2 * p - 1) == this
}
