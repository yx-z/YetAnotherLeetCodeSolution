package median

import java.lang.Integer.max

fun main() {
    Sol845().longestMountain2(intArrayOf(2, 1, 4, 7, 3, 2, 5))
}

class Sol845 {

    // dp: O(n) time, O(n) space
    fun longestMountain(A: IntArray): Int {
        val n = A.size
        if (n == 0) return 0
        val inc = IntArray(n)
        for (i in 1 until n)
            inc[i] = if (A[i] > A[i - 1]) inc[i - 1] + 1 else 0
        val dec = IntArray(n)
        for (i in n - 2 downTo 0) dec[i] =
            if (A[i] > A[i + 1]) dec[i + 1] + 1 else 0
        var max = 0
        for (i in 1 until n - 1)
            if (inc[i] > 0 && dec[i] > 0) max = max(max, inc[i] + dec[i] + 1)
        return max
    }


    // 2pointers: O(n) time, O(1) space
    fun longestMountain2(A: IntArray): Int {
        val n = A.size
        if (n <= 2) return 0
        var max = 0
        var lo = 0
        var hi = 1
        while (hi < n) {
            if (hi < n && A[hi] <= A[hi - 1]) {
                lo++
                hi++
                continue
            }
            while (hi < n && A[hi] > A[hi - 1]) hi++
            // hi == n || A[hi] <= A[hi - 1]
            if (hi == n) break
            // A[hi] <= A[hi - 1]
            if (A[hi] == A[hi - 1]) {
                lo = hi
                hi++
            } else {
                // A[hi] < A[hi - 1] => A[hi - 1] is a peak
                while (hi < n && A[hi] < A[hi - 1]) hi++
                // hi == n || A[hi] >= A[hi - 1]
                max = max(max, hi - lo)
                if (hi == n) break
                // A[hi] >= A[hi - 1]
                if (A[hi] == A[hi - 1]) {
                    lo = hi
                    hi++
                } else {
                    // A[hi] > A[hi - 1]
                    lo = hi - 1
                }
            }
        }
        return max
    }
}