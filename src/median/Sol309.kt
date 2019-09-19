package median

import kotlin.math.max

class Sol309 {

    fun maxProfit(prices: IntArray): Int {
        val n = prices.size
        if (n <= 1) {
            return 0
        }

        val profit = IntArray(n)
        var diff = Int.MIN_VALUE
        for (i in 0 until n) {
            if (i < 2) {
                diff = max(diff, -prices[i])
            }
            when (i) {
                0 -> profit[0] = 0
                1 -> profit[1] = max(prices[1] - prices[0], 0)
                else -> {
                    profit[i] = max(profit[i - 1], diff + prices[i])
                    diff = max(diff, profit[i - 2] - prices[i])
                }
            }
        }
        return profit[n - 1]
    }

    fun redo(prices: IntArray): Int {
        val n = prices.size
        if (n <= 1) return 0
        // now n >= 2
        val P = Array(n) { IntArray(2) { Int.MIN_VALUE / 2 } }
        P[0][0] = 0
        P[0][1] = -prices[0]
        P[1][0] = max(P[0][0], P[0][1] + prices[1])
        P[1][1] = max(P[0][1], -prices[1])
        for (i in 2 until n) {
            P[i][0] = max(P[i - 1][0], P[i - 1][1] + prices[i])
            P[i][1] = max(P[i - 1][1], P[i - 2][0] - prices[i])
        }
        return P[n - 1][0]
    }
}