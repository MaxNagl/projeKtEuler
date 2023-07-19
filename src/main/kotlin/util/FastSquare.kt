package util

import kotlin.math.sqrt
import kotlin.system.measureTimeMillis

class FastSquare {
    val squareCheck = BooleanArray(0x10000).apply {
        for (i in 1L..0x4000) this[((i * i) and 0xFFFF).toInt()] = true
    }

    fun isPosSquare(x: Int): Boolean {
        val last = x and 0xFF
        if (!squareCheck[last]) return false
        val s = sqrt(x.toFloat()).toInt()
        return s * s == x
    }

    fun isSquare(x: Int): Boolean {
        if (x < 0) return false
        val last = x and 0xFF
        if (!squareCheck[last]) return false
        val s = sqrt(x.toFloat()).toInt()
        return s * s == x
    }

    fun isPosSquare(x: Long): Boolean {
        val last = (x and 0xFFFF).toInt()
        if (!squareCheck[last]) return false
        val s = if (x > 281475010265088) sqrt(x.toDouble()).toLong() else sqrt(x.toFloat()).toLong()
        return s * s == x
    }

    fun isSquare(x: Long): Boolean {
        if (x < 0) return false
        val last = (x and 0xFFFF).toInt()
        if (!squareCheck[last]) return false
        val s = if (x > 281475010265088) sqrt(x.toDouble()).toLong() else sqrt(x.toFloat()).toLong()
        return s * s == x
    }
}

fun main() {
    val fs = FastSquare()
    repeat(3) {
        print("Benchmark sqrt(int) * sqrt(int) == i: ")
        println(measureTimeMillis {
            var count = 0
            for (i in 0..100000000) {
                val s = sqrt(i.toFloat()).toInt()
                if (s * s == i) count++
            }
            if (count != 10001) error("Wrong number of squares")
        }.toString() + " ms.")
        print("Benchmark sqrt(long) * sqrt(long) == i: ")
        println(measureTimeMillis {
            var count = 0
            for (i in 0L..100000000L) {
                val s = sqrt(i.toDouble()).toLong()
                if (s * s == i) count++
            }
            if (count != 10001) error("Wrong number of squares")
        }.toString() + " ms.")
        print("Benchmark isSquare(int): ")
        println(measureTimeMillis {
            var count = 0
            for (i in 0..100000000) {
                if (fs.isSquare(i)) count++
            }
            if (count != 10001) error("Wrong number of squares")
        }.toString() + " ms.")
        print("Benchmark isPosSquare(int): ")
        println(measureTimeMillis {
            var count = 0
            for (i in 0..100000000) {
                if (fs.isPosSquare(i)) count++
            }
            if (count != 10001) error("Wrong number of squares")
        }.toString() + " ms.")
        print("Benchmark isPosSquare(long): ")
        println(measureTimeMillis {
            var count = 0
            for (i in 0L..100000000L) {
                if (fs.isPosSquare(i)) count++
            }
            if (count != 10001) error("Wrong number of squares")
        }.toString() + " ms.")
        print("Benchmark isSquare(long): ")
        println(measureTimeMillis {
            var count = 0
            for (i in 0L..100000000L) {
                if (fs.isSquare(i)) count++
            }
            if (count != 10001) error("Wrong number of squares")
        }.toString() + " ms.")
    }

    print("Check all integer values: ")
    println(measureTimeMillis {
        var nextSquare = 0
        var nextSqureInt = 0
        for (i in 0..Int.MAX_VALUE) {
            if (i == nextSquare) {
                if (!fs.isSquare(i)) error("$i")
                nextSquare = ++nextSqureInt * nextSqureInt
            } else if (fs.isSquare(i)) error("$i")
        }
    }.toString() + " ms.")

    print("Check long i² - 1, i², i² + 1 for all Int values: ")
    println(measureTimeMillis {
        for (i in 2..Int.MAX_VALUE) {
            val q = i.toLong() * i
            for (t in listOf(q - 1, q, q + 1)) {
                if (fs.isSquare(t) != (t == q)) error("C $t ")
            }
        }
    }.toString() + " ms.")
}