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

fun ByteArray.toDecString(): String {
    val chars = CharArray(this.size)
    repeat(this.size) { chars[it] = '0' + this[it].toInt() }
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

fun main() {
    testPlus(123, 456)
    testPlus(456, 789)
    testPlus(12345, 2)
    testPlus(2, 12345)
    testPlus(99999, 2)
    testPlus(2, 99999999)
}

private fun testPlus(i: Int, j: Int) {
    val a = i.toDecByteArray()
    val b = j.toDecByteArray()
    val r = a.plusDec(b)
    val c = (i + j).toDecByteArray()
    if (r.asList() != c.asList()) error("${a.toDecString()} + ${b.toDecString()} != ${r.toDecString()}. Shout be: $i + $j = ${i + j}")
}