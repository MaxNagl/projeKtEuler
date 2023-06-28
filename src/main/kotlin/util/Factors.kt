package util

import kotlin.math.sqrt

fun primeFactorForeach(num: Long, primes: IntArray? = null, block: (Int, Int) -> Unit) {
    var f = 0
    var count = 0
    primeFactors(num, primes).forEach {
        if (f != it) {
            if (count != 0) block(it, count)
            f = it
            count = 1
        } else count++
    }
    if (count != 0) block(f, count)
}

fun primeFactors(num: Long, primes: IntArray? = null): IntArray {
    if (num < 2) return IntArray(0)
    val ps = primes ?: primes(sqrt(num.toFloat()).toInt() + 1)
    val factors = ArrayList<Int>()
    var n = num
    ps.forEach {
        while (n % it == 0L) {
            factors.add(it)
            n /= it
        }
        if (n == 1L) return factors.toIntArray()
    }
    factors.add(n.toInt())
    return factors.toIntArray()
}

fun primeFactors(num: Long, primes: IntArray? = null, block: (Int) -> Unit) {
    val ps = primes ?: primes(sqrt(num.toFloat()).toInt() + 1)
    var n = num
    ps.forEach {
        while (n % it == 0L) {
            block(it)
            n /= it
        }
        if (n == 1L) return
    }
    block(n.toInt())
    return
}

fun primeFactors(num: Int, primes: IntArray? = null, block: (Int) -> Unit) {
    val ps = primes ?: primes(sqrt(num.toFloat()).toInt() + 1)
    var n = num
    ps.forEach {
        while (n % it == 0) {
            block(it)
            n /= it
        }
        if (it * it > n) {
            if (n != 1) block(n)
            return
        }
    }
    block(n)
    return
}

fun Int.eulerPhi(primes: IntArray? = null): Int {
    var count = 1
    var last = 0
    primeFactors(this, primes) { f ->
        count *= if (f == last) f else (f - 1)
        last = f
    }
    return count
}

fun divisorsProper(num: Int, block: (Int) -> Unit) {
    val limit = sqrt(num.toFloat()).toInt()
    block(1)
    for (d in 2..limit) {
        if (num % d == 0) {
            block(d)
            if (d * d != num) block(num / d)
        }
    }
}

fun divisorsProperSum(num: Int): Int {
    val limit = sqrt(num.toFloat()).toInt()
    var sum = 1
    for (d in 2..limit) {
        if (num % d == 0) {
            sum += d
            if (d * d != num) sum += num / d
        }
    }
    return sum
}