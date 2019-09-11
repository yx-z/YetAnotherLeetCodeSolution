package median

class Sol377 {

    fun combinationSum4(nums: IntArray, t: Int): Int {
        // dp[i]: # of ways elems in nums summing up to i
        val dp = IntArray(t + 1).apply { this[0] = 1 }
        for (i in 1..t) nums.filter { i >= it }.forEach { dp[i] += dp[i - it] }
        return dp[t]
    }
}
