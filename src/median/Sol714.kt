package median

import kotlin.math.max

class Sol714 {

    fun maxProfit(ps: IntArray, fee: Int): Int {
        val n = ps.size
        if (n == 0) return 0
        val P = Array(n) { IntArray(2) { Int.MIN_VALUE / 2 } }
        P[0][0] = 0
        P[0][1] = -ps[0] - fee
        for (i in 1 until n) {
            P[i][0] = max(P[i - 1][0], P[i - 1][1] + ps[i])
            P[i][1] = max(P[i - 1][1], P[i - 1][0] - ps[i] - fee)
        }
        return max(0, P[n - 1][0])
    }
}