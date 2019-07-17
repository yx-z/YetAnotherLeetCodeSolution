package hard

class Sol32 {

    fun longestValidParentheses(s: String): Int {
        val n = s.length
        if (n < 2) return 0
        val dp = IntArray(n)
        if (s[0] == '(' && s[1] == ')') dp[1] = 2
        for (i in 2 until n) {
            if (s[i] == ')') {
                dp[i] = when {
                    s[i - 1] == '(' -> dp[i - 2] + 2
                    i - dp[i - 1] > 0 && s[i - dp[i - 1] - 1] == '(' ->
                        dp[i - 1] + 2 + if (i - dp[i - 1] >= 2) dp[i - dp[i - 1] - 2] else 0
                    else -> 0
                }
            }
        }
        return dp.max()!!
    }
}