package median

class Sol377 {

    fun combinationSum4(nums: IntArray, t: Int): Int {
        // dp[i]: # of ways elems in nums summing up to i
        val dp = IntArray(t + 1)
        for (i in 1..t) {
            dp[i] = if (i in nums) 1 else 0
            nums.filter { i - it > 0 }.forEach { dp[i] += dp[i - it] }
        }
        return dp[t]
    }
}
