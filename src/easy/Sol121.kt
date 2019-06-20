package easy

import kotlin.math.max
import kotlin.math.min

class Sol121 {
    fun maxProfit(prices: IntArray): Int {
        if (prices.isEmpty()) {
            return 0
        }
        var minPrice = prices[0]
        var maxProfit = 0
        for (p in prices) {
            minPrice = min(minPrice, p)
            maxProfit = max(maxProfit, p - minPrice)
        }
        return maxProfit
    }
}