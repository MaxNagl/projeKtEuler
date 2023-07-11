package util

import kotlinx.coroutines.*
import java.math.BigInteger
import kotlin.math.sqrt
import kotlin.random.Random
import kotlin.system.measureTimeMillis

fun primes(limit: Int): IntArray = primesSieve(limit).indexesOfTrue()

fun primesAlternate(limit: Int): IntArray {
    var steps = 10_000
    while (steps * steps <= limit) steps *= 10
    var primes = primesDirect(limit.coerceAtMost(steps))
    if (limit > steps) {
        runBlocking {
            withContext(Dispatchers.Default) {
                var s = steps
                val tasks = ArrayList<Deferred<IntArray>>()
                while (s < limit) {
                    val start = s
                    val end = s + steps - 1
                    tasks.add(async { primesRage(start, end.coerceAtMost(limit), primes) })
                    s = end + 1
                }
                var pos = primes.size
                primes = primes.copyOf(pos + tasks.sumOf { it.await().size })
                tasks.forEach {
                    val p = it.await()
                    System.arraycopy(p, 0, primes, pos, p.size)
                    pos += p.size
                }
            }
        }
    }
    return primes
}

private fun primesDirect(limit: Int): IntArray {
    var primes = IntArray(1000)
    var pos = 0
    primes[pos++] = 2
    var lastCheck = 2
    var lastCheckPos = 0
    var incLastCheck = lastCheck * lastCheck
    i@ for (i in 3..limit step 2) {
        if (i > incLastCheck) {
            lastCheck = primes[++lastCheckPos]
            incLastCheck = lastCheck * lastCheck
        }
        for (c in primes) {
            if (i % c == 0) continue@i
            if (c == lastCheck) break
        }
        if (pos >= primes.size) primes = primes.copyOf(primes.size * 2)
        primes[pos++] = i
    }
    return primes.copyOf(pos)
}

private fun primesRage(start: Int, end: Int, checks: IntArray): IntArray {
    var lastCheck = checks[0]
    var lastCheckPos = 0
    var incLastCheck = lastCheck * lastCheck
    while (incLastCheck < start) {
        lastCheck = checks[++lastCheckPos]
        incLastCheck = lastCheck * lastCheck
    }
    var primes = IntArray(1000)
    var pos = 0
    i@ for (i in start..end) {
        if (i > incLastCheck) {
            lastCheck = checks[++lastCheckPos]
            incLastCheck = lastCheck * lastCheck
        }
        for (c in checks) {
            if (i % c == 0) continue@i
            if (c == lastCheck) break
        }
        if (pos >= primes.size) primes = primes.copyOf(primes.size * 2)
        primes[pos++] = i
    }
    return primes.copyOf(pos)
}

fun primesSieve(limit: Int): BooleanArray {
    val array = BooleanArray(limit + 1)
    array.fill(true)
    array[0] = false
    array[1] = false
    val max = sqrt(limit.toFloat()).toInt()
    (2..max).forEach { p ->
        if (array[p]) {
            ((p + p) ..limit step p).forEach {
                array[it] = false
            }
        }
    }
    return array
}

fun BooleanArray.indexesOfTrue(): IntArray {
    val primes = IntArray(count { it })
    var i = 0
    forEachIndexed { index, b -> if (b) primes[i++] = index }
    return primes
}

private fun millerRabinTest(d: Int, n: Int): Boolean {
    val a = 2 + Random.nextInt (n - 4)
    var t = a % n
    var e = d
    var x = 1

    while (e > 0) {
        if (e % 2 == 1) x = (x * t) % n
        e /= 2
        t = (t * t) % n
    }

    t = d
    if (x == 1 || x == n - 1) return true
    while (t != n - 1) {
        x = x * x % n
        t *= 2
        if (x == 1) return false
        if (x == n - 1) return true
    }
    return false
}

private fun isProbablePrime(n: Int, k: Int): Boolean {
    if (n <= 1 || n == 4) return false
    if (n <= 3) return true
    var d = n - 1
    while (d % 2 == 0) d /= 2
    for (i in 0 until k) if (!millerRabinTest(d, n)) return false
    return true
}

fun Int.isPrime(checks: IntArray): Boolean {
    if (this < 2) return false
    val last = sqrt(this.toFloat()).toInt()
    for (p in checks) {
        if (p > last) return true
        if (this % p == 0) return false
    }
    return true
}

fun Long.isPrime(checks: IntArray): Boolean {
    if (this < 2) return false
    val last = sqrt(this.toFloat()).toInt()
    for (p in checks) {
        if (p > last) return true
        if (this % p == 0L) return false
    }
    return true
}

fun main() {
    for (i in 1..10) primes(100_000)
    println("Primes until 1,000,000 in " + measureTimeMillis {
        for (i in 1..10) primes(1_000_000)
    } / 10 + "ms")
    println("Primes until 10,000,000 in " + measureTimeMillis {
        for (i in 1..10) primes(10_000_000)
    } / 10 + "ms")
    println("Primes until 100,000,000 in " + measureTimeMillis {
        primes(100_000_000)
    } + "ms")
    println(primes(2_000_000).sum())
}
