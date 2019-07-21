package hard

import kotlin.math.max
import kotlin.math.min

class Sol887 {

    fun superEggDrop(K: Int, N: Int): Int {
        val dp = Array(N + 1) { n ->
            IntArray(K + 1) { k ->
                if (k == 1 || n <= 1) n
                else Int.MAX_VALUE
            }
        }

        for (i in 2..K) {
            for (j in 2..N) {
                for (k in 1..j) {
                    dp[i][j] =
                        min(dp[i][j], 1 + max(dp[i - 1][k - 1], dp[i][j - k]))
                }
            }
        }

        return dp[N][K]
    }

    // leetcode.com/problems/super-egg-drop/discuss/158974/C%2B%2BJavaPython-2D-and-1D-DP-O(KlogN)
    // tbh, i am definitely not smart enough to come up with this
    fun superEggDrop2(K: Int, N: Int): Int {
        val dp = Array(N + 1) { IntArray(K + 1) }
        var m = 0
        while (dp[m][K] < N) {
            m++
            for (k in 1..K) dp[m][k] = 1 + dp[m - 1][k - 1] + dp[m - 1][k]
        }
        return m
    }
}