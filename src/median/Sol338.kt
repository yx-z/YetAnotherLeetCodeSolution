package median

class Sol338 {

    fun countBits(num: Int): IntArray {
        val dp = IntArray(num + 1)
        for (i in 1..num) dp[i] = dp[i shr 1] + (i and 1)
        return dp
    }
}