package problems

import util.primeFactors

fun main() = runProblem()

class Problem0003 : Problem(6857) {
    override fun calc(): Any {
        return primeFactors(600851475143L).last()
    }
}