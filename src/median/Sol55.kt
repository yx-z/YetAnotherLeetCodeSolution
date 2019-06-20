package median

import kotlin.math.max
import kotlin.math.min

class Sol55 {

    // O(n^2)
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

    // O(n)
    fun canJump2(nums: IntArray): Boolean {
        val n = nums.size
        var m = 0
        for (i in 0 until n) {
            if (m < i) return false
            m = max(m, i + nums[i])
        }
        return m >= n - 1
    }
}