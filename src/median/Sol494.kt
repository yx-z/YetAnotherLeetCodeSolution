package median

import kotlin.math.abs

class Sol494 {

    fun findTargetSumWays(nums: IntArray, S: Int): Int {
        val n = nums.size
        val s = nums.sum()
        if (s < abs(S)) {
            return 0
        }
        val dp = Array(n) { i ->
            Array(s + 1) { j ->
                if (i == n - 1 && nums[i] == j) (if (j == 0) 2 else 1)
                else 0
            }
        }
        for (i in n - 2 downTo 0) {
            for (j in 0..s) {
                dp[i][j] = dp[i + 1][abs(j - nums[i])] +
                        (if (j + nums[i] <= s) dp[i + 1][j + nums[i]] else 0)
            }
        }
        return dp[0][abs(S)]
    }
}