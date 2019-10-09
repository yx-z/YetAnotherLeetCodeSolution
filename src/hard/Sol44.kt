package hard

class Sol44 {

    fun isMatch(s: String, p: String): Boolean {
        val m = s.length
        val n = p.length

        // dp[i][j] = whether s[i..m] matches p[j..n], i in 0..m, j in 0..n
        val dp = Array(m + 1) { BooleanArray(n + 1) }
        // space: O(mn)

        // base cases:
        // dp[m][n] = true, since s[m] = p[n] = ''
        dp[m][n] = true
        // dp[i][n] = false for i in 0 until m,
        // since nonempty s cannot match empty p
        // be explicit, even though values are set to be false by default
        for (i in 0 until m) {
            dp[i][n] = false
        }
        // dp[m][j] = (p[j..n] == "**...") since only '*' can match empty s
        // note that even '?' cannot match empty s
        for (j in n - 1 downTo 0) {
            if (p[j] == '*') {
                dp[m][j] = true
            } else {
                break
            }
        }

        // recursive cases:
        // dp[i][j] =
        // if p[j] != '*' (easy case):
        //   dp[i + 1][j + 1] && (s[i] == p[j] || p[j] == '?')
        // otherwise (p[j] == '*'):
        //   dp[i + 1][j] ('*' matches s[i] and potentially more in s[i + 1..m])
        //   || dp[i][j + 1] ('*' matches empty)

        // dp[i][j] depends on dp[i + 1][*] and dp[i][j + 1]
        // so we traverse in reverse order on i, j
        for (i in m - 1 downTo 0) {
            for (j in n - 1 downTo 0) {
                dp[i][j] =
                    if (p[j] != '*')
                        dp[i + 1][j + 1] && (s[i] == p[j] || p[j] == '?')
                    else
                        dp[i + 1][j] || dp[i][j + 1]
            }
        }
        // time: O(mn)

        // whether s[0..m] matches p[0..n]
        return dp[0][0]
    }
}