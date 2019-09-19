package hard

import kotlin.math.max

class Sol188 {

    fun maxProfit(k: Int, prices: IntArray): Int {
        val n = prices.size
        if (n == 0) return 0
        if (k * 2 >= n) {
            // reuse code from problem 122
            // this is basically complete as much as transactions we need
            return (1 until n).map { prices[it] - prices[it - 1] }
                .filter { it > 0 }.sum()
        }
        // reuse code from problem 123
        val P = Array(n) {
            Array(k + 1) { IntArray(2) { Int.MIN_VALUE / 2 } }
        }
        P[0][0][0] = 0
        P[0][0][1] = -prices[0]
        for (i in 1 until n) {
            P[i][0][0] = 0
            P[i][0][1] = max(P[i - 1][0][1], -prices[i])
            for (j in 1..k) {
                P[i][j][0] = max(P[i - 1][j][0], P[i - 1][j - 1][1] + prices[i])
                P[i][j][1] = max(P[i - 1][j][1], P[i - 1][j][0] - prices[i])
            }
        }
        return max(0, (0..k).map { P[n - 1][it][0] }.max()!!)
    }
}