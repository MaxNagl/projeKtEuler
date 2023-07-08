package problems

fun main() = runProblem()

class Problem0109 : Problem(38182) {

    override fun calc(): Any {
        val shots = mutableListOf(25 to 25, 25 to 50)
        (1..20).forEach { i ->
            (1..3).forEach { m ->
                shots.add(i to i * m)
            }
        }

        var checkout = 0
        shots.forEachIndexed { s1i, s1 ->
            if (s1.first * 2 == s1.second && s1.second < 100) checkout++
            shots.forEachIndexed { s2i, s2 ->
                if (s2.first * 2 == s2.second && s1.second + s2.second < 100) checkout++
                if (s1i >= s2i) {
                    shots.forEach { s3 ->
                        if (s3.first * 2 == s3.second && s1.second + s2.second + s3.second < 100) checkout++
                    }
                }
            }
        }
        return checkout
    }
}
