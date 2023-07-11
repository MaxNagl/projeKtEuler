package util

fun <T> MutableMap<T, Int>.inc(key: T) = put(key, (get(key) ?: 0) + 1)