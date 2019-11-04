package median

class Sol837 {

    fun new21Game(N: Int, K: Int, W: Int): Double {
        if (K == 0 || N >= K + W) {
            return 1.0
        }
        // dp[i]: probability of getting i points
        val dp = DoubleArray(N + 1).apply { this[0] = 1.0 }
        var window = 1.0
        dp.indices.drop(1).forEach {
            dp[it] = window / W
            if (it < K) {
                window += dp[it]
            }
            if (it - W >= 0) {
                window -= dp[it - W]
            }
        }
        return dp.drop(K).sum()
    }
}