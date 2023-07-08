package util

import kotlin.LongArray
import kotlin.math.max

fun Iterable<Iterable<Any?>>.printMatrix() {
    var len = 0
    forEach { line ->
        line.forEach { i ->
            len = max(len, i.toString().length)
        }
    }
    val formater = "%${len + 2}s"
    forEach { line ->
        line.forEach { i ->
            print(formater.format(i.toString()))
        }
        println()
    }
}

fun Array<Array<Any>>.printMatrix() = map { it.toList() }.printMatrix()
fun Array<IntArray>.printMatrix() = map { it.toList() }.printMatrix()
fun Array<LongArray>.printMatrix() = map { it.toList() }.printMatrix()
