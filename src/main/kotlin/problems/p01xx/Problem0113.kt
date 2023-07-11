package problems.p01xx

import problems.Problem
import problems.runProblem

fun main() = runProblem()

class Problem0113 : Problem(51161058134250) {
    override fun calc(): Any {
        val nums = LongArray(10)
        nums[0] = 1
        val len = 100
        var dec = 0L
        for (x in 1..len) {
            for (l in 1..9) nums[l] = nums[l] + nums[l - 1]
            dec += nums.sum() - 1
        }
        return nums.sum() + dec - len * 9 - 1
    }
}
