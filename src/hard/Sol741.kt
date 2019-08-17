package hard

import kotlin.math.max

class Sol741 {

    fun cherryPickup(G: Array<IntArray>): Int {
        val N = G.size
        val dp = Array(N + 1) { Array(N + 1) { IntArray(N + 1) { -1 } } }
        dp[1][1][1] = G[0][0]
        for (x1 in 1..N) {
            for (y1 in 1..N) {
                for (x2 in 1..N) {
                    val y2 = x1 + y1 - x2
                    if (dp[x1][y1][x2] > 0 ||
                        y2 !in 1..N ||
                        G[x1 - 1][y1 - 1] == -1 ||
                        G[x2 - 1][y2 - 1] == -1
                    ) continue
                    val cur = max(
                        max(dp[x1 - 1][y1][x2], dp[x1 - 1][y1][x2 - 1]),
                        max(dp[x1][y1 - 1][x2], dp[x1][y1 - 1][x2 - 1])
                    )
                    if (cur < 0) continue
                    dp[x1][y1][x2] = cur + G[x1 - 1][y1 - 1] + if (x1 == x2) 0
                    else G[x2 - 1][y2 - 1]
                }
            }
        }
        return max(0, dp[N][N][N])
    }
}