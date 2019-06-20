package easy

class Sol122 {
    fun maxProfit(prices: IntArray) = (1 until prices.size)
        .map { prices[it] - prices[it - 1] }
        .filter { it > 0 }
        .sum()
}