package median

class Sol322 {

    fun coinChange(coins: IntArray, amount: Int): Int {
        val dp = Array(amount + 1) {
            when {
                it == 0 -> 0
                coins.contains(it) -> 1
                else -> -1
            }
        }
        (1..amount).forEach { i ->
            dp[i] = coins.filter { i >= it && dp[i - it] != -1 }
                .map { dp[i - it] + 1 }.min() ?: -1
        }
        return dp[amount]
    }
}