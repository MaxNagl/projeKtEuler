package problems

import java.math.BigInteger

fun main() = runProblem()

class Problem0029 : Problem(9183) {
    override fun calc(): Any {
        val nums = HashSet<BigInteger>()
        (2..100).forEach { a->
            val num = BigInteger.valueOf(a.toLong())
            var pot = BigInteger.valueOf(a.toLong())
            (2..100).forEach {
                pot *= num
                nums.add(pot)
            }
        }
        return nums.size
    }
}

