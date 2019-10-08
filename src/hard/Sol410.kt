package hard

import kotlin.math.max

class Sol410 {

    fun splitArray(ns: IntArray, m: Int): Int {
        val n = ns.size
        // dp[i][j] (where i in 0 until n, j in 1..m) =
        // min possible max subarray sum for ns[0..i] split into j chunks
        // dp[i][1] = sum of ns[0..i]
        val dp = Array(n) { IntArray(m + 1) }.apply { this[0][1] = ns[0] }
        for (i in 1 until n) dp[i][1] = dp[i - 1][1] + ns[i]
        for (j in 2..m) {
            // ns[0 until j] into j chunks => each chunk has length 1
            dp[j - 1][j] = max(dp[j - 2][j - 1], ns[j - 1])
            for (i in j until n) {
                // find a cut k in j - 2 until i : split ns[0..k] into j - 1
                // chunks and ns[k + 1..i] into the j-th chunk : minimizes
                // the max subarray sum and store it in dp[i][j]
                dp[i][j] = (j - 2 until i)
                    .map { k -> max(dp[k][j - 1], dp[i][1] - dp[k][1]) }.min()!!
            }
        }
        // O(mn^2)
        // min possible max subarray sum ns[0 until n] split into m chunks
        return dp[n - 1][m]
    }

    // O(log(ns.sum() - ns.max()!!) * n). This solution performs on Int or Long
    // better than dp does. But for unbounded integers, this can be slower.
    // reference: https://leetcode.com/problems/split-array-largest-sum/discuss/89817/Clear-Explanation%3A-8ms-Binary-Search-Java
    fun splitArrayBinSearch(ns: IntArray, m: Int): Int {
        val n = ns.size
        val max = ns.max()!!
        val sum = ns.sum()
        if (m == 1) return sum
        var lo = max
        var hi = sum
        val valid = { mid: Int ->
            var count = 1
            var total = 0
            var res = true
            for (i in ns) {
                total += i
                if (total > mid) {
                    total = i
                    count++
                    if (count > m) {
                        res = false
                        break
                    }
                }
            }
            res
        }
        while (lo <= hi) {
            val mid = lo + (hi - lo) / 2
            if (valid(mid)) hi = mid - 1
            else lo = mid + 1
        }
        return lo
    }
}