package median

class Sol935 {
    val BASE = 10
    val RANGE = 0 until BASE
    val MOD = Math.pow(10.0, 9.0).toInt() + 7
    val DIRS = arrayOf(
        intArrayOf(4, 6), intArrayOf(6, 8), intArrayOf(7, 9), intArrayOf(4, 8),
        intArrayOf(0, 3, 9), intArrayOf(), intArrayOf(0, 1, 7),
        intArrayOf(2, 6), intArrayOf(1, 3), intArrayOf(2, 4)
    )

    // N in 1..5000
    fun knightDialer(N: Int): Int {
        // dp[i, j] = (# of i digit nums possible if we end at num j) % MOD
        val dp = Array(N + 1) { IntArray(BASE) }
        for (j in RANGE) dp[1][j] = 1
        for (i in 1 until N) for (j in RANGE) for (k in DIRS[j])
            dp[i + 1][k] = (dp[i + 1][k] + dp[i][j]) % MOD
        return dp[N].reduce { acc, i -> (acc + i) % MOD }
    }

    fun spaceOpt(N: Int): Int {
        if (N == 1) return 10
        var pre = IntArray(BASE) { 1 }
        repeat(N - 1) {
            val cur = IntArray(BASE)
            for (j in RANGE) for (k in DIRS[j]) cur[k] = (cur[k] + pre[j]) % MOD
            pre = cur
        }
        return pre.reduce { acc, i -> (acc + i) % MOD }
    }
}