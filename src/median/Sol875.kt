package median

import kotlin.math.ceil

class Sol875 {

    fun minEatingSpeed(piles: IntArray, H: Int): Int {
        var lo = 1
        var hi = piles.max()!!
        while (lo <= hi) {
            val mid = lo + (hi - lo) / 2
            if (piles.map { ceil((it.toDouble() / mid)).toInt() }.sum() <= H) {
                hi = mid - 1
            } else {
                lo = mid + 1
            }
        }
        return lo
    }
}