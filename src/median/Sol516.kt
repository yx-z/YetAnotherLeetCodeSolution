package median

import kotlin.math.max

class Sol516 {

    fun longestPalindromeSubseq(s: String): Int {
        val l = s.length
        val dp = Array(l) { i -> Array(l) { if (i == it) 1 else 0 } }
        for (i in l - 2 downTo 0) {
            for (j in i + 1 until l) {
                dp[i][j] = max(
                    max(dp[i + 1][j], dp[i][j - 1]),
                    dp[i + 1][j - 1] + (if (s[i] == s[j]) 2 else 0)
                )
            }
        }
        return dp[0][l - 1]
    }
}