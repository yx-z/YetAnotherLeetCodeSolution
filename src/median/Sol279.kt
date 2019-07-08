package median

import kotlin.math.min

class Sol279 {

    fun numSquares(n: Int): Int {
        val dp = Array(n + 1) { Int.MAX_VALUE }
        var k = 0
        while (k * k <= n) {
            dp[k * k] = 1
            k++
        }
        for (i in 2..n) {
            if (dp[i] == Int.MAX_VALUE) {
                for (j in 1 until i) {
                    dp[i] = min(dp[i], dp[j] + dp[i - j])
                }
            }
        }
        return dp[n]
    }
}