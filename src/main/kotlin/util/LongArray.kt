package util

import kotlin.math.absoluteValue

fun LongArray.ggt(): Long {
    var ggt = get(0).absoluteValue
    (1 until size).forEach { ggt = ggt(ggt, get(it).absoluteValue) }
    return ggt
}

fun LongArray.multiplyEach(multi: Long) {
    indices.forEach { i -> set(i, get(i) * multi) }
}

fun LongArray.divideEach(divisor: Long) {
    indices.forEach { i -> set(i, get(i) / divisor) }
}

fun LongArray.subtractEach(other: LongArray, multi: Long) {
    indices.forEach { i -> set(i, get(i) - other[i] * multi) }
}
