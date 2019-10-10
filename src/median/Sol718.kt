package median

import kotlin.math.max

class Sol718 {

    fun findLength(A: IntArray, B: IntArray): Int {
        val la = A.size
        val lb = B.size
        var max = 0
        // dp[i][j] = max len of repeated subarray that end in A[i] and B[j]
        // for all i in A.indices, j in B.indices
        val dp = Array(la) { IntArray(lb) }
        for (i in A.indices) {
            if (A[i] == B[0]) {
                dp[i][0] = 1
                max = 1
            }
        }
        for (j in B.indices) {
            if (B[j] == A[0]) {
                dp[0][j] = 1
                max = 1
            }
        }
        for (i in 1 until la) {
            for (j in 1 until lb) {
                dp[i][j] = if (A[i] == B[j]) 1 + dp[i - 1][j - 1] else 0
                max = max(max, dp[i][j])
            }
        }
        return max
    }
}