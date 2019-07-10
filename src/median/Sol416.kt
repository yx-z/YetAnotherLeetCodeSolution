package median

class Sol416 {

    fun canPartition(nums: IntArray): Boolean {
        val sum = nums.sum()
        if (sum % 2 != 0) {
            return false
        }
        val t = sum / 2
        val n = nums.size
        // dp[0 until n, 0..t]
        // dp[i, j] = true <=> nums[i until n] can be summed to j
        val dp = Array(n) { i ->
            Array(t + 1) { j ->
                when {
                    j == 0 -> true
                    i == n - 1 -> j == nums[i]
                    else -> false
                }
            }
        }
        for (i in n - 2 downTo 0) {
            for (j in 0..t) {
                dp[i][j] =
                    dp[i + 1][j] || (j - nums[i] >= 0 && dp[i + 1][j - nums[i]])
            }
        }
        return dp[0][t]
    }
}