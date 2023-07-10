package util

fun BooleanArray.inc() {
    var i = size - 1
    set(i, !get(i))
    while (i > 0 && !get(i)) {
        i--
        set(i, !get(i))
    }
}
