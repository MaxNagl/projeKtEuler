package problems

fun main() = runProblem()

class Problem0042 : Problem(162) {
    override fun calc(): Any {
        val triangles = BooleanArray(1000)
        (1..20).forEach { i -> triangles[(i * i + i) / 2] = true }
        return getData { it.replace("\"", "").split(",") }
            .count { word -> triangles[word.sumOf { it.code - 'A'.code + 1 }] }
    }
}
