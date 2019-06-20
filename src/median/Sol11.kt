package median

import kotlin.math.max
import kotlin.math.min

class Sol11 {

    fun maxArea(height: IntArray): Int {
        var lo = 0
        var hi = height.size - 1
        var area = 0
        while (lo < hi) {
            area = max(area, min(height[lo], height[hi]) * (hi - lo))
            if (height[lo] < height[hi]) {
                lo++
            } else {
                hi--
            }
        }
        return area
    }
}