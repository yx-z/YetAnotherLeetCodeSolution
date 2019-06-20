package median

import kotlin.math.min

class Sol55 {

    fun canJump(nums: IntArray): Boolean {
        val n = nums.size
        val dp = BooleanArray(n)
        dp[n - 1] = true
        for (i in n - 2 downTo 0) {
            for (j in i + 1..min(n - 1, i + nums[i])) {
                if (dp[j]) {
                    dp[i] = true
                    break
                }
            }
        }
        return dp[0]
    }
}