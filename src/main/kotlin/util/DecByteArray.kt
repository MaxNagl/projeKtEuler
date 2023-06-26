package util

fun ByteArray.plusDec(other: ByteArray): ByteArray {
    val longer = if (this.size > other.size) this else other
    val shorter = if (this.size > other.size) other else this
    val result = ByteArray(longer.size)
    var overflow = 0
    (1..shorter.size).forEach { i ->
        val r = longer[longer.size - i] + shorter[shorter.size - i] + overflow
        result[result.size - i] = (r % 10).toByte()
        overflow = r / 10
    }
    ((shorter.size + 1)..longer.size).forEach { i->
        val r = longer[longer.size - i] + overflow
        result[result.size - i] = (r % 10).toByte()
        overflow = r / 10
    }

    return if (overflow == 0) result else ByteArray(result.size + 1).apply {
        set(0, overflow.toByte())
        System.arraycopy(result, 0, this, 1, result.size)
    }
}

fun ByteArray.multiDec(other: ByteArray): ByteArray {
    if (this.size == 1) {
        if (this[0] == 0.toByte()) return this
        if (this[0] == 1.toByte()) return other
    }
    if (other.size == 1) {
        if (other[0] == 0.toByte()) return other
        if (other[0] == 1.toByte()) return this
    }
    val longer = if (this.size > other.size) this else other
    val shorter = if (this.size > other.size) other else this
    val result = ByteArray(shorter.size + longer.size - 1)
    var r = 0
    (result.indices).forEach { i ->
        shorter.indices.forEach { j ->
            val ai = result.size - i - j - 1
            if (ai >= 0 && ai < longer.size) {
                r += longer[ai] * shorter[j]
            }
        }
        result[result.size - i - 1] = (r % 10).toByte()
        r /= 10
    }
    if (r == 0) return result
    val r2 = ByteArray(result.size + 1)
    r2[0] = (r % 10).toByte()
    System.arraycopy(result,0, r2, 1, result.size)
    return r2
    //return if (result[0] == 0.toByte()) result.copyOfRange(1, result.size) else result
}

fun ByteArray.toDecString(): String {
    val chars = CharArray(this.size)
    chars.indices.forEach { chars[it] = '0' + this[it].toInt() }
    return String(chars)
}

fun Int.toDecByteArray(): ByteArray {
    if (this == 0) return byteArrayOf(0)
    val array = ByteArray(10)
    var i = this
    var pos = 9
    while (i != 0) {
        array[pos--] = (i % 10).toByte()
        i /= 10
    }
    return array.copyOfRange(pos + 1, array.size)
}

fun Long.toDecByteArray(): ByteArray {
    if (this == 0L) return byteArrayOf(0)
    val array = ByteArray(20)
    var i = this
    var pos = 19
    while (i != 0L) {
        array[pos--] = (i % 10).toByte()
        i /= 10
    }
    return array.copyOfRange(pos + 1, array.size)
}

fun main() {
    testPlus(123, 456)
    testPlus(456, 789)
    testPlus(12345, 2)
    testPlus(2, 12345)
    testPlus(99999, 2)
    testPlus(2, 99999999)
    testMulti(102030, 12)
    testMulti(102030, 56)
    testMulti(102030, 6)
    testMulti(102030, 976)
    testMulti(102030, 9762)
    testMulti(102030, 97624)
    testMulti(12030, 97625)
    testMulti(102030, 973625)
    testMulti(0, 973625)
    testMulti(1, 973625)
    testMulti(1, 1)
    testMulti(5987, 1)
    testMulti(0, 0)
}

private fun testPlus(i: Int, j: Int) {
    val a = i.toDecByteArray()
    val b = j.toDecByteArray()
    val r = a.plusDec(b)
    val c = (i + j).toDecByteArray()
    if (r.asList() != c.asList()) error("${a.toDecString()} + ${b.toDecString()} != ${r.toDecString()}. Shout be: $i + $j = ${i + j}")
}

private fun testMulti(i: Int, j: Int) {
    val a = i.toDecByteArray()
    val b = j.toDecByteArray()
    val r = a.multiDec(b)
    val c = (i.toLong() * j.toLong()).toDecByteArray()
    if (r.asList() != c.asList()) error("${a.toDecString()} * ${b.toDecString()} != ${r.toDecString()}. Shout be: $i * $j = ${i * j}")
}