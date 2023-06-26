package util

import kotlin.math.sqrt

fun Long.triangular(): Long = this * (this + 1) / 2
fun Long.square(): Long = this * this
fun Long.pentagonal(): Long = this * (3 * this - 1) / 2
fun Long.hexagonal(): Long = this * (2 * this - 1)
fun Long.heptagonal(): Long = this * (5 * this - 3) / 2
fun Long.octagonal(): Long = this * (3 * this - 2)

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
