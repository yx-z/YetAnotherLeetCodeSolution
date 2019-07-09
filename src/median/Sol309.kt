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
}