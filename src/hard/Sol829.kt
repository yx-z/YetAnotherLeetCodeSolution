package hard

class Sol829 {

    fun consecutiveNumbersSum(N: Int): Int {
        // x + (x + 1) + ... + (x + (m - 1)) = N =>
        // (2x + m - 1)m = 2N
        // 2mx = 2N - (m - 1)m
        // mx = N - (m - 1)m / 2
        var count = 0
        var m = 1
        while (true) {
            // not actual mx, but a candidate
            val mx = N - (m - 1) * m / 2
            if (mx <= 0) break
            if (mx % m == 0) count++
            m++
        }
        return count
    }
}