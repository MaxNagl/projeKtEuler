package util

import kotlin.math.max

fun List<List<Any>>.printMatrix() {
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
