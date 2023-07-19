package util

import kotlin.math.max
import kotlin.math.min
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

inline fun pythagoreanTriple(max: Int, check: (Int, Int, Int) -> Boolean = { _, _, _ -> true }) =
    HashSet<Triple<Int, Int, Int>>().apply {
        var m = 3
        while (true) {
            val mm = m * m
            val minN = sqrt((mm - max).toFloat()).toInt() + 1
            if (2 * m * minN > max) break
            for (n in minN..(m - 2)) {
                val b = 2 * m * n
                if (b > max) break
                if (m % n == 0 && m != 3) continue
                val a = mm - n * n
                val c = mm + n * n
                if (check(a, b, c)) {
                    val ggt = ggt(a, b)
                    if (ggt < 5L) {
                        val t =
                            Triple(
                                (min(a, b) / ggt),
                                (max(a, b) / ggt),
                                (c / ggt)
                            )
                        add(t)
                    }
                }
            }
            m++
        }
    }