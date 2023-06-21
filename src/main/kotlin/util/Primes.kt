package util

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun primes(limit: Int): IntArray {
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
    i@ for (i in 3 .. limit step 2) {
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

fun primesCheckArray(limit: Int) = BooleanArray(limit + 1).also {
    primes(limit).forEach { p -> it[p] = true }
}

fun Int.isPrime(checks: IntArray): Boolean {
    if (this < 2) return false
    for (p in checks) {
        if (p * p > this) return true
        if (this % p == 0) return false
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
