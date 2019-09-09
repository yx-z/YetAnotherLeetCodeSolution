package easy

import kotlin.math.max

class Sol53 {

    fun maxSubArray(nums: IntArray): Int {
        val n = nums.size
        val dp = IntArray(n).apply { this[n - 1] = nums[n - 1] }
        for (i in n - 2 downTo 0) dp[i] = max(nums[i], nums[i] + dp[i + 1])
        return dp.max()!!
    }
}