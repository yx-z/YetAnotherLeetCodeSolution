package median

class Sol5 {

    fun longestPalindrome(s: String): String {
        val l = s.length
        // dp[i, j]: len of longest palindrome starting at s[i], ending at s[j]
        val dp = Array(l) { i ->
            Array(l) { j ->
                when {
                    i == j -> 1
                    j == i + 1 && s[i] == s[j] -> 2
                    else -> null
                }
            }
        }
        for (i in l - 3 downTo 0) {
            for (j in i + 2 until l) {
                if (s[i] == s[j]) {
                    dp[i][j] = if (s[i] == s[j] && dp[i + 1][j - 1] != null) {
                        dp[i + 1][j - 1]!! + 2
                    } else {
                        null
                    }
                }
            }
        }

        var maxLen = 0
        var maxStr = ""
        for (i in 0 until l) {
            for (j in i until l) {
                if (dp[i][j] != null && dp[i][j]!! > maxLen) {
                    maxLen = dp[i][j]!!
                    maxStr = s.substring(i, j + 1)
                }
            }
        }
        return maxStr
    }
}