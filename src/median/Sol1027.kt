package median

import kotlin.math.max

class Sol1027 {

    fun longestArithSeqLength(A: IntArray): Int {
        val n = A.size
        // dp[i][j] = max len arithmetic seq starting at A[i] w/ diff j
        val dp = Array(n) { HashMap<Int, Int>() }
        var res = 2
        for (i in n - 1 downTo 0) {
            for (j in i + 1 until n) {
                val diff = A[j] - A[i]
                if (diff !in dp[i]) {
                    dp[i][diff] = 1 + (dp[j][diff] ?: 1)
                    res = max(res, dp[i][diff]!!)
                }
            }
        }
        return res
    }
}