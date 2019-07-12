package median

class Sol518 {

    fun change(amount: Int, coins: IntArray): Int {
        val n = coins.size
        if (n == 0) {
            return if (amount == 0) 1 else 0
        }
        val dp = Array(n) { i ->
            Array(amount + 1) { j ->
                when {
                    j == 0 -> 1
                    i == n - 1 && j % coins[i] == 0 -> 1
                    else -> 0
                }
            }
        }
        for (i in n - 2 downTo 0) {
            for (j in 0..amount) {
                dp[i][j] = dp[i + 1][j]
                if (j - coins[i] >= 0) {
                    dp[i][j] += dp[i][j - coins[i]]
                }
            }
        }
        return dp[0][amount]
    }
}
