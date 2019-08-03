package hard

import kotlin.math.min

class Sol72 {

    fun minDistance(w1: String, w2: String): Int {
        val m = w1.length
        val n = w2.length
        val dp = Array(m + 1) { IntArray(n + 1) }
        for (j in 0..n) dp[0][j] = j
        for (i in 0..m) dp[i][0] = i
        for (i in 1..m) {
            for (j in 1..n) {
                dp[i][j] = min(
                    min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
                    dp[i - 1][j - 1] + if (w1[i - 1] == w2[j - 1]) 0 else 1
                )
            }
        }
        return dp[m][n]
    }
}