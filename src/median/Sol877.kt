package median

import kotlin.math.max

class Sol877 {

    /**
     * i always win if i play first
     */
    fun stoneGameMath(piles: IntArray) = true

    fun stoneGame(piles: IntArray): Boolean {
        val n = piles.size
        // s[i][j]: sum of piles[i..j] where j >= i
        val s = Array(n) { i -> Array(n) { j -> if (i == j) piles[i] else 0 } }
        for (i in 0 until n) {
            for (j in i + 1 until n) {
                s[i][j] = s[i][j - 1] + piles[j]
            }
        }

        // dp[i, j]: given piles[i..j] where j >= i,
        // !!and another opponent who also plays optimally!!,
        // max number of stones i can get if i play first
        val dp = Array(n) { i ->
            Array(n) { j ->
                when {
                    i == j -> piles[i]
                    j == i + 1 || j == i + 2 -> max(piles[i], piles[j])
                    else -> 0 // placeholder
                }
            }
        }
        for (i in n - 4 downTo 0) {
            for (j in i + 3 until n) {
                dp[i][j] = max(
                    piles[i] + s[i + 1][j] - dp[i + 1][j],
                    piles[j] + s[i][j - 1] - dp[i][j - 1]
                )
            }
        }

        return dp[0][n - 1] > piles.sum() / 2
    }
}