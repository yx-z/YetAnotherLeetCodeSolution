package median

class Sol518 {

    fun change(amount: Int, coins: IntArray): Int {
        val dp = Array(amount + 1) { if (it == 0) 1 else 0 }
        for (c in coins) {
            for (i in c..amount) {
                dp[i] += dp[i - c]
            }
        }
        return dp[amount]
    }
}
