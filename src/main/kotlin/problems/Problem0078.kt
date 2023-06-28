package problems

fun main() = runProblem()

class Problem0078 : Problem(null) {
    override fun calc(): Any {
        return euler()
    }

    private fun euler(): Any {
        val limit = 100000
        val a = IntArray(limit) { 1 }
        for (n in (2 until a.size)) {
            var sign = -1
            var p = a[n - 1] + a[n - 2]
            var j = 2
            var i = n - 5

            while (i >= 0) {
                p += a[i] * sign
                i -= j
                if (i >= 0) {
                    p += a[i] * sign
                    i -= j++ * 2 + 1
                    sign = -sign
                }
            }
            p = (p + 1000000) % 1000000
            if (p == 0) return n
            a[n] = p
        }
        return 0
    }

    private fun mine(): Any {
        val limit = 60000
        val a = IntArray(limit) { 1 }
        for (n in (2 until a.size)) {
            for (m in (n until a.size)) {
                a[m] = (a[m] + a[(m - n)]) % 1000000
                if (m == n && a[m] == 0) return m
            }
        }
        return 0
    }
}