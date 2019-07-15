package median

import kotlin.math.max

class Sol1031 {

    fun maxSumTwoNoOverlap(A: IntArray, L: Int, M: Int): Int {
        val n = A.size
        val lS = IntArray(n).apply { this[0] = A[0] }
        for (i in 1 until n) {
            lS[i] = lS[i - 1] + A[i]
        }
        val rS = IntArray(n).apply { this[n - 1] = A[n - 1] }
        for (i in n - 2 downTo 0) {
            rS[i] = rS[i + 1] + A[i]
        }

        val lL = IntArray(n).apply { this[L - 1] = lS[L - 1] }
        for (i in L until n) {
            lL[i] = max(lL[i - 1], lS[i] - lS[i - L])
        }
        val lM = IntArray(n).apply { this[M - 1] = lS[M - 1] }
        for (i in M until n) {
            lM[i] = max(lM[i - 1], lS[i] - lS[i - M])
        }
        val rL = IntArray(n).apply { this[n - L] = rS[n - L] }
        for (i in n - L - 1 downTo 0) {
            rL[i] = max(rL[i + 1], rS[i] - rS[i + L])
        }
        val rM = IntArray(n).apply { this[n - M] = rS[n - M] }
        for (i in n - M - 1 downTo 0) {
            rM[i] = max(rM[i + 1], rS[i] - rS[i + M])
        }
        var res = 0
        for (i in 0 until n - 1) {
            res = max(res, max(lL[i] + rM[i + 1], lM[i] + rL[i + 1]))
        }
        return res
    }
}