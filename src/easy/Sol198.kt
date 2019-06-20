package easy

import kotlin.math.max

class Sol198 {
    fun rob(nums: IntArray): Int {
        val l = nums.size
        if (l == 0) {
            return 0
        }

        // dp[i]: best i can rob if i start robbing from day i
        val dp = IntArray(l + 1)
        dp[l - 1] = nums[l - 1]
        for (i in l - 2 downTo 0) {
            dp[i] = max(nums[i] + dp[i + 2], dp[i + 1])
        }

        return dp[0]
    }
}