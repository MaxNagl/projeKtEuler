package util

import kotlin.math.sign

fun String.isPalindrome(): Boolean {
    val l = length - 1
    for (i in 0..l / 2) if (get(i) != get(l - i)) return false
    return true
}

fun String.sumOfDigits(): Int {
    return sumOf { it.code - 48 }
}

fun String.numberToWord(): String {
    val sb = java.lang.StringBuilder()
    var last = '0'
    var lastLast = '0'
    forEachIndexed { index, c ->
        val pos = length - index - 1
        val smallPos = pos % 3
        if (c != '0' && smallPos == 0 && last == '0' && lastLast != '0') sb.append("and")
        if (c != '0' && smallPos == 1 && last != '0') sb.append("and")
        if (smallPos == 0 && last == '1') sb.append(
            when (c) {
                '1' -> "eleven"
                '2' -> "twelve"
                '3' -> "thirteen"
                '4' -> "fourteen"
                '5' -> "fifteen"
                '6' -> "sixteen"
                '7' -> "seventeen"
                '8' -> "eighteen"
                '9' -> "nineteen"
                '0' -> "ten"
                else -> ""
            }
        ) else if (smallPos == 1) sb.append(
            when (c) {
                '1' -> ""
                '2' -> "twenty"
                '3' -> "thirty"
                '4' -> "forty"
                '5' -> "fifty"
                '6' -> "sixty"
                '7' -> "seventy"
                '8' -> "eighty"
                '9' -> "ninety"
                else -> ""
            }
        ) else sb.append(
            when (c) {
                '1' -> "one"
                '2' -> "two"
                '3' -> "three"
                '4' -> "four"
                '5' -> "five"
                '6' -> "six"
                '7' -> "seven"
                '8' -> "eight"
                '9' -> "nine"
                else -> ""
            }
        )
        if (smallPos == 2 && c != '0') sb.append("hundred")
        if (smallPos == 0) sb.append(
            when (pos) {
                21 -> "Sextillion"
                18 -> "Quintillion"
                15 -> "quadrillion"
                12 -> "trillion"
                9 -> "billion"
                6 -> "million"
                3 -> "thousand"
                else -> ""
            }
        )
        lastLast = last
        last = c
    }
    return sb.toString()
}

fun String.parseRoman(): Int? {
    var value = 0
    var last = ' '
    var lastValue = 0
    var lastCount = 0
    forEach { c ->
        if (last != c) {
            val newValue = when (c) {
                'I' -> 1; 'V' -> 5
                'X' -> 10; 'L' -> 50
                'C' -> 100; 'D' -> 500
                'M' -> 1000
                else -> return null
            }
            value += lastValue * lastCount * (lastValue - newValue).sign
            last = c
            lastValue = newValue
            lastCount = 1
        } else lastCount++
    }
    return value + lastValue * lastCount
}

private val romanParts = listOf(
    "M" to 1000, "CM" to 900, "DCCC" to 800, "DCC" to 700, "DC" to 600,
    "D" to 500, "CD" to 400, "CCC" to 300, "CC" to 200, "C" to 100,
    "XC" to 90, "LXXX" to 80, "LXX" to 70, "LX" to 60,
    "L" to 50, "XL" to 40, "XXX" to 30, "XX" to 20, "X" to 10,
    "IX" to 9, "VIII" to 8, "VII" to 7, "VI" to 6,
    "V" to 5, "IV" to 4, "III" to 3, "II" to 2, "I" to 1,
)

fun Int.toRomanString(): String {
    val text = StringBuilder()
    var i = this
    romanParts.forEach {
        while (i >= it.second) {
            text.append(it.first)
            i -= it.second
        }
    }
    return text.toString()
}
