package median

import kotlin.math.min

class Sol209 {
    fun minSubArrayLen(s: Int, A: IntArray): Int {
        var lo = 0
        var hi = 0
        var sum = 0
        var min = Int.MAX_VALUE
        while (hi < A.size) {
            sum += A[hi]
            hi++

            while (sum >= s) {
                min = min(min, hi - lo)
                sum -= A[lo]
                lo++
            }
        }
        return if (min == Integer.MAX_VALUE) 0 else min
    }
}