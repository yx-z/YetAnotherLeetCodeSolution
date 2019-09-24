package hard

import kotlin.math.max

// best time to buy and sell stock series
class Sol123 {

    fun maxProfit(prices: IntArray): Int {
        val n = prices.size
        if (n == 0) return 0
        val maxTransactions = 2
        // P[i, j, k] = max profit in day 0..i given that
        // 1. we have done j transactions (SELL j times)
        // 2. we have k stocks on hold (about to sell)
        // in this problem, we have i in 0 until n, j in 0..2, and k in 0..1
        val P = Array(n) {
            Array(maxTransactions + 1) { IntArray(2) { Int.MIN_VALUE / 2 } }
        }
        // init. P w/ all -inf to represent invalid (MIN / 2 to avoid overflow)

        // on day 0, we can either buy or do nothing, but not sell
        // with buy, we have not yet finished/sold a transaction,
        // but do have 1 stock in hand
        P[0][0][0] = 0
        P[0][0][1] = -prices[0]
        for (i in 1 until n) {
            // represents do nothing
            P[i][0][0] = 0
            // P[i, j, k] = max between do nothing (= P[i - 1, j, k])
            //                  and do something (varies by case)
            P[i][0][1] = max(P[i - 1][0][1], -prices[i])
            for (j in 1..maxTransactions) {
                // rest or buy
                P[i][j][0] = max(P[i - 1][j][0], P[i - 1][j - 1][1] + prices[i])
                // rest or sell
                P[i][j][1] = max(P[i - 1][j][1], P[i - 1][j][0] - prices[i])
            }
        }
        return max(0, (0..maxTransactions).map { P[n - 1][it][0] }.max()!!)
    }
}