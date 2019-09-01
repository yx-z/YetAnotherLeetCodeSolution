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
        var lo = 0
        var max = 0
        while (lo < n) {
            var pk = lo
            while (pk + 1 < n && A[pk + 1] > A[pk]) pk++
            if (pk + 1 == n) break
            if (A[pk + 1] == A[pk] || lo == pk) lo = pk + 1
            else {
                var hi = pk
                while (hi + 1 < n && A[hi + 1] < A[hi]) hi++
                max = max(max, hi - lo + 1)
                if (hi + 1 == n) break
                lo = if (A[hi + 1] == A[hi]) hi + 1 else hi
            }
        }
        return max
    }
}