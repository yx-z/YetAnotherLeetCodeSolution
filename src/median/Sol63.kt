package median

class Sol63 {

    fun uniquePathsWithObstacles(G: Array<IntArray>): Int {
        val m = G.size
        if (m == 0) return 0
        val n = G[0].size
        val dp = Array(m) { IntArray(n) }
        dp[m - 1][n - 1] = 1 - G[m - 1][n - 1]
        (n - 2 downTo 0).filter { G[m - 1][it] == 0 }
            .forEach { dp[m - 1][it] = dp[m - 1][it + 1] }
        (m - 2 downTo 0).filter { G[it][n - 1] == 0 }
            .forEach { dp[it][n - 1] = dp[it + 1][n - 1] }
        (m - 2 downTo 0).forEach { r ->
            (n - 2 downTo 0).filter { G[r][it] == 0 }.forEach { c ->
                dp[r][c] = dp[r + 1][c] + dp[r][c + 1]
            }
        }
        return dp[0][0]
    }
}