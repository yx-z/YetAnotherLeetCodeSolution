package hard

import java.util.*
import kotlin.math.min

class Sol862 {

    fun shortestSubarray(A: IntArray, K: Int): Int {
        val n = A.size
        // P[i] = sum(A[0 until i])
        // sum(A[i..j]) = P[j - 1] - P[i]
        val P = IntArray(n + 1)
        for (i in A.indices) P[i + 1] = P[i] + A[i]
        val d = ArrayDeque<Int>()
        var res = Int.MAX_VALUE
        for (i in P.indices) {
            while (d.isNotEmpty() && P[i] - P[d.first] >= K)
                res = min(res, i - d.pollFirst()!!)
            while (d.isNotEmpty() && P[i] <= P[d.last]) d.pollLast()
            d.addLast(i)
        }
        return if (res == Int.MAX_VALUE) -1 else res
    }
}